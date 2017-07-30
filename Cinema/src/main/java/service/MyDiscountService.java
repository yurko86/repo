package service;

import domain.Discount;
import domain.User;

import java.util.List;

public class MyDiscountService {

    List<Discount> strategies;

    public void setAll(List strategies) {
        this.strategies = strategies;
    }

    public List<Discount> getAll() {
        return strategies;
    }

    public void showAll() {
        for (Discount d : strategies) {
            System.out.println(d.toString());
        }
    }

    int getDiscountRatioByName(String name) {
        for (Discount d : strategies) {
            if (name.equals(d.getDiscountName())) {
                return d.getDiscountRatio();
            }
        }
        return 1;
    }

    /**
     * return discount for 1 ticket for defined user
     * @param user
     * @return
     */
    public int getDiscount(User user) {
        int discount = 0;
        //check birthday
        if (user.isUserBirthdayToday()) {
            discount = getDiscountRatioByName("Birthday");
        }

        //check 10th ticket
        if (user.incrementNumberOfOrderedTickets() == 10) {
            //set up counter to 0
            user.resetNumberOfOrderedTickets();
            return Math.max(discount, getDiscountRatioByName("10thTicket"));
        }
        return discount;
    }

}







