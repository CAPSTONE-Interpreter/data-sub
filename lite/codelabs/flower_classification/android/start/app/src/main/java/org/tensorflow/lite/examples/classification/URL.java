package org.tensorflow.lite.examples.classification;

public class URL {
    String id;
    String text;
    String url;

    public URL(String id, String text, String url){
        this.id = id;
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

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
