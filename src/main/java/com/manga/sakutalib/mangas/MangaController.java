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
}
