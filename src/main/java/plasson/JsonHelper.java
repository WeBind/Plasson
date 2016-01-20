/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.google.gson.Gson;


/**
 *
 * @author Nicolas
 *
 * Helps to create configuration messages to send to the consumers and providers
 * Creates a Results object from received results
 */
public class JsonHelper {

    // Creates JsonObject from a consumer
    public JSONObject getJson(Consumer consumer){
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "config");
            obj.put("startingTime", consumer.getStartingTime());
            obj.put("size", consumer.getSize());
            obj.put("period", consumer.getPeriod());
            obj.put("duration", consumer.getDuration());
            obj.put("provider", Config.providerPrefix+consumer.getProvider());

        } catch (JSONException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;  
    }

    // Creates JsonObject from a provider
    public JSONObject getJson(Provider provider){
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "config");
            obj.put("responseLength", provider.getResponseLength());
            obj.put("responseTime", provider.getResponseTime());
        } catch (JSONException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    // Return the "go" message to start a scenario
    public JSONObject getGoJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "go");
        } catch (JSONException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }


    // From received Results, creates a results objets
    public Results getResults(String receivedResults){
        
        Gson g = new Gson();

        Results results = g.fromJson(receivedResults, Results.class);

        return results;
    }


}
