package com.manga.sakutalib.chapters;

import com.manga.sakutalib.chapters.requests.AddChapterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChapterController {
    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/chapters")
    public ResponseEntity GetAllMangaChapters(@RequestParam Long mangaId){
        try{
            return ResponseEntity.ok(chapterService.GetAllMangaChapters(mangaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/chapter")
    public ResponseEntity AddChapter(@RequestBody AddChapterRequest chapterRequest){
        try{
            return ResponseEntity.ok(chapterService.AddChapter(chapterRequest));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}