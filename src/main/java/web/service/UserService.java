package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> listUsers();

    User getUserById(long id);

    void updateUser(User user);

    void deleteUser(Long id);

    void deleteAllUsersFromTable();
}