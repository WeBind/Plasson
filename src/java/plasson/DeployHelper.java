/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.File;
import java.io.IOException;

/**
 * @author Nicolas
 */
public class DeployHelper {
    private final String PROD_DEPLOY_PATH="/opt/Plasson/deploy_producer.sh";
    private final String CONS_DEPLOY_PATH="/opt/Plasson/deploy_consumer.sh";
    //TODO ajouter la technologie
    public void deployConsumer(String name, String exchangeName, String broadcast){
//        try {
////            new ProcessBuilder(CONS_DEPLOY_PATH, name, exchangeName, broadcast).start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void deployProvider(String name, String exchangeName, String broadcast){
        System.out.println("Deploying " + name +  " " + exchangeName + " " + broadcast);
        File f = new File("/tmp/" + name +  ".omg");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Process p = new ProcessBuilder(PROD_DEPLOY_PATH, name, exchangeName, broadcast).redirectErrorStream(true).redirectOutput(f).start();
            p.waitFor();
//            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
