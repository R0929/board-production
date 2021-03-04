package tera;

import java.io.Serializable;

public class ResponseBean implements Serializable {
	private int th_id;
	private int resid;
	private int count;
	private String name;
	private String date;
	private String text;
	
	public int getTh_id() {
		return th_id;
	}
	public void setTh_id(int th_id){
		this.th_id=th_id;
	}

	public int getResid() {
		return resid;
	}
	public void setResid(int resid){
		this.resid=resid;
	}


	public int getCount() {
		return count;
	}
	public void setCount(int count){
		this.count=count;
	}

	public String getName() {
		return name;
	}
	public void setName(String name){
		this.name=name;
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


	public void setAll(int th_id,int resid,int count,String date,String name,String text){
		this.th_id=th_id;
		this.resid=resid;
		this.count=count;
		this.name=name;
		this.date=date;
		this.text=text;
	}
}