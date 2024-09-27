package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    //no-args service constructor to utilize a DAO instance for database access
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    //User Story 1 Service Method
    public Account registerUser(Account acct){
        if (!accountDAO.checkUserExists(acct) && !acct.getUsername().isEmpty() && acct.getPassword().length()>=4){
            Account acc_ret = accountDAO.registerNewAccount(acct);
            return acc_ret;
        }
        return null;
    }

    //User Story 2 Service Method
    public Account loginUser(Account acct){
        return accountDAO.checkAccountCredentials(acct);
    }
}
