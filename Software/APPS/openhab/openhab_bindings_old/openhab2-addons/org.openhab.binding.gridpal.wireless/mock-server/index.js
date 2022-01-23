const express = require('express');
const app = express();
const port = 8080;


const origin = "/rest/wireless";

app.get("/", (req, res) => {
    json = {
        "status": "success"
    }
    res.send(json);
});



app.get("/rest", (req, res) => {
    json = {
        "status": "success"
    }
    res.send(json);
});

app.get(origin, (req, res) => {
    json = {
        "status": "success"
    }
    res.send(json);
});


app.get(origin + "/list", (req, res) => {
    json = [
        {
            "ssid": "APL",
            "signal": "100",
            "security": "WPA1 WPA2"
        },
        {
            "ssid": "APL-EXT",
            "signal": "95",
            "security": "WPA2"
        }
    ]
    res.send(json);
});


app.get(origin + "/status", (req, res) => {
    json = {
        "connected": "true",
        "connection": {
            "name": "APL",
            "device": "wlo1"
        }
    }
    res.send(json);
});


app.post(origin + "/connect", (req, res) => {
    json = {
        "status": "failed",
        "reason": "wrong-password"
    }
    res.send(json);
});


app.get(origin + "/disconnect", (req, res) => {
    json = {
        "status": "success"
    }
    res.send(json);
});


app.listen(port, () => {
    console.log("Server started listening on port " + port);
})
