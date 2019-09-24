package com.mc.kafkascript.dataLog;

import com.alibaba.fastjson.JSON;
import com.mc.kafkascript.DealKafkaRepair.DepositPersonalBookReq;
import com.mc.kafkascript.feign.Deposit;
import com.mc.kafkascript.utils.CocurrentFileWrite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.dataLog
 * 类名称:     HandIsDeposit
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/23 22:21
 */
@RestController
@Slf4j
public class HandIsDeposit {

    private final static String URL = "jdbc:mysql://10.181.0.202:8075/accbook";
    private final static String USER = "accbook";
    private final static String PASS = "Zjs182@13112p";
    String sql = "select * from ac_bill_deposit_flow where user_id = ? and currency_type_id =  '1253' " +
            "and inner_id = '' and external_id = '' and amount = ? and gmt_create > '2019-09-22 17:55:00'";


    @GetMapping("/isIn")
    public boolean isIn() throws IOException, SQLException, InterruptedException {

        Lock lock = new ReentrantLock();
        Connection conn;
        conn = DriverManager.getConnection(URL, USER, PASS);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(System
                .getProperty("user.dir")+"/notVideo.txt"),true);

        FileOutputStream fileOutputStream1 = new FileOutputStream(new File(System
                .getProperty("user.dir")+"/noDeposit.txt"),true);

        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(new File(System
                .getProperty("user.dir")+"/req.txt")));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File(System
                .getProperty("user.dir")+"/noId.txt")));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.submit(new ReadTask(countDownLatch,bufferedReader1,conn,fileOutputStream,fileOutputStream1,lock));
        executorService.submit(new ReadTask(countDownLatch,bufferedReader2,conn,fileOutputStream,fileOutputStream1,lock));
        countDownLatch.await();
        fileOutputStream.close();
        fileOutputStream1.close();
        bufferedReader1.close();
        bufferedReader2.close();
        conn.close();
        log.error("执行结束");
        return true;
    }

    private class ReadTask implements Callable<Boolean> {
        CountDownLatch countDownLatch;
        BufferedReader bufferedReader;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream1;
        Lock lock;
        Connection con;
        public ReadTask(CountDownLatch countDownLatch,BufferedReader bufferedReader1,Connection con
                ,FileOutputStream fileOutputStream,FileOutputStream fileOutputStream1,Lock lock) {
            this.countDownLatch = countDownLatch;
            this.bufferedReader = bufferedReader1;
            this.con = con;
            this.fileOutputStream = fileOutputStream;
            this.fileOutputStream1 = fileOutputStream1;
            this.lock = lock;
        }


        @Override
        public Boolean call() {
            try {
                PreparedStatement statement = con.prepareStatement(sql);
                String msg;
                while ((msg = bufferedReader.readLine()) != null) {
                    DepositPersonalBookReq req = JSON.parseObject(msg,DepositPersonalBookReq.class);
                    String userId = req.getDepositDetail().getUserId().startsWith("013U") ? req.getDepositDetail()
                            .getUserId() : "013U"
                            + req.getDepositDetail().getUserId();
                    String amount = req.getDepositDetail().getDepositCount();
                    statement.setString(1,userId);
                    statement.setString(2,amount);
                    // 不是通看券充值记录的
                    if(!"CMVIDEO_TICKET".equals(req.getDepositDetail().getAcctTypeCode())) {
                        CocurrentFileWrite.write(fileOutputStream,msg.concat("\n"));
                    } else {
                        ResultSet resultSet = statement.executeQuery();
                        // 未查到充值流水记录
                        while(!resultSet.next()) {
                            CocurrentFileWrite.write(fileOutputStream,msg.concat("\n"));
                        }
                    }
                }
            } catch (Exception e) {

            } finally {
                countDownLatch.countDown();
                return true;
            }
        }
    }
}
