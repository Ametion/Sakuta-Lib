package com.manga.sakutalib.mangas.requests;

import org.springframework.web.multipart.MultipartFile;

public class UploadMangaPageRequest {
    public MultipartFile file;
    public Long volumeId;
    public Long chapterId;
    public Long mangaId;
    public Integer pageNumber;
}
