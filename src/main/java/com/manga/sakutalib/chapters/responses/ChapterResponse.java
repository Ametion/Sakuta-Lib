package com.manga.sakutalib.chapters.responses;

import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;

public class ChapterResponse {
    public final Long chapterId;
    public final String chapterName;
    public final Long chapterNumber;
    public final String chapterAddedDate;
    public final Integer chapterVolume;
    public final Integer chapterPagesAmount;
    public final MangaAuthorResponse chapterAuthor;

    public ChapterResponse(Long chapterId, String chapterName, Long chapterNumber, String chapterAddedDate, Integer chapterVolume, Integer chapterPagesAmount, MangaAuthorResponse chapterAuthor) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.chapterAddedDate = chapterAddedDate;
        this.chapterVolume = chapterVolume;
        this.chapterPagesAmount = chapterPagesAmount;
        this.chapterAuthor = chapterAuthor;
    }
}