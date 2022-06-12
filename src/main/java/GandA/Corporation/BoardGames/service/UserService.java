package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.User;
import GandA.Corporation.BoardGames.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    public void saveNotPassword(User user) {
        repo.save(user);
    }

    public User getUserByEmail(String email){
        return repo.getUserByEmail(email);
    }

    public User getAuntUser(){
        return getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public User get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}