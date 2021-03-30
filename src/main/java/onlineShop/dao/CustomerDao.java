
package onlineShop.dao;

import onlineShop.entity.Authorities;
import onlineShop.entity.Customer;
import onlineShop.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository//为了servlet可以使用
//DAO：data access object
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void addCustomer(Customer customer) {
        Authorities authorities = new Authorities();//关于用户所拥有的权限
        authorities.setAuthorities("ROLE_USER");//把user设置成普通用户
        authorities.setEmailId(customer.getUser().getEmailId());
        Session session = null;//用session 来save这些entity

        try {
            //session是可以链接到数据库的一个object
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Customer getCustomerByUserName(String email) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(user != null) {
            return user.getCustomer();
        }

        return null;
    }

}

