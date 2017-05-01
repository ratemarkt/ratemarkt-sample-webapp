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

