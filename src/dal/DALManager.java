package dal;

import be.User;
import bll.exceptions.LoginEX;
import dal.DAO.DAOLogin;

public class DALManager implements DALFacade{

    DAOLogin daoLogin;

    public DALManager(){
        daoLogin = new DAOLogin();
    }

    @Override
    public User checkCredentials(String email, String password) throws LoginEX {
        return daoLogin.checkCredentials(email,password);
    }

}
