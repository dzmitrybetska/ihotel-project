package by.academy.project.hotel.repositories.user;


import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


public final class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl instance = getInstance();
    private final UserMapper mapper = UserMapper.getInstance();
    private EntityManager entityManager;

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> add(UserDto userDto) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = mapper.buildUser(userDto);
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> read() {
        entityManager = JPAUtil.getInstance().getEntityManager();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM by.academy.project.hotel.entities.user.User u", User.class);
        List<User> users = query.getResultList();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return users;
    }

    @Override
    public Optional<User> update(UserDto userDto) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Optional<User> optional = Optional.ofNullable(entityManager.find(User.class, userDto.getId()));
        optional.ifPresent(user -> mapper.updateUser(user, userDto));
        entityTransaction.commit();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return optional;
    }

    @Override
    public Optional<User> delete(Long id) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<User> optional = Optional.ofNullable(entityManager.find(User.class, id));
        optional.ifPresent(entityManager::remove);
        transaction.commit();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return optional;
    }

    @Override
    public Optional<User> getByID(Long id) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        Optional<User> optional = Optional.ofNullable(entityManager.find(User.class, id));
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return optional;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            entityManager = JPAUtil.getInstance().getEntityManager();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM " + User.class.getName() + " u WHERE u.login = ?1", User.class);
            return Optional.ofNullable(query.setParameter(1, login).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            entityManager.close();
            JPAUtil.getInstance().deleteEntityManager();
        }
    }

    @Override
    public List<User> findUser(String name, String surname) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM " + User.class.getName() +
                " u WHERE u.name = ?1 and u.surname = ?2", User.class);
        List<User> users = query.setParameter(1, name).setParameter(2, surname).getResultList();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return users;
    }
}
