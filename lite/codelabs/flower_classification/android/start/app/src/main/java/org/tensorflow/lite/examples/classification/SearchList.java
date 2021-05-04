package org.tensorflow.lite.examples.classification;

import java.io.Serializable;

public class SearchList implements Serializable {
    String text;
    String url;

    public SearchList(String text, String url){
        this.text = text;
        this.url = url;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

}
