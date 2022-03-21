package bll.exceptions;

public class LoginEX extends Throwable {

    private String message;

    public LoginEX(String message, Exception ex){
        this.message = message;
    }

    public String getExceptionMessage(){
        return this.message;
    }
}
