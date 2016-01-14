/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import com.rabbitmq.client.*;

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
    private String BROADCAST_NAME;
    private String CALLBACK_NAME;

    public BrokerHelper(String name, String broadcast, String callback){
        EXCHANGE_NAME = name;
        BROADCAST_NAME = broadcast;
        CALLBACK_NAME = callback;
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
        channel.basicPublish(EXCHANGE_NAME, BROADCAST_NAME, null, message.getBytes());
    }

    public void setUpCallbackQueue(){
        try {
            //Non-persistant queue
            channel.queueDeclare(CALLBACK_NAME, false, false, false, null);
            channel.basicConsume(CALLBACK_NAME,true,getQueueConsumer());
            System.out.println("Plasson : ready to receive results");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private com.rabbitmq.client.Consumer getQueueConsumer(){
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                //TODO : ajouter ici le traitement des résultats
                System.out.println(" [x] Received results'" + message + "'");
            }
        };
        return  consumer;
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
