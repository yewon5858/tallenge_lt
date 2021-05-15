package com.example.tallenge_lt.Catagory_item;

public class Exp_item {
    // 전문분야
    private String computer; //컴퓨터
    private String stock; //주식
    private String history;  //역사
    private String math;  //수학


    public Exp_item() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음

    //전문분야
    public String getComputer() {return computer;}

    public void setComputer(String computer) {this.computer = computer;}

    public String getStock() {return stock;}

    public void setStock(String stock) {this.stock = stock;}

    public String getHistory() {return history;}

    public void setHistory(String history) {this.history = history;}

    public String getMath() {return math;}

    public void setMath(String math) {this.math = math;}


}

