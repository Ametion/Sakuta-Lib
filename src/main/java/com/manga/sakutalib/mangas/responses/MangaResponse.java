package com.manga.sakutalib.mangas.responses;

import com.manga.sakutalib.genres.responses.GenreResponse;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;

import java.util.List;

public class MangaResponse {
    public final Long mangaId;
    public final String mangaName;
    public final String mangaPathName;
    public final String mangaDescription;
    public final MangaAuthorResponse author;
    public final List<GenreResponse> mangaGenres;

    public MangaResponse(Long mangaId, String mangaName, String pathName, String mangaDescription, MangaAuthorResponse author, List<GenreResponse> mangaGenres) {
        this.mangaId = mangaId;
        this.mangaName = mangaName;
        this.mangaPathName = pathName;
        this.mangaDescription = mangaDescription;
        this.author = author;
        this.mangaGenres = mangaGenres;
    }
}
