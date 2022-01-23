# Name      : Gridpal Wireless Binding Test
# Author    : Md. Farhabi Helal Ayon
# Date      : 07-11-2018

# Objective : Develop tests for Gridpal Wireless Binding


import unittest
import re
import requests
from time import sleep
import json as JSON

import nmcli_api as nmcli

HOST_URI = "http://localhost:8080/rest"
ROOT_PATH = '/wireless'
QUERY_PARAMS = ''



CONFIG = None
CONFIG_FILENAME = 'gridpal-wireless-test-config.json'

with open(CONFIG_FILENAME) as file:
    CONFIG = JSON.load(file)


class GridpalWirelessTest(unittest.TestCase):

###############################################################################
#                                   FORMAT
###############################################################################
    def test_listResponseFormat(self):
        ENDPOINT = '/list'
        
        nmcli.turnOnWifi()

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'

        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertTrue(r.json())


    def test_statusResponseFormat(self):
        ENDPOINT = '/status'
        
        nmcli.turnOnWifi()

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'

        r = requests.get(url)

        json = r.json()

        self.assertEqual(r.status_code, 200)

        self.assertTrue(json['connected'])

        if json['connected'] == 'true':
            self.assertTrue(json['connection'])
            self.assertTrue(json['connection']['name'])
            self.assertTrue(json['connection']['device'])

    
    def test_connectResponseFormat(self):
        ENDPOINT = '/connect'
        
        nmcli.turnOnWifi()

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'

        headers = {
            'content-type': 'application/json'
        }

        data = {
            'ssid': 'APL',
            'password': 'Ash01eiJanina'
        }

        r = requests.post(url, data=JSON.dumps(data), headers=headers)
        json = r.json()

        self.assertEqual(r.status_code, 200)

        self.assertTrue(json['status'])

        if json['status'] == 'failed':
            self.assertTrue(json['reason'])

            if json['reason'] == 'exception':
                self.assertTrue(json['exception']['name'])
                self.assertTrue(json['exception']['message'])


    def test_disconnectResponseFormat(self):
        ENDPOINT = '/disconnect'
        
        nmcli.turnOnWifi()

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'

        r = requests.get(url)

        json = r.json()

        self.assertEqual(r.status_code, 200)

        self.assertTrue(json['status'])

        if json['status'] == 'failed':
            self.assertTrue(json['reason'])

            if json['reason'] == 'exception':
                self.assertTrue(json['exception']['name'])
                self.assertTrue(json['exception']['message'])

# ###############################################################################
# #                                   LIST
# ###############################################################################
    def test_emptyListResponseWhenWifiDisabled(self):
        ENDPOINT = '/list'
        
        nmcli.turnOffWifi()

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'

        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json, [])



###############################################################################
#                                   STATUS
###############################################################################
    def test_connectedAfterConnect(self):
        ENDPOINT = '/status'
        SSID = CONFIG['connection']['wifi'][0]['ssid']
        PASSWORD = CONFIG['connection']['wifi'][0]['password']

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        nmcli.wifiConnect(SSID, PASSWORD)
        
        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['connected'], 'true')
        self.assertEqual(json['connection']['name'], SSID)
        self.assertTrue(json['connection']['device'])

    def test_disconnectedAfterDisconnect(self):
        ENDPOINT = '/status'
        SSID = CONFIG['connection']['wifi'][0]['ssid']
        PASSWORD = CONFIG['connection']['wifi'][0]['password']

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()
        nmcli.wifiConnect(SSID, PASSWORD)

        sleep(3)

        nmcli.disconnect(SSID)

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['connected'], 'false')
        self.assertFalse(nmcli.isConnected(SSID))


    def test_disconnectedWhenWifiDisabled(self):
        ENDPOINT = '/status'

        nmcli.turnOnNetworking()
        nmcli.turnOffWifi()

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['connected'], 'false')

    # def test_onlySingleActiveConnection(self):
    #     ENDPOINT = '/status'
    #     SSID = CONFIG['connection']['wifi'][0]['ssid']
    #     PASSWORD = CONFIG['connection']['wifi'][0]['password']

    #     nmcli.turnOnNetworking()
    #     nmcli.turnOnWifi()

    #     nmcli.wifiConnect(SSID, PASSWORD)

    #     url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
    #     r = requests.get(url)
    #     json = r.json()

    #     self.assertEqual(r.status_code, 200)
    #     self.assertEqual(json['connected'], 'true')
    #     self.assertEqual(json['connection']['name'], SSID)
    #     self.assertTrue(json['connection']['device'])
        
    #     activeConnections = nmcli.getActiveConnections()
    #     self.assertEqual(len(activeConnections), 1) 


