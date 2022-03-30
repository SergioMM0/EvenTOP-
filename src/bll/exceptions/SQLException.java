package bll.exceptions;

public class SQLException extends Throwable{

    private String message;

    public SQLException(String message, Exception ex){
        this.message = message;
    }

    public String getExceptionMessage(){
        return this.message;
    }


}
