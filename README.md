# Ratemarkt Sample Client Application

## Usage

You first need to install [ratemarkt-sdk](https://github.com/ratemarkt/ratemarkt-sdk)

```
$ git clone https://github.com/ratemarkt/ratemarkt-sdk.git
$ cd ratemarkt-sdk
$ mvn install
```
Once completed, clone this sample client

```
$ git clone https://github.com/ratemarkt/ratemarkt-sample-client.git
$ cd ratemarkt-sample-client
```

Now you're ready to run the app using your own Ratemarkt API key.

```
$ mvn jetty:run\
  -Dcom.ratemarkt.sampleclient.baseUrl="RATEMARKT_API_URL"\
  -Dcom.ratemarkt.sampleclient.apiKey="YOUR_API_KEY"
```

Screenshots

![Step 1](https://raw.githubusercontent.com/ratemarkt/ratemarkt-sample-client/master/media/step_1.jpg)
![Step 2](https://raw.githubusercontent.com/ratemarkt/ratemarkt-sample-client/master/media/step_2.jpg)
![Step 3](https://raw.githubusercontent.com/ratemarkt/ratemarkt-sample-client/master/media/step_3.jpg)
![Step 4](https://raw.githubusercontent.com/ratemarkt/ratemarkt-sample-client/master/media/step_4.jpg)
