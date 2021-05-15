package com.example.tallenge_lt.Catagory_item;

public class Lan_item {
    //언어
    private String Eng; //영어
    private String Chi; //중국어
    private String Jap;  //일본어
    private String span ;  //스페인어

    public Lan_item() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음
    //언어
    public String getEng() {return Eng;}

    public void setEng(String Eng) {this.Eng = Eng;}

    public String getChi() {return Chi;}

    public void setChi(String Chi) {this.Chi = Chi;}

    public String getJap() {return Jap;}

    public void setJap(String Jap) {this.Jap = Jap;}

    public String getSpan() {return span;}

    public void setSpan(String span) {this.span = span;}

}
