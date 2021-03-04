import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tera.*;

public class ThreadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");

		//POST要求によって送信されたパラメータを取得する
		String ti=req.getParameter("title");
		String tg=req.getParameter("tag");
        String c=req.getParameter("details");

		DBAccess orcl = new DBAccess();

		//HttpServletRequestの実装クラスのインスタンスに
		//threadsという名前でデータを登録する
		req.setAttribute("threads",orcl.ThreadGet(ti,tg,c));

		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("titleJSP");

		//転送先に要求を転送する
		dispatcher.forward(req,res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			DBAccess orcl = new DBAccess();



			//HttpServletRequestの実装クラスのインスタンスに
			//threadsという名前でデータを登録する
			req.setAttribute("threads",orcl.ThSelect());

			//RequestDispatcherインターフェイスを実装するクラスの
			//インスタンスを取得する
			//引数は転送先のURL
			RequestDispatcher dispatcher=
				req.getRequestDispatcher("titleJSP");

			//転送先に要求を転送する
			dispatcher.forward(req,res);
		}
			
	}
