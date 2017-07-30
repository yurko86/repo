package service;

import domain.Event;
import domain.SeatIsBookedException;
import domain.Ticket;
import domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MyBookingService implements BookingService {

    ScheduleService scheduleService;
    MyDiscountService discountService;

    public MyBookingService(ScheduleService scheduleService, MyDiscountService discountService) {
        this.scheduleService = scheduleService;
        this.discountService = discountService;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) throws SeatIsBookedException {
        double ticketPriceWithoutDiscount;
        double endTicketPrice;
        double orderPrice = 0;

        int numberOfOrderedTickets = user.getNumberOfOrderedTickets();

        for (Long seat : seats) {
            //check avalability of seat - if at least one seat is not available available generate error
            if (scheduleService.getScheduledItem(event, dateTime).isSeatBooked(seat)) {
                throw new SeatIsBookedException("Cant calculate order. Seat " + seat + " already booked. Please choose another");
            }
            //base ticket price vip/no vip
            ticketPriceWithoutDiscount = scheduleService.getScheduledItem(event, dateTime).getTicketPrice(seat);
            // minus discount from price
            endTicketPrice = ticketPriceWithoutDiscount - ticketPriceWithoutDiscount * discountService.getDiscount(user) / 100;
            orderPrice += endTicketPrice;
        }

        //return to value of ordered ticket which was before calculation
        user.setNumberOfOrderedTickets(numberOfOrderedTickets);
        return orderPrice;
    }


    public Set<Ticket> generateTickets(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) throws SeatIsBookedException {

        Set<Ticket> result = new HashSet<>();
        for (Long seat : seats) {
            //check avalability of seat - if at least one seat is not available available generate error
            if (scheduleService.getScheduledItem(event, dateTime).isSeatBooked(seat)) {
                throw new SeatIsBookedException("Cant calculate order. Seat " + seat + " already booked. Please choose another");
            }
            result.add(new Ticket(user,event,dateTime,seat));
        }
        return result;
    }


    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        // try to book all seats from parameter
        try {
            for (Ticket ticket : tickets) {
                scheduleService.getScheduledItem(ticket.getEvent(), ticket.getDateTime()).bookSeat(ticket.getUser(), ticket.getSeat());
            }

        } catch (SeatIsBookedException e) {
            e.printStackTrace();
            // in case of seat is already booked, remove all ordered
            for (Ticket ticket : tickets) {
                scheduleService.getScheduledItem(ticket.getEvent(), ticket.getDateTime()).unBookSeat(ticket.getSeat());
            }
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return scheduleService.getScheduledItem(event, dateTime).getPurchasedTickets();

    }
}
