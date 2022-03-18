package bll.login;

import be.User;

public interface LoginFacade {

    User checkCredentials(String email, String password);

}
