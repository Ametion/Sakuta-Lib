package com.manga.sakutalib.mangas;

import com.manga.sakutalib.database.entities.MangaAuthorEntity;
import com.manga.sakutalib.database.entities.MangaEntity;
import com.manga.sakutalib.database.repositories.GenreRepository;
import com.manga.sakutalib.database.repositories.MangaAuthorRepository;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.entities.GenreEntity;
import com.manga.sakutalib.genres.responses.GenreResponse;
import com.manga.sakutalib.mangaAuthors.exceptions.NoMangaAuthorFoundException;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import com.manga.sakutalib.mangas.exceptions.NoMangaFoundException;
import com.manga.sakutalib.mangas.requests.AddMangaRequest;
import com.manga.sakutalib.mangas.responses.MangaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

    public boolean AddManga(AddMangaRequest mangaRequest) throws Exception, NoMangaAuthorFoundException {
        try{
            MangaAuthorEntity author = mangaAuthorRepository.findById(mangaRequest.authorId).get();
            Set<GenreEntity> genres = genreRepository.findByIdIn(mangaRequest.genresId);

            MangaEntity manga = new MangaEntity(mangaRequest.mangaName, mangaRequest.mangaPathName, mangaRequest.mangaDescription, author, genres);

            mangaRepository.save(manga);

            String path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + mangaRequest.mangaPathName;

            new File(path).mkdirs();

            return true;
        } catch (NoSuchElementException noAuthor){
            throw new NoMangaAuthorFoundException("Can not find any manga authors with this id: " + mangaRequest.authorId, mangaRequest.authorId.toString());
        } catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public MangaResponse GetMangaByName(String name) throws Exception, NoMangaFoundException {
        try{
            MangaEntity manga = mangaRepository.findByMangaName(name);

            if(manga == null){
                throw new NoMangaFoundException("Can not find manga with presented name", name);
            }

            MangaAuthorResponse author = new MangaAuthorResponse(manga.getAuthor().getId(), manga.getAuthor().getFirstName(), manga.getAuthor().getFirstName());

            List<GenreResponse> genreResponse = new ArrayList<>();

            manga.getMangaGenres().forEach(g -> genreResponse.add(new GenreResponse(g.getId(), g.getGenre())));

            return new MangaResponse(manga.getId(), manga.getMangaName(), manga.getPathName(), manga.getMangaDescription(), author, genreResponse);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public List<MangaResponse> GetAllMangas() throws Exception {
        try{
            List<MangaResponse> arr = new ArrayList<>();

            var mangas = mangaRepository.findAll();

            mangas.forEach(m -> {
                MangaAuthorResponse author = new MangaAuthorResponse(m.getAuthor().getId(), m.getAuthor().getFirstName(), m.getAuthor().getSecondName());

                List<GenreResponse> genreResponse = new ArrayList<>();

                m.getMangaGenres().forEach(g -> genreResponse.add(new GenreResponse(g.getId(), g.getGenre())));

                arr.add(new MangaResponse(m.getId(), m.getMangaName(), m.getPathName(), "", author, genreResponse));
            });

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public List<MangaResponse> GetMangasByGenres(List<Long> genresId) throws Exception {
        try{
            List<MangaResponse> arr = new ArrayList<>();

            var mangas = mangaRepository.findAll();

            mangas.forEach(m -> {
                var allGenres = true;

                List<Long> mangaGenreIds = new ArrayList<>();

                for (GenreEntity genre : m.getMangaGenres()) {
                    mangaGenreIds.add(genre.getId());
                }

                for (Long genreId : genresId) {
                    if (!mangaGenreIds.contains(genreId)) {
                        allGenres = false;
                        break;
                    }
                }

                if (allGenres) {
                    MangaAuthorResponse author = new MangaAuthorResponse(m.getAuthor().getId(), m.getAuthor().getFirstName(), m.getAuthor().getSecondName());

                    List<GenreResponse> genreResponse = new ArrayList<>();

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
