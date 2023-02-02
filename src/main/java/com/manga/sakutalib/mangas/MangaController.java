package com.manga.sakutalib.mangas;

import com.manga.sakutalib.mangaAuthors.exceptions.NoMangaAuthorFoundException;
import com.manga.sakutalib.mangas.exceptions.NoMangaFoundException;
import com.manga.sakutalib.mangas.requests.AddMangaRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MangaController {
    private static final Logger LOGGER = LogManager.getLogger(MangaController.class);

    private final MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @PostMapping("/manga")
    public ResponseEntity AddManga(@RequestBody AddMangaRequest mangaRequest){
        try{
            return new ResponseEntity(mangaService.AddManga(mangaRequest), HttpStatus.CREATED);
        }catch (NoMangaAuthorFoundException e) {
            LOGGER.warn("TRY TO USE NON EXISTED MANGA AUTHOR ID: " + mangaRequest.authorId);
            return new ResponseEntity("Can not find author with presented id: " + mangaRequest.authorId, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            LOGGER.error("ERROR WHILE ADDING MANGA: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/manga")
    public ResponseEntity GetMangaByName(@RequestParam String name){
        try{
            return new ResponseEntity(mangaService.GetMangaByName(name), HttpStatus.OK);
        } catch (NoMangaFoundException noManga) {
            LOGGER.warn("TRY TO USE NON EXISTED MANGA NAME: " + name);
            return new ResponseEntity(noManga.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            LOGGER.error("ERROR WHILE GETTING MANGA: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mangas")
    public ResponseEntity GetAllMangas(){
        try{
            return new ResponseEntity(mangaService.GetAllMangas(), HttpStatus.OK);
        }catch (Exception ex) {
            LOGGER.error("ERROR WHILE GETTING MANGAS: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mangas/genres")
    public ResponseEntity GetMangasByGenres(@RequestParam List<Long> genresId) {
        try{
            return new ResponseEntity(mangaService.GetMangasByGenres(genresId), HttpStatus.OK);
        }catch (Exception ex) {
            LOGGER.error("ERROR WHILE GETTING MANGAS: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
