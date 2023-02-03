package com.manga.sakutalib.pages;

import com.manga.sakutalib.pages.exceptions.NoFoundMangaPageException;
import com.manga.sakutalib.pages.requests.GetMangaPageRequest;
import com.manga.sakutalib.pages.requests.UploadMangaPageRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@RestController
public class MangaPageController {
    private static final Logger LOGGER = LogManager.getLogger(MangaPageController.class);

    private final MangaPageService mangaPageService;

    @Autowired
    public MangaPageController(MangaPageService mangaPageService) {
        this.mangaPageService = mangaPageService;
    }

    @PostMapping("/uploadPage")
    public ResponseEntity UploadMangaPage(@RequestParam("file") MultipartFile file, @RequestParam Long volumeId,
                                          @RequestParam Long chapterId, @RequestParam Long mangaId, @RequestParam Integer pageNumber) {
        try {
            UploadMangaPageRequest request = new UploadMangaPageRequest();

            request.file = file;
            request.mangaId = mangaId;
            request.volumeId = volumeId;
            request.chapterId = chapterId;
            request.pageNumber = pageNumber;

            return new ResponseEntity(mangaPageService.UploadMangaPage(request), HttpStatus.CREATED);
        } catch (NoSuchElementException noElement){
            LOGGER.warn("CAN NOT FIND IMPORTANT ELEMENTS TO UPLOAD MANGA PAGE");
            return new ResponseEntity("Can not find element with presented id's", HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            LOGGER.error("ERROR WHILE UPLOAD MANGA PAGE: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/read/{mangaName}/{volume}/{chapter}/{page}")
    public ResponseEntity GetMangaPage(@PathVariable String mangaName, @PathVariable String volume, @PathVariable String chapter, @PathVariable String page) {
        try{
            GetMangaPageRequest request = new GetMangaPageRequest();

            request.mangaName = mangaName;
            request.volume = volume;
            request.chapter = chapter;
            request.page = page;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity(mangaPageService.GetMangaPage(request), headers, HttpStatus.OK);
        } catch (NoFoundMangaPageException noPage) {
            LOGGER.warn("CAN NOT FIND MANGA PAGE BY PRESENTED PATH");
            return new ResponseEntity(noPage.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception ex){
            LOGGER.error("ERROR WHILE GETTING MANGA PAGE: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity GetAllPageCommentByPath(@RequestParam String path){
        try{
            return new ResponseEntity(mangaPageService.GetAllPageCommentByPath(path), HttpStatus.OK);
        } catch (NoFoundMangaPageException noPage) {
            LOGGER.warn("CAN NOT FIND MANGA PAGE BY PRESENTED PATH: " + path);
            return new ResponseEntity(noPage.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception ex){
            LOGGER.error("ERROR WHILE GETTING MANGA PAGE COMMENTS: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}