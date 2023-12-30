package File;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test6 {
    //统计一个文件夹每种文件的个数并打印
    public static void main(String[] args) {
        File file = new File("image");
        HashMap<String, Integer> count = getCount(file);
        System.out.println(count);

    }

    public static  HashMap<String,Integer> getCount(File file){
        HashMap<String,Integer> hashMap = new HashMap<>();
        File[] files = file.listFiles();
        assert files != null;
        for(File f:files){
            if(f.isFile()){
                String name =f.getName();
                String[] strings = name.split("\\.");
                if(strings.length>=2){
                    String endName = strings[strings.length-1];
                    if(hashMap.containsKey(endName)){
                        int count = hashMap.get(endName);
                        count++;
                        hashMap.put(endName,count);
                    }else{
                        hashMap.put(endName,1);
                    }
                }
            }else{
                HashMap<String ,Integer> someMap =getCount(f);
                Set<Map.Entry<String, Integer>> entries = someMap.entrySet();
                for (Map.Entry<String, Integer> entry : entries) {
                    String key =entry.getKey();
                    int value = entry.getValue();
                    if(hashMap.containsKey(key)){
                        int hmCount = hashMap.get(key);
                        hashMap.put(key,hmCount+value);
                    }else{
                        hashMap.put(key,value);
                    }
                }
            }
        }
        return hashMap;
    }
}
