package com.manga.sakutalib.volumes;

import com.manga.sakutalib.volumes.requests.AddVolumeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VolumeController {
    private final VolumeService volumeService;

    @Autowired
    public VolumeController(VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @PostMapping("/volume")
    public ResponseEntity AddVolume(@RequestBody AddVolumeRequest volumeRequest){
        try{
            return ResponseEntity.ok(volumeService.AddVolume(volumeRequest));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
