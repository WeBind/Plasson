/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Nicolas
 */
public class BrokerHelper {

    private Connection connection;
    private Channel channel;
    private String EXCHANGE_NAME;

    public BrokerHelper(String name){
        EXCHANGE_NAME = name;
    }

    public void connect() throws IOException{
        try{
        ConnectionFactory factory = new ConnectionFactory();
        //TODO maybe change
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        }catch(TimeoutException e){
            System.out.println("Error : Broker connection");
        }
    }

    public void send(String message, String routingKey) throws IOException{
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
    }

    public void sendBroadcast(String message)  throws IOException{
        channel.basicPublish(EXCHANGE_NAME, "all", null, message.getBytes());
    }

    public void close() throws IOException{
        try{
            channel.close();
            connection.close();
        }catch(TimeoutException e){
            System.out.println("Error : Close broker");
        }
    }



}
