package service;

import domain.Auditorium;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;



public class MyAuditoriumService implements AuditoriumService {

    private Set<Auditorium> auditoriums;

    public void setAll (Set auditoriums){
        this.auditoriums = auditoriums;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return this.auditoriums;
    }

    /**
     * display all auditoriums (for testing)
     */
    public void displayAll(){
        for (Auditorium a:this.auditoriums){
            System.out.println(a.getName() + "("+a.getNumberOfSeats()+","+a.getVipSeats()+")");
        }
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Auditorium result = null;
        for (Auditorium i:this.auditoriums){
            if (name.equals(i.getName())){
                result = i;
            }
        }
        return result;
    }
}
