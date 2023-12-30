package IO;

import java.io.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        File file1 = new File("源文件夹地址");//需要考虑子文件夹
        File file2 = new File("拷贝文件夹地址");
        coprdir(file1,file2);
    }

    private static void coprdir(File file1, File file2) throws IOException {
        file2.mkdirs();
        //1.进入数据源
        File[] files = file1.listFiles();
        //2.遍历数组
        //3.判断是文件拷贝
        //4.判断是文件夹递归
        assert files != null;
        for (File file : files) {
            if(file.isFile()){
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file2,file.getName()));
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len = fileInputStream.read(bytes))!=-1){
                    fileOutputStream.write(bytes,0,len);
                }
                fileOutputStream.close();
                fileInputStream.close();
            }else{
                coprdir(file,new File(file2,file.getName()));
            }

        }


    }
}
