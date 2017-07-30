package service;

import domain.Event;
import domain.ScheduleItem;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 23.07.2017.
 */
public class ScheduleService {
    Set<ScheduleItem> schedule = new HashSet<>();


    public Set getAll(){
        return schedule;
    }

    public void addEvent(ScheduleItem eventItem) {
        schedule.add(eventItem);
    }

    public void removeEvent(Event event, LocalDateTime eventStartTime){
        ScheduleItem e = getScheduledItem(event, eventStartTime);
        schedule.remove(e);
    }

    public void displaySchedule() {
        for (ScheduleItem s : schedule) {
            System.out.println(s.toString());
        }
    }



    public ScheduleItem getScheduledItem(Event event, LocalDateTime eventStartTime){
        for (ScheduleItem scheduleItem: schedule
             ) {
            if (scheduleItem.getEvent().equals(event) & scheduleItem.getEventStartTime().equals(eventStartTime)){
                return scheduleItem;
            }
        }

        System.err.println("no event according input!");
        return null;
    }
}
