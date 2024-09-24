package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;


public class AccountDAO{

    public Account registerNewAccount(Account acct){
        Connection conn = ConnectionUtil.getConnection();
        if (checkAccountExists(acct)==false){
            try {
                String sql = "INSERT INTO account (username,password) VALUES (?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, acct.getUsername());
                ps.setString(2, acct.getPassword());
                ps.executeUpdate();
                ResultSet pkeys = ps.getGeneratedKeys();
                if(pkeys.next()){
                    int generated_account_id = (int) pkeys.getInt(1);
                    return new Account(generated_account_id,acct.getUsername(),acct.getPassword());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public boolean checkAccountExists(Account acct){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM account WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, acct.getUsername());
            ps.setString(2, acct.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}