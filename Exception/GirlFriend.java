package Exception;

public class GirlFriend {
    private String name;
    private int age;

    public GirlFriend() {
    }

    public GirlFriend(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()<3||name.length()>10)
            throw new NameFormatException(name+"姓名长度不对");
        this.name = name;
    }

    public int getAng() {
        return age;
    }

    public void setAge(int age) {
        if(age<18||age>40)
            throw new AgeOutOfRangeException(age+"年龄范围不对");
        this.age = age;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
