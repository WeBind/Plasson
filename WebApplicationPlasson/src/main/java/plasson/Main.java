/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Nicolas
 */
public class Main {

   /*public static void main(String[] args) throws InterruptedException {
        try {
            String xmlPath = "C:\\Users\\Adirael\\Documents\\NetBeansProjects\\WebApplicationPlasson\\src\\main\\java\\plasson\\XMLTest.xml";
            String xsdPath = "C:\\Users\\Adirael\\Documents\\NetBeansProjects\\WebApplicationPlasson\\src\\main\\java\\plasson\\XSDModel.xsd";
            File xmlFile = new File(xmlPath);
            String xml = new Scanner(xmlFile).useDelimiter("\\Z").next();
            System.out.println(xml);
            XMLHelper xmlTester = new XMLHelper();
            try {
                if (xmlTester.validateXMLString(xsdPath, xml)) {
                    System.out.println("VALIDE!!!!! :DDDDD");
                    System.out.println("The number of providers is: " + xmlTester.getProvidersCount(xml));
                    System.out.println("The number of consumers is: " + xmlTester.getConsumersCount(xml));
//                    Consumer consumer1 = xmlTester.getConsumerAt(xmlPath, 1);
//                    Consumer consumer2 = xmlTester.getConsumerAt(xmlPath, 2);
//                    System.out.println("Consumer1="+consumer1.toString());
//                    System.out.println("Consumer2="+consumer2.toString());
//                    Provider provider1 = xmlTester.getProviderAt("C:\\Users\\Nicolas\\Documents\\NetBeansProjects\\WebApplicationPlasson\\src\\java\\plasson\\XMLTest.xml", 1);
//                   Provider provider2 = xmlTester.getProviderAt("C:\\Users\\Nicolas\\Documents\\NetBeansProjects\\WebApplicationPlasson\\src\\java\\plasson\\XMLTest.xml", 2);
//                    System.out.println("Provider1="+provider1.toString());
//                    System.out.println("Provider2="+provider2.toString());
 //                   System.out.println("Consumers=" + xmlTester.getConsumers(xml));
 //                   System.out.println("Providers=" + xmlTester.getProviders(xml));
 //                   Controller c = new Controller("ex1", "all", "callback_queue");
 //                   c.fillModel(xml);
 //                   c.deployScenario();
 //                   Thread.sleep(5000);

 //                    c.startScenario();
                } else {
                    System.out.println("PAS VALIDE¡¡¡¡ DDDDDDDD:");
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//*/

}
