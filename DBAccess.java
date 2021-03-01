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

	private ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();

	public ArrayList<ResponseBean> getResponse(){
		return responses;
	}

	private ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();

	public ArrayList<ThreadBean> getThread(){
		return threads;
	}



	public static Connection DBConnect() throws Exception{	//Oracle�ɐڑ����郁�\�b�h	
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection cn=
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			"bowner","bo");
		System.out.println("�ڑ�����");

		return cn;
	}


	public void ThreadGet(String ti,String c){	//�X���b�h��DataBase�ɒǉ���A�l�����o����List�ɒǉ����郁�\�b�h
		try{
			DBConnect();	//Oracle�ɐڑ�

			//DBConnect�̖߂�l������ϐ���錾
			Connection cn = DBConnect();

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//insert��
			String sql="INSERT INTO board_Thread(th_id,th_title,th_details) VALUES(ThId.NEXTVAL,'"+ti+"','"+c+"')";

			//insert���̎��s
			ResultSet rs=st.executeQuery(sql);

			//select��
			sql="select th_title,th_date,th_details from board_thread order by th_date desc";

			//select���̎��s
			rs=st.executeQuery(sql);

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
			String th_title=rs.getString(1);
			String th_date=rs.getString(2);
			String th_details=rs.getString(3);

			//tomcat�ɕ\��
			System.out.println(th_title+"\t"+th_details);

			//ThreadBean���C���X�^���X�����A�f�[�^���Z�b�g����
			ThreadBean th=new ThreadBean();

			//setter���Ăяo���Ēl���Z�b�g
			th.setAll(th_title,th_date,th_details);

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
	}


	public void ResGet(String t){	//���X��DataBase�ɒǉ���A�l�����o����List�ɒǉ����郁�\�b�h
		try{
			DBConnect();	//Oracle�ɐڑ�

			//DBConnect�̖߂�l������ϐ���錾
			Connection cn = DBConnect();

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//insert��
			String sql="INSERT INTO board_res(th_id,res_id,res_text) VALUES(777777,ResId.NEXTVAL,'"+t+"')";

			//insert���̎��s
			ResultSet rs=st.executeQuery(sql);

			//select��
			sql="select row_number() over(partition by th_id order by res_date asc)res_count,res_date,res_text from board_res";

			//select���̎��s
			rs=st.executeQuery(sql);

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
			int res_count=rs.getInt(1);
			String res_date=rs.getString(2);
			String res_text=rs.getString(3);
			
			//tomcat�ɕ\��
			System.out.println(res_count+"\t"+res_date+"\t"+res_text);

			//ResponseBean���C���X�^���X�����A�f�[�^���Z�b�g����
			ResponseBean res=new ResponseBean();

			//setter���Ăяo���Ēl���Z�b�g
			res.setAll(res_count,res_date,res_text);

			//�Z�b�g�����l��ArrayList�ɒǉ�
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
	}

	public void UserInput(String n,String p){	//���[�U�[��DataBase�ɓo�^����
		try{
			DBConnect();	//Oracle�ɐڑ�

			//DBConnect�̖߂�l������ϐ���錾
			Connection cn = DBConnect();

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

			//insert��
			String sql="INSERT INTO board_user(user_name,user_pass,user_id) VALUES('"+n+"','"+p+"',UserId.nextval)";

			//insert���̎��s
			ResultSet rs=st.executeQuery(sql);

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
		e.printStackTrace();
		}
	}

	public void UserGet(String n,String p){  //���[�U�[�̏��DdtaBase�ɂ��邩�T��
		try{
			DBConnect();	//Oracle�ɐڑ�

			//DBConnect�̖߂�l������ϐ���錾
			Connection cn = DBConnect();

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			Statement st=cn.createStatement();

		//select��
		sql="select th_title,th_date,th_details from board_thread order by th_date desc";

		//select���̎��s
		rs=st.executeQuery(sql);

		//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
		while(rs.next()){
		String th_title=rs.getString(1);
		String th_date=rs.getString(2);
		String th_details=rs.getString(3);

		//tomcat�ɕ\��
		System.out.println(th_title+"\t"+th_details);

		//ThreadBean���C���X�^���X�����A�f�[�^���Z�b�g����
		ThreadBean th=new ThreadBean();

		//setter���Ăяo���Ēl���Z�b�g
		th.setAll(th_title,th_date,th_details);

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
	}
}