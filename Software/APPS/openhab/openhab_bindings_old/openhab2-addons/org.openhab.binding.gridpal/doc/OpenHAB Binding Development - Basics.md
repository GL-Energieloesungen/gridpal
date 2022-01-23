# OpenHAB Binding Development


## IDE Setup

To set up the development environment, [Eclipse Smarthome Development Documentation](https://www.eclipse.org/smarthome/documentation/development/ide.html "IDE Setup")  can be followed.

_Please note_: It is not mandatory to download all the git repositories. Only the _openHAB Development_ repository is required to launch the framework.


## Generating Binding from Skeleton

A generic openHAB binding can be generated from a skeleton script located at `$eclipse_inst_dir/openhab2-development/git/openhab2-addons/addons/binding/`

Then running the following command will generate _Binding Name_ binding.
```shell
./create_openhab_binding_test_skeleton.sh "Binding Name"
```


## Binding Development Basics


### Important Materials

- [Implementing a Binding](https://www.eclipse.org/smarthome/documentation/development/bindings/how-to.html "Implementing a Binding")
- [Thing Definitions](https://www.eclipse.org/smarthome/documentation/development/bindings/thing-definition.html "Thing Definitions")
- [Thing Handler](https://www.eclipse.org/smarthome/documentation/development/bindings/thing-handler.html "Thing Handler")



### Binding Structure

```
|- ESH-INF
|---- binding
|------- binding.xml
|---- thing
|------- *.xml
|---- config
|------- *.xml
|---- i18n
|------- *.properties
|- META-INF
|---- MANIFEST.MF
|- OSGI-INF
|---- *.xml
|- lib
|------- *.jar
|- src
|---- main
|------- java
|---------- [...]
|- build.properties
|- pom.xml
```

- **ESH-INF:** _XML_ configuration files for _Eclipse SmartHome_ (binding, config, thing etc.) are located. 

- **META-INF:** _XML_ configuration file for _Build Dependencies_ are located. 

- **OSGI-INF:** _XML_ configuration files for _OSGi Services_ are located. Files are generated automatically.

- **lib:** 3rd party _JAR_ files are located.

_See_: [Structure of a Binding](https://www.eclipse.org/smarthome/documentation/development/bindings/how-to.html#structure-of-a-binding "Structure of a Binding").



### XML Schema

The schemas for _XML_ configuration files can be found-

- [Binding Definition](https://eclipse.org/smarthome/schemas/config-description-1.0.0.xsd "ESH Binding Definition Schema")
- [Thing Description](https://www.eclipse.org/smarthome/schemas/thing-description-1.0.0.xsd "ESH Thing Description Schema")
- [Config Description](https://eclipse.org/smarthome/schemas/config-description-1.0.0.xsd "ESH Config Description Schema")

_See_: [XML Reference](https://www.eclipse.org/smarthome/documentation/development/bindings/xml-reference.html#bridges-and-thing-descriptions "XML Reference").
