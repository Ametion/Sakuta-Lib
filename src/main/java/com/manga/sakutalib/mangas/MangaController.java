package com.manga.sakutalib.mangas;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class MangaController {
    @PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file, @RequestParam String volume,
                                              @RequestParam String chapter, @RequestParam String manga) {
        try {
            var path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + manga + "/" +
                    volume + "/" + chapter + "/"  + file.getOriginalFilename();
            File f = new File(path);

            if (!f.exists()) {
                f.getParentFile().mkdirs();
            }

            file.transferTo(f);

            return ResponseEntity.ok(true);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{mangaName}/{volume}/{chapter}/{page}")
    public ResponseEntity getImage(@PathVariable String mangaName, @PathVariable String volume, @PathVariable String chapter, @PathVariable String page) throws IOException {
        try{
            File file = new File(System.getProperty("user.home") + "/Desktop/sakuta_lib/" + mangaName + "/" + volume
                    + "/" + chapter + "/" + page);
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imageBytes.length);
            return new ResponseEntity(imageBytes, headers, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
