package com.manga.sakutalib.accounts.exceptions;

public class UserAlreadyExistException extends Throwable{
    private static final long serialVersionUID = -2000000000L;

    private final String login;

    public String GetLogin(){
        return this.login;
    }

    public UserAlreadyExistException(String message, String login) {
        super(message);
        this.login = login;
    }
}
