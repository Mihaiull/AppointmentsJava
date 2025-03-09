package Repository;

import Domain.Appointment;
import Filter.Filter;
import java.util.ArrayList;

public class FilterRepository extends MemoryRepository<Integer, Appointment>
{
    private final Filter<Appointment> filter;

    public FilterRepository(Filter<Appointment> filter)
    {
        this.filter = filter;
    }

    public Iterable<Appointment> filterAppointments()
    {
        ArrayList<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : getAll())
        {
            if (filter.accept(appointment))
            {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }
}