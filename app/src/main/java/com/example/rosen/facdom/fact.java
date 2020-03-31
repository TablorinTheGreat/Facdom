package com.example.rosen.facdom;

public class fact {
    private String Content;
    private String src;

    public fact(String content, String src) {
        Content = content;
        this.src = src;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
