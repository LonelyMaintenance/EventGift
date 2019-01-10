/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author meda
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/RegQueue")
})
public class RegMessage implements MessageListener {
    
    public RegMessage() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        String smessage="";
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                smessage = msg.getText();
                System.out.println("\n******* RegMessage-MDB******** Message from RegQueue: Length = " + smessage.length() + "\tcontent = ******** " + smessage + "******");  
            } else {
               System.err.println("RegMessage-MDB!!!!!! Message of wrong type: " +  message.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Throwable te) {
            te.printStackTrace();
        }        
    }
    
}
