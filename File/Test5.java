package File;

import java.io.File;

public class Test5 {
    //统计一个文件夹的总大小
    public static void main(String[] args) {
        File file = new File("image");
        long getlength = getlength(file);
        System.out.println(getlength);

    }
    public static long getlength(File file){
        long len = 0;
        File[] files = file.listFiles();
        assert files!=null;
        for(File f: files){
            if(f.isFile()){
                len=len+f.length();
            }else{
                len=len+getlength(f);
            }
        }
        return len;
    }
}
