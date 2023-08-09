package by.academy.project.hotel.repositories.user;


import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.util.JPAUtil;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<User> optionalUser = Optional.ofNullable(mapper.buildUser(userDto));
        optionalUser.ifPresent(entityManager::persist);
        transaction.commit();
        entityManager.close();
        return optionalUser;
    }

    @Override
    public List<User> read() {
        entityManager = JPAUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);
        List<User> users = entityManager.createQuery(userCriteriaQuery).getResultList();
        initUsers(users);
        entityManager.close();
        return users;
    }

    @Override
    public Optional<User> update(UserDto userDto) {
        entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Optional<User> optionalUser = Optional.ofNullable(entityManager.find(User.class, userDto.getId()));
        optionalUser.ifPresent(user -> mapper.updateUser(user, userDto));
        optionalUser.ifPresent(this::initUser);
        entityTransaction.commit();
        entityManager.close();
        return optionalUser;
    }

    @Override
    public Optional<User> delete(Long id) {
        try {
            entityManager = JPAUtil.getEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Optional<User> optionalUser = Optional.ofNullable(entityManager.find(User.class, id));
            optionalUser.ifPresent(entityManager::remove);
            optionalUser.ifPresent(this::initUser);
            transaction.commit();
            return optionalUser;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<User> getByID(Long id) {
        entityManager = JPAUtil.getEntityManager();
        Optional<User> optionalUser = Optional.ofNullable(entityManager.find(User.class, id));
        optionalUser.ifPresent(this::initUser);
        entityManager.close();
        return optionalUser;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            entityManager = JPAUtil.getEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = userCriteriaQuery.from(User.class);
            userCriteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("login"), login));
            Optional<User> optionalUser = Optional.ofNullable(entityManager.createQuery(userCriteriaQuery).getSingleResult());
            optionalUser.ifPresent(this::initUser);
            return optionalUser;
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> findUser(String name, String surname) {
        entityManager = JPAUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot).where(
                criteriaBuilder.equal(userRoot.get("name"), name),
                criteriaBuilder.equal(userRoot.get("surname"), surname)
        );
        List<User> users = entityManager.createQuery(userCriteriaQuery).getResultList();
        initUsers(users);
        entityManager.close();
        return users;
    }

    private void initUser(User user) {
        Hibernate.initialize(user.getBookings().stream()
                .map(Booking::getRooms)
                .collect(Collectors.toSet()));
        Hibernate.initialize(user.getAddresses());
    }

    private void initUsers(List<User> users) {
        Hibernate.initialize(users.stream()
                .map(user -> user.getBookings().stream()
                        .map(Booking::getRooms)
                        .collect(Collectors.toSet()))
                .collect(Collectors.toSet()));
        Hibernate.initialize(users.stream()
                .map(User::getAddresses)
                .collect(Collectors.toSet()));
    }
}
