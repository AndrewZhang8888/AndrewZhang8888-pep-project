package Main;

import DAO.*;
import Model.*;
import Service.*;
import Controller.*;
import Util.*;

import io.javalin.Javalin;
import io.javalin.http.Context;


/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    //Enable Tests to Run
    public static boolean serviceTests = false;
    public static boolean handlerTests = false;
    public static boolean daoTests = false;
    //Manual Tests
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        //DAO Implementation Manual Tests
        if(serviceTests){
            String large_text = "John Pork was a man born in Ontario Canada. Like some of us, he experienced severe bullying and distress during his high-school years. "+
                                "This made John self conscious about the way he looked, and eventually he went mad. John invested his entire savings in plastic surgery "+
                                "as a chance to fix his ugly looks, however it backfired. Without John's knowledge, the doctors performing the surgery worked for the CIA, "+
                                "and were conducting illegal experiments through plastic surgery on citizens of the USA. Pig DNA was injected through his skin while under "+
                                "anesthesia, and he was dumped 5 days later in a back alley.";
            //AccountService tests
                AccountService acc_serv = new AccountService();
                Account valid_acct = new Account("china4004", "4004" );
                Account valid_result = acc_serv.registerUser(valid_acct);
                    // Alternative invalid account condition errors
                        // Account invalid_acct = new Account("china4004", "oiweyioqwiey" );
                        Account invalid_acct = new Account("", "oiweyioqwiey" );
                        // Account invalid_acct = new Account("china4005", "o" );
                Account invalid_result = acc_serv.registerUser(invalid_acct);
                //Expect valid_result to contain a fully defined message and invalid_result to contain null
                System.out.println(valid_result);
                System.out.println(invalid_result);                
                //Expect the login method to return a message with valid_acct attributes and auto-generated account id
                System.out.println(acc_serv.loginUser(valid_acct));
                //Reset Test Database
                ConnectionUtil.resetTestDatabase();

            //MessageDAO tests
                MessageService msg_serv = new MessageService();
                Message validMsg = new Message(1, "Do you accept chong pork pay?", 13756890);
                Message validMsgR = msg_serv.createMessage(validMsg);
                // Alternative invalid account condition errors
                    // Message invalidMsg = new Message(1000, "Skibidi Dob Dob Yes Yes", 13756890);
                    // Message invalidMsg = new Message(1, "", 13756890);
                    Message invalidMsg = new Message(1, large_text, 13756890);
                Message invalidMsgR = msg_serv.createMessage(invalidMsg);
                //Expect the valid message to output a fully defined message with 4 attributes
                System.out.println(validMsgR);
                //Expect the invalid message to output a null value
                System.out.println(invalidMsgR);
                //Expect two messages to be printed using the getAllNessages() method
                System.out.println(msg_serv.getAllMessages());
                //After we delete message id=1, we expect only the record with "chong pork pay" to remain
                msg_serv.deleteMessage(1);
                System.out.println(msg_serv.getAllMessages());
                //After we update message id=2, we should see the message "Chong pork pay accepted!"
                msg_serv.updateMessage(2, "Chong pork pay is accepted!");
                System.out.println(msg_serv.getAllMessages());  
        }

        //DAO Implementation Manual Tests
        if(daoTests){
            //AccountDAO tests
                AccountDAO acc_dao = new AccountDAO();
                Account acct = new Account("china4004", "4004" );
                //Expect non-null fully returned account 
                Account ret_acct = acc_dao.registerNewAccount(acct);
                System.out.println(ret_acct);                
                //Expect true return value 
                System.out.println(acc_dao.checkUserExists(acct));
                //Expect user and pass defined in acct along with account_id = 2
                System.out.println(acc_dao.checkAccountCredentials(acct));
                //Expect true return value (id=2)
                System.out.println(acc_dao.checkAccountExistsByID(2));
                //Reset Database
                ConnectionUtil.resetTestDatabase();
                //Test Database Reset (expect false value)
                System.out.println(acc_dao.checkUserExists(acct));
            
            //MessageDAO tests
                MessageDAO msg_dao = new MessageDAO();
                Message msg = new Message(1, "Skibidi Dob Dob Yes Yes", 13756890);
                msg_dao.createMessage(msg.getPosted_by(), msg.getMessage_text(), msg.getTime_posted_epoch());
                //Expect the message to output "Skibidi Dob Dob Yes Yes"
                System.out.println(msg_dao.getMessageByID(2).getMessage_text());
                //Expect there to be two messages posted by user_id 1
                System.out.println(msg_dao.getMessagesByUserID(1));
                //Update the message two text attribute to "John Pork"
                msg_dao.updateMessageByID(2, "John Pork");
                System.out.println(msg_dao.getMessageByID(2).getMessage_text());
                //Delete Message two
                msg_dao.deleteMessageByID(2);
                //Get all messages should return only one message
                System.out.println(msg_dao.getAllMessages());
                //Reset Database
                ConnectionUtil.resetTestDatabase();                
        }

        //Handler Implementation Manual Tests
        if(handlerTests){
            Context ctx=null;
                //Handler 1 
                    try {
                        controller.registerUserHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_1_Fail");
                    }
                //Handler 2 
                    try {
                        controller.loginUserHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_2_Fail");
                    }
                //Handler 3 
                    try {
                        controller.createMessageHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_3_Fail");
                    }
                //Handler 4 
                    try {
                        controller.getAllMessagesHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_4_Fail");
                    }
                //Handler 5 
                    try {
                        controller.getMessageByIdHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_5_Fail");
                    }
                //Handler 6 
                    try {
                        controller.deleteMessageByIdHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_6_Fail");
                    }
                //Handler 7 
                    try {
                        controller.updateMessageByIdHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_7_Fail");
                    }
                //Handler 8 
                    try {
                        controller.getMessagesByAccountIdHandler(ctx);
                    } catch (Exception e) {
                        System.out.println("Handler_8_Fail");
                    }
            }
    }
}
