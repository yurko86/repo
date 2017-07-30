package domain;

import java.time.LocalDateTime;
import java.util.*;


public class ScheduleItem {
    Event event;
    Auditorium auditorium;
    LocalDateTime eventStartTime;
    LocalDateTime eventEndDate;

    HashMap<Long, User> bookedSeats;

    public ScheduleItem(Event event, Auditorium auditorium, LocalDateTime eventStartTime, LocalDateTime eventEndDate
    ) {
        this.event = event;
        this.auditorium = auditorium;
        this.eventStartTime = eventStartTime;
        this.eventEndDate = eventEndDate;
        bookedSeats = new HashMap<>();
    }


    public Event getEvent() {
        return this.event;
    }

    public LocalDateTime getEventStartTime() {
        return this.eventStartTime;
    }


    /*
    public String PurchasedTickets() {
        String result = "";
        for (Map.Entry<Long, String> pair : bookedSeats.entrySet()
                ) {
            result += pair.getValue() + "-" + pair.getKey() + ",  ";
        }
        return result;
    }
*/
    public Set<Ticket> getPurchasedTickets() {
        Set<Ticket> result = new HashSet<>();
        for (Map.Entry<Long, User> pair : bookedSeats.entrySet()) {
            result.add(new Ticket(pair.getValue(), this.event, this.eventStartTime, pair.getKey()));
        }
        return result;
    }


    public boolean isSeatBooked(Long seat) {
        for (Long bookedSeat : bookedSeats.keySet()) {
            if (seat == bookedSeat) {
                return true;
            }
        }
        return false;
    }

    public double getTicketPrice(Long seat) {
        if (auditorium.getVipSeats().contains(seat)) {
            return event.getBasePrice() * event.getVipSeatRatio();
        } else
            return event.getBasePrice();
    }

    public void bookSeat(User user, Long seat) throws SeatIsBookedException {
        if (isSeatBooked(seat)) {
            throw new SeatIsBookedException("Seat already booked");
        }
        bookedSeats.putIfAbsent(seat, user);
    }


    public void unBookSeat(Long seat) {
        if (isSeatBooked(seat)) {
            bookedSeats.remove(seat);
        }
    }

//    public boolean bookSeats(User user, String sits) {
//        for (Long sit : getLongArray(sits)) {
//            bookedSeats.putIfAbsent(sit, user);
//            System.out.println(sit + " booked for " + user.getEmail());
//        }
//        return true;
//    }

    private ArrayList<Long> getLongArray(String string) {
        ArrayList<Long> result = new ArrayList<>();
        List<String> stringArray = new ArrayList<String>(Arrays.asList(string.split(",")));
        for (String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Long.parseLong(stringValue));
            } catch (NumberFormatException nfe) {
                System.out.println("Parsing failed! " + stringValue + " can not be a number");
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return "event:" + this.event.getName().toString() + ", room:" +
                this.auditorium.getName() +
                ", start at:" + this.eventStartTime.toString() +
                //+","+this.eventEndDate.toString()
                ",places in room: " + String.valueOf(auditorium.getNumberOfSeats())
                + "(purchased: " + getPurchasedTickets() + ")"
                ;
    }
}
