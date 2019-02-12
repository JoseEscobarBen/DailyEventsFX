// Person class that represents an entry in an address book.
package Objects;

public class User {

    private int id_user;
    private String turn_user;
    private String surnames_user;
    private String name_user;
    private String dni_user;
    private String address_user;
    private String phone_user;
    private String login_user;
    private String password_user;

    // constructor
    public User() {}

    // constructor
    public User(int id_user,String turnUser,String surnames_user, String name_user, String dni_user,
            String address_user, String phone_user,String login_user, String password_user) {
        this.id_user = id_user;
        this.turn_user = turnUser;
        this.surnames_user = surnames_user;
        this.name_user = name_user;
        this.dni_user = dni_user;
        this.address_user = address_user;
        this.phone_user = phone_user;
        this.login_user = login_user;
        this.password_user = password_user;
    }
    
    public int getId_user(){
        return id_user;
    }
    
    public void setId_user(int id_user){
        this.id_user = id_user;
    }

    public String getTurn_user() {
        return turn_user;
    }

    public void setTurn_user(String turn_user) {
        this.turn_user = turn_user;
    }

    public String getSurnames_user() {
        return surnames_user;
    }

    public void setSurnames_user(String surnames_user) {
        this.surnames_user = surnames_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getDni_user() {
        return dni_user;
    }

    public void setDni_user(String dni_user) {
        this.dni_user = dni_user;
    }

    public String getAddress_user() {
        return address_user;
    }

    public void setAddress_user(String address_user) {
        this.address_user = address_user;
    }

    public String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(String phone_user) {
        this.phone_user = phone_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    
}
