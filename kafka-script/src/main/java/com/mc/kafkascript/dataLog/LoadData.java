package com.mc.kafkascript.dataLog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.dataLog
 * 类名称:     LoadData
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/23 22:10
 */
@RestController
@Slf4j
public class LoadData {
    private final static String URL = "jdbc:mysql://10.181.0.202:8075/accbook_log";
    private final static String USER = "accbook_log_read";
    private final static String PASS = "xx122@@8Ap";

    @GetMapping("/req")
    public Boolean hand() throws SQLException, IOException {
        Connection conn;
        String sql = "select in_args_value from uc_pinkstone_monitor_log22 " +
                "WHERE month = '01309' and  method_name= 'depositPersonalBook' " +
                "and result_code='1620000195' and visited_time > '2019-09-22 17:55:00.00' " +
                "and visited_time < '2019-09-22 20:00:00.00'";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir")+"/req.txt"));
        conn = DriverManager.getConnection(URL, USER, PASS);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                try {
                    String req = resultSet.getString("in_args_value");
                    fileOutputStream.write(req.concat("\n").getBytes());
                } catch (Exception e) {
                    log.error("写文件异常,e is {}",e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("查询日志库异常,e is {}",e.getMessage());
        } finally {
            statement.close();
            conn.close();
        }
        return true;
    }
}
