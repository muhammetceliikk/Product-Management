package com.uniyaz.core.dao;

import com.uniyaz.core.domain.CustomerProduct;
import com.uniyaz.core.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerProductDao {

    public void saveCustomerProduct(CustomerProduct customerProduct) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(customerProduct);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerProduct(CustomerProduct customerProduct) {
    }

    public static List<CustomerProduct> findAllHql() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     customerProduct " +
                    "From       CustomerProduct customerProduct "+
                    "Left join FETCH Customer "+
                    "Left join fetch Product ";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
