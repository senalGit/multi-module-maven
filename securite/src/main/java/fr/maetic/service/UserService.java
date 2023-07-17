package fr.maetic.service;

import fr.maetic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    User update(User patient);

    Optional<User> find(Long id);

    List<User> findAllUsers();


    void delete(Long id);

    void delete(User patient);

    void deleteAll();

    long count();

    User findUserById(Long id);
}
