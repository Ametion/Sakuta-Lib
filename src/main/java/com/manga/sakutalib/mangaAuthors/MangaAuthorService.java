package com.manga.sakutalib.mangaAuthors;

import com.manga.sakutalib.database.entities.MangaAuthorEntity;
import com.manga.sakutalib.database.repositories.MangaAuthorRepository;
import com.manga.sakutalib.mangaAuthors.exceptions.NoMangaAuthorFoundException;
import com.manga.sakutalib.mangaAuthors.requests.AddMangaAuthorRequest;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
            Date birthday = formatter.parse(authorRequest.authorBirthday);

            MangaAuthorEntity author = new MangaAuthorEntity(authorRequest.authorFirstName, authorRequest.authorSecondName, birthday);

            authorRepository.save(author);

            return true;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }

    public MangaAuthorResponse GetMangaAuthorById(Long id) throws Exception, NoMangaAuthorFoundException {
        try{
            MangaAuthorEntity author = authorRepository.findById(id).get();

            return new MangaAuthorResponse(author.getId(), author.getFirstName(), author.getSecondName());
        }catch(NoSuchElementException noAuthor){
            throw new NoMangaAuthorFoundException("Can not find manga with presented id", id.toString());
        } catch (Exception ex){
            throw new Exception(ex);
        }
    }

    public List<MangaAuthorResponse> GetMangaAuthorsByName(String name) throws Exception {
        try{
            List<MangaAuthorResponse> arr = new ArrayList<>();

            Collection<MangaAuthorEntity> authors = authorRepository.findByFirstName(name);

            authors.forEach(a -> {
                var author = new MangaAuthorResponse(a.getId(), a.getFirstName(), a.getSecondName());
                arr.add(author);
            });

            return arr;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }
}
