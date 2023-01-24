package com.manga.sakutalib.volumes.responses;

public class VolumeResponse {
    public final Long volumeId;
    public final Integer volumeNumber;
    public final Integer volumeChaptersAmount;

    public VolumeResponse(Long volumeId, Integer volumeNumber, Integer volumeChaptersAmount) {
        this.volumeId = volumeId;
        this.volumeNumber = volumeNumber;
        this.volumeChaptersAmount = volumeChaptersAmount;
    }
}
