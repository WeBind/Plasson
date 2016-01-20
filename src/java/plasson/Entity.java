/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

/**
 *
 * @author Nicolas
 */
public class Entity {

    private int id;
    private Results results;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }



    public Message getReceivedById(String messageID){

        boolean hasReceived = false;
        Message receivedMessage = null;
        int i = 0;
        while(!hasReceived && i<this.getResults().getReceived().size()){
            if(this.getResults().getReceived().get(i).getId().equals(messageID)){
                hasReceived = true;
                receivedMessage = this.getResults().getReceived().get(i);
            }
            i++;
        }

        return receivedMessage;
    }

}
