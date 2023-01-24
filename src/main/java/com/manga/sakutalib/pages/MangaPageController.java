package com.manga.sakutalib.pages;

import com.manga.sakutalib.mangas.requests.UploadMangaPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MangaPageController {
    private final MangaPageService mangaPageService;

    @Autowired
    public MangaPageController(MangaPageService mangaPageService) {
        this.mangaPageService = mangaPageService;
    }

    @PostMapping("/uploadPage")
    public ResponseEntity UploadMangaPage(@RequestParam("file") MultipartFile file, @RequestParam Long volumeId,
                                      @RequestParam Long chapterId, @RequestParam Long mangaId, @RequestParam Integer pageNumber) {
        try {
            var request = new UploadMangaPageRequest();
            request.file = file;
            request.mangaId = mangaId;
            request.volumeId = volumeId;
            request.chapterId = chapterId;
            request.pageNumber = pageNumber;

            return ResponseEntity.ok(mangaPageService.UploadMangaPage(request));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
