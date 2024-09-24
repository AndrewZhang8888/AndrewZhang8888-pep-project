import Controller.SocialMediaController;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static boolean handler_tests = true;
    int n = SocialMediaController.num;
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        //Handler Implementation Manual Tests
        if(SocialMediaController.test==true){
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
