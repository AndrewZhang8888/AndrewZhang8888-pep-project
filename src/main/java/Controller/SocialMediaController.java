package Controller;

import Main.Main;
import Model.*;
import Service.*;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    private AccountService acct_serv;
    private MessageService msg_serv;

    public SocialMediaController(){
        this.acct_serv = new AccountService();
        this.msg_serv = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerUserHandler);
        app.post("/login", this::loginUserHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountIdHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    //Determine whether handlers should have test implementation
    boolean test = Main.handlerTests;
    //Handler method implementation
    public void registerUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account acct = om.readValue(ctx.body(), Account.class);
        Account registeredUser = acct_serv.registerUser(acct);
        if(registeredUser!=null){
            ctx.json(om.writeValueAsString(registeredUser));
        } else{
            ctx.status(400);
        }
        //Test if handler testing activated 
        if (test==true){
            System.out.println("Handler_1_Success");
        }
    }

    public void loginUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account acct = om.readValue(ctx.body(), Account.class);
        Account loggedInUser = acct_serv.loginUser(acct);
        if(loggedInUser!=null){
            ctx.json(om.writeValueAsString(loggedInUser));
        } else{
            ctx.status(401);
        }
        //Test if handler testing activated 
        if (test==true){
            System.out.println("Handler_2_Success");
        }
    }

    public void createMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message msg = om.readValue(ctx.body(), Message.class);
        Message createdMessage = msg_serv.createMessage(msg);
        if(createdMessage!=null){
            ctx.json(om.writeValueAsString(createdMessage));
        } else{
            ctx.status(400);
        }
        //Test if handler testing activated 
        if (test==true){
            System.out.println("Handler_3_Success");
        }
    }

    public void getAllMessagesHandler(Context ctx) {
        List<Message> allMsgs= msg_serv.getAllMessages();
        ctx.json(allMsgs);
        //Test if handler testing activated     
        if (test==true){
            System.out.println("Handler_4_Success");
        }
    }

    public void getMessageByIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        String msgIdString = ctx.pathParam("message_id");
        int msg_id = Integer.parseInt(msgIdString);
        Message gottenMessage = msg_serv.getMessage(msg_id);
        if(gottenMessage!=null){
            ctx.json(om.writeValueAsString(gottenMessage));
        } else{
            ctx.json("");
        }
        //Test if handler testing activated     
        if (test==true){
            System.out.println("Handler_5_Success");
        }
    }

    public void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        String msgIdString = ctx.pathParam("message_id");
        int msg_id = Integer.parseInt(msgIdString);
        Message deletedMessage = msg_serv.deleteMessage(msg_id);
        if(deletedMessage!=null){
            ctx.json(om.writeValueAsString(deletedMessage));
        } else{
            ctx.json("");
        }
        //Test if handler testing activated        
        if (test==true){
            System.out.println("Handler_6_Success");
        }
    }

    public void updateMessageByIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String msgIdString = ctx.pathParam("message_id");
        Message msg = om.readValue(ctx.body(), Message.class);
        int msg_id = Integer.parseInt(msgIdString);
        Message updatedMessage = msg_serv.updateMessage(msg_id, msg.getMessage_text());
        if(updatedMessage!=null){
            ctx.json(om.writeValueAsString(updatedMessage));
        } else{
            ctx.status(400);
        }
        //Test if handler testing activated        
        if (test==true){
            System.out.println("Handler_7_Success");
        }
    }

    public void getMessagesByAccountIdHandler(Context ctx) {
        String accIdString = ctx.pathParam("account_id");
        int acc_id = Integer.parseInt(accIdString);
        List<Message> gottenMessages = msg_serv.getAllMessagesByUserId(acc_id);
        ctx.json(gottenMessages);
        //Test if handler testing activated  
        if (test==true){
            System.out.println("Handler_8_Success");
        }
    }
}