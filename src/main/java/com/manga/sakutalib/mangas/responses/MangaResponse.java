package com.manga.sakutalib.mangas.responses;

import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;

public class MangaResponse {
    public final String mangaName;
    public final String pathName;
    public final MangaAuthorResponse author;

    public MangaResponse(String mangaName, String pathName, MangaAuthorResponse author) {
        this.mangaName = mangaName;
        this.pathName = pathName;
        this.author = author;
    }
}
