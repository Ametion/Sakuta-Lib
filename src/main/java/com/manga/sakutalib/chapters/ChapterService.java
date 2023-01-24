package com.manga.sakutalib.chapters;

import com.manga.sakutalib.chapters.requests.AddChapterRequest;
import com.manga.sakutalib.chapters.responses.ChapterResponse;
import com.manga.sakutalib.database.entities.ChapterEntity;
import com.manga.sakutalib.database.repositories.ChapterRepository;
import com.manga.sakutalib.database.repositories.MangaAuthorRepository;
import com.manga.sakutalib.database.repositories.VolumeRepository;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final MangaAuthorRepository mangaAuthorRepository;
    private final VolumeRepository volumeRepository;

    @Autowired
    public ChapterService(ChapterRepository chapterRepository, MangaAuthorRepository mangaAuthorRepository, VolumeRepository volumeRepository) {
        this.chapterRepository = chapterRepository;
        this.mangaAuthorRepository = mangaAuthorRepository;
        this.volumeRepository = volumeRepository;
    }

    public List<ChapterResponse> GetAllMangaChapters(Long mangaId) throws Exception {
        try{
            var arr = new ArrayList<ChapterResponse>();

            var chapters = chapterRepository.findAllByVolumeMangaId(mangaId);

            chapters.forEach(c -> {
                var author = c.getAuthor();
                var authorResponse = new MangaAuthorResponse(author.getId(), author.getFirstName(), author.getSecondName());
                var chapterResponse = new ChapterResponse(c.getId(), c.getChapterName(), c.getChapterNumber(), c.getVolume().getVolumeNumber(), authorResponse);
                arr.add(chapterResponse);
            });

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public boolean AddChapter(AddChapterRequest chapterRequest) throws Exception{
        try{
            var author = mangaAuthorRepository.findById(chapterRequest.chapterAuthorId).get();
            var volume = volumeRepository.findById(chapterRequest.chapterVolumeId).get();

            var chapter = new ChapterEntity(chapterRequest.chapterName, chapterRequest.chapterNumber, author, volume);

            chapterRepository.save(chapter);


            var oldChapters = volume.getChapters();
            oldChapters.add(chapter);

            volume.setChapters(oldChapters);

            volumeRepository.save(volume);

            var path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + volume.getManga().getPathName() + "/" + volume.getVolumeNumber() + "/" + chapterRequest.chapterNumber;
            var f = new File(path).mkdirs();

            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
