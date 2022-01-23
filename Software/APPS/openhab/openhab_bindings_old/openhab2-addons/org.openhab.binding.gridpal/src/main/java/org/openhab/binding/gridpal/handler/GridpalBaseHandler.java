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

package org.openhab.binding.gridpal.handler;



import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * The {@link GridpalHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Md. Farhabi Helal - Initial contribution
 */
@NonNullByDefault
public abstract class GridpalBaseHandler extends BaseThingHandler
{

	private final Logger logger = LoggerFactory.getLogger(GridpalBaseHandler.class);

	public GridpalBaseHandler(Thing thing)
	{
		super(thing);
	}
}
