package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Event extends DomainObject {

    private String name;

    private double basePrice;

    private EventRating rating;

    private int vipSeatRatio;

    Event(String name, String basePrice, EventRating rating, int vipSeatRatio) {
        this.name = name;
        this.basePrice = Double.parseDouble(basePrice);
        this.rating = rating;
        this.vipSeatRatio = vipSeatRatio;
    }


    public double getVipSeatRatio(){
        return this.vipSeatRatio;
    }
    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
