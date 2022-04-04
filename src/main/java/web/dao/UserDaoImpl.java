package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import web.model.User;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext(unitName = "getEntityManagerFactory")
    private EntityManager entityManager;


    @Override
    public List<User> getUsers() {

        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {

        Query query = entityManager.createQuery("select u from User u where u.id like :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void removeUserById(Long id) {

        User userToDelete = getUserById(id);
        entityManager.remove(userToDelete);
    }

    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

}
