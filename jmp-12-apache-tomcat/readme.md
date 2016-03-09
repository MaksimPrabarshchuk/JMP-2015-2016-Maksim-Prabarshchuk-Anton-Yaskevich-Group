# Tomcat and Apache integration.

Fst u need:
 1. [Tomcat v9+](http://tomcat.apache.org/download-90.cgi).
 2. [Apache v2.4+](https://www.apachelounge.com/download/).
 3. [Tomcat connector (mod_jk) v1.2+](https://www.apachelounge.com/download/)

### Apache configuration:
 Main config of the server located at: `path/to/ur/server/conf/httpd.conf`
* Fst of all it's necessary to replace default paths in configuration with the path of the server's location.
* On the next step u should add downloaded mod_jk module file(`mod_jk.so` or `mod_jk.dll`) into the Apache server modules directory, which  is located at: `path/to/ur/server/modules`. 
* Also u shoud to add new condiguration into the `http.conf` for loading `mod_jk` module:
```
LoadModule jk_module modules/mod_jk.so
```
* U need to specify workers for mod_jk with special properties. For example, add `workers.properties` file into Apache conf directory and  fill it with properties for the worker: 
```
worker.list=ajp13
worker.ajp13.type=ajp13
worker.ajp13.host=localhost
worker.ajp13.port=8009
```
* Next step it's necessary to mention ur properties file for `mod_jk` module in the main configuration file `httpd.conf`:
```
JkWorkersFile path/to/propeties/workers.properties
```
### Tomcat configuration
* I u need specify env variables for the server, create `CATALINA_HOME` key with the `path/to/tomcat/` as a value.
* We need to specify listening port for Tomcat, cuz by default it has the same value as Apache web server. Specify port value at `tomcat/conf/server.xml`:
```
<Connector port="NEW_VALUE" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
```
* Also config for `ajp` should be uncommented and available (with the same values, which are already set in the `workers.properties`):
```
<Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
```

### Configuration of ur application:
* U should exclude ur static resources from result war. For example, if u use maven as a build tool for ur web project, for war plugin it will be: 
```
<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				...
				<configuration>
     ...
					<packagingExcludes>
         resources/**
     </packagingExcludes>
				</configuration>
			</plugin>
			```
* Then build ur project to get war for deploying.



