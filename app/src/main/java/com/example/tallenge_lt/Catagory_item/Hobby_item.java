package com.example.tallenge_lt.Catagory_item;

public class Hobby_item {
    //취미
    private String music; //음악
    private String Tarot; //타로
    private String Resin;  //레진공예
    private String Drawing;  //그림

    public Hobby_item() { } // 빈 생성자를 선언해줘야 firebase에 값을 가져오고 넣을 때, 오류가 생기지 않음
    //취미
    public String getMusic() {return music;}

    public void setMusic(String music) {this.music = music;}

    public String getTarot() {return Tarot;}

    public void setTarot(String Tarot) {this.Tarot = Tarot;}

    public String getResin() {return Resin;}

    public void setResin(String Resin) {this.Resin = Resin;}

    public String getDrawing() {return Drawing;}

    public void setDrawing(String Drawing) {this.Drawing = Drawing;}


}
