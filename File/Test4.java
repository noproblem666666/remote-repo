package File;

import java.io.File;

public class Test4 {
    //删除一个多级文件夹,依然使用递归
    public static void main(String[] args) {
        File file = new File("myfile");
        delete(file);
    }

    public static void delete(File file){
        File[] files = file.listFiles();
        assert files!=null;
        for(File f:files){
            if(f.isFile()){
                f.delete();
            }else{
                delete(f);
            }
        }
        file.delete();
    }

}
