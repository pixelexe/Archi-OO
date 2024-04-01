package Model;

public class GlobeTrotterUser extends UserCommenter implements UserRater {

    public GlobeTrotterUser(int id, String lastName, String firstName, String email, String password) {
        super(id, lastName, firstName, email, password);
        this.setRateStrength(4);
    }

    @Override
    public void rate(Place place, int rating) {

    }

}
