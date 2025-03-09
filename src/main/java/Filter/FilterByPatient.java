package Filter;

import Domain.Appointment;
import Domain.Person;

public class FilterByPatient implements Filter<Appointment> {
    private final Person person;

    public FilterByPatient(Person person) {
        this.person = person; //initially wanted to sort by different people but that's kinda dumb since i don't really have the same person repeating a lot
    }

    @Override
    public boolean accept(Appointment appointment) {
        return appointment.getPerson().getName().equals(person); //fixed
    }
}