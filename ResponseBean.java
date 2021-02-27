package tera;

import java.io.Serializable;

public class ResponseBean implements Serializable {
	protected String id;
	protected int count;
	protected String date;
	protected String text;
	protected int likes;
	
	public String getId() {
		return id;
	}
	public void setId(String id){
		this.id=id;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count){
		this.count=count;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}

	public String getText() {
		return text;
	}
	public void setText(String text){
		this.text=text;
	}

	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes){
		this.likes=likes;
	}


	public void setAll(String date,String text){
		this.date=date;
		this.text=text;
	}
}