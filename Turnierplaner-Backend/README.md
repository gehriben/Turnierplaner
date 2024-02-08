# Tunierplaner-Backend

## Getting started

This sections describes how to set up the backend of "Turnierplaner".

IntelliJ IDEA (IntelliJ) is used as IDE for this description. Working with other IDEs is not forbidden but not explained here.

### Prerequisites

- Java 8 (version 1.8_162)
- IntelliJ IDEA (tested with version 2018.3.4)
- Gradle plugin for IntellIj IDEA
- **[Lombok Plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin)** (without it no data is accessible because no getters/setters are created)
- MySQL (tested with version 8.0.15 Community Server - GPL) *A local MySQL server installation is only needed when not the remote test server via SSH is used.*

### Development

1. Download the sources from the repository through Git
2. Open the sources as a project
3. If asked, import Gradle project (Gradle now installs the dependencies. After that the application can run)
4. Configure MySQL server ([run locally](#run-locally) or [SSH tunneling](#ssh-tunneling))

Test the application by entering [http://localhost:8090/greeting?name=patrickstar](http://localhost:8090/greeting?name=patrickstar). A page with *"Hellllllo patrickstar"* written on it should appear.

#### Run locally

It is assumed that a MySQL server instance is running (e.g. by itself or XAMPP).

1. Add the user for the backend to connect to the database (username and password can be found in the [application.properties file](src/main/resources/application.properties))
2. Add the roles to the user (*DBAdmin works but is not recommended*)
3. Add a database/schema (database name can be found in the [application.properties file](src/main/resources/application.properties) in the URL)
4. The backend should now be able to connect to the database

#### SSH tunneling

##### With Putty

1. Open Putty
2. Under `Connection` --> `SSH` --> `Auth` add the [private key file](https://zhaw-my.sharepoint.com/personal/widtmbri_students_zhaw_ch/Documents/PSIT4/priv_key.ppk)
3. Under `Connection` --> `SSH` --> `Tunnels` write **3306** for the `Source port` and **127.0.0.1:3306** for the `Destination`. The `Destination` is relative to the connected SSH server.
4. Under `Session` add **160.85.252.125** as `Host Name (or IP address)`
5. Hit `Open` to connect to the remote SSH server
6. After the successful login the SSH tunnel is established

**Note: Open the tunnel before IntelliJ is started!**

##### Alternative

1. First you need to copy the [private key](https://zhaw-my.sharepoint.com/personal/widtmbri_students_zhaw_ch/Documents/PSIT4/priv_key.ppk) file into the `main directory`.
2. Reroute the MySQL port through the SSH tunnel using `ssh -L 3306:localhost:3306 -i private_key psituser@160.85.252.125`
3. Now you can connect via your `localhost:3306` port to the MySQL port on the server `160.85.252.125:3306`
4. Run the spring application

#### Troubleshooting

If an error occurs stating: *"Invalid Gradle JDK configuration found. [...]"*
Check that the project SDK is set (in the Project Settings of IntelliJ).

#### SQL Dummy Data
[SQL-Dummy-Data](https://zhaw-my.sharepoint.com/personal/widtmbri_students_zhaw_ch/Documents/Forms/All.aspx?RootFolder=%2Fpersonal%2Fwidtmbri_students_zhaw_ch%2FDocuments%2FPSIT4%2FSQL%20Dummy%20Data&FolderCTID=0x01200083344153F8C09345BB8CB1C4027AFF1B&View=%7BFCD812B3-6E7E-43ED-B992-B369974EE001%7D)

### Deployment

*todo*

## Built with

- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Programming language
- [Spring](https://spring.io/) - Framework for decoupling
- [MySQL](https://www.mysql.com/) - Database server
  
