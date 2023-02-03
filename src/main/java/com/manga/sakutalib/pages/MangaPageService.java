package com.manga.sakutalib.pages;

import com.manga.sakutalib.database.entities.ChapterEntity;
import com.manga.sakutalib.database.entities.MangaEntity;
import com.manga.sakutalib.database.entities.MangaPageEntity;
import com.manga.sakutalib.database.entities.VolumeEntity;
import com.manga.sakutalib.database.repositories.ChapterRepository;
import com.manga.sakutalib.database.repositories.MangaPageRepository;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.repositories.VolumeRepository;
import com.manga.sakutalib.pages.exceptions.NoFoundMangaPageException;
import com.manga.sakutalib.pages.requests.GetMangaPageRequest;
import com.manga.sakutalib.pages.requests.UploadMangaPageRequest;
import com.manga.sakutalib.pages.responses.MangaPageCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public boolean UploadMangaPage(UploadMangaPageRequest mangaPageRequest) throws NoSuchElementException, Exception {
        try{
            MangaEntity manga = mangaRepository.findById(mangaPageRequest.mangaId).get();
            VolumeEntity volume = volumeRepository.findById(mangaPageRequest.volumeId).get();
            ChapterEntity chapter = chapterRepository.findById(mangaPageRequest.chapterId).get();

            String path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + manga.getPathName() + "/" +
                    volume.getVolumeNumber() + "/" + chapter.getChapterNumber() + "/"  + mangaPageRequest.file.getOriginalFilename();
            File f = new File(path);

            if (!f.exists()) {
                f.mkdirs();
            }

            mangaPageRequest.file.transferTo(f);

            MangaPageEntity page = new MangaPageEntity(mangaPageRequest.pageNumber, f.getAbsolutePath(),  chapter);

            mangaPageRepository.save(page);

            List<MangaPageEntity> newPages = chapter.getPages();
            newPages.add(page);
            chapter.setPages(newPages);

            chapter.setPagesAmount(chapter.getPagesAmount() + 1);

            chapterRepository.save(chapter);

            return true;
        } catch(NoSuchElementException noElement){
            throw new NoSuchElementException("Can not find elements with presented id");
        } catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public byte[] GetMangaPage(GetMangaPageRequest pageRequest) throws Exception, NoFoundMangaPageException {
        try{
            File file = new File(System.getProperty("user.home") + "/Desktop/sakuta_lib/" + pageRequest.mangaName + "/" + pageRequest.volume
                    + "/" + pageRequest.chapter + "/" + pageRequest.page);

            if(!file.exists()){
                throw new NoFoundMangaPageException("Can not find any pages by presented path", file.getPath());
            }

            return Files.readAllBytes(file.toPath());
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }

    public List<MangaPageCommentResponse> GetAllPageCommentByPath(String path) throws Exception, NoFoundMangaPageException {
        try{
            File file = new File(System.getProperty("user.home") + "/Desktop/sakuta_lib/" + path);

            if(!file.exists()){
                throw new NoFoundMangaPageException("Can not find any pages by presented path", file.getPath());
            }

            List<MangaPageCommentResponse> arr = new ArrayList<>();

            MangaPageEntity page = mangaPageRepository.findByPath("/home/yehor/Desktop/sakuta_lib/" + path);

            page.getComments().forEach(c -> arr.add(new MangaPageCommentResponse(c.getId(), c.getContent(), c.getAuthor().getLogin())));

            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

}
