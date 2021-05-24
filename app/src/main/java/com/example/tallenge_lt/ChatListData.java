package com.example.tallenge_lt;

public class ChatListData {
    private String roomID;
    private String nickname;//사용자
    private String other_nickname;//상대방?

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOther_nickname() {
        return other_nickname;
    }

    public void setOther_nickname(String other_nickname) {
        this.other_nickname = other_nickname;
    }

    ChatListData(){}

}
