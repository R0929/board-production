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

class DBAccess{

	private Connection cn;
	private Statement st;

	private ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();

	public ArrayList<ResponseBean> getResponse(){
		return responses;
	}

	public static Connection DBConnect() throws Exception{	//Oracleに接続するメソッド	
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection cn=
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			"bowner","bo");
		System.out.println("接続完了");

		return cn;
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
			sql="select res_date,res_text from board_res order by res_date asc";

			//select文の実行
			rs=st.executeQuery(sql);

			//カーソルを一行だけスクロールし、データをフェッチする
			while(rs.next()){
			String res_date=rs.getString(1);
			String res_text=rs.getString(2);
			
			//tomcatに表示
			System.out.println(res_date+"\t"+res_text);

			//ResponseBeanをインスタンス化し、データをセットする
			ResponseBean res=new ResponseBean();

			//setterを呼び出して値をセット
			res.setAll(res_date,res_text);

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
}
