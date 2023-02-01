package com.manga.sakutalib.accounts;

import com.manga.sakutalib.accounts.exceptions.NoUserFoundException;
import com.manga.sakutalib.accounts.exceptions.UserAlreadyExistException;
import com.manga.sakutalib.accounts.requests.EditFavouriteMangaRequest;
import com.manga.sakutalib.accounts.requests.LoginAccountRequest;
import com.manga.sakutalib.accounts.requests.RegisterAccountRequest;
import com.manga.sakutalib.database.entities.MangaEntity;
import com.manga.sakutalib.database.entities.UserEntity;
import com.manga.sakutalib.database.repositories.MangaRepository;
import com.manga.sakutalib.database.repositories.UserRepository;
import com.manga.sakutalib.genres.responses.GenreResponse;
import com.manga.sakutalib.mangaAuthors.responses.MangaAuthorResponse;
import com.manga.sakutalib.mangas.exceptions.NoMangaFoundException;
import com.manga.sakutalib.mangas.responses.MangaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AccountsService {
    private final UserRepository userRepository;
    private final MangaRepository mangaRepository;

    @Autowired
    public AccountsService(UserRepository userRepository, MangaRepository mangaRepository) {
        this.userRepository = userRepository;
        this.mangaRepository = mangaRepository;
    }

    public boolean RegisterAccount(RegisterAccountRequest registerAccountRequest) throws Exception, UserAlreadyExistException {
        try{
            if(userRepository.findByLogin(registerAccountRequest.login) != null){
                throw new UserAlreadyExistException("User with this login is already exist in database", registerAccountRequest.login);
            }

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

    public boolean LoginAccount(LoginAccountRequest loginAccountRequest) throws Exception, NoUserFoundException {
        try{
            var user = userRepository.findByLogin(loginAccountRequest.login);

            if(user == null){
                throw new NoUserFoundException("Can not find any users with this login", loginAccountRequest.login);
            }

            return user.getPassword().equals(loginAccountRequest.password);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public boolean EditFavouriteManga(EditFavouriteMangaRequest favouriteMangaRequest) throws Exception, NoMangaFoundException, NoUserFoundException {
        try{
            var manga = mangaRepository.findById(favouriteMangaRequest.mangaId).get();

            var user = userRepository.findByLogin(favouriteMangaRequest.userLogin);

            if(user == null){
                throw new NoUserFoundException("Can not find any users with this login", favouriteMangaRequest.userLogin);
            }

            Set<MangaEntity> newFavourites = user.getFavouriteMangas();

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
        }catch(NoSuchElementException noManga){
            throw new NoMangaFoundException("Can not find manga with presented id", favouriteMangaRequest.mangaId);
        } catch(Exception ex){
            throw new Exception(ex);
        }
    }
    
    public List<MangaResponse> GetFavouriteManga(String userLogin) throws Exception, NoUserFoundException {
        try{
            List<MangaResponse> arr = new ArrayList<>();
            
            var user = userRepository.findByLogin(userLogin);

            if(user == null){
                throw new NoUserFoundException("Can not find any users with this login", userLogin);
            }
            
            user.getFavouriteMangas().forEach(m -> {
                MangaAuthorResponse author = new MangaAuthorResponse(m.getAuthor().getId(), m.getAuthor().getFirstName(), m.getAuthor().getSecondName());

                List<GenreResponse> genreResponse = new ArrayList<>();

                m.getMangaGenres().forEach(g -> genreResponse.add(new GenreResponse(g.getId(), g.getGenre())));

                arr.add(new MangaResponse(m.getId(), m.getMangaName(), m.getPathName(), "", author, genreResponse));
            });
            
            return arr;
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}