/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Nicolas
 */
public class Controller {

    private String exchangeName;
    private String broadcastName;
    private String callbackName;

    public Controller(String exchangeName, String broadcastName, String callbackName){

        this.exchangeName = exchangeName;
        this.broadcastName = broadcastName;
        this.callbackName = callbackName;

    }

    public void fillModel(String xml) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        XMLHelper xmlHelper = new XMLHelper();

        Model.getInstance().setConsumers(xmlHelper.getConsumers(xml));
        Model.getInstance().setProviders(xmlHelper.getProviders(xml));

    }

    public void deployScenario(){
        DeployHelper deployHelper = new DeployHelper();
        HashMap<String, Consumer> consumers = Model.getInstance().getConsumers();
        HashMap<String, Provider> providers = Model.getInstance().getProviders();


        for(String consumerId : consumers.keySet()){
            deployHelper.deployConsumer(consumerId, exchangeName, broadcastName, callbackName);
        }
        for(String providerId : providers.keySet()){
            deployHelper.deployProvider(providerId, exchangeName, broadcastName, callbackName);
        }

    }

    public void startScenario() throws IOException{

        BrokerHelper broker = new BrokerHelper(exchangeName, broadcastName, callbackName);
        JsonHelper jsonBuilder = new JsonHelper();
        broker.connect();
        HashMap<String, Consumer> consumers = Model.getInstance().getConsumers();
        HashMap<String, Provider> providers = Model.getInstance().getProviders();

        broker.setUpCallbackQueue();

        for(String consumerId : consumers.keySet()){
            broker.send(jsonBuilder.getJson(consumers.get(consumerId)).toString(),consumerId);
        }
        for(String providerId : providers.keySet()){
            broker.send(jsonBuilder.getJson(providers.get(providerId)).toString(),providerId);
        }

        broker.sendBroadcast(jsonBuilder.getGoJson().toString());
    }

}

