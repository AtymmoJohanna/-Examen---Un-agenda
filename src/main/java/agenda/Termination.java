package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class Termination {
    private LocalDate start;
    private ChronoUnit frequency;
    private LocalDate end;
    private long iteration;


    public LocalDate terminationDateInclusive() {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        if (end == null){
            if (iteration == 1) {
                end = start;
            }

            // Ajouter (iteration - 1) occurrences à la date de départ
            end = start.plus(iteration - 1, frequency);
        }
       //else{
            return end;
        //}
    }

    public long numberOfOccurrences() {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        if (end != null) {
            iteration = frequency.between(start, end)+1;
        }//else{
            return iteration;
       // }

    }


    /**
     * Constructs a fixed termination event ending at a given date
     * @param start the start time of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     * @see ChronoUnit#between(Temporal, Temporal)
     */
    public Termination(LocalDate start, ChronoUnit frequency, LocalDate terminationInclusive) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        this.start = start;
        this.frequency = frequency;
        this.end = terminationInclusive;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     * @param start the start time of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public Termination(LocalDate start, ChronoUnit frequency, long numberOfOccurrences) {
        // TODO : implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        this.start = start;
        this.frequency = frequency;
        this.iteration = numberOfOccurrences;
    }

}
