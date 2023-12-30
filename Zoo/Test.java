package Zoo;

public class Test {
    public static void main(String[] args) {
        Person person1 =new Person(30,"老王");
        Dog dog =new Dog(2,"黑");
        person1.keepPet(dog,"骨头");

        Person person2 =new Person(25,"老李");
        Cat cat =new Cat(3,"灰");
        person2.keepPet(cat,"鱼");



    }
}
