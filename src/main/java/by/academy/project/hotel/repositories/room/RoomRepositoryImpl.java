package by.academy.project.hotel.repositories.room;


import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public final class RoomRepositoryImpl implements RoomRepository {
    private static RoomRepositoryImpl instance;
    private final RoomMapper mapper = RoomMapper.getInstance();
    private EntityManager entityManager;

    private RoomRepositoryImpl() {
    }

    public static RoomRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new RoomRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Optional<Room> add(RoomDto roomDto) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Room room = mapper.buildRoom(roomDto);
        entityManager.persist(room);
        transaction.commit();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return Optional.ofNullable(room);
    }

    @Override
    public List<Room> read() {
        entityManager = JPAUtil.getInstance().getEntityManager();
        TypedQuery<Room> query = entityManager.createQuery("SELECT r FROM by.academy.project.hotel.entities.room.Room r ", Room.class);
        List<Room> rooms = query.getResultList();
        JPAUtil.getInstance().deleteEntityManager();
        return rooms;
    }

    @Override
    public Optional<Room> update(RoomDto roomDto) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<Room> optional = Optional.ofNullable(entityManager.find(Room.class, roomDto.getId()));
        optional.ifPresent(room -> mapper.updateRoom(room, roomDto));
        transaction.commit();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return optional;
    }

    @Override
    public Optional<Room> delete(Long id) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<Room> optional = Optional.ofNullable(entityManager.find(Room.class, id));
        optional.ifPresent(entityManager::remove);
        transaction.commit();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return optional;
    }

    @Override
    public Optional<Room> getByID(Long id) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        Optional<Room> optional = Optional.ofNullable(entityManager.find(Room.class, id));
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return optional;
    }

    @Override
    public Optional<Room> getRoomByNumber(String number) {
        try {
            entityManager = JPAUtil.getInstance().getEntityManager();
            TypedQuery<Room> query = entityManager.createQuery("SELECT r FROM " + Room.class.getName() + " r WHERE r.number = ?1", Room.class);
            return Optional.ofNullable(query.setParameter(1, number).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            entityManager.close();
            JPAUtil.getInstance().deleteEntityManager();
        }
    }

    @Override
    public List<Room> searchRoomsByCategory(RoomCategory category) {
        entityManager = JPAUtil.getInstance().getEntityManager();
        TypedQuery<Room> query = entityManager.createQuery("SELECT r FROM " + Room.class.getName() + " r WHERE r.roomCategory = ?1", Room.class);
        List<Room> rooms = query.setParameter(1, category).getResultList();
        entityManager.close();
        JPAUtil.getInstance().deleteEntityManager();
        return rooms;
    }

}