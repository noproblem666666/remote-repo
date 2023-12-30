package EnhancedStudentsManage;

public class Student {
    private String name;
    private String code;
    private String ID;
    private String phone;

    public Student() {
    }

    public Student(String name, String code, String ID, String phone) {
        this.name = name;
        this.code = code;
        this.ID = ID;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
