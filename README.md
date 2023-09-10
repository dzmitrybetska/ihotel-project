<br />
<div align="center">
<h3 align="center">IHotel project</h3>

  <p align="center">
   Dzmitry Betska’s study project!
    <br />
    <a <strong>Sample hotel room reservation</strong></a>

  </p>
</div>

## About The Project

This hotel booking application is a quick way to book a room in our hotel for every taste and budget. You can quickly select and book a hotel room. The hotel has a flexible discount system. By choosing the FLEXIBLE or ADVANCE PURCHASE rates, you can have a great breakfast in our hotel restaurant.

By staying with us you will make another of the right decisions for your trip.

You can contact us by email: d.betska@gmail.com

The project developed gradually over four months of training:
* First month - web servlet application
* Second month - Hibernate, Hibernate query language, Criteria API.
* The third month is a gradual transition to spring.
* Month four - Spring Boot, Spring Data JPA, JUnit 5, Spring Cloud, Netflix Eureka

## Road map

- [x] Spring Boot
- [x] Spring Data JPA
- [x] CRUD service for entities
- [x] Cloud application configuration
- [x] Spring Cloud Netflix Eureka
- [x] Remote API for getting room descriptions
- [x] Transition and development of microservice application architecture
- [x] Test for service

## Technologies

The project was developed using:

* Spring
    * Data JPA
    * Validation
    * Cloud config
    * Test
    * OpenFeign
    * Netflix Eureka
* PostgreSQL
* Mapstruct
* Lombok

## Remote API

1. Remote application registration - <a href="https://github.com/dzmitrybetska/registry">Eureka server</a>
2. Cloud settings of the main application - <a href="https://github.com/dzmitrybetska/settings-api">Settings service</a>
3. Obtaining a description of a room depending on its category - <a href="https://github.com/dzmitrybetska/description-server">Description service</a>

## Demonstration of some project possibilities

Through logging of controllers implemented.
java
    
    @Pointcut("execution(* by.academy.project.hotel.controllers..*(..)) && !@annotation(SkipLogging)")
    public void pointCut() {}

    @Before("pointCut()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        Object[] args = joinPoint.getArgs();
        String shortString = joinPoint.getSignature().toShortString();
        log.info(REQUEST_LOG_PATTERN, method, requestURI, args, shortString);
    }

    @AfterReturning(pointcut = "pointCut()", returning = "response")
    public void logResponse(JoinPoint joinPoint, Object response) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String shortString = joinPoint.getSignature().toShortString();
        log.info(RESPONSE_LOG_PATTERN, method, requestURI, shortString,
                ofNullable(response)
                        .orElse(EMPTY));
    }

Validation of phone numbers has been implemented

    @Constraint(validatedBy = PhoneNumberConstraintValidator.class)
    @Target({ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PhoneNumber {
    String message() default "Invalid number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    }

    public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneField == null) {
            return false;
        }
        return phoneField.matches("^\\+?[0-9]{10,12}$");
      }
    }

Implemented testing of Services layer methods

    @SpringBootTest
    @ExtendWith(MockitoExtension.class)
    @DisplayName("Testing methods of the BookingService")
    public class BookingServiceTest {

    private BookingService bookingService;
    @Mock
    private UserService userService;
    @Mock
    private RoomService roomService;
    @Autowired
    private BookingMapper bookingMapper;
    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void init() {
        bookingService = new BookingServiceImpl(userService, roomService, bookingMapper, bookingRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingBookArguments.class)
    void bookTest(BookingRequest bookingRequest, Booking booking, BookingResponse bookingResponse,
                  User user, Room room) {
        when(userService.findUserByIdForBooking(any(Long.class))).thenReturn(Optional.of(user));
        when(roomService.findRoomsByIdForBooking(anyList())).thenReturn(List.of(room));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        BookingResponse actualBookingResponse = bookingService.book(bookingRequest);
        assertEquals(bookingResponse, actualBookingResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingGetArguments.class)
    void readBookingsTest(Booking booking, BookingResponse bookingResponse) {
        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        List<BookingResponse> bookingResponseList = bookingService.read();
        assertEquals(Collections.singletonList(bookingResponse), bookingResponseList);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingUpdateArguments.class)
    void updateBookingTest(BookingRequest bookingRequest, Booking booking, BookingResponse bookingResponse, Room newRoom) {
        when(bookingRepository.findById(any(Long.class))).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(roomService.findRoomsByIdForBooking(anyList())).thenReturn(List.of(newRoom));
        BookingResponse actualBookingResponse = bookingService.update(booking.getId(), bookingRequest);
        assertEquals(bookingResponse, actualBookingResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingGetArguments.class)
    void findBookingById(Booking booking, BookingResponse bookingResponse) {
        when(bookingRepository.findById(any(Long.class))).thenReturn(Optional.of(booking));
        BookingResponse actualBookingResponse = bookingService.findBookingById(booking.getId());
        assertEquals(bookingResponse, actualBookingResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingInvalidArguments.class)
    void findBookingByIdExpectedException(Long id) {
        when(bookingRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookingService.findBookingById(id));
    }
    }

Implemented exception tracking and handling layer

    @Slf4j
    @RestControllerAdvice
    public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityByIdNotFoundException(EntityNotFoundException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintValidationException(ConstraintViolationException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return buildErrorResponse(exception);
    }

    private ErrorResponse buildErrorResponse(EntityNotFoundException exception) {
        return ErrorResponse.builder()
                .errors(buildErrors(exception))
                .status(BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }

    private ErrorResponse buildErrorResponse(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .errorCount(exception.getFieldErrorCount())
                .time(LocalDateTime.now())
                .status(BAD_REQUEST)
                .errors(buildValidationErrors(exception))
                .build();
    }

    private ErrorResponse buildErrorResponse(ConstraintViolationException exception) {
        return ErrorResponse.builder()
                .errorCount(exception.getConstraintViolations().size())
                .errors(buildValidationErrors(exception))
                .status(BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }

    private List<Error> buildErrors(EntityNotFoundException exception) {
        return List.of(new Error(exception.getMessage()));
    }

    private List<Error> buildValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors()
                .stream()
                .map(this::getError)
                .toList();
    }

    private List<Error> buildValidationErrors(ConstraintViolationException exception) {
        return exception.getConstraintViolations()
                .stream()
                .map(this::getError)
                .toList();
    }

    private Error getError(FieldError fieldError) {
        return new Error(fieldError.getField(), fieldError.getDefaultMessage());
    }

    private Error getError(ConstraintViolation<?> violation) {
        return new Error(violation.getPropertyPath().toString(), violation.getMessage());
    }
    }

## Contact

Dzmitry Betska - d.betska@gmail.com

Project Link: [https://github.com/dzmitrybetska?tab=repositories](https://github.com/dzmitrybetska?tab=repositories)

## My trainer

Dmitriy Steba - stebadmitriy@gmail.com