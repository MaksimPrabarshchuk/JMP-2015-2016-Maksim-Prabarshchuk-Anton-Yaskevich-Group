# Tomcat and Apache integration.

Fst u need:
 1. [Tomcat v9+](http://tomcat.apache.org/download-90.cgi).
 2. [Apache v2.4+](https://www.apachelounge.com/download/).
 3. [Tomcat connector (mod_jk) v1.2+](https://www.apachelounge.com/download/)

### Apache confihuration:
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
 


