package StudentsManage;

public class Student {
    private String ID;
    private String name;
    private int age;
    private String address;

    public Student() {
    }

    public Student(String ID, String name, int age, String address) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
