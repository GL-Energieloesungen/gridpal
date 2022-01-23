# Gridpal Device Manager Binding

Enables support for wide range of devices into the platfrom. Generates and modifies openHAB _configuration_ and _item_ files using _REST API_.

## Dependencies

- `org.json`
- `mysql-connector-java`

## Supoorted Protocols

- _Modbus_
- _Mbus_
- _KNX_
- _WMBus_

## REST API

### Device Resources

BASE = `/devices`

### Endpoints

- `/`
- `/$device_name`
- `/add`
- `/update`
- `/remove/$device_name`
- `/remove/all`
- `/export/items/$item_name`


_Note: The endpoints are relative to `$BASE`._

### Config Resources

BASE = `/config`

### Endpoints

- `/delete/$binding_symbolic_name`

_Note: The endpoints are relative to `$BASE`._

### Delete

Deletes bundle's configuration from OSGi runtime.

_Example_ : To delete configuration of a binding with symbolic name `org.openhab.modbus`:

```http
~/config/delete/org.openhab.modbus
```

_Note : Postman export of the REST API is available in the `/developer/openHAB.json`._

## Design

### Control Flow

```
Web Client <--> REST API  <--> Generic Manager <--> Specefic Manager(s) <--> System
```

### Managers

| Name     | Responsibility                               |
| -------- | -------------------------------------------- |
| _Device_ | Manages devices                              |
| _Config_ | Delegates config changes to the OSGi runtime |
| _Export_ | Handles request of data export               |

### Device

| File             | Responsibility                                             |
| ---------------- | ---------------------------------------------------------- |
| _Manager_        | Supervises device                                          |
| _Config Manager_ | Manages device configuration                               |
| _Config Parser_  | Translates configuration file                              |
| _Item_           | Model class representing item of the device                |
| _Items Parser_   | Translates item file                                       |
| _Config Key_     | Contains constants of the configuration                    |
| _Constants_      | Contains constants that are not part of configuration file |

### Exception & Error Handling

Exceptions are cascaded back to the caller in most cases. In the event of exceptions, _HTTP Status Code 500_ is sent back to the client.