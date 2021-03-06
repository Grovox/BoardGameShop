package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.User;
import GandA.Corporation.BoardGames.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String mail)
            throws UsernameNotFoundException {
        mail = mail.toLowerCase();
        User user = repo.getUserByMail(mail);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserDetailsService(user);
    }

}
