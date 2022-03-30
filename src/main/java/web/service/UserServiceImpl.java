package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Component
public class UserServiceImpl implements UserService{

    private  UserDao dao;

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Override
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }

    @Override
    public void removeUserById(Long id) {
        dao.removeUserById(id);

    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);

    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
