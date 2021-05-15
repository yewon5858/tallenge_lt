package com.example.tallenge_lt.Exp_catagory;

public class Spo_item {

    //스포츠
    private String swim; //수영
    private String Pilates; //필라테스
    private String HT;  //홈트
    private String Badminton;  //배드민턴

    public Spo_item() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음

    //스포츠
    public String getSwim() {return swim;}

    public void setSwim(String swim) {this.swim = swim;}

    public String getPilates() {return Pilates;}

    public void setPilates(String Pilates) {this.Pilates = Pilates;}

    public String getHT() {return HT;}

    public void setHT(String HT) {this.HT = HT;}

    public String getBadminton() {return Badminton;}

    public void setBadminton(String Badminton) {this.Badminton = Badminton;}
}

