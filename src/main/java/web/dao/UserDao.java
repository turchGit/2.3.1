package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User getUserById(Long id);

    void removeUserById(Long id);

    void addUser(User user);

    void updateUser(User user);
}
