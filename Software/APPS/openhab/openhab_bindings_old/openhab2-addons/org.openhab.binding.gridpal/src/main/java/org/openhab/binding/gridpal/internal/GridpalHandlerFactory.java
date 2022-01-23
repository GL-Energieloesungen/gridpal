/**
 * Copyright (c) 2014,2018 by the respective copyright holders.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.openhab.binding.gridpal.internal;



import static org.openhab.binding.gridpal.GridpalBindingConstants.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.gridpal.handler.GatewayHandler;
import org.openhab.binding.gridpal.handler.SmartSwitchHandler;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * The {@link GridpalHandlerFactory} is responsible for creating things and
 * thing handlers.
 *
 * @author Md. Farhabi Helal - Initial contribution
 */
@Component(service = ThingHandlerFactory.class, immediate = true, configurationPid = "binding.gridpal")
@NonNullByDefault
public class GridpalHandlerFactory extends BaseThingHandlerFactory
{
	private Logger logger = LoggerFactory.getLogger(GridpalHandlerFactory.class);

	private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = new HashSet<>(Arrays
			.asList(THING_TYPE_UID_GRIDPAL_GATEWAY, THING_TYPE_UID_APL_SMART_BULB, THING_TYPE_UID_APL_SMART_SWITCH));

	@Override
	public boolean supportsThingType(ThingTypeUID thingTypeUID)
	{
		return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
	}

	@Override
	protected @Nullable ThingHandler createHandler(Thing thing)
	{
		ThingTypeUID thingTypeUID = thing.getThingTypeUID();

		if (thingTypeUID.equals(THING_TYPE_UID_GRIDPAL_GATEWAY))
		{
			logger.debug("\n\n[ CREATING GRIDPAL GATEWAY ]\n\n");
			return new GatewayHandler(thing);
		}
		else if (thingTypeUID.equals(THING_TYPE_UID_APL_SMART_BULB))
		{
			logger.debug("\n\n[ CREATING APL SMART BULB ]\n\n");
			// return new SmartBulbHandler(thing);
		}
		else if (thingTypeUID.equals(THING_TYPE_UID_APL_SMART_SWITCH))
		{
			logger.debug("\n\n[ CREATING APL SMART SWITCH ]\n\n");
			return new SmartSwitchHandler(thing);
		}

		return null;
	}
}
