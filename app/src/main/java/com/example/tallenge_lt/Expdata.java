package com.example.tallenge_lt;

import com.example.tallenge_lt.Exp_catagory.Exp_item;
import com.example.tallenge_lt.Exp_catagory.Hobby_item;
import com.example.tallenge_lt.Exp_catagory.Lan_item;
import com.example.tallenge_lt.Exp_catagory.Spo_item;

public class Expdata {
    // 전문분야
    private String computer; //컴퓨터
    private String stock; //주식
    private String history;  //역사
    private String math;  //수학
    //취미
    private String music; //음악
    private String Tarot; //타로
    private String Resin;  //레진공예
    private String Drawing;  //그림
    //언어
    private String Eng; //영어
    private String Chi; //중국어
    private String Jap;  //일본어
    private String span ;  //스페인어
    //스포츠
    private String swim; //수영
    private String Pilates; //필라테스
    private String HT;  //홈트
    private String Badminton;  //배드민턴

     private Exp_item exp_item; // Exp_item 을 받는 클래스
     private Hobby_item hobby_item; // Hobby_item 을 받는 클래스
     private Lan_item lan_item; // Lan_item 을 받는 클래스
     private Spo_item spo_item; // Spo_item 을 받는 클래스


    public Expdata() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음

//    //child를 새로 만듦
//    public Exp_item getExp_item(){ return exp_item;}
//
//    public void setExp_item(Exp_item exp_item) {this.exp_item = exp_item;}
//
//    public Hobby_item getHobby_item(){ return hobby_item;}
//
//    public void setHobby_item(Hobby_item hobby_item) {this.hobby_item = hobby_item;}
//
//    public Lan_item getLan_item(){ return lan_item;}
//
//    public void setLan_item(Lan_item lan_item) {this.lan_item = lan_item;}
//
//    public Spo_item getSpo_item(){ return spo_item;}
//
//    public void setSpo_item(Spo_item spo_item) {this.spo_item = spo_item;}


 //   --------------------------------------------------------------------
    //전문분야
    public String getComputer() {return computer;}

    public void setComputer(String computer) {this.computer = computer;}

    public String getStock() {return stock;}

    public void setStock(String stock) {this.stock = stock;}

    public String getHistory() {return history;}

    public void setHistory(String history) {this.history = history;}

    public String getMath() {return math;}

    public void setMath(String math) {this.math = math;}

    //취미
    public String getMusic() {return music;}

    public void setMusic(String music) {this.music = music;}

    public String getTarot() {return Tarot;}

    public void setTarot(String Tarot) {this.Tarot = Tarot;}

    public String getResin() {return Resin;}

    public void setResin(String Resin) {this.Resin = Resin;}

    public String getDrawing() {return Drawing;}

    public void setDrawing(String Drawing) {this.Drawing = Drawing;}

    //언어
    public String getEng() {return Eng;}

    public void setEng(String Eng) {this.Eng = Eng;}

    public String getChi() {return Chi;}

    public void setChi(String Chi) {this.Chi = Chi;}

    public String getJap() {return Jap;}

    public void setJap(String Jap) {this.Jap = Jap;}

    public String getSpan() {return span;}

    public void setSpan(String span) {this.span = span;}


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
