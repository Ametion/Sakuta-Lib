package com.manga.sakutalib.mangaAuthors.responses;

public class MangaAuthorResponse {
    public final Long authorId;
    public final String authorFirstName;
    public final String authorSecondName;

    public MangaAuthorResponse(Long authorId, String firstName, String secondName) {
        this.authorId = authorId;
        this.authorFirstName = firstName;
        this.authorSecondName = secondName;
    }
}
