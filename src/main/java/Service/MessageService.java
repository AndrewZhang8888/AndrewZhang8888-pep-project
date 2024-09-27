package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;


public class MessageService {
    private MessageDAO msg_dao;
    private AccountDAO acc_dao;

    //no-args service constructor to utilize DAO instances for database access
    public MessageService(){
        msg_dao = new MessageDAO();
        acc_dao = new AccountDAO();
    }

    //User Story 3 Service Method 
    public Message createMessage(Message msg){
        if(msg.getMessage_text().length()<=255 && !msg.getMessage_text().isEmpty() && acc_dao.checkAccountExistsByID(msg.getPosted_by())){
            Message msg_ret = msg_dao.createMessage(msg.getPosted_by(), msg.getMessage_text(), msg.getTime_posted_epoch());
            return msg_ret;
        }
        return null;
    }

    //User Story 4 Service Method 
    public List<Message> getAllMessages(){
        return msg_dao.getAllMessages();
    }

    //User Story 5 Service Method  
    public Message getMessage(int id){
        return msg_dao.getMessageByID(id);
    }

    //User Story 6 Service Method  
    public Message deleteMessage(int id){
        if (getMessage(id)!=null){
            return msg_dao.deleteMessageByID(id);
        }
        return null;
    }

    //User Story 7 Service Method 
    public Message updateMessage(int id, String msg_text){
        if (msg_text.length()<=255 && !msg_text.isEmpty() && getMessage(id)!=null){
            return msg_dao.updateMessageByID(id, msg_text);
        }
        return null;
    }

    //User story 8 Service Method 
    public List<Message> getAllMessagesByUserId(int id){
        return msg_dao.getAllMessages();
    }
}
