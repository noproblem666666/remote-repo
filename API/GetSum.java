package API;

public class GetSum {
    public static void main(String[] args) {
        getsum(1,1,2,3,4,5,6,8);
    }
    public static void getsum(int...args){
        int sum =0 ;
        for(int i:args){
            sum+=i;
        }
        System.out.println(sum);
    }
}
