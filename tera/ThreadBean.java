package tera;

import java.io.Serializable;

public class ThreadBean implements Serializable {
    private int th_id;
    private String title;
    private String date;
    private String tag;
    private String details;


    public int getth_id(){
        return th_id;
    }
    public void setth_id(int th_id) {
        this.th_id = th_id;
    }

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

    public String getdetails(){
        return details;
    }
    public void setdetails(String details) {
        this.details = details;
    }

    public void setAll(int th_id,String title,String tag,String date,String details){
        this.th_id=th_id;
        this.title=title;
        this.tag=tag;
        this.date=date;
        this.details=details;
    }
}