package Model;

public class BeginnerUser extends User implements UserRater{

    public BeginnerUser(int id, String lastName, String firstName, String email, String password) {
        super(id, lastName, firstName, email, password);
        this.setRateStrength(1);
    }

    @Override
    public void rate(Place place, int rating){
        place.addRating(this, rating);
    }
}
