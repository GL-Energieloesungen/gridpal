# OpenHAB Binding Development


## Binding Development

### Overview
After generating the binding from the skeleton script, customization steps are as follows-

__Configuration files__ Adding/Editing _XML_ configuration files in `$binding_root_dir/ESH-INF/`.
    - Binding information: `binding/`.
    - ThingType definitions: `thing/`.
    - Configurations: `config/`.

    
__Build Dependencies__ All the packages the binding should require must be declared as dependencies in the `MANIFEST.MF` file located at `$binding_root_dir/META-INF/`.

__Third-party Library__ Third party _JAR_ files can be used. It is best practice to place them in `$binding_root_dir/lib`. They must be inclued in the build path.


__Constants__ Adding the constants (_BindingID_, _ThingTypeID_, _ChannelTypeID_ etc.) into _java_ class(es).
 
__Things__
- Create a `ThingHandler` class for each `ThingType` by extending the `BaseThingHandler` class.

- Adding the `ThingTypes` in the `ThingHandlerFactory` so that they can be created by the framework.

- Overriding abstract functions of the `ThingHandler` which the framework expects the plugin to implement. Some of these functions are:
    
    - `initialize()`
    - `dispose()`
    - `handleCommand()`
    - `handleConfigurationUpdate()`

- Notifying the framework about events/changes via callbacks. Some callbacks are:
    - `updateThing()`
    - `updateState()`


### Discovery
The `AbstractDiscoveryService` implements the  `DiscoveryService` and needs to be extended further. A discovery service should provide `DiscoveyResult`s to the framework which contains all the necessary informations about the discovered thing via callback `thingDiscovered()`.

There are two types of discovery:
- _Background Discovery_
- _Active Scan_

See: 
- [Inbox & Discovery](https://www.eclipse.org/smarthome/documentation/concepts/discovery.html "Inbox & Discovery")
- [Implementing a Discovery Service](https://www.eclipse.org/smarthome/documentation/development/bindings/discovery-services.html "Implementing a Discovery Service")


### Dynamic Things & Channels Manipulation
__Thing__: `ThingBuilder` has all the necessary functions to create a `Thing`. To informe the framework about the change, `updateThing()` must be called with the updated thing object.

Here is an example:
```java
ThingBuilder thingBuilder = editThing();

...

updateThing(thingBuilder.withChannels(channels).build());
```

See: [Updating the Thing Structure](https://www.eclipse.org/smarthome/documentation/development/bindings/thing-handler.html#updating-the-thing-structure "Updating the Thing Structure")


__Channel__: `ChannelBuilder` class has all the necessary functions to create a `Channel`.

Here is an example:
```java
ChannelBuilder channelBuilder = ChannelBuilder.create(channelUID, itemType);
    
    ...
    
Channel channel = channelBuilder.withLabel(label).withDescription(description).withType().withConfiguration().withProperties().withDefaultTags().withKind().build();
```


### Adding REST Resource
OpenHAB uses _Jersey_ implementation of _JAX-RS_ to provide _RESTful_ service. The `org.eclipse.smarthome.io.rest.core` package contains all the _REST_ resources of the _openHAB_. 

To add a custom _REST_ resource, we need to create a resource class and register it as a _OSGi_ service.

Example:

```java
@Path(SomeResources.SomePath)
@RolesAllowed({ Role.ADMIN })
@Component(service = { SomeResources.class })
public class SomeResources
{
    /** The URI path to this resource */
    public static final String SomePath = "path";

    @GET
    @RolesAllowed({ Role.USER, Role.ADMIN })
    @Produces(MediaType.TEXT_PLAIN)
    public Response get(@QueryParam("param") String param)
    {
        String response = "";
        
        ...

        return Response.ok(response).build();
    }
}
```