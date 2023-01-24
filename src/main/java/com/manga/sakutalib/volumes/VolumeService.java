package com.manga.sakutalib.volumes;

import com.manga.sakutalib.database.entities.VolumeEntity;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.repositories.VolumeRepository;
import com.manga.sakutalib.volumes.requests.AddVolumeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolumeService {
    private final VolumeRepository volumeRepository;
    private final MangaRepository mangaRepository;

    @Autowired
    public VolumeService(VolumeRepository volumeRepository, MangaRepository mangaRepository) {
        this.volumeRepository = volumeRepository;
        this.mangaRepository = mangaRepository;
    }

    public boolean AddVolume(AddVolumeRequest volumeRequest) throws Exception {
        try{
            var manga = mangaRepository.findById(volumeRequest.mangaId).get();

            var volume = new VolumeEntity(volumeRequest.volumeNumber, manga);

            volumeRepository.save(volume);

            return true;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }
}