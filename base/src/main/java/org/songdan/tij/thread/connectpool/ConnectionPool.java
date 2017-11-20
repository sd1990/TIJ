package org.songdan.tij.thread.connectpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 连接池
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        for (int i = 0; i < 10; i++) {
            pool.addLast(DriverManager.getConnection("", "", ""));
        }
    }

    public Connection fetchConnection(long time) throws InterruptedException {
        synchronized (pool) {
            if (time <= 0) {

                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.getFirst();
            } else {

                long future = System.currentTimeMillis() + time;
                long remaining = time;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.getFirst();
                }
                return result;
            }
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (pool) {
            pool.addLast(connection);
            pool.notifyAll();

        }
    }

}
