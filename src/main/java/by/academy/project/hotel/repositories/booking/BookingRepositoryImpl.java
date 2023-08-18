package by.academy.project.hotel.repositories.booking;

import by.academy.project.hotel.dto.BookingDto;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.util.JPAUtil;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class BookingRepositoryImpl implements BookingRepository {

    private static BookingRepositoryImpl instance;
    private final BookingMapper bookingMapper = BookingMapper.getInstance();
    private EntityManager entityManager;

    private BookingRepositoryImpl() {
    }

    public static BookingRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new BookingRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Optional<Booking> add(BookingDto bookingDto, Long userId, List<Long> roomsId) {
        try {
            entityManager = JPAUtil.getEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Optional<Booking> optionalBooking = Optional.ofNullable(bookingMapper.buildBooking(bookingDto));
            Optional<User> optionalUser = Optional.ofNullable(entityManager.find(User.class, userId));
            Set<Room> rooms = roomsId.stream()
                    .map(id -> entityManager.find(Room.class, id).setIsBooked(true))
                    .collect(Collectors.toSet());
            if (optionalBooking.isPresent() && optionalUser.isPresent() && rooms.size() != 0) {
                entityManager.persist(optionalBooking.get().setUser(optionalUser.get()).setRooms(rooms));
                optionalBooking.ifPresent(this::enrichBooking);
                transaction.commit();
                return optionalBooking;
            } else {
                return Optional.empty();
            }
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Booking> read() {
        entityManager = JPAUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Booking> bookingCriteriaQuery = criteriaBuilder.createQuery(Booking.class);
        Root<Booking> bookingRoot = bookingCriteriaQuery.from(Booking.class);
        bookingCriteriaQuery.select(bookingRoot);
        List<Booking> bookings = entityManager.createQuery(bookingCriteriaQuery).getResultList();
        enrichBookings(bookings);
        entityManager.close();
        return bookings;
    }

    @Override
    public Optional<Booking> update(BookingDto bookingDto) {
        entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<Booking> optionalBooking = Optional.ofNullable(entityManager.find(Booking.class, bookingDto.getId()));
        optionalBooking.ifPresent(booking -> bookingMapper.updateBooking(booking, bookingDto));
        optionalBooking.ifPresent(this::enrichBooking);
        transaction.commit();
        entityManager.close();
        return optionalBooking;
    }

    @Override
    public Optional<Booking> delete(Long id) {
        try {
            entityManager = JPAUtil.getEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Optional<Booking> optionalBooking = Optional.ofNullable(entityManager.find(Booking.class, id));
            optionalBooking.ifPresent(entityManager::remove);
            optionalBooking.ifPresent(this::enrichBooking);
            transaction.commit();
            return optionalBooking;
        } finally {
            entityManager.close();
        }
    }

    private void enrichBooking(Booking booking) {
        Hibernate.initialize(booking.getUser().getAddresses());
        Hibernate.initialize(booking.getRooms());
    }

    private void enrichBookings(List<Booking> bookings) {
        Hibernate.initialize(bookings.stream()
                .map(booking -> booking.getUser().getAddresses())
                .collect(Collectors.toSet()));
        Hibernate.initialize(bookings.stream()
                .map(Booking::getRooms)
                .collect(Collectors.toSet()));
    }
}
