package com.manga.sakutalib.accounts.exceptions;

public class NoUserFoundException extends Throwable{
    private static final long serialVersionUID = -2000000000L;

    private final String login;

    public  String GetLogin(){
        return this.login;
    }

    public NoUserFoundException(String message, String login) {
        super(message);
        this.login = login;
    }
}
