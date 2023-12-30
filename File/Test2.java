package File;

import java.io.File;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        File file = new File("myfile");
        boolean b = haveavi(file);
        System.out.println(b);
    }

    public static boolean haveavi(File file) {
        File[] files = file.listFiles();
        System.out.println(Arrays.toString(files));
        assert files != null;
        for (File f : files){
            if(f.isFile()&&f.getName().endsWith(".avi")){
                return true;
            }
        }
        return false;
    }
}
