package com.manga.sakutalib.mangas;

import com.manga.sakutalib.mangas.requests.AddMangaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;

@RestController
public class MangaController {
    private final MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @PostMapping("/manga")
    public ResponseEntity AddManga(@RequestBody AddMangaRequest mangaRequest){
        try{
            return ResponseEntity.ok(mangaService.AddManga(mangaRequest));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/manga")
    public ResponseEntity GetMangaByName(@RequestParam String name){
        try{
            return ResponseEntity.ok(mangaService.GetMangaByName(name));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/mangas")
    public ResponseEntity GetAllMangas(){
        try{
            return ResponseEntity.ok(mangaService.GetAllMangas());
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
