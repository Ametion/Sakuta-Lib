package com.manga.sakutalib.pages.exceptions;

public class NoFoundMangaPageException extends Throwable {
    private static final long serialVersionUID = -2000000000L;

    private final String path;

    public String getPath() {
        return path;
    }

    public NoFoundMangaPageException(String message, String path) {
        super(message);
        this.path = path;
    }
}
