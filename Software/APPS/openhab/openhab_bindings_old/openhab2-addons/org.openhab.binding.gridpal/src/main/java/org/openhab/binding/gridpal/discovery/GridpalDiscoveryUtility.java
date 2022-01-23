
package org.openhab.binding.gridpal.discovery;

public class GridpalDiscoveryUtility
{
	private CIDR networkAddressInCidr;


	public GridpalDiscoveryUtility(String networkAddressInCidr)
	{
		this.networkAddressInCidr = new CIDR(networkAddressInCidr);
	}

}
