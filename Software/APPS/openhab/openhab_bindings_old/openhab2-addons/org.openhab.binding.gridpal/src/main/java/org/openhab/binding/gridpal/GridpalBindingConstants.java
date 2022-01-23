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

package org.openhab.binding.gridpal;



import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;





/**
 * The {@link GridpalBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Md. Farhabi Helal - Initial contribution
 */
@NonNullByDefault
public class GridpalBindingConstants
{

	public static final String BINDING_ID = "gridpal";

	public static final String THING_TYPE_ID_GRIDPAL_GATEWAY = "gridpal-gateway";
	public static final String THING_TYPE_ID_SMART_BULB = "apl-smart-bulb";
	public static final String THING_TYPE_ID_SMART_SWITCH = "apl-smart-switch";

	// List of all Thing Type UIDs
	public static final ThingTypeUID THING_TYPE_UID_GRIDPAL_GATEWAY = new ThingTypeUID(BINDING_ID,
			THING_TYPE_ID_GRIDPAL_GATEWAY);
	public static final ThingTypeUID THING_TYPE_UID_APL_SMART_BULB = new ThingTypeUID(BINDING_ID,
			THING_TYPE_ID_SMART_BULB);
	public static final ThingTypeUID THING_TYPE_UID_APL_SMART_SWITCH = new ThingTypeUID(BINDING_ID,
			THING_TYPE_ID_SMART_SWITCH);

	// APL Smart Bulb channels
	public static final String CHANNEL_APL_SMART_BULB_COLOR = "channel-apl-smart-bulb-color";
	public static final String CHANNEL_APL_SMART_BULB_SWITCH = "channel-apl-smart-bulb-switch";

	// APL Smart Switch channels
	public static final String CHANNEL_TYPE_ID_SMART_SWITCH_01 = "smart-switch-01";
	public static final String CHANNEL_TYPE_ID_SMART_SWITCH_02 = "smart-switch-02";
	public static final String CHANNEL_TYPE_ID_SMART_SWITCH_03 = "smart-switch-03";
	public static final String CHANNEL_TYPE_ID_SMART_SWITCH_04 = "smart-switch-04";
	public static final String CHANNEL_TYPE_ID_SMART_SWITCH_05 = "smart-switch-05";
	public static final String CHANNEL_TYPE_ID_SMART_SWITCH_06 = "smart-switch-06";

}
