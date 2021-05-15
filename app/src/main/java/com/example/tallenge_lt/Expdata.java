package com.example.tallenge_lt;

import com.example.tallenge_lt.Catagory_item.Exp_item;
import com.example.tallenge_lt.Catagory_item.Hobby_item;
import com.example.tallenge_lt.Catagory_item.Lan_item;
import com.example.tallenge_lt.Catagory_item.Spo_item;

public class Expdata {

     private Exp_item exp_item; // Exp_item 을 받는 클래스
     private Hobby_item hobby_item; // Hobby_item 을 받는 클래스
     private Lan_item lan_item; // Lan_item 을 받는 클래스
     private Spo_item spo_item; // Spo_item 을 받는 클래스


    public Expdata() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음

    //모두 클래스이기 때문에 child 새로 만듦
    public Exp_item getExp_item(){ return exp_item;}

    public void setExp_item(Exp_item exp_item) {this.exp_item = exp_item;}

    public Hobby_item getHobby_item(){ return hobby_item;}

    public void setHobby_item(Hobby_item hobby_item) {this.hobby_item = hobby_item;}

    public Lan_item getLan_item(){ return lan_item;}

    public void setLan_item(Lan_item lan_item) {this.lan_item = lan_item;}

    public Spo_item getSpo_item(){ return spo_item;}

    public void setSpo_item(Spo_item spo_item) {this.spo_item = spo_item;}


}
