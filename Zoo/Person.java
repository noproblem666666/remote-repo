package Zoo;

public class Person {
    private int age;
    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void keepPet(Dog dog,String something){
//        System.out.println(age+"岁的"+name+"养了一只"+dog.getColor()+"颜色的"+ dog.getAge()+"岁的狗");
//        dog.eat(something);
//    }
//    public void keepPet(Cat cat,String something){
//        System.out.println(age+"岁的"+name+"养了一只"+cat.getColor()+"颜色的"+ cat.getAge()+"岁的狗");
//        cat.eat(something);
//    }
    public void keepPet(Animal animal,String something){          //用到了多态
        if(animal instanceof Dog dog){
            System.out.println(age+"岁的"+name+"养了一只"+animal.getColor()+"颜色的"+ animal.getAge()+"岁的狗");
            dog.eat(something);
            dog.lookhome();
        }else if(animal instanceof Cat cat){
            System.out.println(age+"岁的"+name+"养了一只"+cat.getColor()+"颜色的"+ cat.getAge()+"岁的狗");
            cat.eat(something);
            cat.catchmouse();
        }else{
            System.out.println("没有这种动物");
        }
    }
}
