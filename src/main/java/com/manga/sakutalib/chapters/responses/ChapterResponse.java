package com.manga.sakutalib.chapters.responses;

import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;

public class ChapterResponse {
    public final Long chapterId;
    public final String chapterName;
    public final Long chapterNumber;
    public final Integer chapterVolume;
    public final MangaAuthorResponse chapterAuthor;

    public ChapterResponse(Long chapterId, String chapterName, Long chapterNumber, Integer chapterVolume, MangaAuthorResponse chapterAuthor) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.chapterVolume = chapterVolume;
        this.chapterAuthor = chapterAuthor;
    }
}