package indi.tom.test.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author totian
 * @Date 2019/11/6 22:52
 * @Version 1.0
 * @Description
 */
public class FileOutputSteamTest {
    public static void main(String[] args) throws IOException {
        //1.创建源
        File file = new File("mytest.dat");
        //2.选择流
        OutputStream os = new FileOutputStream(file);
        //3.操作
        String s = "IO is so easy!";
        byte[] datas = s.getBytes();
        os.write(datas);
        //4.关闭流
        if(os != null){
            os.close();
        }
    }
}
