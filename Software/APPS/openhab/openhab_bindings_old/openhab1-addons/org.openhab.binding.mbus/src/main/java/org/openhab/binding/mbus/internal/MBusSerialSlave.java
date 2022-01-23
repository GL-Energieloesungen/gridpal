
package org.openhab.binding.mbus.internal;



import java.io.IOException;

import org.openhab.core.library.types.DecimalType;
import org.openmuc.jmbus.MBusConnection;
import org.osgi.service.cm.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class MBusSerialSlave extends MBusSlave
{
    public String port = null;
    public int baudrate = 0;
    public int dataBits = 0;
    public String parity = "";
    public int stopBits = 0;
    public String encoding;
    public int interTransactionDelay = 35;
    public int receiveTimeout = 1500;
    public Double value = 0.0;

    private final Logger logger = LoggerFactory.getLogger(MBusSerialSlave.class);
    private static final Agent agent = new Agent();

    public MBusSerialSlave()
    {
    }

    @Override
    public void update(MBusBinding binding)
    {
        if (!isSerialPortAvailable())
        {
            logger.error("{} not found! Skipping slave {}.", port, name);
            return;
        }

        if (!updatedPool.contains(id))
        {
            // logger.debug("ID={} not available in the pool.", id);

            try
            {
                // logger.debug("Reading data from the connection for ID={}", id);

                // logger.debug("\n[ UPDATING SLAVE ]\n\n{}\n", toString());
                mbusConnection = getConnection();
                read(mbusConnection, id);
                updatedPool.add(id);
            }
            catch (NullPointerException e)
            {
                logger.error("{} not found!", port);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        value = records.get(start);
        // logger.debug("\n[ SLAVE UPDATED ]\n\n{}\n", toString());

        binding.internalUpdateItem(getItemName(), new DecimalType(value.intValue()));
    }

    public void setSerialParameters(String connection) throws ConfigurationException, NumberFormatException
    {
        String[] chunks = connection.split(":");

        if (chunks.length < 6)
        {
            throw new ConfigurationException("Connection", "the connection string must contain atleast 6 fields.");
        }

        this.port = chunks[0];
        this.baudrate = Integer.valueOf(chunks[1]);
        this.dataBits = Integer.valueOf(chunks[2]);
        this.parity = chunks[3];
        this.stopBits = Integer.valueOf(chunks[4]);
        this.encoding = chunks[5];
        this.interTransactionDelay = Integer.valueOf(chunks[6]);
        this.receiveTimeout = Integer.valueOf(chunks[7]);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%s = %s\n", "Connection", String.format("%s:%s:%s:%s:%s:%s:%s:%s", port, baudrate,
                dataBits, parity, stopBits, encoding, interTransactionDelay, receiveTimeout)));
        sb.append(String.format("%s = %s\n", "Start", start));
        sb.append(String.format("%s = %.3f\n", "Value", value));

        return sb.toString();
    }

    @Override
    public MBusConnection getConnection() throws IOException
    {
        return MBusConnection.newSerialBuilder(port).setBaudrate(baudrate).setTimeout(receiveTimeout).build();
    }

    private boolean isSerialPortAvailable()
    {
        String[] command = { "/bin/bash", "-c", String.format("ls /dev/ | grep %s", port.replace("/dev/", "")) };

        return !agent.Execute(command).trim().isEmpty();
    }

}
