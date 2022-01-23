# Gridpal Wireless Binding Test Cases

## Cases

### Format

- Validate response format for List
- Validate response format for Status
- Validate response format for Connect
- Validate response format for Disconnect

### List

- Validate Array is empty when WiFi is disabled



### Status

- Validate when after connect
- Validate after disconnect
- Validate empty when WiFi is off
- Validate only one active WiFi connection

### Connect

- Try when WiFi is disabled
- Try with wrong password
- Try to invalid SSID
- Try to blank SSID
- Try with blank password
- Try with password non-pritable characters
- Try while WiFi device is booting
- Validate device when multiple 2.4 Hz devices are available


### Disconnect

- Try when no connection is active
- Try when only active connection is wired
- Try when only active connection is wifi
- Try when both wired and wifi connections are available but only connected to wired
- Try when both wired and wifi connections are available but only connected to wifi
- Try when both wired and wifi connections are active