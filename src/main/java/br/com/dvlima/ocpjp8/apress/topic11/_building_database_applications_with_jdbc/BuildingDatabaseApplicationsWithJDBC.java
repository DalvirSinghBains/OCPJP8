package br.com.dvlima.ocpjp8.apress.topic11._building_database_applications_with_jdbc;

/**
 Building Database Applications with JDBC

 - Describe the interfaces that make up the core of the JDBC API including the 
 	Driver, Connection, Statement, and ResultSet interfaces and their relationship 
 	to provider implementations
 - Identify the components required to connect to a database using the 
 	DriverManager class including the JDBC URL
 - Submit queries and read results from the database including creating statements, 
 	returning result sets,iterating through the results, and properly 
 	closing result sets, statements, and connections
 */
public class BuildingDatabaseApplicationsWithJDBC {
	//The boolean absolute(int) method in ResultSet moves the cursor to the passed row number in that ResultSet object.
	// If the row number is positive, it moves to that position from the beginning of the ResultSet object; if the row
	// number is negative, it moves to that position from the end of the ResultSet object. Assume that there are ten
	// entries in the ResultSet object. Calling absolute(3) moves the cursor to the third row. Calling absolute(−bs)
	// moves the cursor to the eighth row. If you give out-of-range values, the cursor moves to either the beginning or the end.


//	In a ResultSet object, calling absolute(1) is equivalent to calling first(), and
//	calling absolute(-1) is equivalent to calling last().

//	You can use a column name or column index with ResultSet methods. The index you use is the index of the ResultSet object,
// not the column number in the database table.

//	A Statement object closes the current ResultSet object if the Statement object is closed, re-executed, or made to
// retrieve the next set of results. That means it is not necessary to call close() explicitly with a ResultSet object;
// however, it is good practice to call close() once you are done with the object.

//	You may use the column name of a ResultSet object without worrying about the case: getXXX() methods accept
// case-insensitive column names to retrieve the associated value.

// Think of a case in which you have two columns in a ResultSet object with the same name. How you can retrieve the
// associated values using the column name? If you use a column name to retrieve the value, it always points to the first
// column that matches the given name. Hence, you have to use column index in this case to retrieve values associated
// with both columns.

//	You may remember that the PreparedStatement interface inherits from Statement. However, PreparedStatement overrides
// all flavors of execute methods. For instance, the behavior of executeUpdate() may be different from its base method.

//	You may cancel any update you made using the method cancelRowUpdates(). However, you must call this method before
// calling updateRow(). In all other cases, it has no impact on the row.

//	While connecting to the database, you need to specify the correct username and password. If the provided username
// or password is not correct, you get a SQLException.

	/**
	 * Identify the components required to connect to a database using the DriverManager class including the JDBC URL
	 * */

// JDBC hides the heterogeneity of all the DBMSs and offers a single set of APIs to interact with all types of databases.
// The complexity of heterogeneous interactions is delegated to the JDBC driver manager and JDBC drivers.

// The getConnection() method in the DriverManager class takes three arguments: a URL string, a username string, and a password string.

// The syntax of the URL (which needs to be specified to get the Connection object) is jdbc: <subprotocol>:<subname>.

// If the JDBC API is not able to locate the JDBC driver, it throws a SQLException. If jars for the drivers are available,
// they need to be included in the classpath to enable the JDBC API to locate the driver.

    /**
     * Describe the interfaces that make up the core of the JDBC API including the Driver, Connection,
     * Statement, and ResultSet interfaces and their relationship to provider implementations
     * */
// The java.sql.Connection interface provides a channel through which the application and the database communicate.

// JDBC supports two classes for querying and updating: Statement and Resultset.

// A Statement is a SQL statement that can be used to communicate a SQL statement to the connected database and receive
// results from the database. There are three types of Statements:

// Statement: Sends a SQL statement to the database without any parameters

// PreparedStatement: Represents a precompiled SQL statement that can be customized using IN parameters

// CallableStatement: Executes stored procedures; can handle IN as well as OUT and INOUT parameters

// A resultset is a table with column heading and associated values requested by the query.


    /**
     * Submit queries and read results from the database including creating statements, returning result sets,
     * iterating through the results, and properly closing result sets, statements, and connections
     */
// A ResultSet object maintains a cursor pointing to the current row. Initially, the cursor is set to just before the
// first row; calling the next() method advances the cursor position by one row.

// The column index in the ResultSet object starts from 1 (not from 0).

// You need to call updateRow() after modifying the row contents in a resultset; otherwise, changes made to
// the ResultSet object are lost.

// You can use a try-with-resources statement to close resources (Connection,ResultSet, and Statement) automatically.
}
