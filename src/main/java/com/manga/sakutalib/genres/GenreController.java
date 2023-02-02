package com.manga.sakutalib.genres;

import com.manga.sakutalib.genres.requests.AddGenreRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {
    private static final Logger LOGGER = LogManager.getLogger(GenreController.class);

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/genre")
    public ResponseEntity AddGenre(@RequestBody AddGenreRequest genreRequest){
        try{
            return new ResponseEntity(genreService.AddGenre(genreRequest), HttpStatus.OK);
        }catch (Exception ex){
            LOGGER.error("ERROR WHILE ADDING NEW GENRE \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/genres")
    public ResponseEntity GetAllGenres(){
        try{
            return new ResponseEntity(genreService.GetAllGenres(), HttpStatus.OK);
        }catch (Exception ex){
            LOGGER.error("ERROR WHILE GETTING GENRES \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
