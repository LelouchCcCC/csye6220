package com.csye6220.shareonline.dao;

import com.csye6220.shareonline.model.User;
import org.hibernate.query.Query;
import java.util.List;

public class UserDAO extends DAO {

    public User saveOrUpdateUser(User u) {
        try {
            begin();
            getSession().saveOrUpdate(u);
            commit();
            return u;
        } catch (Exception e) {
            rollback(); throw e;
        } finally { close(); }
    }

    public User findById(Long id) {
        try {
            begin();
            User user = getSession().get(User.class, id);
            commit();
            return user;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    public User findByUsername(String username) {
        try {
            begin();
            Query<User> query = getSession().createQuery(
                    "FROM User WHERE username = :uname", User.class);
            query.setParameter("uname", username);
            User result = query.uniqueResult();
            commit();
            return result;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    public User findByEmail(String email) {
        try {
            begin();
            Query<User> query = getSession().createQuery(
                    "FROM User WHERE email = :mail", User.class);
            query.setParameter("mail", email);
            User result = query.uniqueResult();
            commit();
            return result;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }

    public List<User> getAllUsers() {
        try {
            begin();
            Query<User> query = getSession().createQuery("FROM User", User.class);
            List<User> list = query.list();
            commit();
            return list;
        } catch (Exception e) {
            rollback();
            throw e;
        } finally {
            close();
        }
    }
}
