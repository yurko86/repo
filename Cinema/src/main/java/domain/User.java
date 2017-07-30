package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class User extends DomainObject {

    private String firstName;

    private String lastName;

    private String email;

    private boolean isNotRegisteredUser;

    private LocalDate birthday;

    private int numberOfOrderedTickets;

    //private NavigableSet<Ticket> tickets = new TreeSet<>();

    public User(String firstName, String lastName, String email, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numberOfOrderedTickets = 0;
        this.isNotRegisteredUser = false;

        try {
            this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("uuuu MM dd"));
        } catch (DateTimeParseException exception) {
            System.err.println("Wrong date of birth!!");
            this.birthday = null;
        }

    }

    public User() {
        this.numberOfOrderedTickets = 0;
        this.isNotRegisteredUser = true;
    }

    public boolean isUserBirthdayToday() {
        if (!this.isNotRegisteredUser) {
            if (birthday.getDayOfMonth() == LocalDate.now().getDayOfMonth() && birthday.getMonthValue() == LocalDate.now().getMonthValue()) {
                System.err.println("today birthday");
                return true;
            }
        }
        return false;
    }

    public int incrementNumberOfOrderedTickets() {
        this.numberOfOrderedTickets += 1;
        return numberOfOrderedTickets;
    }

    public int getNumberOfOrderedTickets(){
        return numberOfOrderedTickets;
    }

    public void setNumberOfOrderedTickets(int numberOfOrderedTickets){
        this.numberOfOrderedTickets = numberOfOrderedTickets;
    }

    public void resetNumberOfOrderedTickets() {
        this.numberOfOrderedTickets = 0;
    }

    public String toString() {
        if (!this.isNotRegisteredUser) {
            return this.firstName + ", " + this.lastName + ", " + this.email + "," + this.birthday;
        }
        return "not registered user";
    }

    public String getFirstName() {
        if (!this.isNotRegisteredUser) {
            return firstName;
        }
        return "not registered user";
    }

    public String getLastName() {
        if (!this.isNotRegisteredUser) {
            return lastName;
        }
        return "not registered user";
    }

    public String getEmail() {
        if (!this.isNotRegisteredUser) {
            return email;
        }
        return "not registered user";
    }


    /*public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets = tickets;
    }
*/
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
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
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

}
