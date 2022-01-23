
package org.openhab.binding.gridpal.sysinfo.agent;



import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * This is a helper class for executing shell commands.
 * @author	Md. Farhabi Helal Ayon
 * @since	1.0
 */
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

		logger.debug("\n\n[ EXECUTING COMMAND ] : " + command[2]);

		StringBuilder response = new StringBuilder("");

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

			logger.debug("\n\n[ EXECUTION ] : Complete\n");

		}
		catch (Exception e)
		{
			response.append("Exception!");
			e.printStackTrace();
		}

		return response.toString();
	}
}
