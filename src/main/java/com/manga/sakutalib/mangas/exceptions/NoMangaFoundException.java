package com.manga.sakutalib.mangas.exceptions;

public class NoMangaFoundException extends Throwable{
    private static final long serialVersionUID = -2000000000L;

    private final Long mangaId;
    private final String mangaName;

    public String getMangaName() {
        return mangaName;
    }

    public Long GetMangaId(){
        return this.mangaId;
    }

    public NoMangaFoundException(String message, Long mangaId) {
        super(message);
        this.mangaId = mangaId;
        this.mangaName = null;
    }

    public NoMangaFoundException(String message, String mangaName) {
        super(message);
        this.mangaName = mangaName;
        this.mangaId = null;
    }
}
