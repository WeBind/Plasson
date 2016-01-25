/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceContext;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 * REST Web Service
 *
 * @author Nicolas
 */

@Path("/")
public class GenericResource {

    @Context
    private ServletContext sc;

    @Context
    private UriInfo context;

    /** Creates a new instance of GenericResource */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of plasson.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {

        String results = null;
        try {
            ControllerGet c = new ControllerGet(sc);
            results = c.listenToResults();

        } catch (EmptyContextException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

            return results;

    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/xml")
    public void postXml(String content) throws InterruptedException {
        try {
            /*try {
            Controller controller = new Controller("plassonOrder", "all", "callback_queue");
            controller.fillModel(content);
            controller.deployScenario();
            Thread.sleep(5000);
            controller.startScenario();
            } catch (ParserConfigurationException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            System.out.println("[REST API] POST: " + content);
            ControllerPost c = new ControllerPost(sc);
            c.fillModel(content);
            c.saveContext();
            c.deployScenario();
            Thread.sleep(5000);
            c.startScenario();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
