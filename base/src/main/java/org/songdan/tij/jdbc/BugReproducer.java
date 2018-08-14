package org.songdan.tij.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author: Songdan
 * @create: 2018-08-13 11:19
 **/
public class BugReproducer {
   Connection con;
    String importQuery = "SET FOREIGN_KEY_CHECKS = 1;DELETE FROM Category WHERE id=\'0205342903\';SET " +
            "FOREIGN_KEY_CHECKS = 0;INSERT INTO Category VALUES(\'d0450f050a0dfd8e00e6da7bda3bb07e\',\'0205342903\'," +
            "\'00000000000000000000000000000000\',\'\',\'0\');INSERT INTO Attribute VALUES" +
            "(\'d0450f050a0dfd8e00e6da7b00dfa3c5\',\'d0450f050a0dfd8e00e6da7bda3bb07e\' ,\'eType\',\'machine\',null);SET FOREIGN_KEY_CHECKS = 1;";
    String updateQuery = "SET FOREIGN_KEY_CHECKS = 1;DELETE FROM Attribute WHERE " +
            "foreignuuid=\'d0450f050a0dfd8e00e6da7bda3bb07e\' AND name=\'eType\';SET FOREIGN_KEY_CHECKS = 0;" +
            "INSERT INTO Attribute VALUES(\'d0563ba70a0dfd8e01df43e22395b352\'," + "\'d0450f050a0dfd8e00e6da7bda3bb07e\' ,\'eType\',\'machine\',null);SET FOREIGN_KEY_CHECKS = 1;";
    String bugQuery = "SELECT name,value FROM Attribute WHERE foreignuuid=\'d0450f050a0dfd8e00e6da7bda3bb07e\'";

    /**
     * Sets up db connection and sets autocommit to false
     *
     * @throws Exception in case anything goes wrong during setup
     */
    public BugReproducer() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // CONFIGURE YOUR DB PARAMETERS HERE
        this.con =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true","root", "123456");
        this.con.setAutoCommit(false);
    }

    /**
     * Executes an update statment, closes statement
     * and commits transaction.
     * Closes connection.
     *
     * @throws Exception in case anything goes wrong
     */
    public void testBug() throws Exception {
        Statement stmt = this.con.createStatement();
        stmt.executeUpdate(this.importQuery);
        stmt.close();
        this.con.commit();
        this.con.close();
    }

    /**
     * Executes an update statement and a query,
     * reads first row of ResultSet which randomly leads to an SQLException.
     * Closes statement and commits transaction.
     * Closes connection.
     *
     * @throws Exception in case anything goes wrong
     */
    public void testBug1() throws Exception {
        Statement stmt = this.con.createStatement();
        stmt.executeUpdate(this.updateQuery);
        ResultSet rs = stmt.executeQuery(this.bugQuery);
        // THIS IS WHERE THINGS GO WRONG
        rs.next();
        stmt.close();
        this.con.commit();
        this.con.close();
    }

    /**
     * Creates two instances of BugReproducer class sequentially and calls
     testBug()
     * resp. testBug2(). This happens in loop which should lead to an SQLException
     with
     * error message "ResultSet is from UPDATE. No Data."
     *
     * @param args unused
     */
    public static void main(String[] args) {
        int i = 0;
        try {
            for (i=0; i <= 5000; i++) {
                BugReproducer one = new BugReproducer();
                one.testBug();

                BugReproducer two = new BugReproducer();
                two.testBug1();

                if (i%250 == 0) {
                    System.out.println("Count: " + i);
                }
            }
        } catch (Exception e) {
            System.out.println("Count: " + i);
            e.printStackTrace();
        }
    }
}
