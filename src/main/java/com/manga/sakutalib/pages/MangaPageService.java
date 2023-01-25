package com.manga.sakutalib.pages;

import com.manga.sakutalib.database.entities.MangaPageEntity;
import com.manga.sakutalib.database.repositories.ChapterRepository;
import com.manga.sakutalib.database.repositories.MangaPageRepository;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.repositories.VolumeRepository;
import com.manga.sakutalib.pages.requests.UploadMangaPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MangaPageService {
    private final MangaPageRepository mangaPageRepository;
    private final MangaRepository mangaRepository;
    private final VolumeRepository volumeRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public MangaPageService(MangaPageRepository mangaPageRepository, MangaRepository mangaRepository, VolumeRepository volumeRepository, ChapterRepository chapterRepository) {
        this.mangaPageRepository = mangaPageRepository;
        this.mangaRepository = mangaRepository;
        this.volumeRepository = volumeRepository;
        this.chapterRepository = chapterRepository;
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
                f.mkdirs();
            }

            mangaPageRequest.file.transferTo(f);

            var page = new MangaPageEntity(mangaPageRequest.pageNumber, f.getAbsolutePath(),  chapter);

            mangaPageRepository.save(page);

            var newPages = chapter.getPages();
            newPages.add(page);
            chapter.setPages(newPages);

            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

}
