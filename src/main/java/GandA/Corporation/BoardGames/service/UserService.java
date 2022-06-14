package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.Role;
import GandA.Corporation.BoardGames.domain.User;
import GandA.Corporation.BoardGames.repo.RoleRepository;
import GandA.Corporation.BoardGames.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService{

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void savePassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    public void newUserSave(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepo.getOne(1L);
        user.setRole(role);
        repo.save(user);
    }

    public void saveNotPassword(User user) {
        repo.save(user);
    }


    public User getUserByEmail(String email){
        return repo.getUserByMail(email);
    }

    public User getAuntUser(){
        return getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public List<User> findByMailContaining(String mail){
        return repo.findByMailContainingIgnoreCase(mail);
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

    public User findByEmail(String mail) {
        return repo.findByMail(mail);
    }
}