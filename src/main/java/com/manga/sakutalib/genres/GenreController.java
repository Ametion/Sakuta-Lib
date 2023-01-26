package com.manga.sakutalib.genres;

import com.manga.sakutalib.genres.requests.AddGenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/genre")
    public ResponseEntity AddGenre(@RequestBody AddGenreRequest genreRequest){
        try{
            return ResponseEntity.ok(genreService.AddGenre(genreRequest));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/genres")
    public ResponseEntity GetAllGenres(){
        try{
            return ResponseEntity.ok(genreService.GetAllGenres());
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
