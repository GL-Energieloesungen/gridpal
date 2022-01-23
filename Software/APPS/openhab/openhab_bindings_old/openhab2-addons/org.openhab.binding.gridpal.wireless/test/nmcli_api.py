import subprocess as sp
from time import sleep

OPTS = '-c no -t'


# enables networking
def turnOnNetworking():
    cmd = f'nmcli {OPTS} networking on'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()
    
    sleep(3)
    
    cmd = f'nmcli {OPTS} networking connectivity'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()
    
    return result.strip() is not 'none'

# disables networking
def turnOffNetworking():
    cmd = f'nmcli {OPTS} networking off'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()
    
    sleep(3)
    
    cmd = f'nmcli {OPTS} networking connectivity'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()
    
    return result.strip() is 'none'


# gets system wireless status
def getWifiStatus():
    cmd = f'nmcli {OPTS} -f wifi general status'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    return result


def turnOnWifi():
    cmd = 'nmcli radio wifi on'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()


def turnOffWifi():
    cmd = 'nmcli radio wifi off'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()


# gets list of network interfaces
def getInterfaces():
    kDevice = 'device'
    kType = 'type'
    kState = 'state'
    kConnection = 'connection'

    cmd = f'nmcli {OPTS} -f {kDevice},{kType},{kState},{kConnection} device status'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    lines = [ x for x in result.split('\n') if x is not '' ]

    interfaces = []
    for line in lines:
        values = line.split(':')
        interfaces.append(
            {
                kDevice: values[0],
                kType: values[1],
                kState: values[2],
                kConnection: values[3],
            }
        )

    return interfaces


# gets list of available connections
def getWifiConnections():
    cmd = 'nmcli -c no -f ssid,signal,security -t device wifi list'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    return [ x for x in result.split('\n') if x is not '' ]


# connects to a ssid
def wifiConnect(ssid, password):
    connections = getConnections()

    # print(f"Connections : {connections}")

    for conn in connections:
        if conn['name'] == ssid and conn['type'] == '802-11-wireless':
            cmd = f'nmcli {OPTS} connection delete "{ssid}"'
            result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()
            break
    
    sleep(2)

    cmd = f'nmcli {OPTS} device wifi connect "{ssid}" password "{password}"'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    return not result.__contains__('Error')

def isConnected(ssid):
    cmd = f'nmcli {OPTS} -f name connection show --active | grep "{ssid}"'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()
    return result is not ""

def getConnections():
    keys = [ 'name', 'type', 'device', 'uuid' ]

    cmd = f'nmcli {OPTS} -f {",".join(keys)} connection show'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    lines = [ x for x in result.split('\n') if x is not '' ]

    connections = []
    for line in lines:
        values = line.split(':')
        
        if len(values) != len(keys):
            continue

        conn = {}
        for i in range(len(keys)):
            conn[keys[i]] = values[i] 
        
        connections.append(conn)

    return connections


def getActiveConnections():
    kName = 'name'
    kType = 'type'
    kDevice = 'device'

    cmd = f'nmcli {OPTS} -f {kName},{kType},{kDevice} connection show --active'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    lines = [ x for x in result.split('\n') if x is not '' ]

    connections = []
    for line in lines:
        values = line.split(':')
        connections.append(
            {
                kName: values[0],
                kType: values[1],
                kDevice: values[2]
            }
        )

    return connections


def connect(connection):
    cmd = f'nmcli {OPTS} connection up "{connection}"'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    return result.__contains__(f'\'{connection}\' successfully activated')

def disconnect(connection):
    cmd = f'nmcli {OPTS} connection down "{connection}"'
    result = str(sp.run(cmd, stdout=sp.PIPE, shell=True).stdout, encoding="UTF-8").strip()

    return result.__contains__(f"'{connection}' successfully deactivated")




def test():

    # print('[ turnOffNetworking ]')
    # print(turnOffNetworking())
    
    # print('[ turnOnNetworking ]')
    # print(turnOnNetworking())

    # print('[ getInterfaces ]')
    # print(getInterfaces())

    # print('[ getWifiStatus ]')
    # print(getWifiStatus())

    wifiConnect('APL', 'Ash01eiJanina')




if __name__ == '__main__':
    test()