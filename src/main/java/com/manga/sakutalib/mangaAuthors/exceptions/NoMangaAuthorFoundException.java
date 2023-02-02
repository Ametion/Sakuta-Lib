package com.manga.sakutalib.mangaAuthors.exceptions;

public class NoMangaAuthorFoundException extends Throwable{
    private final String mangaAuthorName;

    public String getMangaAuthorName() {
        return mangaAuthorName;
    }

    public NoMangaAuthorFoundException(String message, String mangaAuthorName) {
        super(message);
        this.mangaAuthorName = mangaAuthorName;
    }
}
