package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    public static boolean test = true;

    //User Story 3 method
    public Message createMessage(int posted_by, String message_text, long time_posted_epoch){
        Connection conn = ConnectionUtil.getConnection();
        //AccountDAO method access
        AccountDAO accDao = new AccountDAO();
        if(message_text.length()<=255 && accDao.checkAccountExistsByID(posted_by)){
            try{
                String sql = "INSERT INTO message (posted_by,message_text,time_posted_epoch) VALUES (?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, posted_by);
                ps.setString(2, message_text);
                ps.setLong(1, time_posted_epoch);
                ps.executeUpdate();
                ResultSet pkeys = ps.getGeneratedKeys();
                pkeys.next();
                int generated_account_id = (int) pkeys.getInt(1);
                return new Message(generated_account_id, posted_by, message_text, time_posted_epoch);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    //User Story 5 method
    public Message getMessageByID(int id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM message WHERE message_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String msg_text = rs.getString("message_text");
                long msg_time = rs.getLong("time_posted_epoch");
                int msg_poster = rs.getInt("posted_by");
                int msg_id = rs.getInt("message_id");
                return new Message(msg_id, msg_poster, msg_text, msg_time);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;    }

    //User Story 6 method
    public void deleteMessageByID(int id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //User Story 7 method
    public Message updateMessageByID(int id, String text){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "UPDATE message SET message_text=?, WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, text);  
            ps.setInt(2, id);
            ps.executeUpdate();
            Message new_msg = getMessageByID(id);
            return new_msg;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //User Story 8 method
    public List<Message> getMessagesByUserID(int id){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM message WHERE posted_by=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String msg_text = rs.getString("message_text");
                long msg_time = rs.getLong("time_posted_epoch");
                int msg_poster = rs.getInt("posted_by");
                int msg_id = rs.getInt("message_id");
                messages.add(new Message(msg_id, msg_poster, msg_text, msg_time));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    //User Story 4 method
    public List<Message> getAllMessages(){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM message";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String msg_text = rs.getString("message_text");
                long msg_time = rs.getLong("time_posted_epoch");
                int msg_poster = rs.getInt("posted_by");
                int msg_id = rs.getInt("message_id");
                messages.add(new Message(msg_id, msg_poster, msg_text, msg_time));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
