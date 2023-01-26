package com.manga.sakutalib.genres;

import com.manga.sakutalib.database.entities.GenreEntity;
import com.manga.sakutalib.database.repositories.GenreRepository;
import com.manga.sakutalib.genres.requests.AddGenreRequest;
import com.manga.sakutalib.genres.responses.GenreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public boolean AddGenre(AddGenreRequest genreRequest) throws Exception {
        try{
            var genre = new GenreEntity(genreRequest.genre);

            genreRepository.save(genre);

            return true;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }

    public List<GenreResponse> GetAllGenres() throws Exception {
        try{
            var arr = new ArrayList<GenreResponse>();

            var genres = genreRepository.findAll();

            genres.forEach(g -> arr.add(new GenreResponse(g.getId(), g.getGenre())));

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
