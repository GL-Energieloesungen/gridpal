
package org.openhab.binding.gridpal.discovery;

public class IPv4
{
	private String[] binaryStringArray;


	public IPv4(String dottedDecimalString)
	{
		this.binaryStringArray = toBinaryStringArrayFromDottedDecimalString(dottedDecimalString);
	}

	// public IP(int[] decimals)
	// {
	//
	// }
	//
	//
	// public IP(String[] binaries)
	// {
	//
	// }

	// public String format(String ip)
	// {
	//
	// }


	public static String toBinaryStringFromDottedDecimalString(String dottedDecimalString)
	{
		return String.join("", IPv4.toBinaryStringArrayFromDottedDecimalString(dottedDecimalString));
	}


	public static String toBinaryStringFromDottedBinaryString(String dottedBinaryString)
	{
		return String.join("", dottedBinaryString.split("\\."));
	}


	public static String toDottedBinaryStringFromBinaryString(String binaryString)
	{
		// return (binaryString.substring(0, 8) + "." + binaryString.substring(8, 16) +
		// "."
		// + binaryString.substring(16, 24) + "." + binaryString.substring(24,
		// binaryString.length()));

		StringBuilder dottedBinaryString = new StringBuilder(binaryString);

		dottedBinaryString.insert(8, ".");
		dottedBinaryString.insert(17, ".");
		dottedBinaryString.insert(26, ".");

		return dottedBinaryString.toString();
	}


	public static String toDottedBinaryStringFromDottedDecimalString(String dottedDecimalString)
	{
		return String.join(".", IPv4.toBinaryStringArrayFromDottedDecimalString(dottedDecimalString));
	}


	public static String toDottedDecimalStringFromDottedBinaryString(String dottedBinaryString)
	{
		return String.join(".", IPv4.toDecimalStringArrayFromDottedBinaryString(dottedBinaryString));
	}


	public static String toDottedDecimalStringFromBinaryString(String binaryString)
	{
		return IPv4
				.toDottedDecimalStringFromDottedBinaryString(IPv4.toDottedBinaryStringFromBinaryString(binaryString));
	}


	public static String[] toDecimalStringArrayFromDottedBinaryString(String dottedBinaryString)
	{
		return toDecimalStringArray(dottedBinaryString, "\\.");
	}


	public static String[] toDecimalStringArray(String delimitedBinaryString, String delim)
	{
		if (delim.equals(""))
		{
			return IPv4.toDecimalStringArrayFromDottedBinaryString(
					IPv4.toDottedBinaryStringFromBinaryString(delimitedBinaryString));
		}

		String[] binaryStringArray = delimitedBinaryString.split(delim);
		String[] decimalStringArray = new String[binaryStringArray.length];

		for (int i = 0; i < decimalStringArray.length; i++)
		{
			decimalStringArray[i] = "" + Integer.parseInt(binaryStringArray[i], 2);
		}

		return decimalStringArray;
	}


	public static String[] toBinaryStringArrayFromDottedDecimalString(String dottedDecimalString)
	{
		return toBinaryStringArray(dottedDecimalString, "\\.");
	}


	public static String[] toBinaryStringArray(String delimitedDecimalString, String delim)
	{
		String[] decimalStringArray = delimitedDecimalString.split(delim);
		String[] binaryStringArray = new String[decimalStringArray.length];

		for (int i = 0; i < binaryStringArray.length; i++)
		{
			binaryStringArray[i] = Integer.toBinaryString(Integer.parseInt(decimalStringArray[i]));
			while (binaryStringArray[i].length() < 8)
			{
				binaryStringArray[i] = "0" + binaryStringArray[i];
			}
		}

		return binaryStringArray;
	}

}
