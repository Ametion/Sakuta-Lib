package com.manga.sakutalib.chapters;

import com.manga.sakutalib.chapters.responses.ChapterResponse;
import com.manga.sakutalib.database.repositories.ChapterRepository;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;

    @Autowired
    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
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
}
