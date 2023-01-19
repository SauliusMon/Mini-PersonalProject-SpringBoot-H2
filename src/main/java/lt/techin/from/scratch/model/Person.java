package lt.techin.from.scratch.model;


import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String role;
    private String name;

    private String secondName;

    private String password;
    private int room;

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return room == person.room && id.equals(person.id) && role.equals(person.role) && name.equals(person.name) && secondName.equals(person.secondName) && password.equals(person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, name, secondName, password, room);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassword() {
        return password;
    }

    public int getRoom() {
        return room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
