package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

    private EntityManagerFactory sessionFactory;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            result = sessionFactory.createEntityManager().createQuery("select u from User u",User.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
        return result;

    }

    @Override
    public User getUserById(Long id) {
        User result = null;
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select u from User u where u.id like :id");
            query.setParameter("id", id);
            result = (User) query.getSingleResult();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
        return  result;

    }

    @Override
    public void removeUserById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select u from User u where u.id like :id");
            query.setParameter("id", id);
            User userToDelete = (User) query.getSingleResult();
            entityManager.remove(userToDelete);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void updateUser(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("update User u " +
                    "set u.firstName = :newFirstName, " +
                    "u.secondName = :newSecondName," +
                    "u.age = :newAge " +
                    "where u.id = :id");
            query.setParameter("newFirstName", user.getFirstName());
            query.setParameter("newSecondName", user.getSecondName());
            query.setParameter("newAge", user.getAge());
            query.setParameter("id", user.getId());
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

    }

}
