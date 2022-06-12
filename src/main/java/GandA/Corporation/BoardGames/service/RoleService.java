package GandA.Corporation.BoardGames.service;

import GandA.Corporation.BoardGames.domain.Role;
import GandA.Corporation.BoardGames.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repo;

    public List<Role> listAll() {
        return repo.findAll();
    }

    public Role get(Long id) {
        return repo.findById(id).get();
    }

}
