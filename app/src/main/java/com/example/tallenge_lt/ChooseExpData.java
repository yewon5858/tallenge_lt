package com.example.tallenge_lt;

public class ChooseExpData {
    private int iv_ctf;   //이미지뷰는 int값으로 처리함
    private String tv_exp;


    public ChooseExpData(int iv_ctf, String tv_exp) {
        this.iv_ctf = iv_ctf;
        this.tv_exp = tv_exp;
    }


    public int getIv_ctf() {
        return iv_ctf;
    }

    public void setIv_ctf(int iv_ctf) {
        this.iv_ctf = iv_ctf;
    }

    public String getTv_exp() {
        return tv_exp;
    }

    public void setTv_exp(String tv_exp) {
        this.tv_exp = tv_exp;
    }
}
