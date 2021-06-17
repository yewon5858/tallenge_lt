package com.example.tallenge_lt;
//firebase에 저장될 DB명 선언
public class AddCheckData {
    private String checkitem1;
    private String checkitem2;
    private String checkitem3;
    private String checkitem4;

    public String getCheckitem1() {
        return checkitem1;
    }

    public void setCheckitem1(String checkitem1) {
        this.checkitem1 = checkitem1;
    }

    public String getCheckitem2() {
        return checkitem2;
    }

    public void setCheckitem2(String checkitem2) {
        this.checkitem2 = checkitem2;
    }

    public String getCheckitem3() {
        return checkitem3;
    }

    public void setCheckitem3(String checkitem3) {
        this.checkitem3 = checkitem3;
    }

    public String getCheckitem4() {
        return checkitem4;
    }

    public void setCheckitem4(String checkitem4) {
        this.checkitem4 = checkitem4;
    }

    public String getCheckitem5() {
        return checkitem5;
    }

    public void setCheckitem5(String checkitem5) {
        this.checkitem5 = checkitem5;
    }

    private String checkitem5;

    private String checkTitle;
    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
    public AddCheckData(String CheckTitle,String checkitem1,String checkitem2,String checkitem3,String checkitem4,String checkitem5){
        this.checkTitle=CheckTitle;
        this.checkitem1=checkitem1;
        this.checkitem2=checkitem2;
        this.checkitem3=checkitem3;
        this.checkitem4=checkitem4;
        this.checkitem5=checkitem5;
    }


    public AddCheckData(){}


    public String getCheckTitle() {
        return checkTitle;
    }

    public void setCheckTitle(String checkTitle) {
        this.checkTitle = checkTitle;
    }
}
