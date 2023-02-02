package com.manga.sakutalib.mangaAuthors;

import com.manga.sakutalib.mangaAuthors.exceptions.NoMangaAuthorFoundException;
import com.manga.sakutalib.mangaAuthors.requests.AddMangaAuthorRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MangaAuthorController {
    private static final Logger LOGGER = LogManager.getLogger(MangaAuthorController.class);

    private final MangaAuthorService authorService;

    @Autowired
    public MangaAuthorController(MangaAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity AddAuthor(@RequestBody AddMangaAuthorRequest authorRequest){
        try{
            return new ResponseEntity(authorService.AddAuthor(authorRequest), HttpStatus.CREATED);
        }catch(Exception ex){
            LOGGER.error("ERROR WHILE ADDING MANGA AUTHOR: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/author/{id}")
    public ResponseEntity GetMangaAuthorById(@PathVariable Long id){
        try{
            return new ResponseEntity(authorService.GetMangaAuthorById(id), HttpStatus.OK);
        }catch (NoMangaAuthorFoundException noAuthor) {
            LOGGER.warn("TRY TO GET NON EXISTED ID OF MANGA AUTHOR: " + noAuthor.getMangaAuthorName());
            return new ResponseEntity(noAuthor.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/author")
    public ResponseEntity GetMangaAuthorById(@RequestParam String name){
        try{
            return new ResponseEntity(authorService.GetMangaAuthorsByName(name), HttpStatus.OK);
        }catch(Exception ex){
            LOGGER.error("ERROR WHILE GETTING AUTHOR BY NAME \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
