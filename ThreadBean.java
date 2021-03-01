package tera;

import java.io.Serializable;

public class ThreadBean implements Serializable {
    private String title;
    private String date;
    private String tag;
    private String comment;

    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate(){
        return date;
    }
    public void setDate(){
        this.date=date;
    }

    public String getTag(){
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getComment(){
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAll(String title,String date,String comment){
        this.title=title;
        this.date=date;
        this.comment=comment;
    }
}