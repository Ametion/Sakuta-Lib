package com.manga.sakutalib.volumes;

import com.manga.sakutalib.mangas.exceptions.NoMangaFoundException;
import com.manga.sakutalib.volumes.requests.AddVolumeRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VolumeController {
    private static final Logger LOGGER = LogManager.getLogger(VolumeController.class);

    private final VolumeService volumeService;

    @Autowired
    public VolumeController(VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @PostMapping("/volume")
    public ResponseEntity AddVolume(@RequestBody AddVolumeRequest volumeRequest){
        try{
            return new ResponseEntity(volumeService.AddVolume(volumeRequest), HttpStatus.CREATED);
        }catch (NoMangaFoundException noManga){
            LOGGER.warn("Try to use non-existed manga, manga id: " + noManga.GetMangaId());
            return new ResponseEntity(noManga.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            LOGGER.error("ERROR WHILE ADDING MANGA VOLUME: \n" + ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/volumes")
    public ResponseEntity GetAllMangaVolumes(@RequestParam Long mangaId){
        try{
            return new ResponseEntity(volumeService.GetAllMangaVolumes(mangaId), HttpStatus.OK);
        }catch (NoMangaFoundException noManga){
            LOGGER.warn("Try to use non-existed manga, manga id: " + noManga.GetMangaId());
            return new ResponseEntity(noManga.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            LOGGER.error("ERROR WHILE GETTING VOLUMES: \n" + ex.getMessage());
            return  new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
