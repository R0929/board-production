import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tera.*;

public class ResponseServlet extends HttpServlet {

	static int th_id;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");

		//POST要求によって送信されたパラメータを取得する
		// int i = Integer.parseInt(req.getParameter("id"));
		String t=req.getParameter("text");
		String n=req.getParameter("name");
		System.out.println(th_id); 

		DBAccess orcl = new DBAccess();

		//HttpServletRequestの実装クラスのインスタンスに
		//responsesという名前でデータを登録する
		req.setAttribute("responses",orcl.ResGet(th_id,t,n));
		
		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		// RequestDispatcher dispatcher=
		// 	req.getRequestDispatcher("addResponse");


		// //転送先に要求を転送する
		// dispatcher.forward(req,res);
		res.sendRedirect("addResponse?th_id="+th_id);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		DBAccess orcl = new DBAccess();

		th_id = Integer.parseInt(req.getParameter("th_id"));

		//HttpServletRequestの実装クラスのインスタンスに
		//responsesという名前でデータを登録する
		req.setAttribute("responses",orcl.ThidSelect(th_id));
		req.setAttribute("threads",orcl.TitleSelect(th_id));
		
		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("responseslist");

		//転送先に要求を転送する
		dispatcher.forward(req,res);

	}
}