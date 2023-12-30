package Zoo;

public class Cat extends Animal{
    public Cat() {
    }

    public Cat(int age, String color) {
        super(age, color);             //调用父类的构造方法
    }
    @Override
    public void eat(String something){
        System.out.println(getAge()+"岁的"+getColor()+"颜色的猫吃"+something);
    }

    public void catchmouse(){
        System.out.println("猫抓老鼠");
    }
}
