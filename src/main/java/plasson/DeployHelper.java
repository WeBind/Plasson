/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nicolas
 *
 * It makes easier the deployments of consumers and providers
 */
public class DeployHelper {
    private final String PROD_DEPLOY_PATH="/opt/Plasson/petals/producers/deploy_producer.sh";
    private final String CONS_DEPLOY_PATH="/opt/Plasson/petals/consumers/deploy_consumer.sh";
    //TODO ajouter la technologie
    public void deployConsumer(String name, String exchangeName, String broadcast, String callback){
        System.out.println("Deploying " + name +  " " + exchangeName + " " + broadcast);
        File f = new File("/tmp/" + name +  ".omg");
        File f2 = new File("/tmp/" + name + ".err.omg");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Process p;
        try {
            p = new ProcessBuilder(CONS_DEPLOY_PATH, name, exchangeName, broadcast).start();
            p.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(DeployHelper.class.getName()).log(Level.SEVERE, null, ex);
        }catch (InterruptedException ex) {
            Logger.getLogger(DeployHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void deployProvider(String name, String exchangeName, String broadcast, String callback){
        System.out.println("Deploying " + name +  " " + exchangeName + " " + broadcast);
        File f = new File("/tmp/" + name +  ".omg");
        File f2 = new File("/tmp/" + name + ".err.omg");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
            Process p;
        try {
            p = new ProcessBuilder(PROD_DEPLOY_PATH, name, exchangeName, broadcast).start();
            p.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(DeployHelper.class.getName()).log(Level.SEVERE, null, ex);
        }catch (InterruptedException ex) {
            Logger.getLogger(DeployHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        
    }

}