###############################################################################
#                                   CONNECT
###############################################################################
    def test_connectFailureWhenWifiDisabled(self):
        ENDPOINT = '/connect'
        SSID = CONFIG['connection']['wifi'][0]['ssid']
        PASSWORD = CONFIG['connection']['wifi'][0]['password']

        nmcli.turnOnNetworking()
        nmcli.turnOffWifi()

        sleep(1)

        headers = {
            'content-type': 'application/json'
        }
        data = {
            'ssid': SSID,
            'password': PASSWORD
        }

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.post(url, data=JSON.dumps(data), headers=headers)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'failed')
        self.assertEqual(json['reason'], 'exception')
        self.assertTrue(json['exception'])
        self.assertTrue(json['exception']['message'].lower() == 'wifi is disabled')


    def test_connectFailureWhenWrongPassword(self):
        ENDPOINT = '/connect'
        SSID = CONFIG['connection']['wifi'][0]['ssid']
        PASSWORD = 'WrongPassword'

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        sleep(1)

        headers = {
            'content-type': 'application/json'
        }
        data = {
            'ssid': SSID,
            'password': PASSWORD
        }

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.post(url, data=JSON.dumps(data), headers=headers)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'failed')
        self.assertEqual(json['reason'], 'exception')
        self.assertTrue(json['exception'])
        self.assertTrue(json['exception']['message'].lower() == 'invalid credentials')



    def test_connectFailureWhenWrongSsid(self):
        ENDPOINT = '/connect'
        SSID = 'AAAAAAAAAAAAPL'
        PASSWORD = CONFIG['connection']['wifi'][0]['password']

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        sleep(1)

        headers = {
            'content-type': 'application/json'
        }
        data = {
            'ssid': SSID,
            'password': PASSWORD
        }

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.post(url, data=JSON.dumps(data), headers=headers)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'failed')
        self.assertEqual(json['reason'], 'exception')
        self.assertTrue(json['exception']['message'].lower() == 'invalid ssid')


    def test_connectFailureWhenBlankSsid(self):
        ENDPOINT = '/connect'
        SSID = ''
        PASSWORD = CONFIG['connection']['wifi'][0]['password']

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        sleep(1)

        headers = {
            'content-type': 'application/json'
        }
        data = {
            'ssid': SSID,
            'password': PASSWORD
        }

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.post(url, data=JSON.dumps(data), headers=headers)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'failed')
        self.assertEqual(json['reason'], 'exception')
        self.assertTrue(json['exception']['message'].lower() == 'ssid and password must be non-empty.')


    def test_connectFailureWhenBlankPassword(self):
        ENDPOINT = '/connect'
        SSID = CONFIG['connection']['wifi'][0]['ssid']
        PASSWORD = ''

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        sleep(1)

        headers = {
            'content-type': 'application/json'
        }
        data = {
            'ssid': SSID,
            'password': PASSWORD
        }

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.post(url, data=JSON.dumps(data), headers=headers)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'failed')
        self.assertEqual(json['reason'], 'exception')
        self.assertTrue(json['exception']['message'].lower() == 'ssid and password must be non-empty.')


    # def test_connectFailureWhenNonPrintableCharactersExist():
    #     ENDPOINT = '/connect'
    #     IFACE_WIFI = 'wlo1'
    #     SSID = 'APL'
    #     PASSWORD = 'Ash01eiJanina'

    #     nmcli.turnOnNetworking()
    #     nmcli.turnOnWifi()

    #     data = {
    #         'ssid': SSID,
    #         'password': PASSWORD
    #     }

    #     url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
    #     r = requests.post(url, data=data)
    #     json = r.json()

    #     self.assertEqual(r.status_code, 200)
    #     self.assertEqual(json['status'], 'success')


    # def test_connectFailureWhenWifiDeviceBooting(self):
    #     ENDPOINT = '/connect'
    #     IFACE = 'wlo1'
    #     SSID = 'APL'
    #     PASSWORD = 'Ash01eiJanina'

    #     nmcli.turnOnNetworking()
    #     nmcli.turnOnWifi()

    #     headers = {
    #         'content-type': 'application/json'
    #     }
    #     data = {
    #         'ssid': SSID,
    #         'password': PASSWORD
    #     }

    #     url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
    #     r = requests.post(url, data=JSON.dumps(data), headers=headers)
    #     json = r.json()

    #     self.assertEqual(r.status_code, 200)
    #     self.assertEqual(json['status'], 'failed')
    #     self.assertEqual(json['reason'], 'exception')
    #     self.assertTrue(json['exception']['message'].lower() == 'ssid and password must be non-empty.')


    # def test_connectedDeviceWhenMultipleDevicesAvailable(self):
    #     ENDPOINT = '/connect'
    #     IFACE_WIFI = 'wlo1'
    #     SSID = 'APL'
    #     PASSWORD = 'Ash01eiJanina'

    #     nmcli.turnOnNetworking()
    #     nmcli.turnOnWifi()

    #     data = {
    #         'ssid': SSID,
    #         'password': PASSWORD
    #     }

    #     url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
    #     r = requests.post(url, data=data)
    #     json = r.json()

    #     self.assertEqual(r.status_code, 200)
    #     self.assertEqual(json['status'], 'success')




