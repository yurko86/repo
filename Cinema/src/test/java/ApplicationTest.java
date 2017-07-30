import domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationTest {

    AbstractApplicationContext context;

    User user1;
    User user2;
    User userNotRegistered;

    MyAuditoriumService auditoriums;

    Event event1;
    Event event2;
    ScheduleService eventService;

    MyDiscountService discountService;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("beans.xml");

    }

    @Test
    public void testUserService() throws Exception {

        user1 = (User) context.getBean("user", "ivan", "ivanov", "ivan.ivarov@gmail.com", "2011 07 24");

        assertEquals("Result for user lastname", "ivanov", user1.getLastName());
        assertEquals("Result for user name", "ivan", user1.getFirstName());
        assertEquals("Result for user email", "ivan.ivarov@gmail.com", user1.getEmail());

        user2 = (User) context.getBean("user", "sergey", "efanov", "se@gmail.com", "1999 07 25");
        MyUserService userService = (MyUserService) context.getBean("userService");
        userService.save(user1, null);
        userService.save(user2, null);

        assertEquals("user count before delete", 2, userService.getAll().size());
        userService.remove(userService.getUserByEmail("ivan.ivarov@gmail.com"));
        assertEquals("user count after delete", 1, userService.getAll().size());

        user1 = (User) context.getBean("user", "ivan", "ivanov", "ivan.ivarov@gmail.com", "2011 07 24");

        // create not registered user
        userNotRegistered = (User) context.getBean("user");

    }

    @Test
    public void testAuditoriumService() throws Exception {
        auditoriums = (MyAuditoriumService) context.getBean("auditoriumService");
        auditoriums.setAll((Set) context.getBean("auditoriums"));

        assertEquals("Auditorium Loaded from xml: ", 3, auditoriums.getAll().size());
        assertEquals("Auditorium get by name", "WhiteAuditorium", auditoriums.getByName("WhiteAuditorium").getName());
    }


    @Test
    public void testEventService() throws Exception {

        testAuditoriumService();

        event1 = (Event) context.getBean("event", "event_1", "10.20", EventRating.HIGH, 5);
        event2 = (Event) context.getBean("event", "event_2", "4.50", EventRating.MID, 3);

        eventService = (ScheduleService) context.getBean("scheduleService");

        eventService.addEvent(
                new ScheduleItem(
                        event1,
                        auditoriums.getByName("BlackAuditorium"),
                        LocalDateTime.parse("2017 08 01 11:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")),
                        LocalDateTime.parse("2017 08 01 12:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm"))
                )
        );

        eventService.addEvent(
                new ScheduleItem(
                        event2,
                        auditoriums.getByName("GreenAuditorium"),
                        LocalDateTime.parse("2017 08 02 08:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")),
                        LocalDateTime.parse("2017 08 02 10:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm"))
                )
        );

        assertEquals("Events in schedule ", 2, eventService.getAll().size());

        eventService.removeEvent(event2, LocalDateTime.parse("2017 08 02 08:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")));

        assertEquals("Events in schedule ", 1, eventService.getAll().size());

        eventService.addEvent(
                new ScheduleItem(
                        event2,
                        auditoriums.getByName("GreenAuditorium"),
                        LocalDateTime.parse("2017 08 02 08:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")),
                        LocalDateTime.parse("2017 08 02 10:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm"))
                )
        );
    }

    @Test
    public void testDiscountService() throws Exception {
        discountService = (MyDiscountService) context.getBean("discountService");
        discountService.setAll((List) context.getBean("dStrategies"));
        // create user with birthday today
        LocalDate localDate = LocalDate.now();
        user1 = (User) context.getBean("user", "ivan", "ivanov", "ivan.ivarov@gmail.com", localDate.format(DateTimeFormatter.ofPattern("uuuu MM dd")));

        assertEquals("Discount for person with birthday:", 5, discountService.getDiscount(user1));

        // order 9 tickets for user
        for (int i = 1; i < 9; i++) {
            user1.incrementNumberOfOrderedTickets();
        }
        // and order 10th now
        assertEquals("Discount for person with birthday:", 50, discountService.getDiscount(user1));

    }

    @Test
    public void testBookingService() throws Exception {
        testUserService();
        testAuditoriumService();
        testEventService();
        testAuditoriumService();

        BookingService bookingService = (BookingService) context.getBean("bookingService");

        // event 1 -> price 10.20
        //vip seats 1,3
        // vip ratio 5

        HashSet<Long> tickets = new HashSet<>();
        tickets.add((long) 1);//51 - one vip seat on event1
        assertEquals(51, bookingService.getTicketsPrice(event1, LocalDateTime.parse("2017 08 01 11:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")), user1, tickets), 0);
        tickets.add((long) 2);//+10.2 not vip
        assertEquals(61.2, bookingService.getTicketsPrice(event1, LocalDateTime.parse("2017 08 01 11:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")), user1, tickets), 0);
        tickets.add((long) 3);//+51 one more vip seat
        assertEquals(112.2, bookingService.getTicketsPrice(event1, LocalDateTime.parse("2017 08 01 11:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")), user1, tickets), 0);

        // generate ticket
        Set<Ticket> ticketSet = new HashSet<>();
        Ticket t1 = ((Ticket) context.getBean("ticket", user1, event1, LocalDateTime.parse("2017 08 01 11:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm")), 1));
        ticketSet.add(t1);

        //check if ticket is ordered
        assertFalse("Ticket isn't ordered and not in return", bookingService.getPurchasedTicketsForEvent(event1, LocalDateTime.parse("2017 08 01 11:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm"))).contains(t1));

        //do order the ticket
        bookingService.bookTickets(ticketSet);

        assertTrue("Ticket is ordered and must be in return", bookingService.getPurchasedTicketsForEvent(event1, LocalDateTime.parse("2017 08 01 11:00", DateTimeFormatter.ofPattern("uuuu MM dd HH:mm"))).contains(t1));

    }

    @After
    public void tearDown() throws Exception {
        context.registerShutdownHook();
    }
}