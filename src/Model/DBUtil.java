package Model;

import Controller.CustomerController;
import com.sun.rowset.CachedRowSetImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static Model.vehiclesDAO.getEmployeeFromResultSet;
import static Model.vehiclesDAO.searchEmployees;


public class DBUtil {

        private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        private static CustomerController c = new CustomerController();
        //Connection
        private static Connection conn = null;

        //Connection String
          private static final String connStr = "jdbc:mysql://127.0.0.1:3306/inspect?autoReconnect=true&useSSL=false";

        //Connect to DB
        public static ObservableList<vehicles> dbConnect() throws SQLException, ClassNotFoundException {
            ObservableList<vehicles> empData = FXCollections.observableArrayList();
            //Setting Oracle JDBC Driver
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your mySQL JDBC Driver?");
                e.printStackTrace();
                throw e;
            }

            System.out.println("Oracle JDBC Driver Registered!");

            //Establish the Oracle Connection using Connection String
            try {
                conn = DriverManager.getConnection(connStr,"root","karen258");
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console" + e);
                e.printStackTrace();
                throw e;
            }
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from vehicles");
               // c.populateEmployees(searchEmployees());
                while (rs.next())
                    System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+" " + rs.getString(4)
                            +" " + rs.getString(5)+" " + rs.getString(6)+" " + rs.getString(7));
                conn.close();
            }
        catch(Exception e)
        { System.out.println(e);}
            return empData;
       }
        //Close Connection
        public static void dbDisconnect() throws SQLException {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e){
                throw e;
            }
        }

        //DB Execute Query Operation
        public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
            //Declare statement, resultSet and CachedResultSet as null
            Statement stmt = null;
            ResultSet resultSet = null;
            CachedRowSetImpl crs = null;
            try {
                //Connect to DB (Establish Oracle Connection)
                dbConnect();
                System.out.println("Select statement: " + queryStmt + "\n");

                //Create statement
                stmt = conn.createStatement();

                //Execute select (query) operation
                resultSet = stmt.executeQuery(queryStmt);

                //CachedRowSet Implementation
                //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
                //We are using CachedRowSet
                crs = new CachedRowSetImpl();
                crs.populate(resultSet);
            } catch (SQLException e) {
                System.out.println("Problem occurred at executeQuery operation : " + e);
                throw e;
            } finally {
                if (resultSet != null) {
                    //Close resultSet
                    resultSet.close();
                }
                if (stmt != null) {
                    //Close Statement
                    stmt.close();
                }
                //Close connection
                dbDisconnect();
            }
            //Return CachedRowSet
            return crs;
        }

        //DB Execute Update (For Update/Insert/Delete) Operation
        public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
            //Declare statement as null
            Statement stmt = null;
            try {
                //Connect to DB (Establish Oracle Connection)
                dbConnect();
                //Create Statement
                stmt = conn.createStatement();
                //Run executeUpdate operation with given sql statement
                stmt.executeUpdate(sqlStmt);
            } catch (SQLException e) {
                System.out.println("Problem occurred at executeUpdate operation : " + e);
                throw e;
            } finally {
                if (stmt != null) {
                    //Close statement
                    stmt.close();
                }
                //Close connection
                dbDisconnect();
            }
        }
    }
