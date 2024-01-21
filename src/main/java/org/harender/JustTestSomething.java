package org.harender;

import org.harender.utils.PropertiesReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JustTestSomething {

    static Connection connection=null;

    // Database connection parameters
    static String filePath = "src/test/resources/DBconfig.properties"; // Replace with your properties file path

    static PropertiesReader reader = new PropertiesReader(filePath);
    static String url,username,password,database,tableName,forNameORdriverClassName;

    static
    {
        url = reader.getProperty("db.url");
        username = reader.getProperty("db.username");
        database = reader.getProperty("db.database");
        password = reader.getProperty("db.root.password");
        tableName = reader.getProperty("db.table_Name");
        forNameORdriverClassName = reader.getProperty("db.forName");
    }

    public static void main(String[] args) {

        arrayListUnderstanding();

//       try {
//            // Load the JDBC driver
//           loadJdbcDriver(forNameORdriverClassName);
//
//            // Create a database connection
//            connection=getConnection();
//
//            // Create a statement
//            Statement statement = connection.createStatement();
//
//            // Execute a query
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableName);
//
//            String [] columnHeaders=getColumnHeaders(resultSet);
//
//            int numberOfColumnHeaders=columnHeaders.length;
//            System.out.println("Total Column Headers: "+numberOfColumnHeaders);
//
//            for (int c = 0; c < numberOfColumnHeaders; c++) {
//                System.out.println(columnHeaders[c]);
//            }
//
//            // Process the result set
//            while (resultSet.next()) {
//
//            }
//
//            // Close resources
//           closeConnection(connection,statement,resultSet);
//
//        } catch (Exception e) {
//           System.out.println("We Can use Logger Log4j Also"+
//                   "\n JustTestSomething.main"+
//                   "\n Right now"+
//                   "\n Error getMessage - "+ e.getMessage()+
//                   "\n Error toString - "+ e.toString()
//           );
//            e.printStackTrace();
//        }
    }
    // Reusable method to get Connection
    public static Connection getConnection() {

        if(connection != null) {
            return connection;
        }
        try {
            connection = DriverManager.getConnection(url+database, username, password);
        } catch ( SQLException e ) {
            System.out.println("We Can use Logger Log4j Also"+
                    "\n JustTestSomething.Connection"+
                    "\n Right now"+
                    "\n Error Code - "+ e.getErrorCode()+
                    "\n Error getMessage - "+ e.getMessage()
                    );

            throw new RuntimeException(e);
        }
        return connection;
    }
    // Reusable method to close a JDBC connection, statement, and result set
    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("We Can use Logger Log4j Also" +
                    "\n JustTestSomething.closeConnection"+
                    "\n Right now"+
                    "\n Error Code - "+ e.getErrorCode()+
                    "\n Error getMessage - "+ e.getMessage()
            );
            e.printStackTrace();
            // Handle the exception based on your application's needs
        }
    }
    // Reusable method to getHeaders of current Resultset
    public static String[] getColumnHeaders(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] headers = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                headers[i - 1] = metaData.getColumnName(i);
            }

            return headers;

        } catch (SQLException e) {
            System.out.println("We Can use Logger Log4j Also"+
                    "\n JustTestSomething.getColumnHeaders"+
                    "\n Right now"+
                    "\n Error Code - "+ e.getErrorCode()+
                    "\n Error getSQLState - "+ e.getSQLState()
            );
            e.printStackTrace();
            // Handle the exception based on your application's needs
            return new String[0]; // Return an empty array in case of an error
        }
    }
    // Reusable method to load a JDBC driver
    public static void loadJdbcDriver(String driverClassName) {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("We Can use Logger Log4j Also" +
                    "\n JustTestSomething.loadJdbcDriver"+
                    "\n Right now"+
                    "\n Error Code - "+ e.toString()+
                    "\n Error getMessage - "+ e.getMessage()
            );
            e.printStackTrace();
            // Handle the exception based on your application's needs
            throw new RuntimeException("Failed to load JDBC driver.", e);
        }
    }
    /*---------------------Arraylist---------------------------------------------------------------------
    1. Collection Framework
                         -----------------------------------------
                         | Architecture of Collection Framework  |
                         -----------------------------------------
                                   Iterable <interface>
                                           |
                                  Collection <interface>
          |---------------------  ---------|---------------------------------|
   List <interface>                  Queue <interface>                 Set <interface>
 .'|'.                             .'|'.           .'|'.                .'|'.   .'|'.
   |                                 |               |                    |       |
   |-- ArrayList <Class>     Deque <interface>  Priority Queue <Class>    |       |<--- HashSet <Class>
   |                               .'|'.                                  |       |
   |-- LinkedList <Class> -----------|                                    |       |<--- Linked HashSet <Class>
   |                                 |                                    |
   |-- Vector <Class>                |                           SortedSet <Interface>
   |       .'|'.                     |                                  .'|'.
   |         |               ArrayDeque <Class>                           |
   |         |                                                            |
   |-- Stack <Class>                                              TreeSet <Class>


    2. Hierarchy of ArrayList Class
        Shared Above
    3. ArrayList in Java
        ArrayList is part of Collection Framework and present in java.util package. It provides dynamic arrays in Java.
        -Implementation of Listing interface where the elements can be dynamically added or removed from the list.
        -Size of list increased dynamically if the elements are added more than the initial size. Though,
        -It may be slower than standard Array's but can be very helpful in the programs where lots of manipulation in the
         Array is required.
        Key Points:
        -ArrayList is initialized by a size however size can be increased if the collection grows or shrunk if the
         objects are removed from the Collection.
        -ArrayList allows us to randomly access the list and array can not be used for the Primitive types like
         int, char etc. fir those we need wrapper classes for such.


    4. Internal Working of ArrayList
       - Array size is fixed not Dynamic but list size is Dynamic.

    5. Constructors of ArrayList
       5.1 ArrayList():
            This constructor creates/builds an empty arraylist.
            Syntax: ArrayList<E> myArray = new ArrayList<E>();
            initials capacity of it is 10, but can be large as per requested.

       5.2 ArrayList(Collection c):
            This Constructor builds an array list that is initialized with the elements of the Collection c.
            Syntax: public boolean addAll(Collection c)

       5.3 ArrayList(int Capacity):
            It is used to build an array list that has the specified initial capacity.
            Syntax: ArrayList myArray = new ArrayList(int initialCapacity);

    6. Methods in ArrayList
    7. Benefits of ArrayList Vs Array


     */

    public static void arrayListUnderstanding(){

        //------------------------------------------------------------------------------------------

        ArrayList<String> listOfStrings=new ArrayList<String>();
        int counter=0;

        for(String s:listOfStrings){
            counter++;
        }
        System.out.println("No Arguments:(can't obtain) - "+counter);

        //-------------------------------------------------------------------------------------------

        ArrayList<String> listOfStringsWithCapacity=new ArrayList<String>(30);
        counter=0;

        for(String s1:listOfStringsWithCapacity){
            counter++;
        }
        System.out.println("No Arguments with capacity:(can't obtain) - "+counter);

        //-------------------------------------------------------------------------------------------

        String arrayOfStringsWithElements[]= {"Ram","Shayam"};
        List<String> list= Arrays.asList(arrayOfStringsWithElements);
        ArrayList<String> listOfStringsWithElements=new ArrayList<String>(list);
        listOfStringsWithElements.add("Harry");
        counter=0;

        for(String s2:listOfStringsWithElements){
            System.out.println("ArrayList listOfStringsWithElements Elements - "+s2);
        }
        System.out.println(counter);
    }

}
