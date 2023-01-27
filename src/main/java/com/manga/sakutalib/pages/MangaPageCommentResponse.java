package com.manga.sakutalib.pages;

public class MangaPageCommentResponse {
    public final Long commentId;
    public final String commentContent;
    public final String commentAuthorLogin;

    public MangaPageCommentResponse(Long commentId, String commentContent, String commentAuthorLogin) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentAuthorLogin = commentAuthorLogin;
    }
}