package domain;


public class Discount {

    private String name;
    private Integer discount;

    public Discount(String name, Integer discount) {
        this.name = name;
        this.discount = discount;
    }

    public int getDiscountRatio() {
        return this.discount;
    }

    public String getDiscountName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name+"-"+this.discount;
    }

}
