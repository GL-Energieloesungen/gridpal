from flask import Flask, request, jsonify
import json
import os
import requests
import sys
import time

app = Flask(__name__)


def readJson():
    return open('modbus.json', 'r').read()


@app.route('/devices', methods=['GET'])
def somename():
    param = request.args.get("param")
    if param is None:
        return readJson(), 200
    else:
        jsonData = json.loads(readJson())
        for i in jsonData["devices"]:
            if param == i["name"]:
                return jsonify(i), 200


@app.route('/devices/<device_name>', methods=['POST'])
def somename2(device_name):
    data = request.get_json()

    jsonData = json.loads(readJson())

    for jsonObj in jsonData["devices"]:
      if device_name == jsonObj["name"]:
        jsonObj.update(data)
        return jsonify(jsonObj), 200




if __name__ == '__main__':
    app.run(host="0.0.0.0")
