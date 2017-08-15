package Model;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vehiclesDAO {

        //*******************************
        //SELECT an Employee
        //*******************************
        public static vehicles searchEmployee (String empId) throws SQLException, ClassNotFoundException {
            //Declare a SELECT statement
            String selectStmt = "SELECT * FROM vehicles";

            //Execute SELECT statement
            try {
                //Get ResultSet from dbExecuteQuery method
                ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

                //Send ResultSet to the getEmployeeFromResultSet method and get employee object
                vehicles employee = getEmployeeFromResultSet(rsEmp);

                //Return employee object
                return employee;
            } catch (SQLException e) {
                System.out.println("While searching an employee with " + empId + " id, an error occurred: " + e);
                //Return exception
                throw e;
            }
        }

        //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
        public static vehicles getEmployeeFromResultSet(ResultSet rs) throws SQLException
        {
            vehicles emp = null;
            if (rs.next()) {
                emp = new vehicles();
                emp.setVehicle_id(rs.getInt("vinNumber"));
                emp.setMake(rs.getString("make"));
                emp.setModel(rs.getString("model"));
                emp.setBodyType(rs.getString("bodyType"));
                emp.setColor(rs.getString("color"));
                emp.setYear(rs.getInt("year"));

            }
            return emp;
        }

        //*******************************
        //SELECT Employees
        //*******************************
        public static ObservableList<vehicles> searchEmployees () throws SQLException, ClassNotFoundException {
            //Declare a SELECT statement
            String selectStmt = "SELECT * FROM vehicles";

            //Execute SELECT statement
            try {
                //Get ResultSet from dbExecuteQuery method
                ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

                //Send ResultSet to the getEmployeeList method and get employee object
                ObservableList<vehicles> empList = getEmployeeList(rsEmps);

                //Return employee object
                return empList;
            } catch (SQLException e) {
                System.out.println("SQL select operation has been failed: " + e);
                //Return exception
                throw e;
            }
        }

        //Select * from employees operation
        private static ObservableList<vehicles> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
            //Declare a observable List which comprises of Employee objects
            ObservableList<vehicles> empList = FXCollections.observableArrayList();

            while (rs.next()) {
                vehicles emp = new vehicles();
                emp.setVehicle_id(rs.getInt("vinNumber"));
                emp.setMake(rs.getString("make"));
                emp.setModel(rs.getString("model"));
                emp.setBodyType(rs.getString("bodyType"));
                emp.setColor(rs.getString("color"));
                emp.setYear(rs.getInt("year"));

                //Add employee to the ObservableList
                empList.add(emp);
            }
            //return empList (ObservableList of Employees)
            return empList;
        }

        //*************************************
        //UPDATE an employee's email address
        //*************************************
        public static void updateEmpEmail (String empId, String empEmail) throws SQLException, ClassNotFoundException {
            //Declare a UPDATE statement
            String updateStmt =
                    "BEGIN\n" +
                            "   UPDATE vehicles\n" +
                            "      SET EMAIL = '" + empEmail + "'\n" +
                            "    WHERE EMPLOYEE_ID = " + empId + ";\n" +
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
        //DELETE an employee
        //*************************************
        public static void deleteEmpWithId (String empId) throws SQLException, ClassNotFoundException {
            //Declare a DELETE statement
            String updateStmt =
                    "BEGIN\n" +
                            "   DELETE FROM employees\n" +
                            "         WHERE employee_id ="+ empId +";\n" +
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
        //INSERT an employee
        //*************************************
        public static void insertEmp (String name, String lastname, String email) throws SQLException, ClassNotFoundException {
            //Declare a DELETE statement
            String updateStmt =
                    "BEGIN\n" +
                            "INSERT INTO employees\n" +
                            "(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID)\n" +
                            "VALUES\n" +
                            "(sequence_employee.nextval, '"+name+"', '"+lastname+"','"+email+"', SYSDATE, 'IT_PROG');\n" +
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
