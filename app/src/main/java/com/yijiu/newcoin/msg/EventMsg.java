package com.yijiu.newcoin.msg;

public class EventMsg {
    @Override
    public String toString() {
        return "EventMsg{" +
                "word='" + word + '\'' +
                '}';
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    String word;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public EventMsg(String type, String word) {
        this.word = word;
        this.type = type;
    }


    public EventMsg(String word) {
        this.word = word;
    }
}
