package API;

public class ToBinary {
    public static void main(String[] args) {
        int num=123;
        StringBuilder stringBuilder =new StringBuilder();
        while(true){
            if(num==0)
                break;
            int reminder =num%2;
            num =num/2;
            stringBuilder.insert(0,reminder);
        }
        System.out.println(stringBuilder.toString());
    }
}
