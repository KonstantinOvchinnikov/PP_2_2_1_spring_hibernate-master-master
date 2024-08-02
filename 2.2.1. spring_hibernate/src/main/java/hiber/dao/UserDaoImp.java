package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        if (user.getCar() != null) {
            sessionFactory.getCurrentSession().save(user.getCar());
        }
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User ");
        return query.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
    }

    public List<User> getUserWhithCar(String model, int series) {
        String hql = "from User where car.model =: model and car.series =: series";

        return sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();


    }

}
