package domain;

public class SeatIsBookedException extends Exception {
    public SeatIsBookedException(String msg) {
        super(msg);
    }
}
