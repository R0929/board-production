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
	public static Connection DBConnect() throws Exception{	//Oracleに接続するメソッド	
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection cn=
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			"bowner","bo");
		System.out.println("接続完了");

		return cn;
	}


	public ArrayList<ThreadBean> ThreadGet(String ti,String tg,String c){	//スレッドをDataBaseに追加後、値を取り出してListに追加するメソッド
		ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();
		try{
			//DBConnectの戻り値を入れる変数を宣言
			Connection cn = DBConnect();

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

			//insert文
			String sql="INSERT INTO board_Thread(th_id,th_title,th_category,th_details) VALUES(ThId.NEXTVAL,'"+ti+"','"+tg+"','"+c+"')";

			//insert文の実行
			ResultSet rs=st.executeQuery(sql);

			//select文
			sql="select * from board_thread order by th_date desc";

			//select文の実行
			rs=st.executeQuery(sql);

			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
			int th_id=rs.getInt(1);
			String th_title=rs.getString(2);
			String th_category=rs.getString(3);
			String th_date=rs.getString(4);
			String th_details=rs.getString(5);

			//tomcatに表示
			System.out.println(th_id+"\t"+th_title+"\t"+th_details);

			//ThreadBeanをインスタンス化し、データをセットする
			ThreadBean th=new ThreadBean();

			//setterを呼び出して値をセット
			th.setAll(th_id,th_title,th_category,th_date,th_details);

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
		return threads;
	}

	public ArrayList<ThreadBean> ThSelect(){ //selectのみのメソッド
		ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();
		try{
			//DBConnectの戻り値を入れる変数を宣言
			Connection cn = DBConnect();
			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

			//select文
			String sql="select * from board_thread order by th_date desc";
			//select文の実行
			ResultSet rs=st.executeQuery(sql);

			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
				int th_id=rs.getInt(1);
				String th_title=rs.getString(2);
				String th_category=rs.getString(3);
				String th_date=rs.getString(4);
				String th_details=rs.getString(5);
	
				//tomcatに表示
				System.out.println(th_id+"\t"+th_title+"\t"+th_details);
	
				//ThreadBeanをインスタンス化し、データをセットする
				ThreadBean th=new ThreadBean();
	
				//setterを呼び出して値をセット
				th.setAll(th_id,th_title,th_category,th_date,th_details);
	
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
			System.out.println("切断完了");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL関連の例外みたい。");
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
		System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
		e.printStackTrace();
		}
			return threads;
	}

	public int getResCount(int th_id){ //レスのカウントを数えるメソッド
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
			System.out.println("切断完了");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
		e.printStackTrace();
		}
		
		return res_count+1;
	}


	public ArrayList ResGet(int th_id,String t,String n){	//レスをDataBaseに追加後、値を取り出してListに追加するメソッド
		ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();
		try{
			//DBConnectの戻り値を入れる変数を宣言
			Connection cn = DBConnect();

			//Statementインターフェイスを実装するクラスをインスタンス化する
			Statement st=cn.createStatement();

			int res_count=getResCount(th_id);

			//insert文
			String sql="INSERT INTO board_res(th_id,res_id,res_count,user_name,res_text) VALUES("+th_id+",ResId.NEXTVAL,'"+res_count+"','"+n+"','"+t+"')";

			//insert文の実行
			ResultSet rs=st.executeQuery(sql);

			//select文
			sql="select * from board_res order by res_date desc";

			//select文の実行
			rs=st.executeQuery(sql);

			//カーソルを一行だけスクロールし、データをフェッチする
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

			//Oracleから切断する
			cn.close();
			System.out.println("切断完了");

		}catch(SQLException e){
		e.printStackTrace();
		System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
		e.printStackTrace();
		}
		return responses;
	}

}
