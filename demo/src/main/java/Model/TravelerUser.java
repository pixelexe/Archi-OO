package Model;

public class TravelerUser extends User implements UserRater{

    public TravelerUser(int id, String lastName, String firstName, String email, String password) {
        super(id, lastName, firstName, email, password);
        this.setRateStrength(2);
    }

    @Override
    public void rate(Place place, int rating) {

    }
}
