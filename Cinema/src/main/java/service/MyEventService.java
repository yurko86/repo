package service;

import domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyEventService implements EventService {

    List<Event> events = new ArrayList<>();


    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        Event result = null;
        for (Event i : events) {
            if (name == i.getName()) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public Event save(@Nonnull Event object) {
        events.add(object);
        return object;
    }

    @Override
    public void remove(@Nonnull Event object) {
        events.remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return events.get(Math.toIntExact(id));
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return events;
    }


}
