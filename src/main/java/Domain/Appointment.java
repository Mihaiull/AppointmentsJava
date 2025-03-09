package Domain;
import java.io.Serializable;

public class Appointment implements Identifiable<Integer>, Serializable {

    private Person person;
    private int year;
    private String month;
    private int hour;
    private int minute;
    private String notes;
    private String condition;
    private int day;
    private Integer appointmentid;

    public Appointment(Integer id, Person person, int year, String month, int day, int hour, int minute, String condition,  String notes) {
        this.person = person;
        this.year = year;
        this.hour = hour;
        this.month = month;
        this.minute = minute;
        this.notes = "";
        this.condition = condition;
        this.day = day;
        this.appointmentid = id;
    }

    //who could tell these were added later
    @Override
    public Integer getId(){
        return appointmentid;
    }

    @Override
    public void setId(Integer id){
        appointmentid = id;
    }

    public Person getPerson() {
        return this.person;
    }

    public int getYear(){
        return this.year;
    }

    public String getMonth(){
        return this.month;
    }

    public int getDay(){
        return this.day;
    }

    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return this.minute;
    }

    public String getCondition(){
        return this.condition;
    }

    public String getNotes(){
        return this.notes;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setHour(int hour){
        this.hour=hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public void setCondition(String condition){
        this.condition = condition;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    @Override
    public String toString(){
        return "Appointment ID: " + appointmentid + " Patient: " + person.toString() + "Condition: " + condition +"\n Date and time: " + year + " " + month + " " + day + "  " + hour + ":" + minute + "\n Notes: " + notes;
    }
}
