package Filter;

import Domain.Appointment;

public class FilterByCondition implements Filter<Appointment> {
    private final String condition;

    public FilterByCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean accept(Appointment appointment) {
        return appointment.getCondition().equals(condition);
    }
}