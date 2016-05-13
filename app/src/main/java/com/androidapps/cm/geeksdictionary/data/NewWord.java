package com.androidapps.cm.geeksdictionary.data;

// +----------------------------------------------------------------------
// | CreateTime: 16/5/10 
// +----------------------------------------------------------------------
// | Author:     cm
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public class NewWord {
    private String word;
    private String meaing;

    public String getWord() {
        return word;
    }

    public String getMeaing() {
        return meaing;
    }

    public void setWord(String word) {

        this.word = word;
    }

    public void setMeaing(String meaing) {
        this.meaing = meaing;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "word:"+word+"meaing:"+meaing;
    }


}
