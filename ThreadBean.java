package bean;

import java.io.Serializable;

public class ThreadBean implements Serializable{
    private String title;
    private String tag;
    private String comment;

    public ThreadBean(){}

    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
	public void setComment(String Comment){
		this.comment = comment;
	}

}
