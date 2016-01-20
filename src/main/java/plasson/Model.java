/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.HashMap;


/**
 *
 * @author Nicolas
 *
 * Singleton which stores the providers and consumers lists
 * It also stores the scenario duration inorder to set the timeout
 *
 */
public final class Model {

     private static volatile Model instance = null;

     private HashMap<String,Provider> providers;
     private HashMap<String,Consumer> consumers;
     private int scenarioEndTime;

    
     private Model() {
         
         super();
         providers = new HashMap<String,Provider>();
         consumers = new HashMap<String,Consumer>();
     }
     public final static Model getInstance() {

         if (Model.instance == null) {

            synchronized(Model.class) {
              if (Model.instance == null) {
                Model.instance = new Model();
              }
            }
         }
         return Model.instance;
     }

    public HashMap<String, Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(HashMap<String, Consumer> consumers) {
        this.consumers = consumers;
    }

    public HashMap<String, Provider> getProviders() {
        return providers;
    }

    public void setProviders(HashMap<String, Provider> providers) {
        this.providers = providers;
    }

    public int getScenarioEndTime() {
        return scenarioEndTime;
    }

    public void setScenarioEndTime(int scenarioEndTime) {
        this.scenarioEndTime = scenarioEndTime;
    }

    

}
