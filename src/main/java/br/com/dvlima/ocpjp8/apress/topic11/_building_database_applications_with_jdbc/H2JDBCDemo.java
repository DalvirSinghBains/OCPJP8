package br.com.dvlima.ocpjp8.apress.topic11._building_database_applications_with_jdbc;

import java.sql.*;
import java.time.Instant;

/*
    javac H2JDBCDemo.java && java -classpath "*:." H2JDBCDemo
 */

class DbConnector {
    public static Connection connectToDb() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./db1", "sa", "sa");
    }
}

public class H2JDBCDemo {

    public static void main(String[] args) {
        queryDatabase();
    }

    private static void createTable() {
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement()) {
            // use CREATE TABLE SQL statement to
            // create table familyGroup
            statement.executeUpdate("CREATE TABLE familyGroup (id int not null auto_increment, nickName varchar(30)not null, primary key(id));");
            System.out.println("Table created successfully");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(-1);
        }
    }

    private static void deleteDatabase() {
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM REGISTRATION")) {

            if (resultSet.next()) {
                // delete the first row
                resultSet.deleteRow();
            }
            resultSet.close();

            try (ResultSet resultSet2 = statement.executeQuery("SELECT * FROM REGISTRATION")) {
                System.out.println("After the deletion");
                printValues(resultSet2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertDatabase() {
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM REGISTRATION")) {

            System.out.println("Before the insert");
            printValues(resultSet);

            resultSet.moveToInsertRow();
            resultSet.updateInt("id", Math.toIntExact(Instant.now().getEpochSecond()));
            resultSet.updateString("first", "Josh");
            resultSet.updateString("last", "Klimber");
            resultSet.updateInt("age", 28);

            resultSet.insertRow();

            System.out.println("After the insert");
            resultSet.beforeFirst();
            printValues(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void updateDatabase() {
        try (
                Connection connection = DbConnector.connectToDb();
                // create a statement from which the created ResultSets
                // are "scroll sensitive" as well as "updatable"
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery("SELECT * FROM REGISTRATION where age = 18");
        ) {
            System.out.println("Before the update");
            printValues(resultSet);

            // now update the resultset and display the modified data
            resultSet.absolute(1);
            resultSet.updateString("last", "Ali");

            // reflect those changes back to the database
            // by calling updateRow() method

            //always call updateRow after modifying the row contents; otherwise, you will lose the changes.
            resultSet.updateRow();

            System.out.println("After the update");
            resultSet.beforeFirst();
            printValues(resultSet);
            /*
            Before the update
            id      first   last    Age
            1527132755      Zara    Ali     18

            After the update
            id      first   last    Age
            1527132755      Zara    Abul    18
            */
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private static void queryDatabase() {
        try (
                Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT id, first, last, age FROM REGISTRATION");
        ) {

            int numOfColumns = resultSet.getMetaData().getColumnCount();
            System.out.println("id \tfirst \tlast \tAge");

            while (resultSet.next()) {
                //Column Label
                System.out.println(resultSet.getInt("id") + "\t"
                        + resultSet.getString("first") + "\t"
                        + resultSet.getString("last") + "\t"
                        + resultSet.getInt("age"));

                //Column Index
                //the column index in the ResultSet object starts from 1, not 0.
                System.out.println(resultSet.getInt(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getInt(4));

                // from resultSet metadata, find out how many columns there are
                // and then read the column entries
                // remember that the column index starts from 1 not 0
                for (int i = 1; i <= numOfColumns; i++) {
                    // since we do not know the data type of the column, we use getObject()
                    System.out.print(resultSet.getObject(i) + "\t");
                }
                System.out.println("");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        System.out.println("Goodbye!");
    }

    private static void createDatabaseOldWay() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./db1";
        final String USER = "sa";
        final String PASS = "sa";

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Create a Statement object that can be used to send SQL statements to the database.
            stmt = conn.createStatement();

            System.out.println("delete if exists...");
            String delete = "DROP TABLE IF EXISTS REGISTRATION";
            stmt.executeUpdate(delete);

            System.out.println("Created table in given database...");
            String sql = "CREATE TABLE REGISTRATION(id INTEGER not NULL, first VARCHAR(255),last VARCHAR(255),age INTEGER,PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Registration VALUES (" + Instant.now().getEpochSecond() + ", 'Zara', 'Ali', 18)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Registration VALUES (101, 'Mahnaz', 'Fatma', 25)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Registration VALUES (102, 'Zaid', 'Khan', 30)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Registration VALUES(103, 'Sumit', 'Mittal', 28)";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");

            sql = "SELECT id, first, last, age FROM Registration";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            // STEP 5: Clean-up environment
            rs.close();

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }

    private static void printValues(ResultSet resultSet) throws SQLException {
        System.out.println("id \tfirst \tlast \tAge");
        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.print(resultSet.getObject(i) + "\t");
            }
            System.out.println("");
        }
    }
}
