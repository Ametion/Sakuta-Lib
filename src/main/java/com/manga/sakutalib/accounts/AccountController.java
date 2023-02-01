package com.manga.sakutalib.accounts;

import com.manga.sakutalib.accounts.exceptions.NoUserFoundException;
import com.manga.sakutalib.accounts.exceptions.UserAlreadyExistException;
import com.manga.sakutalib.accounts.requests.EditFavouriteMangaRequest;
import com.manga.sakutalib.accounts.requests.LoginAccountRequest;
import com.manga.sakutalib.accounts.requests.RegisterAccountRequest;
import com.manga.sakutalib.mangas.MangaController;
import com.manga.sakutalib.mangas.exceptions.NoMangaFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private static final Logger LOGGER = LogManager.getLogger(MangaController.class);

    private final AccountsService accountsService;

    @Autowired
    public AccountController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/register")
    public ResponseEntity RegisterAccount(@RequestBody RegisterAccountRequest registerAccountRequest){
        try{
            return ResponseEntity.ok(accountsService.RegisterAccount(registerAccountRequest));
        } catch(UserAlreadyExistException user) {
            LOGGER.warn("Try to register login which already exist: " + user.GetLogin());
            return new ResponseEntity(user.getMessage(), HttpStatus.CONFLICT);
        }catch(Exception ex){
            LOGGER.error(ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity LoginAccount(@RequestBody LoginAccountRequest loginAccountRequest){
        try{
            return ResponseEntity.ok(accountsService.LoginAccount(loginAccountRequest));
        }catch(NoUserFoundException noUser){
            LOGGER.warn("Try to use non-existed user login: " + noUser.GetLogin());
            return new ResponseEntity(noUser.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception ex){
            LOGGER.error(ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/favouriteManga")
    public ResponseEntity EditFavouriteManga(@RequestBody EditFavouriteMangaRequest favouriteMangaRequest) {
        try{
            return ResponseEntity.ok(accountsService.EditFavouriteManga(favouriteMangaRequest));
        }catch(NoUserFoundException noUser){
            LOGGER.warn("Try to use non-existed user login: " + noUser.GetLogin());
            return new ResponseEntity(noUser.getMessage(), HttpStatus.NOT_FOUND);
        }catch (NoMangaFoundException noManga){
            LOGGER.warn("Try to use non-existed manga, manga id: " + noManga.GetMangaId());
            return new ResponseEntity(noManga.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception ex){
            LOGGER.error(ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/favouriteManga")
    public ResponseEntity GetFavouriteManga(@RequestParam String userLogin) {
        try{
            return ResponseEntity.ok(accountsService.GetFavouriteManga(userLogin));
        }catch(NoUserFoundException noUser){
            LOGGER.warn("Try to use non-existed user login: " + noUser.GetLogin());
            return new ResponseEntity(noUser.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
