package org.songdan.tij.array.jdbc;

import org.songdan.tij.array.random.IMajorKey;
import org.songdan.tij.array.random.impl.DistributedIdCreator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class JDBCDemo {

    private static final Properties props = new Properties();

    private static String URL;

    private static String USER;

    private static String PASS;
    //可以通过策略者模式注入
    private static IMajorKey majorKey =new DistributedIdCreator();

    static {
        try {
            InputStream inputStream = JDBCDemo.class.getClassLoader().getResourceAsStream("datasource.properties");
            props.load(inputStream);
            URL = props.getProperty("url");      
            
            USER = props.getProperty("username");
                                                 
            PASS = props.getProperty("password");
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        //注册mysql驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取connection
        Connection connection = DriverManager.getConnection(URL,USER,PASS);
        return connection;
    }
    
    public static void executeInsert(String sql,Object... datas) {
        Connection connection = null ;
        try {
            connection= getConnection();
            //开启事务
            connection.setAutoCommit(false);
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < datas.length; i++) {
                prepareStatement.setObject(i+1, datas[i]);
            }
            boolean result = prepareStatement.execute();
            //提交事务
            connection.commit();
            //关闭资源
            prepareStatement.close();
            connection.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                //事务回滚
                connection.rollback();
            }
            catch (SQLException e1) {
            }
        }
    }
    public static void executeBatchInsert(String sql,List<Object[]> list) {
        Connection connection = null ;
        try {
            connection= getConnection();
            //开启事务
            connection.setAutoCommit(false);
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            for (Object[] objects : list) {
                for (int i = 0; i < objects.length; i++) {
                    prepareStatement.setObject(i+1, objects[i]);
                }
                prepareStatement.addBatch();
            }
            int[] executeBatch = prepareStatement.executeBatch();
            //提交事务
            connection.commit();
            //关闭资源
            prepareStatement.close();
            connection.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                //事务回滚
                connection.rollback();
            }
            catch (SQLException e1) {
            }
        }
    }
    public static void executeQuery(String sql,Object data) {
        Connection connection = null ;
        PreparedStatement prepareStatement=null;
        ResultSet result = null;
        try {
            connection= getConnection();
            prepareStatement = connection.prepareStatement(sql);
            System.out.println(sql);
            result = prepareStatement.executeQuery();
            List<Object[]> list = new LinkedList<>();
//          DateFormat format = new SimpleDateFormat('yyyy-MM-dd')
          while(result.next()){
              Object[] objs=new Object[7];
              objs[0]=createSingleId();
              objs[1]=result.getString("score_item_id");
              objs[2]=result.getString("user_id");
              objs[3]=result.getString("score_item_type");
              objs[4]=new Date();
              objs[5]=result.getString("score_item_value");
              objs[6]="{\"code\":\"\",\"msg\":\"雅学体验报告\"}";
              list.add(objs);
          }
          String insertSql="INSERT INTO `b_user_score_in` (`score_in_id`, `score_id`, `user_id`, `earn_type`, `earn_time` , `earn_num`, `remark`)"
                          +" VALUES (?, ?, ?, ?, ? , ?, ?);";
          executeBatchInsert(insertSql, list);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally{
          //关闭资源
            try {
                prepareStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        String sql =""
                + "SELECT si.score_item_id, u.user_id,si.score_item_type, si.score_item_value"
                +" FROM s_score_item si, b_user u "
                +" WHERE si.score_item_id = '656311076262121472' "
                +" AND u.loginname IN ('程婷婷', '153539', '谢博帆', 'wangxiuyun1977', '欧阳骁123456', 'zemelanchen@163.com', '陈德群', '廖文君', '金陈超', 'hanrongkai', '王增玉', 'zhang123456', '张佳良', 'hurk4120', '刘云徽', '刘巍', '缪智伟', 'Molly0926', 'runyang', '拖油瓶，，有点忘了', '18022919616', 'zgfy821015', 'ZGZMYZGZMY', '徐科', 'lx130633', '常丽婷', '第十八号元素', '陈道彬', 'littlerabbit')";
        executeQuery(sql, null);
    }
    
    private static String createSingleId(){
        return majorKey.getMajorKey();
    }

    private static List<String> createIdList(int i) {
        List<String> ids = new LinkedList<>();
        for (int j = 0; j < i; j++) {
            ids.add(majorKey.getMajorKey());
        }
        return ids;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
