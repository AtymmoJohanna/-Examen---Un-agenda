package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {

    private ArrayList<Event> evennements;

    public Agenda(){
        this.evennements = new ArrayList<>();
    }
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        evennements.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        ArrayList<Event> listEventDay = new ArrayList<>();
        for (Event e : evennements) {
            if (e.isInDay(day)){
                listEventDay.add(e);
            }
        }
        return listEventDay;
    }
}
