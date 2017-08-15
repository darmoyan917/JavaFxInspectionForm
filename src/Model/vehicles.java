package Model;

import javafx.beans.property.*;

public class vehicles {
        //Declare vehicles Table Columns
        private IntegerProperty vehicle_id;
        private StringProperty make;
        private StringProperty model;
        private StringProperty bodyType;
        private StringProperty color;
        private IntegerProperty year;

        //Constructor
        public vehicles() {
            this.vehicle_id = new SimpleIntegerProperty();
            this.make = new SimpleStringProperty();
            this.model = new SimpleStringProperty();
            this.bodyType = new SimpleStringProperty();
            this.color = new SimpleStringProperty();
            this.year = new SimpleIntegerProperty();
        }

        //vehicle_id
        public int getvehicle_id() {
            return vehicle_id.get();
        }

        public void setVehicle_id(int employeeId){
            this.vehicle_id.set(employeeId);
        }

        public IntegerProperty vehidleidProperty(){
            return vehicle_id;
        }

        //make
        public String getMake () {
            return make.get();
        }

        public void setMake(String firstName){
            this.make.set(firstName);
        }

        public StringProperty makeProperty() {
            return make;
        }

        //model
        public String getModel () {
            return model.get();
        }

        public void setModel(String lastName){
            this.model.set(lastName);
        }

        public StringProperty modelProperty() {
            return model;
        }

        //bodyType
        public String getBodyType() {
            return bodyType.get();
        }

        public void setBodyType(String bodyType){
            this.bodyType.set(bodyType);
        }

        public StringProperty bodyTypeProperty() {
            return bodyType;
        }

        //color
        public String getColor() {
            return color.get();
        }

        public void setColor (String phoneNumber){
            this.color.set(phoneNumber);
        }

        public StringProperty colorProperty() {
            return color;
        }


        //year
        public int getYear() {
            return year.get();
        }

        public void setYear(int year){
            this.year.set(year);
        }

        public IntegerProperty yearProperty(){
            return year;
        }


    }
