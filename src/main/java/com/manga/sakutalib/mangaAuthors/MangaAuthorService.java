package com.manga.sakutalib.mangaAuthors;

import com.manga.sakutalib.database.entities.MangaAuthorEntity;
import com.manga.sakutalib.database.repositories.MangaAuthorRepository;
import com.manga.sakutalib.mangaAuthors.requests.AddMangaAuthorRequest;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class MangaAuthorService {
    private final MangaAuthorRepository authorRepository;

    @Autowired
    public MangaAuthorService(MangaAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public boolean AddAuthor(AddMangaAuthorRequest authorRequest) throws Exception {
        try{
            var formatter = new SimpleDateFormat("yyyy-MM-dd");
            var birthday = formatter.parse(authorRequest.authorBirthday);

            var author = new MangaAuthorEntity(authorRequest.authorFirstName, authorRequest.authorSecondName, birthday);

            authorRepository.save(author);

            return true;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }

    public MangaAuthorResponse GetMangaAuthorById(Long id) throws Exception {
        try{
            var author = authorRepository.findById(id).get();

            return new MangaAuthorResponse(author.getFirstName(), author.getSecondName());
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }
}
