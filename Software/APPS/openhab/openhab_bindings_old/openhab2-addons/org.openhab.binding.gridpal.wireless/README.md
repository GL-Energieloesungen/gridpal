# Gridpal Wireless Binding


## Path
`/wireless`

## Endpoints

- `/list`
- `/status`
- `/connect`
- `/disconnect`
- `/delete`


## JSON Formats

### List

_Response_
```json
[
    {
        "ssid": "string",
        "signal": "string",
        "security": "string",
        "saved": "true|false"
    }
]
```

### Status

_Response_
```json
{
    "connected": "boolean",
    "connection":
    {
        "name": "string",
        "device": "string"
    }
}
```

### Connect

_POST Request_
```json
{
    "ssid": "string",
    "password": "string"
}
```
_Response_
```json
{
    "status": "string",
    "reason": "string"
}
```

### Disconnect

_Response_
```json
{
    "status": "string"
}
```

### Delete

_POST Request_
```json
{
    "ssid": "string"
}
```
_Response_
```json
{
    "status": "string",
    "reason": "string"
}
```