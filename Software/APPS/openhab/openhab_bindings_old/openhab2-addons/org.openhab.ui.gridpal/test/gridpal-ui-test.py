# Name      : Gridpal UI Test
# Author    : Md. Farhabi Helal Ayon
# Date      : 22-10-2018

# Objective : Develop tests for Gridpal UI


import unittest
import re
import requests

HOST_URI = "http://localhost:8080/rest"
UI_HOME = 'gridpal-ui'
QUERY_PARAMS = ''



class GridpalUITest(unittest.TestCase):

    def test_loadHome(self):
        contentType = 'text/html'
        url = '{0}/{1}'.format(HOST_URI, UI_HOME)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadHtml(self):
        filename = 'index.html'
        contentType = 'text/html'
        url = '{0}/{1}/{2}'.format(HOST_URI, UI_HOME, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadCss(self):
        filename = 'style.css'
        contentType = 'text/css'
        url = '{0}/{1}/{2}'.format(HOST_URI, UI_HOME, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadJs(self):
        filename = 'script.js'
        contentType = 'application/javascript'
        url = '{0}/{1}/{2}'.format(HOST_URI, UI_HOME, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadTxt(self):
        filename = 'text.txt'
        contentType = 'text/plain'
        url = '{0}/{1}/{2}'.format(HOST_URI, UI_HOME, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadUnknownTextFile(self):
        filename = 'style.less'
        contentType = 'text/plain'
        url = '{0}/{1}/{2}'.format(HOST_URI, UI_HOME, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadBinary(self):
        filename = 'favicon.ico'
        acceptedContentTypes = [
            'image/',
            'audio/',
            'video/',
            'application/octet-stream',
            'application/pdf'
        ]
        url = '{0}/{1}/{2}'.format(HOST_URI, UI_HOME, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertTrue(bool(re.search('|'.join(f'({x})' for x in acceptedContentTypes), r.headers['content-type'])))

    def test_loadHtmlFromNestedDirectory(self):
        dir = 'html'
        filename = 'page.html'
        contentType = 'text/html'
        url = '{0}/{1}/{2}/{3}'.format(HOST_URI, UI_HOME, dir, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadCssFromNestedDirectory(self):
        dir = 'css'
        filename = 'style.css'
        contentType = 'text/css'
        url = '{0}/{1}/{2}/{3}'.format(HOST_URI, UI_HOME, dir, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadJsFromNestedDirectory(self):
        dir = 'js'
        filename = 'script.js'
        contentType = 'application/javascript'
        url = '{0}/{1}/{2}/{3}'.format(HOST_URI, UI_HOME, dir, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadTxtFromNestedDirectory(self):
        dir = 'text'
        filename = 'text.txt'
        contentType = 'text/plain'
        url = '{0}/{1}/{2}/{3}'.format(HOST_URI, UI_HOME, dir, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadUnknownTextFileFromNestedDirectory(self):
        dir = 'less'
        filename = 'style.less'
        contentType = 'text/plain'
        url = '{0}/{1}/{2}/{3}'.format(HOST_URI, UI_HOME, dir, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.headers['content-type'], contentType)

    def test_loadBinaryFromNestedDirectory(self):
        dir = 'icon'
        filename = 'favicon.ico'
        acceptedContentTypes = [
            'image/',
            'audio/',
            'video/',
            'application/octet-stream',
            'application/pdf'
        ]
        url = '{0}/{1}/{2}/{3}'.format(HOST_URI, UI_HOME, dir, filename)
        
        r = requests.get(url)

        self.assertEqual(r.status_code, 200)
        self.assertTrue(bool(re.search('|'.join(f'({x})' for x in acceptedContentTypes), r.headers['content-type'])))

    def test_loadInvalidOrNonExistentFile(self):
        filename = 'iiiiiiiiiiiiiiiiiiiiiinvalid.html'
        url = '{0}/{1}/{2}'.format(HOST_URI, UI_HOME, filename)
        
        r = requests.get(url)

        self.assertNotEqual(r.status_code, 200)







if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(GridpalUITest)
    unittest.TextTestRunner(verbosity=2).run(suite)
