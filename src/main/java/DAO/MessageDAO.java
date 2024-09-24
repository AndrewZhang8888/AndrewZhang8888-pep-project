package DAO;

import java.util.ArrayList;
import java.util.List;
import Model.Message;

public class MessageDAO {
    public Message createMessage(){
        return new Message();
    }
    public Message getMessageByID(){
        return new Message();
    }
    public void deleteMessageByID(){

    }
    public List<Message> getAllMessages(){
        List<Message> message = new ArrayList<>();
        return message;
    }
}
