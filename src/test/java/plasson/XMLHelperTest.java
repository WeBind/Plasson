/*
 * It is important to verify that XMLTest and XSDModel xml and xsd files are found by this class. (xmlPath and xsdPath variables)
 */

package plasson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import junit.framework.TestCase;

import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import plasson.Consumer;
import plasson.Main;
import plasson.Provider;
import plasson.XMLHelper;
import plasson.XMLHelper.ConsumerReturn;
import plasson.XMLHelper.ProviderReturn;

/**
 *
 * @author Adirael
 */
public class XMLHelperTest extends TestCase {

    final String dir;
    String xmlPath;
    String xsdPath;
    private int providers;
        
    public XMLHelperTest(String testName) {
        super(testName);
         dir = System.getProperty("user.dir");
         if(System.getProperty("os.name").toLowerCase().contains("windows".toLowerCase()))
         {
             xmlPath = dir + "\\src\\test\\java\\resources\\XMLTest.xml";
             xsdPath = dir + "\\src\\main\\java\\plasson\\XSDModel.xsd";
         }
         else
         {
             xmlPath = dir + "/src/test/java/resources/XMLTest.xml";
             xsdPath = dir + "/src/main/java/plasson/XSDModel.xsd";
         }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of validateXML method, of class XMLHelper.
     */
    public void testValidateXML() {
        System.out.println("validateXML");

        boolean expResult = true;
        boolean result = XMLHelper.validateXML(xsdPath, xmlPath);
        assertEquals(result, expResult);
    }

    /**
     * Test of validateXMLString method, of class XMLHelper.
     */
    public void testValidateXMLString() {
        System.out.println("validateXMLString");

        boolean expResult = true;
        try
         {
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            XMLHelper instance = new XMLHelper();
            boolean result = instance.validateXMLString(xsdPath, xml);
            assertEquals(expResult, result);
          }  catch (IOException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Test of getConsumerAt method, of class XMLHelper.
     */
    public void testGetConsumerAt() throws Exception {
        System.out.println("getConsumerAt");
        try
         {
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            XMLHelper instance = new XMLHelper();
            try {
                if (instance.validateXMLString(xsdPath, xml))
                {
                    Consumer consumer1 = instance.getConsumerAt(xml, 1);
                    Consumer consumer2 = instance.getConsumerAt(xml, 3);
                    
                    Consumer expConsumer1 = new Consumer(18, 1000, 100,1, 300, 0);
                    assertEquals(consumer1.toString(), expConsumer1.toString());

                    Consumer expConsumer2 = new Consumer(148, 1000, 100,1, 300, 0);
                    assertEquals(consumer2.toString(), expConsumer2.toString());
                }else {
                    fail("The path is not valid.");
                }
            } catch (ParserConfigurationException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            fail("Exception found.");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProviderAt method, of class XMLHelper.
     */
    public void testGetProviderAt() throws Exception
    {
        System.out.println("getProviderAt");
        
         try
         {
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            XMLHelper instance = new XMLHelper();
            try {
                if (instance.validateXMLString(xsdPath, xml))
                {
                    Provider provider1 = instance.getProviderAt(xml, 1);
                    Provider provider2 = instance.getProviderAt(xml, 6);

                    Provider expConsumer1 = new Provider(0, 1089, 1);
                    assertEquals(provider1.toString(), expConsumer1.toString());

                    Provider expConsumer2 = new Provider(5, 19, 1);
                    assertEquals(provider2.toString(), expConsumer2.toString());
                }else {
                    fail("The path is not valid.");
                }
            } catch (ParserConfigurationException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            fail("Exception found.");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProvidersCount method, of class XMLHelper.
     */
    public void testGetProvidersCount() throws Exception
    {
        System.out.println("getProvidersCount");

         try
         {
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            XMLHelper instance = new XMLHelper();
            try {
                if (instance.validateXMLString(xsdPath, xml))
                {
                    int expResult = 6;
                    int result = instance.getProvidersCount(xml);
                    assertEquals(expResult, result);
                }else {
                    fail("The path is not valid.");
                }
            } catch (ParserConfigurationException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            fail("Exception found.");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getConsumersCount method, of class XMLHelper.
     */
   public void testGetConsumersCount() throws Exception
   {
         System.out.println("getConsumersCount");

         try
         {
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            XMLHelper instance = new XMLHelper();
            try {
                if (instance.validateXMLString(xsdPath, xml))
                {
                    int expResult = 3;
                    int result = instance.getConsumersCount(xml);
                    assertEquals(expResult, result);
                }else {
                    fail("The path is not valid.");
                }
            } catch (ParserConfigurationException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            fail("Exception found.");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProviders method, of class XMLHelper.
     */
    public void testGetProviders() throws Exception {
        System.out.println("getProviders");

        try
         {
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            XMLHelper instance = new XMLHelper();
            try {
                if (instance.validateXMLString(xsdPath, xml))
                {
                    //Create expected providers hashmap manually (according to the XMLTest.xml)
                    HashMap<String, Provider> expProviders = new HashMap<String, Provider>();
                    expProviders.put(Config.providerPrefix+ 0, instance.getProviderAt(xml, 1));
                    expProviders.put(Config.providerPrefix+ 1, instance.getProviderAt(xml, 2));
                    expProviders.put(Config.providerPrefix+ 2, instance.getProviderAt(xml, 3));
                    expProviders.put(Config.providerPrefix+ 3, instance.getProviderAt(xml, 4));
                    expProviders.put(Config.providerPrefix+ 4, instance.getProviderAt(xml, 5));
                    expProviders.put(Config.providerPrefix+ 5, instance.getProviderAt(xml, 6));

                    //Retrieve values from the manually created hashmaps and the function created objects into hashmaps
                    ProviderReturn expResultProviders = new ProviderReturn(expProviders, 0);
                    ProviderReturn resultProviders = instance.getProviders(xml);
                    HashMap<String, Provider> expResultHash = expResultProviders.getProviders();
                    HashMap<String, Provider> resultHash = resultProviders.getProviders();

                    //Iterate and compare manually expected values from function values
                    int number = instance.getProvidersCount(xml);
                    Provider expResult;
                    Provider result;

                    for(int i=0 ; i < number ; i++){
                        expResult = expResultHash.get(Config.providerPrefix+ i);
                        result = (Provider)resultHash.get(Config.providerPrefix+ i);
                        assertEquals(expResult.toString(), result.toString());
                    }
                }else {
                    fail("The path is not valid.");
                }
            } catch (ParserConfigurationException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            fail("Exception found.");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getConsumers method, of class XMLHelper. This test also verifies that IDs are generated and retrieved correctly
     */
    public void testGetConsumers() throws Exception {
        System.out.println("getConsumers");

        try
         {
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            XMLHelper instance = new XMLHelper();
            try {
                if (instance.validateXMLString(xsdPath, xml))
                {
                    //Create expected consumers hashmap manually (according to the XMLTest.xml)
                    HashMap<String, Consumer> expConsumers = new HashMap<String, Consumer>();
                    expConsumers.put(Config.consumerPrefix+ 0, instance.getConsumerAt(xml, 1));
                    expConsumers.put(Config.consumerPrefix+ 1, instance.getConsumerAt(xml, 2));
                    expConsumers.put(Config.consumerPrefix+ 2, instance.getConsumerAt(xml, 3));

                    //Retrieve values from the manually created hashmaps and the function created objects into hashmaps
                    ConsumerReturn expResultConsumers = new ConsumerReturn(expConsumers, 0);
                    ConsumerReturn resultConsumers = instance.getConsumers(xml);
                    HashMap<String, Consumer> expResultHash = expResultConsumers.getConsumers();
                    HashMap<String, Consumer> resultHash = resultConsumers.getConsumers();

                    //Iterate and compare manually expected values from function values
                    int number = instance.getConsumersCount(xml);
                    Consumer expResult;
                    Consumer result;

                    for(int i=0 ; i < number ; i++){
                        expResult = expResultHash.get(Config.consumerPrefix+ i);
                        result = (Consumer)resultHash.get(Config.consumerPrefix+ instance.getConsumerAt(xml, i+1).getId());
                        assertEquals(expResult.toString(), result.toString());
                    }
                }else {
                    fail("The path is not valid.");
                }
            } catch (ParserConfigurationException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                fail("Exception found.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            fail("Exception found.");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of writeTimelineToXML method, of class XMLHelper. This test also verifies that IDs are generated and retrieved correctly
     */
    /*TODO add consumers to the timeline*/
    public void testWriteTimelineToXML() throws Exception {
        System.out.println("writeTimelineToXML");

        XMLHelper instance = new XMLHelper();
        Timeline timeline = new Timeline(10,30);
        timeline.display();
        String expResults = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                            "<Intervals><Interval Number=\"0\"><Start>0</Start><End>3</End>" +
                            "</Interval><Interval Number=\"1\"><Start>3</Start><End>6</End>" +
                            "</Interval><Interval Number=\"2\"><Start>6</Start><End>9</End>" +
                            "</Interval><Interval Number=\"3\"><Start>9</Start><End>12</End>" +
                            "</Interval><Interval Number=\"4\"><Start>12</Start><End>15</End>" +
                            "</Interval><Interval Number=\"5\"><Start>15</Start><End>18</End>" +
                            "</Interval><Interval Number=\"6\"><Start>18</Start><End>21</End>" +
                            "</Interval><Interval Number=\"7\"><Start>21</Start><End>24</End>" +
                            "</Interval><Interval Number=\"8\"><Start>24</Start><End>27</End>" +
                            "</Interval><Interval Number=\"9\"><Start>27</Start><End>30</End></Interval></Intervals>";
        String result = instance.writeTimelineToXML(timeline);
        assertEquals(expResults, result);
    }

     /**
     * Test of testGetEndTimeXML method, of class XMLHelper. This test also verifies that IDs are generated and retrieved correctly
     */
    /*TODO add consumers to the timeline*/
    public void testGetEndTimeXML() throws Exception {
        System.out.println("getEndTimeXML");

        XMLHelper instance = new XMLHelper();
        Timeline timeline = new Timeline(10,30);
        timeline.display();
        String expResults = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><endTime>2</endTime>";
        String result = instance.getEndTimeXML(2);
        assertEquals(expResults, result);
    }
}
