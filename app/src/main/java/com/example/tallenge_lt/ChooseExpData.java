package com.example.tallenge_lt;

public class ChooseExpData {
    private String iv_ctf;
    private String tv_big_category;
    private String tv_small_category;
    private String isCertified;

    public ChooseExpData(){

    }

    public ChooseExpData(String iv_ctf, String tv_big_category, String tv_small_category, String isCertified) {
        this.iv_ctf = iv_ctf;
        this.tv_big_category = tv_big_category;
        this.tv_small_category = tv_small_category;
        this.isCertified = isCertified;
    }

    public String getIv_ctf() {
        return iv_ctf;
    }

    public void setIv_ctf(String iv_ctf) {
        this.iv_ctf = iv_ctf;
    }

    public String getTv_big_category() {
        return tv_big_category;
    }

    public void setTv_big_category(String tv_big_category) {
        this.tv_big_category = tv_big_category;
    }

    public String getTv_small_category() {
        return tv_small_category;
    }

    public void setTv_small_category(String tv_small_category) {
        this.tv_small_category = tv_small_category;
    }

    public String getCertified() {
        return isCertified;
    }

    public void setCertified(String certified) {
        isCertified = certified;
    }
}
