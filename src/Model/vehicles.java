package Model;

import javafx.beans.property.*;

public class vehicles {
        //Declare vehicles Table Columns
        private StringProperty vehicle_id;
        private StringProperty make;
        private StringProperty model;
        private StringProperty bodyType;
        private StringProperty mileage;
        private IntegerProperty year;
        private StringProperty date;

        //Constructor
        public vehicles() {
            this.vehicle_id = new SimpleStringProperty();
            this.make = new SimpleStringProperty();
            this.model = new SimpleStringProperty();
            this.bodyType = new SimpleStringProperty();
            this.mileage = new SimpleStringProperty();
            this.year = new SimpleIntegerProperty();
            this.date = new SimpleStringProperty();
        }

        //vehicle_id
        public String getvehicle_id() {
            return vehicle_id.get();
        }

        public void setVehicle_id(String vehicle_id){
            this.vehicle_id.set(vehicle_id);
        }

        public StringProperty vehidleidProperty(){
            return vehicle_id;
        }

        //make
        public String getMake () {
            return make.get();
        }

        public void setMake(String make){
            this.make.set(make);
        }

        public StringProperty makeProperty() {
            return make;
        }

        //model
        public String getModel () {
            return model.get();
        }

        public void setModel(String model){
            this.model.set(model);
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

        //mileage
        public String getMileage() {
            return mileage.get();
        }

        public void setMileage(String mileage){
            this.mileage.set(mileage);
        }

        public StringProperty mileageProperty() {
            return mileage;
        }
        //date
        public String getDate(){
            return date.get();
        }
        public void setDate(String date){
            this.date.set(date);
        }
        public StringProperty dateProperty(){
            return date;
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
