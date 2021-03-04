import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import tera.*;

//�ϐ�res��ResponseBean�̃C���X�^���X
//�ϐ�reponses��ArrayList<ResponseBean>�̃C���X�^���X
//�ϐ�th��ThreadBean�̃C���X�^���X
//�ϐ�threads��ArrayList<ThreadBean>�̃C���X�^���X


class DBAccess{
	public static Connection DBConnect() throws Exception{	//Oracle�ɐڑ����郁�\�b�h	
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection cn=
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			"bowner","bo");
		System.out.println("�ڑ�����");

		return cn;
	}


	public ArrayList<ThreadBean> ThreadGet(String ti,String tg,String c){	//�X���b�h��DataBase�ɒǉ���A�l�����o����List�ɒǉ����郁�\�b�h
		ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();
		try{
			//DBConnect�̖߂�l������ϐ���錾
			Connection cn = DBConnect();

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//insert��
			String sql="INSERT INTO board_Thread(th_id,th_title,th_category,th_details) VALUES(ThId.NEXTVAL,'"+ti+"','"+tg+"','"+c+"')";

			//insert���̎��s
			ResultSet rs=st.executeQuery(sql);

			//select��
			sql="select * from board_thread order by th_date desc";

			//select���̎��s
			rs=st.executeQuery(sql);

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
			int th_id=rs.getInt(1);
			String th_title=rs.getString(2);
			String th_category=rs.getString(3);
			String th_date=rs.getString(4);
			String th_details=rs.getString(5);

			//tomcat�ɕ\��
			System.out.println(th_id+"\t"+th_title+"\t"+th_details);

			//ThreadBean���C���X�^���X�����A�f�[�^���Z�b�g����
			ThreadBean th=new ThreadBean();

			//setter���Ăяo���Ēl���Z�b�g
			th.setAll(th_id,th_title,th_category,th_date,th_details);

			//�Z�b�g�����l��ArrayList�ɒǉ�
			threads.add(th);
			}

			//Oracle����ؒf����
			cn.close();
			System.out.println("�ؒf����");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
		e.printStackTrace();
		}
		return threads;
	}

	public ArrayList<ThreadBean> ThSelect(){ //select�݂̂̃��\�b�h
		ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();
		try{
			//DBConnect�̖߂�l������ϐ���錾
			Connection cn = DBConnect();
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//select��
			String sql="select * from board_thread order by th_date desc";
			//select���̎��s
			ResultSet rs=st.executeQuery(sql);

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
				int th_id=rs.getInt(1);
				String th_title=rs.getString(2);
				String th_category=rs.getString(3);
				String th_date=rs.getString(4);
				String th_details=rs.getString(5);
	
				//tomcat�ɕ\��
				System.out.println(th_id+"\t"+th_title+"\t"+th_details);
	
				//ThreadBean���C���X�^���X�����A�f�[�^���Z�b�g����
				ThreadBean th=new ThreadBean();
	
				//setter���Ăяo���Ēl���Z�b�g
				th.setAll(th_id,th_title,th_category,th_date,th_details);
	
				//�Z�b�g�����l��ArrayList�ɒǉ�
				threads.add(th);
			}
	
				//Oracle����ؒf����
				cn.close();
				System.out.println("�ؒf����");
	
		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
		e.printStackTrace();
		}
		return threads;
	}

	public ArrayList<ResponseBean> ThidSelect(int th_id){
		ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();
		try{
			Connection cn=DBConnect();

			Statement st=cn.createStatement();

			String sql="select * from board_res where th_id="+th_id+" order by res_date asc";

			ResultSet rs=st.executeQuery(sql);

			while(rs.next()){
			th_id=rs.getInt(1);
			int res_id=rs.getInt(2);
			String res_date=rs.getString(3);
			String res_text=rs.getString(4);
			int res_count=rs.getInt(5);
			String user_name=rs.getString(6);

			ResponseBean res=new ResponseBean();
			res.setAll(th_id,res_id,res_count,res_date,user_name,res_text);
			responses.add(res);
			}

			cn.close();
			System.out.println("�ؒf����");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
		e.printStackTrace();
		}
		return responses;
	}

	public ArrayList<ThreadBean> TitleSelect(int th_id){
		ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();
		try{
			Connection cn=DBConnect();

			Statement st=cn.createStatement();

			String sql="select th_title,th_category from board_thread where th_id="+th_id+"";

			ResultSet rs=st.executeQuery(sql);

			while(rs.next()){
				String th_title=rs.getString(1);
				String th_category=rs.getString(2);

				ThreadBean th=new ThreadBean();

				th.setTitle(th_title);
				th.setTag(th_category);

				threads.add(th);

			}
		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
		e.printStackTrace();
		}
			return threads;
	}

	public int getResCount(int th_id){ //���X�̃J�E���g�𐔂��郁�\�b�h
		int res_count=0;
		try{
			Connection cn=DBConnect();

			Statement st=cn.createStatement();
	
			String sql="select max(res_count) from board_res where th_id="+th_id+"";

			ResultSet rs=st.executeQuery(sql);

			if(rs.next() && rs.getString(1) != null){
				res_count=rs.getInt(1);
				System.out.println(res_count);
			}
			cn.close();
			System.out.println("�ؒf����");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
		e.printStackTrace();
		}
		
		return res_count+1;
	}


	public ArrayList ResGet(int th_id,String t,String n){	//���X��DataBase�ɒǉ���A�l�����o����List�ɒǉ����郁�\�b�h
		ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();
		try{
			//DBConnect�̖߂�l������ϐ���錾
			Connection cn = DBConnect();

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			int res_count=getResCount(th_id);

			//insert��
			String sql="INSERT INTO board_res(th_id,res_id,res_count,user_name,res_text) VALUES("+th_id+",ResId.NEXTVAL,'"+res_count+"','"+n+"','"+t+"')";

			//insert���̎��s
			ResultSet rs=st.executeQuery(sql);

			//select��
			sql="select * from board_res order by res_date desc";

			//select���̎��s
			rs=st.executeQuery(sql);

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
				th_id=rs.getInt(1);
				int res_id=rs.getInt(2);
				String res_date=rs.getString(3);
				String res_text=rs.getString(4);
				res_count=rs.getInt(5);
				String user_name=rs.getString(6);
	
				ResponseBean res=new ResponseBean();
				res.setAll(th_id,res_id,res_count,res_date,user_name,res_text);
				responses.add(res);
				}

			//Oracle����ؒf����
			cn.close();
			System.out.println("�ؒf����");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
		e.printStackTrace();
		}
		return responses;
	}

}
