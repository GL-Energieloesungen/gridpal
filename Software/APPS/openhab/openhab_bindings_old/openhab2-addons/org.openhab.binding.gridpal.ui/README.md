# Gridpal UI Binding

Loads Gridpal Front-End from anywhere in the system using openHAB.

## Config
 
 _FILENAME_ = `gridpalui.cfg`

### Parameters

- `resources_dir`
- `route_endpoints`
 
### Resources Directory

- key = `resources_dir`
- Value must be enclosed in `"`.
- Value must not contain `/` at the end.
- default = `"/usr/share/openhab2/gridpalui"`

### Route Endpoints

- key = `route_endpoints`
- Value must be enclosed in `"`.
- Value must be in _CSV_ format.


### Example

```ini
# gridpalui.cfg
resources_dir = "/home/gridpal/ui"
route_endpoints = "home, internet, devicemanager, live-data"
````
