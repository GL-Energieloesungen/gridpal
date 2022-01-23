package org.openhab.binding.mbus;





import org.openhab.binding.mbus.internal.MBusBindingConfig;
import org.openhab.core.binding.BindingProvider;





public interface MBusBindingProvider extends BindingProvider
{
	MBusBindingConfig getConfig(String itemName);
}
