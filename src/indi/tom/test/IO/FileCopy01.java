package indi.tom.test.IO;

import java.io.*;
import java.nio.Buffer;

/**
 * @Author totian
 * @Date 2019/11/6 23:06
 * @Version 1.0
 * @Description
 */
public class FileCopy01 {

    public static void main(String[] args) {

        //1.创建源
        File sourceFile = new File("mytest.dat");
        File destFile = new File("mydest.dat");
        //2.选择流
        try(InputStream is = new BufferedInputStream(new FileInputStream(sourceFile));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destFile));) {
            byte[] buffer = new byte[1024];

            //3.操作流
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer,0,length);
            }
            //4.关闭流
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
