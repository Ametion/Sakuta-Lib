package com.manga.sakutalib.mangas;

import com.manga.sakutalib.database.entities.MangaEntity;
import com.manga.sakutalib.database.repositories.ChapterRepository;
import com.manga.sakutalib.database.repositories.MangaAuthorRepository;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.repositories.VolumeRepository;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import com.manga.sakutalib.mangas.requests.AddMangaRequest;
import com.manga.sakutalib.mangas.requests.UploadMangaPageRequest;
import com.manga.sakutalib.mangas.responses.MangaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MangaService {
    private final MangaRepository mangaRepository;
    private final VolumeRepository volumeRepository;
    private final ChapterRepository chapterRepository;
    private final MangaAuthorRepository mangaAuthorRepository;

    @Autowired
    public MangaService(MangaRepository mangaRepository, VolumeRepository volumeRepository, ChapterRepository chapterRepository, MangaAuthorRepository mangaAuthorRepository) {
        this.mangaRepository = mangaRepository;
        this.volumeRepository = volumeRepository;
        this.chapterRepository = chapterRepository;
        this.mangaAuthorRepository = mangaAuthorRepository;
    }

    public boolean UploadMangaPage(UploadMangaPageRequest mangaPageRequest) throws Exception {
        try{
            var manga = mangaRepository.findById(mangaPageRequest.mangaId).get();
            var volume = volumeRepository.findById(mangaPageRequest.volumeId).get();
            var chapter = chapterRepository.findById(mangaPageRequest.chapterId).get();

            var path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + manga.getPathName() + "/" +
                    volume.getVolumeNumber() + "/" + chapter.getChapterNumber() + "/"  + mangaPageRequest.file.getOriginalFilename();
            var f = new File(path);

            if (!f.exists()) {
                f.getParentFile().mkdirs();
            }

            mangaPageRequest.file.transferTo(f);


            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public boolean AddManga(AddMangaRequest mangaRequest) throws Exception {
        try{
            var manga = new MangaEntity(mangaRequest.mangaName, mangaRequest.mangaPathName, mangaAuthorRepository.findById(mangaRequest.authorId).get());

            mangaRepository.save(manga);

            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public MangaResponse GetMangaByName(String name) throws Exception {
        try{
            var manga = mangaRepository.findByMangaName(name);

            var author = new MangaAuthorResponse(manga.getAuthor().getId(), manga.getAuthor().getFirstName(), manga.getAuthor().getFirstName());

            return new MangaResponse(manga.getId(), manga.getMangaName(), manga.getPathName(), author);
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
                var mangaResponse = new MangaResponse(m.getId(), m.getMangaName(), m.getPathName(), author);
                arr.add(mangaResponse);
            });

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
