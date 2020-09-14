import java.sql.*;
import java.util.*;

public class Database {

    /*
     * public static void main(String[] args) throws Exception {
     * 
     * //ArrayList<String> array = new ArrayList<String>(); array.add("1234");
     * array.add("'testy'"); array.add("15"); array.add("'04/25/1999'");
     * array.add("'Male'"); array.add("'Black'"); post("Patients", array);
     * 
     * System.out.println(get("Patients"));
     * 
     * delete("Patients", "PID", "1234");
     * 
     * }
     */

    // Creates connection to the database
    public static Connection getConnection() throws Exception {
        try {

            String url = "jdbc:sqlserver://localhost:1433;databaseName=EHR;user=java;password=java";
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Connected");
            return conn;

        } catch (Exception e) {
            System.out.println(e);

        }

        System.out.println("Did not connect");

        return null;
    }

    // Creates a table with an ID field and sets it as the primary key
    public static void createTable(String tablename) throws Exception {
        try {

            Connection conn = getConnection();
            PreparedStatement create = conn
                    .prepareStatement("CREATE TABLE " + tablename + " (id int NOT NULL, PRIMARY KEY(id))");
            create.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            System.out.println("Returning from createTable");

        }
    }

    public static void dropTable(String tablename) throws Exception {
        try {

            Connection conn = getConnection();
            PreparedStatement create = conn.prepareStatement("DROP TABLE " + tablename);
            create.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            System.out.println("Returning from dropTable");

        }
    }

    // Insert data into a table. Please ensure that all strings are surrounded by
    // single quotes
    public static void post(String tablename, ArrayList<String> array) {

        String values = array.toString();
        values = values.replace("[", "").replace("]", "");

        try {

            Connection conn = getConnection();
            PreparedStatement post = conn.prepareStatement("INSERT INTO " + tablename + " values (" + values + ")");
            post.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            System.out.println("Added: " + values);
            System.out.println("Returning from post");

        }
    }

    // Adds a column to a table
    public static void addColumn(String tablename, String column, String type) {
        try {

            Connection conn = getConnection();
            PreparedStatement post = conn
                    .prepareStatement("ALTER TABLE " + tablename + " ADD COLUMN " + column + " " + type);
            post.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            System.out.println("Returning from addColumn");

        }
    }

    // Update a value in the table. Need to specify the PID in the id variable.
    public static void updateTable(String tablename, String column, String value, int pid) {
        try {

            Connection conn = getConnection();
            PreparedStatement post = conn
                    .prepareStatement("UPDATE " + tablename + " SET " + column + "=" + value + " WHERE PID=" + pid);
            post.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            System.out.println("Returning from updateTable");

        }
    }

    // Update a value in the table. Need to specify the MID in the id variable.
    public static void updateTable(String tablename, String column, String value, String mid) {
        try {

            Connection conn = getConnection();
            PreparedStatement post = conn
                    .prepareStatement("UPDATE " + tablename + " SET " + column + "=" + value + " WHERE MID=" + mid);
            post.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            System.out.println("Returning from updateTable");

        }
    }

    // Delete (a) row(s) from the table.
    public static void delete(String tablename, String column, String value) {
        try {

            Connection conn = getConnection();
            PreparedStatement post = conn
                    .prepareStatement("DELETE FROM " + tablename + " WHERE " + column + "=" + value);
            post.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            System.out.println("Returning from delete");

        }
    }

    // Runs a query against the database to return all rows of a table. Data is
    // returned in an ArrayList of ArrayLists of Strings.
    // Each element in the outer arraylist represents a row, while each element in
    // the inner arraylists represents a column
    public static ArrayList<ArrayList<String>> get(String table) throws Exception {
        try {

            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + table);
            ResultSet result = statement.executeQuery();

            System.out.println("All records have been selected.");
            return getResults(result);

        } catch (Exception e) {

            System.out.println(e);

        }

        return null;

    }

    // Overloaded method: queries against database to return rows of a table that
    // match the filter
    public static ArrayList<ArrayList<String>> get(String table, String column, String value) throws Exception {
        try {

            Connection conn = getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("SELECT * FROM " + table + " WHERE " + column + "=" + value);
            ResultSet result = statement.executeQuery();

            System.out.println("The specified records have been selected.");
            return getResults(result);

        } catch (Exception e) {

            System.out.println(e);

        }

        return null;
    }

    private static ArrayList<ArrayList<String>> getResults(ResultSet result) throws Exception {

        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        ResultSetMetaData metadata = result.getMetaData();
        int numCol = metadata.getColumnCount();

        while (result.next()) {

            array.add(new ArrayList<String>(numCol));
            int index = array.size() - 1;
            System.out.println("Size of array: " + array.size());

            for (int i = 0; i < numCol; i++) {
                array.get(index).add(result.getString(i + 1));
            }
        }
        return array;
    }
}