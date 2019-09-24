package com.mc.kafkascript.DealKafkaRepair;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.DealKafkaRepair
 * 类名称:     Repair
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/23 16:27
 */
@RestController
@Slf4j
public class Repair {

    private final static String URL = "jdbc:mysql://10.181.0.202:8075/accbook";
    private final static String USER = "accbook";
    private final static String PASS = "Zjs182@13112p";

    /*private final static String URL = "jdbc:mysql://10.21.20.228:8067/video_dev";
    private final static String USER = "usr_dev";
    private final static String PASS = "usr_dev";*/


    private static Connection conn = null;

    static String sql = "select * from ac_bill_flow_route where identifier = ?";

    @GetMapping(value = "/repair")
    public boolean repair(HttpServletRequest request) throws IOException, SQLException {
        // 手动指定充值异常文件
        String fileName = request.getParameter("file") == null ? "final.log" : request.getParameter("file");
        // 无内外部编码文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File(System
                .getProperty("user.dir") + "/noId.txt"), true);

        //重试未正常充值的文件
        FileOutputStream needReSendOutPutStream = new FileOutputStream(new File(System
                .getProperty("user.dir") + "/reSend.txt"), true);

        //未出问题的源文件
        FileOutputStream ori = new FileOutputStream(new File(System
                .getProperty("user.dir") + "/ori.txt"), true);


        FileOutputStream json = new FileOutputStream(new File(System
                .getProperty("user.dir") + "/json.txt"), true);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(System
                .getProperty("user.dir") + File.separator + fileName)));


        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql);
            String msg;

            // 0:method_name  1:result_code  2:in_args_value  3:out_args_value
            String[] msgArray;
            while ((msg = bufferedReader.readLine()) != null) {
                log.error("文件读取:" + msg);
                msgArray = msg.split("\\+");

                // 入参
                msg = msg.substring(msg.indexOf("+{")+1,msg.indexOf("}+")+1);

                // 方法名
                if (!"DepositPersonalBook".equals(msgArray[0])) {
                    continue;
                }
                String identifier;
                DepositPersonalBookReq req;
                try {

                    req = JSON
                            .parseObject(msg, DepositPersonalBookReq.class);
                } catch (Exception e) {
                    log.error("json转换失败");
                    json.write(msg.getBytes());
                    continue;
                }
                // 1.是否不包含内外部编码
                if (StringUtils.isEmpty(req.getDepositDetail().getInnerId())
                        && StringUtils.isEmpty(req.getDepositDetail().getExternalId())) {
                    fileOutputStream.write(msg.concat("\n").getBytes());
                } else {
                    identifier = StringUtils.isEmpty(req.getDepositDetail().getInnerId())
                            ? req.getDepositDetail().externalId : req.getDepositDetail().getInnerId();
                    identifier = identifier.startsWith("013") ? identifier : "013" + identifier;
                    ps.setString(1, identifier);
                    ResultSet resultSet = ps.executeQuery();
                    // 查询无结果集
                    if (resultSet == null || !resultSet.next()) {
                        log.error("---------无结果！！！");
                        needReSendOutPutStream.write(msg.concat("\n").getBytes());
                    } else {
                        ori.write(msg.concat("\n").getBytes());
                    }
                }
            }
            log.error("文件读取结束！！！！！");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            fileOutputStream.close();
            needReSendOutPutStream.close();
            bufferedReader.close();
            json.getClass();
            return true;
        }
    }
}