###############################################################################
#                                   DISCONNECT
###############################################################################
    def test_disconnectFailureWhenWifiDisabled(self):
        ENDPOINT = '/disconnect'

        nmcli.turnOnNetworking()
        nmcli.turnOffWifi()

        sleep(1)

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'failed')
        self.assertEqual(json['reason'], 'exception')
        self.assertTrue(json['exception']['message'].lower() == 'not connected')

    def test_disconnectFailureWhenOnlyWiredConnectionActive(self):
        ENDPOINT = '/disconnect'
        WIRED_CONNECTION_NAME = CONFIG['connection']['wired'][0]['name']

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        nmcli.connect(WIRED_CONNECTION_NAME)

        sleep(1)

        activeConnectionsOld = nmcli.getActiveConnections()
        activeConnections = []
        for conn in activeConnectionsOld:
            if conn['type'] == '802-11-wireless':
                nmcli.disconnect(conn['name'])
            else:
                activeConnections.append(conn)

        self.assertEqual(len(activeConnections), 1)
        self.assertEqual(activeConnections[0]['type'], '802-3-ethernet')

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'failed')
        self.assertEqual(json['reason'], 'exception')
        self.assertTrue(json['exception']['message'].lower() == 'not connected')


    def test_disconnectSuccessWhenOnlyWifiConnectionActive(self):
        ENDPOINT = '/disconnect'
        # wifi connection
        SSID = CONFIG['connection']['wifi'][0]['ssid']
        PASSWORD = CONFIG['connection']['wifi'][0]['password']

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        sleep(1)

        nmcli.wifiConnect(SSID, PASSWORD)

        sleep(1)

        activeConnectionsOld = nmcli.getActiveConnections()
        activeConnections = []
        for conn in activeConnectionsOld:
            if conn['type'] == '802-11-wireless' and conn['name'] == SSID:
                activeConnections.append(conn)
            else:
                nmcli.disconnect(conn['name'])

        self.assertEqual(len(activeConnections), 1)
        self.assertEqual(activeConnections[0]['type'], '802-11-wireless')

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'success')


    def test_disconnectSuccessWhenBothWiredAndWifiConnectionActive(self):
        ENDPOINT = '/disconnect'
        # wired connection
        WIRED_CONNECTION_NAME = CONFIG['connection']['wired'][0]['name']
        # wifi connection
        SSID = CONFIG['connection']['wifi'][0]['ssid']
        PASSWORD = CONFIG['connection']['wifi'][0]['password']

        nmcli.turnOnNetworking()
        nmcli.turnOnWifi()

        nmcli.connect(WIRED_CONNECTION_NAME)

        sleep(1)

        nmcli.wifiConnect(SSID, PASSWORD)
        sleep(1)

        activeConnectionsOld = nmcli.getActiveConnections()
        activeConnections = []
        for conn in activeConnectionsOld:
            if conn['type'] == '802-11-wireless' and conn['name'] == SSID:
                activeConnections.append(conn)

        self.assertEqual(len(activeConnections), 1)
        self.assertEqual(activeConnections[0]['type'], '802-11-wireless')

        url = f'{HOST_URI}{ROOT_PATH}{ENDPOINT}'
        
        r = requests.get(url)
        json = r.json()

        self.assertEqual(r.status_code, 200)
        self.assertEqual(json['status'], 'success')



if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(GridpalWirelessTest)
    unittest.TextTestRunner(verbosity=2).run(suite)
