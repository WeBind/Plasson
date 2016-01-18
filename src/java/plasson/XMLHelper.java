/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import java.lang.*;
import java.util.HashMap;
import javax.xml.transform.Source;
import org.xml.sax.InputSource;
/**
 *
 * @author Nicolas
 */
public class XMLHelper {

    private int maxConsumerEndTime = 0;
    private int maxProviderResponseTime = 0;
   

    public static boolean validateXML(String xsdPath, String xmlPath){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean validateXMLString(String xsdPath, String xml){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new StringReader(xml));

            validator.validate(source);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public Consumer getConsumerAt(String xmlPath,int index) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        Document doc = buildDocument(xmlPath);

        //Now, a new instance of XPath should be done :
        XPath xpath = XPathFactory.newInstance().newXPath();

       // The XPath expression to be evaluated :
        XPathExpression exprId = xpath.compile("/Scenario/Consumers/Consumer["+index+"]/id/text()");
        XPathExpression exprStartingTime = xpath.compile("/Scenario/Consumers/Consumer["+index+"]/Starting_time/text()");
        XPathExpression exprSize = xpath.compile("/Scenario/Consumers/Consumer["+index+"]/Size/text()");
        XPathExpression exprFrequency = xpath.compile("/Scenario/Consumers/Consumer["+index+"]/Frequency/text()");
        XPathExpression exprDuration = xpath.compile("/Scenario/Consumers/Consumer["+index+"]/Duration/text()");
        XPathExpression exprProvider = xpath.compile("/Scenario/Consumers/Consumer["+index+"]/Provider/text()");

        //TODO : Verifier si c'est nul si la verif accepte le null;
        Consumer consumer = new Consumer();
        Node node;
        node = (Node)exprId.evaluate(doc,XPathConstants.NODE);
        consumer.setId(Integer.valueOf(node.getNodeValue()));

        node = (Node)exprStartingTime.evaluate(doc,XPathConstants.NODE);
        consumer.setStartingTime(Integer.valueOf(node.getNodeValue()));

        node = (Node)exprSize.evaluate(doc,XPathConstants.NODE);
        consumer.setSize(Integer.valueOf(node.getNodeValue()));

        node = (Node)exprFrequency.evaluate(doc,XPathConstants.NODE);
        consumer.setFrequency(Float.valueOf(node.getNodeValue()));

        node = (Node)exprDuration.evaluate(doc,XPathConstants.NODE);
        consumer.setDuration(Integer.valueOf(node.getNodeValue()));

        node = (Node)exprProvider.evaluate(doc,XPathConstants.NODE);
        consumer.setProvider(Integer.valueOf(node.getNodeValue()));

        if(consumer.getDuration()+consumer.getStartingTime()>maxConsumerEndTime){

            maxConsumerEndTime = consumer.getDuration()+consumer.getStartingTime();
        }

    return consumer;
    }

    public Provider getProviderAt(String xmlPath,int index) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        Document doc = buildDocument(xmlPath);

        //Now, a new instance of XPath should be done :
        XPath xpath = XPathFactory.newInstance().newXPath();

         // The XPath expression to be evaluated :
        XPathExpression exprId = xpath.compile("/Scenario/Providers/Provider["+index+"]/id/text()");
        XPathExpression exprResponseLength = xpath.compile("/Scenario/Providers/Provider["+index+"]/Response_length/text()");
        XPathExpression exprResponseTime = xpath.compile("/Scenario/Providers/Provider["+index+"]/Response_time/text()");

        //TODO : Verifier si c'est nul si la verif accepte le null;
        Provider provider = new Provider();
        Node node;
        node = (Node)exprId.evaluate(doc,XPathConstants.NODE);
        provider.setId(Integer.valueOf(node.getNodeValue()));

        node = (Node)exprResponseLength.evaluate(doc,XPathConstants.NODE);
        provider.setResponseLength(Integer.valueOf(node.getNodeValue()));

        node = (Node)exprResponseTime.evaluate(doc,XPathConstants.NODE);
        provider.setResponseTime(Integer.valueOf(node.getNodeValue()));

        if(provider.getResponseTime()>maxProviderResponseTime){
            maxProviderResponseTime = provider.getResponseTime();
        }

    return provider;
    }

    public int getProvidersCount(String xmlPath) throws ParserConfigurationException, ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        Document doc = buildDocument(xmlPath);

        //Now, a new instance of XPath should be done :
        XPath xpath = XPathFactory.newInstance().newXPath();

       // The XPath expression to be evaluated :
        XPathExpression expr = xpath.compile("count(//Scenario/Providers/Provider)");
        Number result = (Number) expr.evaluate(doc ,XPathConstants.NUMBER);

        return result.intValue();

    }

    public int getConsumersCount(String xmlPath) throws ParserConfigurationException, ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        Document doc = buildDocument(xmlPath);

        //Now, a new instance of XPath should be done :
        XPath xpath = XPathFactory.newInstance().newXPath();

       // The XPath expression to be evaluated :
        XPathExpression expr = xpath.compile("count(//Scenario/Consumers/Consumer)");
        Number result = (Number) expr.evaluate(doc ,XPathConstants.NUMBER);

        return result.intValue();

    }

    public ProviderReturn getProviders(String xmlPath) throws ParserConfigurationException, SAXException, SAXException, IOException, XPathExpressionException{
        
        maxProviderResponseTime = 0;


        Document doc = buildDocument(xmlPath);
        HashMap<String, Provider> providers = new HashMap<String, Provider>();

        int number = this.getProvidersCount(xmlPath);

        for(int i = 1 ; i <= number ; i++){
            Provider provider = this.getProviderAt(xmlPath, i);
            providers.put("provider"+provider.getId(), provider);
        }

        ProviderReturn result = new ProviderReturn(providers, maxProviderResponseTime);

        return result;

    }

    public ConsumerReturn getConsumers(String xmlPath) throws ParserConfigurationException, SAXException, SAXException, IOException, XPathExpressionException{

        maxConsumerEndTime = 0;
        Document doc = buildDocument(xmlPath);
        HashMap<String, Consumer> consumers = new HashMap<String, Consumer>();

        int number = this.getConsumersCount(xmlPath);

        for(int i = 1 ; i <= number ; i++){
            Consumer consumer = this.getConsumerAt(xmlPath, i);
            consumers.put("consumer"+consumer.getId(), consumer);
        }

        ConsumerReturn result = new ConsumerReturn(consumers, maxConsumerEndTime);
        return result;

    }

    private Document buildDocument(String xml) throws ParserConfigurationException, SAXException, IOException //builds the xml reqder
    {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        Document doc = builder.parse(inputSource);
        return doc;
    }

    public class ConsumerReturn{
        private HashMap<String,Consumer> consumers;
        private int maxConsumerEndTime;

        public ConsumerReturn(HashMap<String, Consumer> consumers, int maxConsumerEndTime) {
            this.consumers = consumers;
            this.maxConsumerEndTime = maxConsumerEndTime;
        }


        public HashMap<String, Consumer> getConsumers() {
            return consumers;
        }

        public int getMaxConsumerEndTime() {
            return maxConsumerEndTime;
        }

    }

    public class ProviderReturn{
        private HashMap<String,Provider> providers;
        private int maxProviderEndTime;

        public ProviderReturn(HashMap<String, Provider> providers, int maxProviderEndTime) {
            this.providers = providers;
            this.maxProviderEndTime = maxProviderEndTime;
        }

        public int getMaxProviderEndTime() {
            return maxProviderEndTime;
        }

        public HashMap<String, Provider> getProviders() {
            return providers;
        }


    }
}

