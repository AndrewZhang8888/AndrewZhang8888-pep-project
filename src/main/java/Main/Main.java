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
    public static boolean serviceTests = true;
    public static boolean handlerTests = false;
    public static boolean daoTests = true;
    //Manual Tests
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        //DAO Implementation Manual Tests
        if(serviceTests){
            //AccountService tests
                AccountService acc_serv = new AccountService();
                Account valid_acct = new Account("china4004", "4004" );
                Account valid_result = acc_serv.registerUser(valid_acct);
                Account invalid_acct = new Account("china4003", "400" );
                Account invalid_result = acc_serv.registerUser(invalid_acct);
                //Expect valid_result to contain a fully defined message and invalid_result to contain null
                System.out.println(valid_result);
                System.out.println(invalid_result);                
                //Expect the login method to return a message with valid_acct attributes and auto-generated account id
                System.out.println(acc_serv.loginUser(valid_acct));
                //Reset Test Database
                ConnectionUtil.resetTestDatabase();
        }    
        //     //MessageDAO tests
        //         MessageDAO msg_dao = new MessageDAO();
        //         Message msg = new Message(1, "Skibidi Dob Dob Yes Yes", 13756890);
        //         msg_dao.createMessage(msg.getPosted_by(), msg.getMessage_text(), msg.getTime_posted_epoch());
        //         //Expect the message to output "Skibidi Dob Dob Yes Yes"
        //         System.out.println(msg_dao.getMessageByID(2).getMessage_text());
        //         //Expect there to be two messages posted by user_id 1
        //         System.out.println(msg_dao.getMessagesByUserID(1));
        //         //Update the message two text attribute to "John Pork"
        //         msg_dao.updateMessageByID(2, "John Pork");
        //         System.out.println(msg_dao.getMessageByID(2).getMessage_text());
        //         //Delete Message two
        //         msg_dao.deleteMessageByID(2);
        //         //Get all messages should return only one message
        //         System.out.println(msg_dao.getAllMessages());
        //         //Reset Database
        //         ConnectionUtil.resetTestDatabase();                
        // }

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
