package com.manga.sakutalib.mangas.requests;

import java.util.Collection;

public class AddMangaRequest {
    public String mangaName;
    public String mangaPathName;
    public String mangaDescription;
    public Long authorId;
    public Collection<Long> genresId;
}