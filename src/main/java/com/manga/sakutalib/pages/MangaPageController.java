package com.manga.sakutalib.pages;

import com.manga.sakutalib.pages.requests.UploadMangaPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

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

    @GetMapping("/read/{mangaName}/{volume}/{chapter}/{page}")
    public ResponseEntity getImage(@PathVariable String mangaName, @PathVariable String volume, @PathVariable String chapter, @PathVariable String page) {
        try{
            var file = new File(System.getProperty("user.home") + "/Desktop/sakuta_lib/" + mangaName + "/" + volume
                    + "/" + chapter + "/" + page);
            var imageBytes = Files.readAllBytes(file.toPath());

            var headers = new HttpHeaders();

            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imageBytes.length);

            return new ResponseEntity(imageBytes, headers, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/comments")
    public ResponseEntity GetAllPageCommentByPath(@RequestParam String path){
        try{
            return ResponseEntity.ok(mangaPageService.GetAllPageCommentByPath(path));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}