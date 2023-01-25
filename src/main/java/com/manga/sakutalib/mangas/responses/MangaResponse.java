package com.manga.sakutalib.mangas.responses;

import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;

public class MangaResponse {
    public final Long mangaId;
    public final String mangaName;
    public final String mangaPathName;
    public final String mangaDescription;
    public final MangaAuthorResponse author;

    public MangaResponse(Long mangaId, String mangaName, String pathName, String mangaDescription, MangaAuthorResponse author) {
        this.mangaId = mangaId;
        this.mangaName = mangaName;
        this.mangaPathName = pathName;
        this.mangaDescription = mangaDescription;
        this.author = author;
    }
}
