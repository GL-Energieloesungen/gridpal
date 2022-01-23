# DEPLOY GUIDE

## Running Application

* system must contain `nodejs=v10.x`, `npm=v5.8`
* anguler global package requirement `@angular/cli`

## Steps

### Install dependencies:

```bash
# java ppa
sudo add-apt-repository ppa:webupd8team/java`

# nodejs 
curl -sL https://deb.nodesource.com/setup_10.x -o nodesource_setup.sh

# openhab2 
echo 'deb https://dl.bintray.com/openhab/apt-repo2 stable main' | sudo tee /etc/apt/sources.list.d/openhab2.list
sudo apt update
sudo apt install -y oracle-java8-installer nodejs openhab2 openhab2-addons openhab2-addons-legacy git nginx-light

# add any openhab binding jar files to
sudo cp org.openhab.binding.shellapi_2.3.0.201804091001.jar /usr/share/openhab2/addons

# start openhab2
sudo systemctl start openhab2.service
```

## OpenHAB needs `Access-Control-Allow-Origin` to be turned on

```bash
echo 'org.eclipse.smarthome.cors:enable=true' >> /etc/openhab2/services/runtime.cfg
```

## Build UI

### Global Package Requirement:

```bash
sudo npm i -g @angular/cli
```

* Development Build

    ```bash 
    npm i 
    ng serve --open
    ```

* Production Build

    ```bash
    npm i
    ng build --aot --prod
    ```

## Production Deployment with nginx

Set server `root` to the `gridpalui/dist` directory in `/etc/nginx/sites-available/default`

Example Configuration File

```perl
server {
	listen 80 default_server;
	root /path/to/gridpal.ui/dist;
	index index.html;
	server_name _;
	location / {
		try_files $uri $uri/ /index.html;
	}
}
```

* Reload nginx with `sudo nginx -s reload`

