package com.manga.sakutalib.volumes;

import com.manga.sakutalib.database.entities.VolumeEntity;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.repositories.VolumeRepository;
import com.manga.sakutalib.volumes.requests.AddVolumeRequest;
import com.manga.sakutalib.volumes.responses.VolumeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

            var path = System.getProperty("user.home") + "/Desktop/sakuta_lib/" + manga.getPathName() + "/" + volumeRequest.volumeNumber;
            var f = new File(path).mkdirs();

            return true;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }

    public List<VolumeResponse> GetAllMangaVolumes(Long mangaId) throws Exception {
        try{
            var arr = new ArrayList<VolumeResponse>();

            var volumes = volumeRepository.findAllByManga_Id(mangaId);

            volumes.forEach(v -> arr.add(new VolumeResponse(v.getId(), v.getVolumeNumber(), v.getChapters().size())));

            return arr;
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }
}