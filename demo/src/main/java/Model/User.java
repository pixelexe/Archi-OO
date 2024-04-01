package Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class User {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int rateStrength;

    public User(int id, String lastName, String firstName, String email, String password) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }

}