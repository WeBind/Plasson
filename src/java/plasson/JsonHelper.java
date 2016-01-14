/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Nicolas
 */
public class JsonHelper {

    public JSONObject getJson(Consumer consumer){
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "config");
            obj.put("startingTime", consumer.getStartingTime());
            obj.put("size", consumer.getSize());
            obj.put("frequency", consumer.getFrequency());
            obj.put("duration", consumer.getDuration());
            obj.put("provider", consumer.getProvider());
        } catch (JSONException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;  
    }

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

    public JSONObject getGoJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("type", "go");
        } catch (JSONException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }


}
