package dal;

import be.User;
import dal.DAO.DAOLogin;

public class DALManager implements DALFacade{

    DAOLogin daoLogin;

    public DALManager(){
        daoLogin = new DAOLogin();
    }

    @Override
    public User checkCredentials(String email, String password) {
        return daoLogin.checkCredentials(email,password);
    }

    public Exception loginFailed(Exception ex){
        return ex;
    }

}
