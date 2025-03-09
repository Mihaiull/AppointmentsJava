package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Identifiable<Integer>,Serializable {

    private Integer personId;
    private String surname;
    private String name;
    private int age;

    public Person(Integer id, String surname, String name, int age){
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.personId = id;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public Integer getId(){
        return this.personId;
    }

    public String getSurname(){
        return this.surname;
    }

    public int getAge(){
        return this.age;
    }

    @Override
    public void setId(Integer id){
        this.personId = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public String toString(){
        return name + " " + surname + "  Age: " + age + "y/o"; 
    }

    @Override
    public boolean equals(Object obj){
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return Objects.equals(this.personId, person.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}

