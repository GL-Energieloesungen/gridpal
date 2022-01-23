
package org.openhab.binding.gridpal.discovery;



import java.util.ArrayList;





public class CIDR
{
	private String addressInDottedDecimal = "";
	private String addressInBinary = "";

	private int networkBitLength = 0;
	private int hostBitLength = 0;


	public CIDR(String addressInCidr)
	{
		this.addressInDottedDecimal = addressInCidr.split("/")[0];
		this.networkBitLength = Integer.parseInt(addressInCidr.split("/")[1]);
		this.addressInBinary = IPv4.toBinaryStringFromDottedDecimalString(addressInDottedDecimal);
		this.hostBitLength = 32 - networkBitLength;
	}


	public CIDR(String addressInDottedDecimal, int networkBitLength)
	{
		this.addressInDottedDecimal = addressInDottedDecimal;
		this.networkBitLength = networkBitLength;
		this.addressInBinary = IPv4.toBinaryStringFromDottedDecimalString(addressInDottedDecimal);
		this.hostBitLength = 32 - networkBitLength;
	}


	public ArrayList<String> getIPv4Range()
	{
		ArrayList<String> hosts = new ArrayList<>();
		int hostCount = (int) Math.pow(2, hostBitLength);

		String networkPrefixInBinary = addressInBinary.substring(0, networkBitLength);

		for (int i = 0; i < hostCount; i++)
		{
			String hostIdInBinray = Integer.toBinaryString(i);

			while (hostIdInBinray.length() < hostBitLength)
			{
				hostIdInBinray = "0" + hostIdInBinray;
			}

			hosts.add(IPv4.toDottedDecimalStringFromBinaryString(networkPrefixInBinary + hostIdInBinray));
		}

		return hosts;
	}


	@Override
	public String toString()
	{
		return addressInDottedDecimal + "/" + networkBitLength;
	}
}
