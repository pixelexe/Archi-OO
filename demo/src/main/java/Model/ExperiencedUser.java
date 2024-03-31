package Model;

public class ExperiencedUser extends UserCommenter implements UserRater {

    public ExperiencedUser(int id, String lastName, String firstName, String email, String password) {
        super(id, lastName, firstName, email, password);
        this.setRateStrength(3);
    }

    @Override
    public void rate(Place place, int rating) {

    }
}

