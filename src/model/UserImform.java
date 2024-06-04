package model;

public class UserImform {


    private String username;
    private String userEmail;
    private long password;
    private long password1;
    public UserImform(String username,String userEmail,long password,long password1)
    {
        this.username=username;
        this.userEmail=userEmail;
        this.password=password;
        this.password1=password1;
    }
    public void setPassword(long key){password=key;}
    public void setPassword1(long key){password1=key;}
    public String getUsername() {return username;}

    public String getUserEmail() {return userEmail;}

    public long getPassword() {return password;}
    public long getPassword1() {
        return password1;
    }
    public String getPasswordAsString() {
        return String.valueOf(password);
    }
}
