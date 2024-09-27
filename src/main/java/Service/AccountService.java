package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    //no-args service constructor to utilize a DAO instance for database access
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    //User Story 1 
    public Account registerUser(Account acct){
        if (!accountDAO.checkAccountExists(acct) && !acct.getUsername().isEmpty() && acct.getPassword().length()>=4){
            accountDAO.registerNewAccount(acct);
        }
        return null;
    }

    //User Story 2
    public Account loginUser(Account acct){
        if(accountDAO.checkAccountCredentials(acct)){
            return acct;
        }
        return null;
    }
}
