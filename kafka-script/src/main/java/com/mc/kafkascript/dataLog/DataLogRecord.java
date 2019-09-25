package com.mc.kafkascript.dataLog;

import java.sql.*;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.dataLog
 * 类名称:     DataLogRecord
 * 类描述:     处理日志监控查询类
 * 创建人:     mc
 * 创建时间:   2019/9/23 14:54
 */
public class DataLogRecord {

    private final static String URL = "jdbc:mysql://10.181.0.202:8075/accbook_log";
    private final static String USER = "accbook_log_read";
    private final static String PASS = "xx122@@8Ap";

    public static void main(String[] args) {
        Connection conn;
        String sql = "select * from uc_pinkstone_monitor_log22 where month = '01309' " +
                "and result_code = '1600000099' order by visited_timde desc limit 1";
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);

            Statement statement = conn.createStatement();
            statement.executeQuery(sql);

            // 结果集
            ResultSet result = statement.getResultSet();
            /*result.getString()*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
