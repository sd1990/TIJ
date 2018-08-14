package org.songdan.tij.jdbc;

import java.sql.*;

/**
 * @author: Songdan
 * @create: 2018-08-11 16:53
 **/
public class JdbcDemo {

    public static void main(String[] args) {
        Connection connection;
        PreparedStatement statement;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","123456");
            statement = connection.prepareStatement("select * from salaries where 1!=1");
            System.out.println(statement.execute());
            ResultSet resultSet = statement.getResultSet();
            skipRows(resultSet,RowBounds.DEFAULT);
            while (resultSet.next()) {
                System.out.println(resultSet.getMetaData());
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected static void skipRows(ResultSet rs, RowBounds rowBounds) throws SQLException {
        if (rs.getType() != ResultSet.TYPE_FORWARD_ONLY) {
            if (rowBounds.getOffset() != RowBounds.NO_ROW_OFFSET) {
                rs.absolute(rowBounds.getOffset());
            }
        } else {
            for (int i = 0; i < rowBounds.getOffset(); i++) rs.next();
        }
    }

    static class RowBounds {

        public final static int NO_ROW_OFFSET = 0;
        public final static int NO_ROW_LIMIT = Integer.MAX_VALUE;
        public final static RowBounds DEFAULT = new RowBounds();

        private int offset;
        private int limit;

        public RowBounds() {
            this.offset = NO_ROW_OFFSET;
            this.limit = NO_ROW_LIMIT;
        }

        public RowBounds(int offset, int limit) {
            this.offset = offset;
            this.limit = limit;
        }

        public int getOffset() {
            return offset;
        }

        public int getLimit() {
            return limit;
        }

    }

}
