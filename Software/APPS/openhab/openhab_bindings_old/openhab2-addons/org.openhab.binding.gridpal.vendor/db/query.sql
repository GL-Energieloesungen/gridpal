-- Get Interfaces
SELECT id, name FROM interface
rest/vendor/interface

-- Get Manufacturers
SELECT manufacturer.id, manufacturer.name FROM manufacturer, device WHERE device.id_interface = 1 GROUP BY manufacturer.id
rest/vendor/manufacturer?id_interface=1

-- Get Device Models
SELECT device.id, device.device_model FROM device WHERE device.id_manufacturer = 1 AND  device.id_interface = 1
rest/vendor/device?id_interface=1&id_manufacturer=1

-- Get Device Configs
SELECT config FROM device WHERE device.id = 1
rest/vendor/device?id=1

-- Get Register Map
select valuetype, start, length, multiply, unit, description, type from registermap where id_device=1
rest/vendor/registermap?id_device=1