package org.songdan.tij.jdbc;

import java.sql.*;

/**
 * @author: Songdan
 * @create: 2018-08-11 16:53
 **/
public class JdbcDemo {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {

                try {
                    executeOne();
                } catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            }

        }).start();
        new Thread(() -> {
            while (true) {

                try {
                    executeTwo();
                } catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            }

        }).start();
    }

    private static Connection getConnection(String db) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, "root", "123456");
    }

    private static void executeOne() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection("test");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("update t_index set utime = UNIX_TIMESTAMP() where confirm_id = 1002;");
            statement.executeUpdate();
            connection.rollback();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                throw e1;
            }

        }
    }

    private static void executeTwo() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection("test");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("update t_index set confirm_id = 1003,status = 2 where poi_id in (130)");
            statement.executeUpdate();
            connection.rollback();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                throw e1;
            }

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
