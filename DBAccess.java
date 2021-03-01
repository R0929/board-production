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

//変数resはResponseBeanのインスタンス
//変数reponsesはArrayList<ResponseBean>のインスタンス
//変数thはThreadBeanのインスタンス
//変数threadsはArrayList<ThreadBean>のインスタンス


class DBAccess{

	private ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();

	public ArrayList<ResponseBean> getResponse(){
		return responses;
	}

	private ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();

	public ArrayList<ThreadBean> getThread(){
		return threads;
	}



	public static Connection DBConnect() throws Exception{	//Oracleに接続するメソッド	
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection cn=
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			"bowner","bo");
		System.out.println("接続完了");

		return cn;
	}


	public void ThreadGet(String ti,String c){	//スレッドをDataBaseに追加後、値を取り出してListに追加するメソッド
		try{
			DBConnect();	//Oracleに接続

			//DBConnectの戻り値を入れる変数を宣言
			Connection cn = DBConnect();

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

			//insert文
			String sql="INSERT INTO board_Thread(th_id,th_title,th_details) VALUES(ThId.NEXTVAL,'"+ti+"','"+c+"')";

			//insert文の実行
			ResultSet rs=st.executeQuery(sql);

			//select文
			sql="select th_title,th_date,th_details from board_thread order by th_date desc";

			//select文の実行
			rs=st.executeQuery(sql);

			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
			String th_title=rs.getString(1);
			String th_date=rs.getString(2);
			String th_details=rs.getString(3);

			//tomcatに表示
			System.out.println(th_title+"\t"+th_details);

			//ThreadBeanをインスタンス化し、データをセットする
			ThreadBean th=new ThreadBean();

			//setterを呼び出して値をセット
			th.setAll(th_title,th_date,th_details);

			//セットした値をArrayListに追加
			threads.add(th);
			}

			//Oracleから切断する
			cn.close();
			System.out.println("切断完了");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
		e.printStackTrace();
		}
	}


	public void ResGet(String t){	//レスをDataBaseに追加後、値を取り出してListに追加するメソッド
		try{
			DBConnect();	//Oracleに接続

			//DBConnectの戻り値を入れる変数を宣言
			Connection cn = DBConnect();

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

			//insert文
			String sql="INSERT INTO board_res(th_id,res_id,res_text) VALUES(777777,ResId.NEXTVAL,'"+t+"')";

			//insert文の実行
			ResultSet rs=st.executeQuery(sql);

			//select文
			sql="select row_number() over(partition by th_id order by res_date asc)res_count,res_date,res_text from board_res";

			//select文の実行
			rs=st.executeQuery(sql);

			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
			int res_count=rs.getInt(1);
			String res_date=rs.getString(2);
			String res_text=rs.getString(3);
			
			//tomcatに表示
			System.out.println(res_count+"\t"+res_date+"\t"+res_text);

			//ResponseBeanをインスタンス化し、データをセットする
			ResponseBean res=new ResponseBean();

			//setterを呼び出して値をセット
			res.setAll(res_count,res_date,res_text);

			//セットした値をArrayListに追加
			responses.add(res);
			}

			//Oracleから切断する
			cn.close();
			System.out.println("切断完了");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
		e.printStackTrace();
		}
	}

	public void UserInput(String n,String p){	//ユーザーをDataBaseに登録する
		try{
			DBConnect();	//Oracleに接続

			//DBConnectの戻り値を入れる変数を宣言
			Connection cn = DBConnect();

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

			//insert文
			String sql="INSERT INTO board_user(user_name,user_pass,user_id) VALUES('"+n+"','"+p+"',UserId.nextval)";

			//insert文の実行
			ResultSet rs=st.executeQuery(sql);

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
		e.printStackTrace();
		}
	}

	public void UserGet(String n,String p){  //ユーザーの情報がDdtaBaseにあるか探す
		try{
			DBConnect();	//Oracleに接続

			//DBConnectの戻り値を入れる変数を宣言
			Connection cn = DBConnect();

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

		//select文
		sql="select th_title,th_date,th_details from board_thread order by th_date desc";

		//select文の実行
		rs=st.executeQuery(sql);

		//カーソルを一行だけスクロールし、データをフェッチする
		while(rs.next()){
		String th_title=rs.getString(1);
		String th_date=rs.getString(2);
		String th_details=rs.getString(3);

		//tomcatに表示
		System.out.println(th_title+"\t"+th_details);

		//ThreadBeanをインスタンス化し、データをセットする
		ThreadBean th=new ThreadBean();

		//setterを呼び出して値をセット
		th.setAll(th_title,th_date,th_details);

		//セットした値をArrayListに追加
		threads.add(th);
		}

		//Oracleから切断する
		cn.close();
		System.out.println("切断完了");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
		e.printStackTrace();
		}
	}
}