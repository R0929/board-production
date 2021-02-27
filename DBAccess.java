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

class DBAccess{

	private Connection cn;
	private Statement st;

	private ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();

	public ArrayList<ResponseBean> getResponse(){
		return responses;
	}

	public static Connection DBConnect() throws Exception{	//Oracle�ɐڑ����郁�\�b�h	
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection cn=
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			"bowner","bo");
		System.out.println("�ڑ�����");

		return cn;
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
			sql="select res_date,res_text from board_res order by res_date asc";

			//select���̎��s
			rs=st.executeQuery(sql);

			//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
			while(rs.next()){
			String res_date=rs.getString(1);
			String res_text=rs.getString(2);
			
			//tomcat�ɕ\��
			System.out.println(res_date+"\t"+res_text);

			//ResponseBean���C���X�^���X�����A�f�[�^���Z�b�g����
			ResponseBean res=new ResponseBean();

			//setter���Ăяo���Ēl���Z�b�g
			res.setAll(res_date,res_text);

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
}
