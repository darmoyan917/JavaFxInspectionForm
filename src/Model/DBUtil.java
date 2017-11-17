package Model;

import Controller.customerController;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;


public class DBUtil {

        private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        private static customerController c = new customerController();
        //Connection
        private static Connection conn = null;

        //Connection String
          private static final String connStr = "jdbc:mysql://127.0.0.1:3306/inspect?autoReconnect=true&useSSL=false";

        //Connect to DB
        public static void dbConnect() throws SQLException, ClassNotFoundException {
            //Setting mySQL JDBC Driver
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your mySQL JDBC Driver?");
                e.printStackTrace();
                throw e;
            }

            //Establish the mySQL Connection using Connection String
            try {
                conn = DriverManager.getConnection(connStr,"root","karen258");
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console" + e);
                e.printStackTrace();
                throw e;
            }
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
        public static int FindMaxID(String sql) throws SQLException {
            int maxID = 0;
            try {
                dbConnect();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                maxID = rs.getInt("max_id");
            }
            dbDisconnect();
            return maxID;
        }
    }
