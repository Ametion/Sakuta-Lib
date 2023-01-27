package com.manga.sakutalib.accounts;

import com.manga.sakutalib.accounts.requests.EditFavouriteMangaRequest;
import com.manga.sakutalib.accounts.requests.LoginAccountRequest;
import com.manga.sakutalib.accounts.requests.RegisterAccountRequest;
import com.manga.sakutalib.database.entities.UserEntity;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.repositories.UserRepository;
import com.manga.sakutalib.genres.responses.GenreResponse;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import com.manga.sakutalib.mangas.responses.MangaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountsService {
    private final UserRepository userRepository;
    private final MangaRepository mangaRepository;

    @Autowired
    public AccountsService(UserRepository userRepository, MangaRepository mangaRepository) {
        this.userRepository = userRepository;
        this.mangaRepository = mangaRepository;
    }

    public boolean RegisterAccount(RegisterAccountRequest registerAccountRequest) throws Exception {
        try{
            var user = new UserEntity(registerAccountRequest.firstName, registerAccountRequest.secondName, registerAccountRequest.login, registerAccountRequest.password);

            if (registerAccountRequest.nickName != null) {
                user.setNickName(registerAccountRequest.nickName);
            } else {
                user.setNickName(registerAccountRequest.login);
            }

            userRepository.save(user);

            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public boolean LoginAccount(LoginAccountRequest loginAccountRequest) throws Exception {
        try{
            var user = userRepository.findByLogin(loginAccountRequest.login);

            if(user == null){
                return false;
            }

            return user.getPassword().equals(loginAccountRequest.password);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public boolean EditFavouriteManga(EditFavouriteMangaRequest favouriteMangaRequest) throws Exception {
        try{
            var manga = mangaRepository.findById(favouriteMangaRequest.mangaId).get();

            var user = userRepository.findByLogin(favouriteMangaRequest.userLogin);

            var newFavourites = user.getFavouriteMangas();

            if(newFavourites.contains(manga)){
                newFavourites.remove(manga);
                user.setFavouriteMangas(newFavourites);

                userRepository.save(user);

                return false;
            }

            newFavourites.add(manga);

            user.setFavouriteMangas(newFavourites);

            userRepository.save(user);

            return true;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
    
    public List<MangaResponse> GetFavouriteManga(String userLogin) throws Exception {
        try{
            var arr = new ArrayList<MangaResponse>();
            
            var user = userRepository.findByLogin(userLogin);

            if(user == null){
                throw new Exception("Cant find user with this login");
            }
            
            user.getFavouriteMangas().forEach(m -> {
                var author = new MangaAuthorResponse(m.getAuthor().getId(), m.getAuthor().getFirstName(), m.getAuthor().getSecondName());

                var genreResponse = new ArrayList<GenreResponse>();

                m.getMangaGenres().forEach(g -> genreResponse.add(new GenreResponse(g.getId(), g.getGenre())));

                arr.add(new MangaResponse(m.getId(), m.getMangaName(), m.getPathName(), "", author, genreResponse));
            });
            
            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}