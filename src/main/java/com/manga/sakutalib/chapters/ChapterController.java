package com.manga.sakutalib.chapters;

import com.manga.sakutalib.chapters.requests.AddChapterRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChapterController {
    private static final Logger LOGGER = LogManager.getLogger(ChapterController.class);

    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/chapters")
    public ResponseEntity GetAllMangaChapters(@RequestParam Long mangaId){
        try{
            return new ResponseEntity(chapterService.GetAllMangaChapters(mangaId), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("ERROR WHILE GETTING CHAPTERS: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/chapter")
    public ResponseEntity AddChapter(@RequestBody AddChapterRequest chapterRequest){
        try{
            return new ResponseEntity(chapterService.AddChapter(chapterRequest), HttpStatus.CREATED);
        } catch (Exception ex) {
            LOGGER.error("ERROR WHILE ADDING CHAPTER: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}