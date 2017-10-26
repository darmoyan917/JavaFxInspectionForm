package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class customersDAO {
        //*******************************
        //SELECT a Customer
        //*******************************
        public static customers searchCustomers(String firstName, String lastName) throws SQLException, ClassNotFoundException {
            //Declare a SELECT statement
            String selectStmt = "SELECT * FROM customers WHERE firstName= '" +firstName +"' AND lastName='" +lastName+"'";

            //Execute SELECT statement
            try {
                //Get ResultSet from dbExecuteQuery method
                ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

                //Send ResultSet to the getVehicleFromResultSet method and get vehicle object
                customers vehicle = getVehicleFromResultSet(rsEmp);

                //Return vehicles object
                return vehicle;
            } catch (SQLException e) {
                System.out.println("While searching for " + firstName + " an error occurred: " + e);
                //Return exception
                throw e;
            }
        }

        //Use ResultSet from DB as parameter and set Vehicle Object's attributes and return vehicle object.
        public static customers getVehicleFromResultSet(ResultSet rs) throws SQLException
        {
            customers cust = null;
            if (rs.next()) {
                cust = new customers();
                cust.setFirstName(rs.getString("firstName"));
                cust.setLastName(rs.getString("lastName"));
                cust.setAddress(rs.getString("address"));
                cust.setEmail(rs.getString("email"));
                cust.setCity(rs.getString("city"));
                cust.setState(rs.getString("state"));
                cust.setZipCode(rs.getInt("zipCode"));
                cust.setVinNumber(rs.getString("vinNumber"));

            }
            return cust;
        }

        //*******************************
        //SELECT Vehicles
        //*******************************
        public static ObservableList<customers> searchCustomers() throws SQLException, ClassNotFoundException {
            //Declare a SELECT statement
            String selectStmt = "SELECT * FROM customers";

            //Execute SELECT statement
            try {
                //Get ResultSet from dbExecuteQuery method
                ResultSet reVehicles = DBUtil.dbExecuteQuery(selectStmt);

                //Send ResultSet to the getVehicleList method and get employee object
                ObservableList<customers> custList = getVehicleList(reVehicles);

                //Return employee object
                return custList;
            } catch (SQLException e) {
                System.out.println("SQL select operation has been failed: " + e);
                //Return exception
                throw e;
            }
        }

        //Select * from vehicles operation
        private static ObservableList<customers> getVehicleList(ResultSet rs) throws SQLException, ClassNotFoundException {
            //Declare a observable List which comprises of Employee objects
            ObservableList<customers> vehList = FXCollections.observableArrayList();

            while (rs.next()) {
                customers cust = new customers();
                cust.setFirstName(rs.getString("firstName"));
                cust.setLastName(rs.getString("lastName"));
                cust.setAddress(rs.getString("address"));
                cust.setEmail(rs.getString("email"));
                cust.setCity(rs.getString("city"));
                cust.setState(rs.getString("state"));
                cust.setZipCode(rs.getInt("zipCode"));
                cust.setVinNumber(rs.getString("vinNumber"));

                //Add vehicle to the ObservableList
                vehList.add(cust);
            }
            //return vehList (ObservableList of vehicles)
            return vehList;
        }

        //*************************************
        //UPDATE an vehicle's model
        //*************************************
        public static void updateVehicleModel(String vehicleID, String vehModel) throws SQLException, ClassNotFoundException {
            //Declare a UPDATE statement
            String updateStmt =
                    "BEGIN\n" +
                            "   UPDATE customers\n" +
                            "      SET model = '" + vehModel + "'\n" +
                            "    WHERE vinNumber = " + vehicleID + ";\n" +
                            "   COMMIT;\n" +
                            "END;";

            //Execute UPDATE operation
            try {
                DBUtil.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while UPDATE Operation: " + e);
                throw e;
            }
        }

        //*************************************
        //DELETE an vehicle
        //*************************************
        public static void deleteCustomerWithName(String firstName, String lastName) throws SQLException, ClassNotFoundException {
            //Declare a DELETE statement
            String updateStmt =
                    "BEGIN\n" +
                            "   DELETE FROM customers\n" +
                            "         WHERE name ="+ firstName + " " +lastName +";\n" +
                            "   COMMIT;\n" +
                            "END;";

            //Execute UPDATE operation
            try {
                DBUtil.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }

        //*************************************
        //INSERT a vehicle
        //*************************************
        public static void insertCustomer(String firstName, String lastName, String address, String city, String state, String zipCode, String vinNumber, String email) throws SQLException, ClassNotFoundException {
            //Declare a DELETE statement
            String updateStmt =
                    "BEGIN\n" +
                            "INSERT INTO customers" +"\n" +
                            "(firstName, lastName, address, city, state, zipCode, vinNumber, email)\n" +
                            "VALUES\n" +
                            "(sequence_employee.nextval, '"+firstName+"', '"+lastName+"','"+address+"','"+city+"','"+state+"','"+zipCode+"','"+vinNumber+"','"+email +"');\n" +
                            "END;";

            //Execute DELETE operation
            try {
                DBUtil.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }
    }
