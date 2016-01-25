/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.List;

/**
 *
 * @author Nicolas
 *
 * Results sent by consumers
 *
 */
public class Results {

    // COnsumer's id
    private String id;
    // list of the sent messages
    private List<Message> sent;
    // list of the received messages
    private List<Message> received;

    public Results(String id, List<Message> sent, List<Message> received) {
        this.id = id;
        this.sent = sent;
        this.received = received;
    }

    public Results() {

    }

    @Override
    public String toString(){
        String sentMessages= "";
        String receivedMessages="";
        for(int i=0; i<sent.size();i++){
            sentMessages += " " + sent.get(i).toString();
        }
        for(int j=0; j<received.size();j++){
            receivedMessages += " " + received.get(j).toString();
        }
        return id + " " + sentMessages + " " + receivedMessages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Message> getReceived() {
        return received;
    }

    public void setReceived(List<Message> received) {
        this.received = received;
    }

    public List<Message> getSent() {
        return sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }


    public Message getReceivedById(String messageID){

        boolean hasReceived = false;
        Message receivedMessage = null;
        int i = 0;
        while(!hasReceived && i<getReceived().size()){
            if(getReceived().get(i).getId().equals(messageID)&&getReceived().get(i).getTime()!=-1){
                hasReceived = true;
                receivedMessage = getReceived().get(i);
            }
            i++;
        }

        return receivedMessage;
    }




}
