package agenda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private Duration myDuration;

    private Repetition repetition ;




    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;

    }

    public void setRepetition(ChronoUnit frequency) {
        // TODO : implémenter cette méthode
       // throw new UnsupportedOperationException("Pas encore implémenté");
        repetition = new Repetition(frequency);
    }

    public void addException(LocalDate date) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        if(repetition!=null){
            repetition.addException(date);
        }
    }

    public void setTermination(LocalDate terminationInclusive) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        repetition.setTermination(new Termination(myStart.toLocalDate(), repetition.getFrequency(), terminationInclusive));
    }

    public void setTermination(long numberOfOccurrences) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        repetition.setTermination(new Termination(myStart.toLocalDate(), repetition.getFrequency(), numberOfOccurrences));
    }

    public int getNumberOfOccurrences() {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        int retour = 0;
        if(repetition!=null){
            if(repetition.getTermination()!=null){
                retour = (int) repetition.getTermination().numberOfOccurrences();
            }
        }
        return retour;
    }

    public LocalDate getTerminationDate() {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        LocalDate retour = null;
        if(repetition!=null){
            if(repetition.getTermination()!=null){
                retour = repetition.getTermination().terminationDateInclusive();
            }
        }
        return retour;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        boolean retour = false;

        if (this.repetition==null){
            if( myStart.toLocalDate().isEqual(aDay) || myStart.plus(myDuration).toLocalDate().isEqual(aDay) ){
                retour = true;
            }
        }
        else{
            if(!repetition.getDateException().contains(aDay)) {
                ChronoUnit freq = repetition.getFrequency();
                if( myStart.toLocalDate().isEqual(aDay) || myStart.plus(myDuration).toLocalDate().isEqual(aDay) ){
                    retour = true;
                }
                if(myStart.toLocalDate().isBefore(aDay)){
                    if(freq == ChronoUnit.DAYS) {
                        retour = true;
                    }
                    if(freq == ChronoUnit.MONTHS) {
                        if(myStart.toLocalDate().getMonth()!=aDay.getMonth() && myStart.toLocalDate().getYear()!=aDay.getYear()) {retour = true;}
                    }
                    if(freq == ChronoUnit.WEEKS) {
                        int numSemMyStart = myStart.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                        int numSemADay = aDay.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                        if (!(myStart.toLocalDate().getYear()==aDay.getYear() && numSemADay==numSemMyStart)){
                            retour = true;
                        }
                    }
                }
            }

            if (retour && repetition.getTermination() != null) {
                // Exclure les dates après la terminaison
                if (repetition.getTermination().terminationDateInclusive().isBefore(aDay) && !repetition.getTermination().terminationDateInclusive().isBefore(aDay)) {
                    retour = false;
                }
            }

        }

         return retour;
    }

    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }
}
