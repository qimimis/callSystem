package com.yuanhao.utils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class JdbcUtils {
    static File file = new File("call_server/src/com/yuanhao/config/jdbc.properties");
    static Map<String,String> map;

    static {
        try {
            map = PropretiesUtils.getProperties(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String USERNAME= map.get("jdbc.userName");
    private static final String PASSWORD=map.get("jdbc.password");
    private static final String URI = map.get("jdbc.url");
    static {
        try {
            Class.forName(map.get("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public JdbcUtils() throws IOException {
    }

    public static Connection openConn() throws SQLException {
        return DriverManager.getConnection(URI,USERNAME,PASSWORD);
    }
    public static void closeResoure(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static int JdbcExcuteUpdate(String sql,Object... objects){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rows = -1;
        try {
            connection = JdbcUtils.openConn();
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i+1,objects[i]);
            }
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.closeResoure(null,preparedStatement,connection);
            return rows;
        }

    }
}
