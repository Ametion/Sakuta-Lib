package com.manga.sakutalib.mangaAuthors;

import com.manga.sakutalib.mangaAuthors.requests.AddMangaAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MangaAuthorController {
    private final MangaAuthorService authorService;

    @Autowired
    public MangaAuthorController(MangaAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity AddAuthor(@RequestBody AddMangaAuthorRequest authorRequest){
        try{
            return ResponseEntity.ok(authorService.AddAuthor(authorRequest));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/author/{id}")
    public ResponseEntity GetMangaAuthorById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(authorService.GetMangaAuthorById(id));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/author")
    public ResponseEntity GetMangaAuthorById(@RequestParam String name){
        try{
            return ResponseEntity.ok(authorService.GetMangaAuthorsByName(name));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
