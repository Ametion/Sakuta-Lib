package com.manga.sakutalib.mangas.requests;

import org.springframework.web.multipart.MultipartFile;

public class UploadMangaPageRequest {
    public final MultipartFile file;
    public final Long volumeId;
    public final Long chapterId;
    public final Long mangaId;

    public UploadMangaPageRequest(MultipartFile file, Long volumeId, Long chapterId, Long mangaId) {
        this.file = file;
        this.volumeId = volumeId;
        this.chapterId = chapterId;
        this.mangaId = mangaId;
    }
}
