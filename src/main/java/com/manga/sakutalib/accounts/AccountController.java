package com.manga.sakutalib.accounts;

import com.manga.sakutalib.accounts.requests.EditFavouriteMangaRequest;
import com.manga.sakutalib.accounts.requests.LoginAccountRequest;
import com.manga.sakutalib.accounts.requests.RegisterAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountsService accountsService;

    @Autowired
    public AccountController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/register")
    public ResponseEntity RegisterAccount(@RequestBody RegisterAccountRequest registerAccountRequest){
        try{
            return ResponseEntity.ok(accountsService.RegisterAccount(registerAccountRequest));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity LoginAccount(@RequestBody LoginAccountRequest loginAccountRequest){
        try{
            return ResponseEntity.ok(accountsService.LoginAccount(loginAccountRequest));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/favouriteManga")
    public ResponseEntity EditFavouriteManga(@RequestBody EditFavouriteMangaRequest favouriteMangaRequest) {
        try{
            return ResponseEntity.ok(accountsService.EditFavouriteManga(favouriteMangaRequest));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
