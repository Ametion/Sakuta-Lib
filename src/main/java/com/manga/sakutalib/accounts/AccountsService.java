package com.manga.sakutalib.accounts;

import com.manga.sakutalib.accounts.requests.LoginAccountRequest;
import com.manga.sakutalib.accounts.requests.RegisterAccountRequest;
import com.manga.sakutalib.database.entities.UserEntity;
import com.manga.sakutalib.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {
    private final UserRepository userRepository;

    @Autowired
    public AccountsService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}