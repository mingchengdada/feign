package com.mc.kafkascript.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.utils
 * 类名称:     CocurrentFileWrite
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/24 9:50
 */
public class CocurrentFileWrite {

    public static void write(FileOutputStream fileOutputStream, String messga) throws IOException {
        FileChannel fileChannel = fileOutputStream.getChannel();
        FileLock fileLock = null;
        try {
            while (true) {
                try {
                    fileLock = fileChannel.lock();
                    break;
                } catch (Exception e) {
                    // 高并发写文件，为获取到锁会抛出异常
                }
            }
            fileOutputStream.write(messga.getBytes());
        } finally {
            fileLock.release();
            fileChannel.close();
        }
    }
}
