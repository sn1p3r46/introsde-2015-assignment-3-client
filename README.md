
# Introsde 2015 Assignment 3 (client part)

**Student:** Andrea Galloni

**Organization:** [UniTN](http://www.unitn.it/en)

**Course** [Introduction to Service Design and Engineering](https://sites.google.com/site/introsdeunitn/)

<p align="center">
  <img src="http://www.cliparthut.com/clip-arts/512/cartoon-soap-bar-512266.png" width="200">
</p>


This project implements a client to connect and invoke some methods on a server using a SOAP web service here the repo: ([introsde-assignment-3](https://github.com/sn1p3r46/introsde-2015-assignment-3)).

This client is developed to perform the requests on the server project. All the client server interactions make use of the [SOAP](https://en.wikipedia.org/wiki/SOAP)  ([w3c](http://www.w3schools.com/xml/xml_soap.asp)) protocol.


##Package Structure

The project is divided into 3 packages. Each package contains:

`introsde.introsde.client`: The client program which connects to the Web Service.([link](https://github.com/sn1p3r46/introsde-2015-assignment-3-client/tree/master/src/introsde/client))

`introsde.introsde.handlers`: It contains static variables and a function, it is responsible of the pretty printing of the output. ([link](https://github.com/sn1p3r46/introsde-2015-assignment-3-client/tree/master/src/introsde/handlers))

`introsde.introsde.ws`: The classes generated by `wsimport` command, this package contains all the models and the functions that are needed to perform a correct communication between client and server. ([link](https://github.com/sn1p3r46/introsde-2015-assignment-3-client/tree/master/src/introsde/ws))

##Files included

The project contains some additional files.

`Client_logs.log`: is the output of the client when I ran it before pushing. (proof that it worked once :D)

##Execution

This project contains a `build.xml` file which can be run by `ant`. It will download all the required dependencies using ivy. It will also download ivy if it is not installed.

To execute the client run:
```
ant start.client
```

This will install the missing dependencies, compile, and execute the program. The output will be save to `client.log`.

`PLEASE:  If you are running the server locally MIND to change the server address and launch the server application and before running the client`

The URI of the server is hard coded in `src/introsde/ws/HealthServiceImplementationService.java` file.
