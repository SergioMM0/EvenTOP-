package bll.login;

import be.User;
import be.UserType;

public interface LoginFacade {

    User checkCredentials(String email, String password);

}
