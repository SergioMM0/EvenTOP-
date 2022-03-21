package dal;

import be.User;
import bll.exceptions.LoginEX;

public interface DALFacade {

    User checkCredentials(String email,String password) throws LoginEX;

}
