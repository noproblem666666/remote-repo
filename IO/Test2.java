package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("");
        FileOutputStream fileOutputStream = new FileOutputStream("");
        int b;
        while((b=fileInputStream.read())!=-1){
            fileOutputStream.write(b^2);  //用异或进行加密

        }
        fileOutputStream.close();
        fileInputStream.close();
    }
}
