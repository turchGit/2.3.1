package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private  UserDao dao;

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        dao.removeUserById(id);

    }

    @Override
    @Transactional
    public void addUser(User user) {
        dao.addUser(user);

    }

    @Override
    @Transactional
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
