# Gridpal System Information Binding

Retrieves information about the system.


## Dependencies

- `org.json`



## REST API

BASE = `system`

### Endpoints

- `/resources`
- `interfaces`
- `/buses`
- `/internet/status`
- `/serials`

_Note: The endpoints are relative to `$BASE`._

## Workflow

```
Web Client <--> REST API <--> SystemUtility <--> Agent <--> System
```