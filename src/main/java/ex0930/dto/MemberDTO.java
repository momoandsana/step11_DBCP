package ex0930.dto;

public class MemberDTO
{
    private String id;
    private String pwd;
    private String name;
    public String age;
    private String phone;
    private String addr;
    private String joinDate;

    public MemberDTO(){}

    public MemberDTO(String id, String pwd, String name,String age, String phone, String addr, String joinDate) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.age=age;
        this.phone = phone;
        this.addr = addr;
        this.joinDate = joinDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}
