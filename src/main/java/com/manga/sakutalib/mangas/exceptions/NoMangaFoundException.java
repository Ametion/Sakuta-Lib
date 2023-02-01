package com.manga.sakutalib.mangas.exceptions;

public class NoMangaFoundException extends Throwable{
    private static final long serialVersionUID = -2000000000L;

    private final Long mangaId;

    public Long GetMangaId(){
        return this.mangaId;
    }

    public NoMangaFoundException(String message, Long mangaId) {
        super(message);
        this.mangaId = mangaId;
    }
}
