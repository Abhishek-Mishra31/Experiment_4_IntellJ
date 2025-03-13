package com.abhishek.kumar.employee_management_system.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.abhishek.kumar.employee_management_system.model.User;


public class UserService {
    private static final SessionFactory factory;

    static {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
    }

    public static void saveUsers(User user) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }
}
