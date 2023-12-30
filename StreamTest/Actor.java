package StreamTest;

public class Actor {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Actor() {
    }

    public Actor(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString(){
        return this.getName()+","+this.getAge();

    }
}
