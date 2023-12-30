package File;

import java.io.File;

public class Test3 {
    //用递归来遍历可能有子文件夹的文件
    public static void main(String[] args) {
        File file = new File("myfile");
        findavi(file);
    }
    public static void findavi(File file){
        File[] files = file.listFiles();
        assert files != null;
        for(File f: files){
            if(f.isFile()){
                System.out.println(f.getName());
            }else{
                findavi(f);
            }
        }
    }
}
