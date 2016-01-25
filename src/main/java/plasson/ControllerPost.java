package plasson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceContext;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import plasson.ResultsHelper.ComputedResults;

/**
 *
 * @author Nicolas
 *
 * This class is the controller
 * It does the link between the rest API and the model.
 *
 */
public class ControllerPost {

    private final String contextName = Config.contextName;
    private int responseCounter = 0;
    private Timer scenarioTimeout;
    private BrokerHelper myBroker;
    private ServletContext myContext;
    private Model myModel;
    

    // Set up the BrokerHelper with the config
    // and listen to it implementing receiveResult()
    public ControllerPost(ServletContext sc) {
        //NULL because no one wants to listen results for now
        myBroker = new BrokerHelper(Config.exchangeName, Config.broadcastName, Config.callbackName, null);
        scenarioTimeout = new Timer();
        myContext = sc;
        if (myContext.getAttribute(contextName) != null) {
            myModel = (Model) myContext.getAttribute(contextName);
        } else {
            myModel = new Model();
        }

    }

    // From a xml String fill the model with the different consumers and providers described in the xml.
    public void fillModel(String xml) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

        XMLHelper xmlHelper = new XMLHelper();

        XMLHelper.ConsumerReturn consumers = xmlHelper.getConsumers(xml);
        XMLHelper.ProviderReturn providers = xmlHelper.getProviders(xml);
        myModel.setConsumers(consumers.getConsumers());
        myModel.setProviders(providers.getProviders());
        myModel.setScenarioEndTime(consumers.getMaxConsumerEndTime() + providers.getMaxProviderEndTime());



    }

    // Go through the consumers and providers list to deploy each consumer
    // and provider using a deployHelper
    public void deployScenario() {
        DeployHelper deployHelper = new DeployHelper();
        HashMap<String, Consumer> consumers = myModel.getConsumers();
        HashMap<String, Provider> providers = myModel.getProviders();


        for (String consumerId : consumers.keySet()) {
            deployHelper.deployConsumer(consumerId, Config.exchangeName, Config.broadcastName, Config.callbackName);
        }
        for (String providerId : providers.keySet()) {
            deployHelper.deployProvider(providerId, Config.exchangeName, Config.broadcastName, Config.callbackName);
        }

    }

    // Configures each consumer and provider sendind them a json through RabbitMQ
    // The connection with rabbitMQ is made by the BrokerHelper
    // Send the start message
    // Start the scenarioTimeout
    public void startScenario() throws IOException {

        JsonHelper jsonBuilder = new JsonHelper();
        myBroker.connect();
        HashMap<String, Consumer> consumers = myModel.getConsumers();
        HashMap<String, Provider> providers = myModel.getProviders();

        // SET UP CALLBACK IS DONE WHEN THE RESULTS ARE ASKED
        //myBroker.setUpCallbackQueue();

        for (String consumerId : consumers.keySet()) {
            myBroker.send(jsonBuilder.getJson(consumers.get(consumerId)).toString(), consumerId);
        }
        for (String providerId : providers.keySet()) {
            myBroker.send(jsonBuilder.getJson(providers.get(providerId)).toString(), providerId);
        }

        myBroker.sendBroadcast(jsonBuilder.getGoJson().toString());
        myBroker.close();
        //TIMEOUT IS DONE BY THE WEBAPP
        //startTimeout();
    }

    public void saveContext() {
        myContext.setAttribute(contextName, myModel);
    }

    
}

