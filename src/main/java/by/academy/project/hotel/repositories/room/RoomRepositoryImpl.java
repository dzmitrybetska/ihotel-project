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
    private EntityManager em;

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
        em = JPAUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        Room room = mapper.buildRoom(roomDto);
        em.persist(room);
        em.getTransaction().commit();
        em.close();
        JPAUtil.getInstance().deleteEntityManager();
        return Optional.ofNullable(room);
    }

    @Override
    public List<Room> read() {
        em = JPAUtil.getInstance().getEntityManager();
        TypedQuery<Room> query = em.createQuery("SELECT r FROM by.academy.project.hotel.entities.room.Room r ", Room.class);
        List<Room> rooms = query.getResultList();
        JPAUtil.getInstance().deleteEntityManager();
        return rooms;
    }

    @Override
    public Optional<Room> update(RoomDto roomDto) {
        em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Room room = em.find(Room.class, roomDto.getId());
            mapper.updateRoom(room, roomDto);
            em.getTransaction().commit();
            return Optional.of(room);
        } catch (NullPointerException e) {
            return Optional.empty();
        } finally {
            em.close();
            JPAUtil.getInstance().deleteEntityManager();
        }
    }

    @Override
    public Optional<Room> delete(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            Room room = em.find(Room.class, id);
            em.remove(room);
            et.commit();
            return Optional.of(room);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        } finally {
            em.close();
            JPAUtil.getInstance().deleteEntityManager();
        }
    }

    @Override
    public Optional<Room> getByID(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        Room room = em.find(Room.class, id);
        em.close();
        JPAUtil.getInstance().deleteEntityManager();
        return Optional.ofNullable(room);
    }

    @Override
    public Optional<Room> getRoomByNumber(String number) {
        em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Room> query = em.createQuery("SELECT r FROM " + Room.class.getName() + " r WHERE r.number = ?1", Room.class);
            Room room = query.setParameter(1, number).getSingleResult();
            return Optional.of(room);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
            JPAUtil.getInstance().deleteEntityManager();
        }
    }

    @Override
    public List<Room> searchRoomsByCategory(RoomCategory category) {
        em = JPAUtil.getInstance().getEntityManager();
        TypedQuery<Room> query = em.createQuery("SELECT r FROM " + Room.class.getName() + " r WHERE r.roomCategory = ?1", Room.class);
        List<Room> rooms = query.setParameter(1, category).getResultList();
        em.close();
        JPAUtil.getInstance().deleteEntityManager();
        return rooms;
    }

}