package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
	public static int num = 1;
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
    public static boolean test = true;
    //Handler method implementation
    public void registerUserHandler(Context ctx) throws JsonProcessingException {
        //Test if handler testing activated 
        if (test==true){
            System.out.println("Handler_1_Success");
        }
    }
    public void loginUserHandler(Context ctx) throws JsonProcessingException {
        if (test==true){
            System.out.println("Handler_2_Success");
        }
    }
    public void createMessageHandler(Context ctx) throws JsonProcessingException {
        if (test==true){
            System.out.println("Handler_3_Success");
        }
    }
    public void getAllMessagesHandler(Context ctx) {
        if (test==true){
            System.out.println("Handler_4_Success");
        }
    }
    public void getMessageByIdHandler(Context ctx) {
        if (test==true){
            System.out.println("Handler_5_Success");
        }
    }
    public void deleteMessageByIdHandler(Context ctx) {
        if (test==true){
            System.out.println("Handler_6_Success");
        }
    }
    public void updateMessageByIdHandler(Context ctx) throws JsonProcessingException {
        
        if (test==true){
            System.out.println("Handler_7_Success");
        }
    }
    public void getMessagesByAccountIdHandler(Context ctx) {
        if (test==true){
            System.out.println("Handler_8_Success");
        }
    }
}