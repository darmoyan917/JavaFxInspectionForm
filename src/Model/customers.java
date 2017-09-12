package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class customers {
    //Declare vehicles Table Columns
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty address;
    private StringProperty city;
    private StringProperty state;
    private IntegerProperty zipCode;
    private StringProperty vinNumber;
    private StringProperty email;

    //Constructor
    public customers() {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.state = new SimpleStringProperty();
        this.zipCode = new SimpleIntegerProperty();
        this.vinNumber = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    //firstName
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String vehicle_id){
        this.firstName.set(vehicle_id);
    }

    public StringProperty firstNameProperty(){
        return firstName;
    }

    //lastName
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    //address
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address){
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    //city
    public String getCity() {
        return city.get();
    }

    public void setCity(String city){
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    //state
    public String getState() {
        return state.get();
    }

    public void setState(String state){
        this.state.set(state);
    }

    public StringProperty stateProperty() {
        return state;
    }
    //date

    //zipCode
    public int getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(int zipCode){
        this.zipCode.set(zipCode);
    }

    public IntegerProperty zipCodeProperty(){
        return zipCode;
    }
    public String getVinNumber(){
        return  vinNumber.get();
    }

    public void setVinNumber(String vinNumber){
        this.vinNumber.set(vinNumber);
    }

    public StringProperty vinNumberProperty(){
        return vinNumber;
    }

    public String getemail(){
        return email.get();
    }
    public void setEmail(String email){
        this.email.set(email);
    }
    public StringProperty emailProperty() {
        return email;
    }
}
