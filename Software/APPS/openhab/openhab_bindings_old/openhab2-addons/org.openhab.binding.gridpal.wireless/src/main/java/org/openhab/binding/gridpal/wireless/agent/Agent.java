
package org.openhab.binding.gridpal.wireless.agent;



import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class Agent
{
	private Process process;
	private Logger logger = LoggerFactory.getLogger(Agent.class);

	public Agent()
	{
	}

	public String Execute(String[] command)
	{
		if (command[2] == null)
		{
			return "";
		}

		logger.debug("[ COMMAND ] : " + command[2]);

		StringBuilder response = new StringBuilder();

		try
		{
			process = Runtime.getRuntime().exec(command);

			Scanner scanner = new Scanner(process.getInputStream());
			while (scanner.hasNextLine())
			{
				response.append(scanner.nextLine() + "\n");
			}
			process.waitFor();
			scanner.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		logger.debug("[ RESPONSE ] : " + response.toString());
		return response.toString().trim();
	}

	public String Execute(String command)
	{
		return Execute(new String [] { "/bin/bash", "-c", command });
	}
}
