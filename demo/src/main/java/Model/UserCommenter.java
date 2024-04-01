package Model;

public abstract class UserCommenter extends User {

    public UserCommenter(int id, String lastName, String firstName, String email, String password) {
        super(id, lastName, firstName, email, password);
    }

    public void comment(Place place, String comment) {
        place.addComment(this, comment);
    }
}
