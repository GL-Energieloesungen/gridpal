
package org.openhab.binding.mbus.internal;



import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class MBusActivator implements BundleActivator
{
	private static final Logger logger = LoggerFactory.getLogger(MBusActivator.class);
	private static BundleContext context;

	@Override
	public void start(BundleContext bc) throws Exception
	{
		logger.debug("MBus binding has been started.");
		context = bc;
	}

	@Override
	public void stop(BundleContext arg0) throws Exception
	{
		logger.debug("MBus binding has been stopped.");
	}

	public static BundleContext getContext()
	{
		return context;
	}
}
