package web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class UserDaoImp implements UserDao {

    final static String ALL_SINGER_NATIVE_QUERY =
            "select id, name, last_name, email, from users";

    private static Logger logger = LoggerFactory.getLogger(UserDaoImp.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        return entityManager
                .createQuery("select u from User u", User.class)
                .getResultList();
    }


    @Transactional
    @Override
    public User getUserById(Long id){
        return entityManager.find(User.class, id);
    }
    /*
    @Transactional(readOnly=true)
    @Override
    public List<User> listUsers() {
        return entityManager
                .createNamedQuery(User.FIND_ALL, User.class).getResultList();
    }
     */

    /*
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        String hql = "select u from User u";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }
 */


    @Override
    public void addUser(User user) {
        if (user.getId() == null) {
            logger.info("Inserting new user");
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
            logger.info("Updating existing user");
        }
        logger.info("User saved with id: " + user.getId());
    }


//    @Override
//    public User getUserById(long id) {
//        User userGotById;
//        try {
//            String hqlUser = "from User u where id = '" + id + "'";
//            TypedQuery<User> queryUser = sessionFactory.getCurrentSession().createQuery(hqlUser);
//            userGotById = queryUser.getSingleResult();
//            System.out.println("User " + userGotById.getFirstName() + " " + userGotById.getLastName());
//        } catch (Exception e) {
//            userGotById = null;
//            System.out.println("No user is in the users table");
//        }
//        return userGotById;
//    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        logger.info("Updating existing user");
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        User mergedContact = entityManager.merge(user);
        entityManager.remove(mergedContact);
        logger.info("User with id: " + user.getId() + " deleted successfully");
    }

    @Transactional
    @Override
    public void deleteAllUsersFromTable() {
        String hql = "select u from User u";
        TypedQuery<User> query = entityManager.createNamedQuery(hql, User.class);
        List<User> users = query.getResultList();
        for (User user : users) {
            User mergedContact = entityManager.merge(user);
            entityManager.remove(mergedContact);
        }
    }

    @Transactional
    public List<User> findAllByNativeQuery() {
        return entityManager.createNativeQuery(ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
    }

}