package com.manga.sakutalib.mangas;

import com.manga.sakutalib.database.entities.MangaEntity;
import com.manga.sakutalib.database.repositories.GenreRepository;
import com.manga.sakutalib.database.repositories.MangaAuthorRepository;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.entities.GenreEntity;
import com.manga.sakutalib.genres.responses.GenreResponse;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import com.manga.sakutalib.mangas.requests.AddMangaRequest;
import com.manga.sakutalib.mangas.responses.MangaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MangaService {
    private final MangaRepository mangaRepository;
    private final MangaAuthorRepository mangaAuthorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public MangaService(MangaRepository mangaRepository, MangaAuthorRepository mangaAuthorRepository, GenreRepository genreRepository) {
        this.mangaRepository = mangaRepository;
        this.mangaAuthorRepository = mangaAuthorRepository;
        this.genreRepository = genreRepository;
    }

    public boolean AddManga(AddMangaRequest mangaRequest) throws Exception {
        try{
            var author = mangaAuthorRepository.findById(mangaRequest.authorId).get();
            var genres = genreRepository.findByIdIn(mangaRequest.genresId);

            var manga = new MangaEntity(mangaRequest.mangaName, mangaRequest.mangaPathName, mangaRequest.mangaDescription, author, genres);

            mangaRepository.save(manga);

            var path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + mangaRequest.mangaPathName;
            var f = new File(path).mkdirs();

            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public MangaResponse GetMangaByName(String name) throws Exception {
        try{
            var manga = mangaRepository.findByMangaName(name);

            var author = new MangaAuthorResponse(manga.getAuthor().getId(), manga.getAuthor().getFirstName(), manga.getAuthor().getFirstName());

            var genreResponse = new ArrayList<GenreResponse>();

            manga.getMangaGenres().forEach(g -> genreResponse.add(new GenreResponse(g.getId(), g.getGenre())));

            return new MangaResponse(manga.getId(), manga.getMangaName(), manga.getPathName(), manga.getMangaDescription(), author, genreResponse);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public List<MangaResponse> GetAllMangas() throws Exception {
        try{
            var arr = new ArrayList<MangaResponse>();

            var mangas = mangaRepository.findAll();

            mangas.forEach(m -> {
                var author = new MangaAuthorResponse(m.getAuthor().getId(), m.getAuthor().getFirstName(), m.getAuthor().getSecondName());
                var genreResponse = new ArrayList<GenreResponse>();

                m.getMangaGenres().forEach(g -> genreResponse.add(new GenreResponse(g.getId(), g.getGenre())));

                var mangaResponse = new MangaResponse(m.getId(), m.getMangaName(), m.getPathName(), "", author, genreResponse);
                arr.add(mangaResponse);
            });

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public List<MangaResponse> GetMangasByGenres(List<Long> genresId) throws Exception {
        try{
            var arr = new ArrayList<MangaResponse>();

            var mangas = mangaRepository.findAll();

            mangas.forEach(m -> {
                var allGenres = true;

                var mangaGenreIds = new ArrayList<>();

                for (var genre : m.getMangaGenres()) {
                    mangaGenreIds.add(genre.getId());
                }

                for (var genreId : genresId) {
                    if (!mangaGenreIds.contains(genreId)) {
                        allGenres = false;
                        break;
                    }
                }
                if (allGenres) {
                    var author = new MangaAuthorResponse(m.getAuthor().getId(), m.getAuthor().getFirstName(), m.getAuthor().getSecondName());

                    var genreResponse = new ArrayList<GenreResponse>();

                    m.getMangaGenres().forEach(g -> genreResponse.add(new GenreResponse(g.getId(), g.getGenre())));

                    arr.add(new MangaResponse(m.getId(), m.getMangaName(), m.getPathName(), m.getMangaDescription(), author, genreResponse));
                }
            });

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
