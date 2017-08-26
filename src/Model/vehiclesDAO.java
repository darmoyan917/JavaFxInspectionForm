package Model;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vehiclesDAO {

        //*******************************
        //SELECT an Employee
        //*******************************
        public static vehicles searchVehicles(String customer) throws SQLException, ClassNotFoundException {
            //Declare a SELECT statement
            String selectStmt = "SELECT * FROM vehicles WHERE customer= '" +customer +"'";

            //Execute SELECT statement
            try {
                //Get ResultSet from dbExecuteQuery method
                ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

                //Send ResultSet to the getVehicleFromResultSet method and get vehicle object
                vehicles vehicle = getVehicleFromResultSet(rsEmp);

                //Return vehicles object
                return vehicle;
            } catch (SQLException e) {
                System.out.println("While searching a vehicle with " + customer + " as owner, an error occurred: " + e);
                //Return exception
                throw e;
            }
        }

        //Use ResultSet from DB as parameter and set Vehicle Object's attributes and return vehicle object.
        public static vehicles getVehicleFromResultSet(ResultSet rs) throws SQLException
        {
            vehicles veh = null;
            if (rs.next()) {
                veh = new vehicles();
                veh.setVehicle_id(rs.getString("vinNumber"));
                veh.setMake(rs.getString("make"));
                veh.setModel(rs.getString("model"));
                veh.setBodyType(rs.getString("bodyType"));
                veh.setMileage(rs.getString("mileage"));
                veh.setDate(rs.getString("date"));
                veh.setYear(rs.getInt("year"));

            }
            return veh;
        }

        //*******************************
        //SELECT Vehicles
        //*******************************
        public static ObservableList<vehicles> searchVehicles () throws SQLException, ClassNotFoundException {
            //Declare a SELECT statement
            String selectStmt = "SELECT * FROM vehicles";

            //Execute SELECT statement
            try {
                //Get ResultSet from dbExecuteQuery method
                ResultSet reVehicles = DBUtil.dbExecuteQuery(selectStmt);

                //Send ResultSet to the getVehicleList method and get employee object
                ObservableList<vehicles> vehList = getVehicleList(reVehicles);

                //Return employee object
                return vehList;
            } catch (SQLException e) {
                System.out.println("SQL select operation has been failed: " + e);
                //Return exception
                throw e;
            }
        }

        //Select * from vehicles operation
        private static ObservableList<vehicles> getVehicleList(ResultSet rs) throws SQLException, ClassNotFoundException {
            //Declare a observable List which comprises of Employee objects
            ObservableList<vehicles> vehList = FXCollections.observableArrayList();

            while (rs.next()) {
                vehicles veh = new vehicles();
                veh.setVehicle_id(rs.getString("vinNumber"));
                veh.setMake(rs.getString("make"));
                veh.setModel(rs.getString("model"));
                veh.setBodyType(rs.getString("bodyType"));
                veh.setMileage(rs.getString("mileage"));
                veh.setDate(rs.getString("date"));
                veh.setYear(rs.getInt("year"));

                //Add vehicle to the ObservableList
                vehList.add(veh);
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
                            "   UPDATE vehicles\n" +
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
        public static void deleteVehicleWithID(String vehicleID) throws SQLException, ClassNotFoundException {
            //Declare a DELETE statement
            String updateStmt =
                    "BEGIN\n" +
                            "   DELETE FROM vehicles\n" +
                            "         WHERE vinNumber ="+ vehicleID +";\n" +
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
        public static void insertVehicle(String make, String model, String bodyType) throws SQLException, ClassNotFoundException {
            //Declare a DELETE statement
            String updateStmt =
                    "BEGIN\n" +
                            "INSERT INTO vehicles" +"\n" +
                            "(vinNumber, make, model, bodyType, color, year)\n" +
                            "VALUES\n" +
                            "(sequence_employee.nextval, '"+make+"', '"+model+"','"+bodyType+"');\n" +
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
