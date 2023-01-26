package com.manga.sakutalib.genres.responses;

public class GenreResponse {
    public final Long genreId;
    public final String genre;

    public GenreResponse(Long genreId, String genre) {
        this.genreId = genreId;
        this.genre = genre;
    }
}
