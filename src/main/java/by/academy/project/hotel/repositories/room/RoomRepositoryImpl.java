package by.academy.project.hotel.repositories.room;


import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.mappers.RoomMapper;
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

import static by.academy.project.hotel.util.configuration.Constants.NUMBER;
import static by.academy.project.hotel.util.configuration.Constants.ROOM_CATEGORY;

public final class RoomRepositoryImpl implements RoomRepository {

    private static RoomRepositoryImpl instance;
    private final RoomMapper roomMapper = RoomMapper.getInstance();
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
        entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<Room> optionalRoom = Optional.ofNullable(roomMapper.buildRoom(roomDto));
        optionalRoom.ifPresent(entityManager::persist);
        transaction.commit();
        entityManager.close();
        return optionalRoom;
    }

    @Override
    public List<Room> read() {
        entityManager = JPAUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> roomCriteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> roomRoot = roomCriteriaQuery.from(Room.class);
        roomCriteriaQuery.select(roomRoot);
        List<Room> rooms = entityManager.createQuery(roomCriteriaQuery).getResultList();
        enrichRooms(rooms);
        entityManager.close();
        return rooms;
    }

    @Override
    public Optional<Room> update(RoomDto roomDto) {
        entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<Room> optionalRoom = Optional.ofNullable(entityManager.find(Room.class, roomDto.getId()));
        optionalRoom.ifPresent(room -> roomMapper.updateRoom(room, roomDto));
        optionalRoom.ifPresent(this::enrichRoom);
        transaction.commit();
        entityManager.close();
        return optionalRoom;
    }

    @Override
    public Optional<Room> delete(Long id) {
        try {
            entityManager = JPAUtil.getEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Optional<Room> optionalRoom = Optional.ofNullable(entityManager.find(Room.class, id));
            optionalRoom.ifPresent(entityManager::remove);
            optionalRoom.ifPresent(this::enrichRoom);
            transaction.commit();
            return optionalRoom;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Room> getByID(Long id) {
        entityManager = JPAUtil.getEntityManager();
        Optional<Room> optionalRoom = Optional.ofNullable(entityManager.find(Room.class, id));
        optionalRoom.ifPresent(this::enrichRoom);
        entityManager.close();
        return optionalRoom;
    }

    @Override
    public Optional<Room> getRoomByNumber(String number) {
        try {
            entityManager = JPAUtil.getEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> roomCriteriaQuery = criteriaBuilder.createQuery(Room.class);
            Root<Room> roomRoot = roomCriteriaQuery.from(Room.class);
            roomCriteriaQuery.select(roomRoot).where(criteriaBuilder.equal(roomRoot.get(NUMBER), number));
            Optional<Room> optionalRoom = Optional.ofNullable(entityManager.createQuery(roomCriteriaQuery).getSingleResult());
            optionalRoom.ifPresent(this::enrichRoom);
            return optionalRoom;
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Room> searchRoomsByCategory(RoomCategory category) {
        entityManager = JPAUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> roomCriteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> roomRoot = roomCriteriaQuery.from(Room.class);
        roomCriteriaQuery.select(roomRoot).where(criteriaBuilder.equal(roomRoot.get(ROOM_CATEGORY), category));
        List<Room> rooms = entityManager.createQuery(roomCriteriaQuery).getResultList();
        enrichRooms(rooms);
        entityManager.close();
        return rooms;
    }

    private void enrichRoom(Room room) {
        Hibernate.initialize(room.getBookings().stream()
                .map(booking -> booking.getUser().getAddresses())
                .collect(Collectors.toSet()));
    }

    private void enrichRooms(List<Room> rooms) {
        Hibernate.initialize(rooms.stream()
                .map(room -> room.getBookings().stream()
                        .map(booking -> booking.getUser().getAddresses())
                        .collect(Collectors.toSet()))
                .collect(Collectors.toSet()));
    }
}