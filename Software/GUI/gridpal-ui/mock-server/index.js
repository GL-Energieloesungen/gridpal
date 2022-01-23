const express = require('express');
const cors = require('cors');
const app = express();
const port = 8081;

//app.use(cors);


const origin = "/rest/wireless";

app.get("/",  cors(), (req, res, next) => {
    json = {
        "status": "success"
    }
    res.send(json);
});



app.get("/rest",  cors(), (req, res, next) => {
    json = {
        "status": "success"
    }
    res.send(json);
});

app.get(origin, (req, res, next) => {
    json = {
        "status": "success"
    }
    res.send(json);
});


app.get(origin + "/list",  cors(), (req, res, next) => {
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
        },
        {
            "ssid": "Skylark",
            "signal": "56",
            "security": "WPA2"
        },
        {
            "ssid": "Dark Knight",
            "signal": "25",
            "security": "WPA2"
        }
    ]
    res.send(json);
});


app.get(origin + "/status",  cors(), (req, res, next) => {
    json = {
        "connected": "true",
        "connection": {
            "ssid": "APL",
            "device": "wlo1"
        }
    }
    res.send(json);
});


app.post(origin + "/connect",  cors(), (req, res, next) => {
    json = {
        "status": "failed",
        "reason": "wrong-password"
    }
    res.send(json);
});


app.get(origin + "/disconnect",  cors(), (req, res, next) => {
    json = {
        "status": "success"
    }
    res.send(json);
});


app.listen(port, () => {
    console.log("Server started listening on port " + port);
})
