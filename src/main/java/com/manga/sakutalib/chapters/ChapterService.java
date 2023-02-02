package com.manga.sakutalib.chapters;

import com.manga.sakutalib.chapters.requests.AddChapterRequest;
import com.manga.sakutalib.chapters.responses.ChapterResponse;
import com.manga.sakutalib.database.entities.ChapterEntity;
import com.manga.sakutalib.database.entities.MangaAuthorEntity;
import com.manga.sakutalib.database.entities.VolumeEntity;
import com.manga.sakutalib.database.repositories.ChapterRepository;
import com.manga.sakutalib.database.repositories.MangaAuthorRepository;
import com.manga.sakutalib.database.repositories.VolumeRepository;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
            List<ChapterResponse> arr = new ArrayList<>();

            Collection<ChapterEntity> chapters = chapterRepository.findAllByVolumeMangaId(mangaId);

            var inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            var outputFormat = new SimpleDateFormat("dd-MM-yyyy");

            for (ChapterEntity c : chapters) {
                var date = inputFormat.parse(c.getDateAdded());

                MangaAuthorEntity author = c.getAuthor();
                var authorResponse = new MangaAuthorResponse(author.getId(), author.getFirstName(), author.getSecondName());

                var chapterResponse = new ChapterResponse(c.getId(), c.getChapterName(), c.getChapterNumber(), outputFormat.format(date),
                        c.getVolume().getVolumeNumber(), c.getPagesAmount(), authorResponse);

                arr.add(chapterResponse);
            }

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public boolean AddChapter(AddChapterRequest chapterRequest) throws Exception{
        try{
            MangaAuthorEntity author = mangaAuthorRepository.findById(chapterRequest.chapterAuthorId).get();
            VolumeEntity volume = volumeRepository.findById(chapterRequest.chapterVolumeId).get();

            ChapterEntity chapter = new ChapterEntity(chapterRequest.chapterName, chapterRequest.chapterNumber, new Date().toString(), author, volume);

            chapterRepository.save(chapter);

            List<ChapterEntity> oldChapters = volume.getChapters();
            oldChapters.add(chapter);

            volume.setChapters(oldChapters);

            volumeRepository.save(volume);

            String path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + volume.getManga().getPathName() + "/" + volume.getVolumeNumber() + "/" + chapterRequest.chapterNumber;

            new File(path).mkdirs();

            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
