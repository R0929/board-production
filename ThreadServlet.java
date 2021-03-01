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
        String c=req.getParameter("details");

		//ArrayListを使用
        ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();
		DBAccess orcl = new DBAccess();

		//リストに追加する
		orcl.ThreadGet(ti,c);

		threads = orcl.getThread();

		//HttpServletRequestの実装クラスのインスタンスに
		//threadsという名前でデータを登録する
		req.setAttribute("threads",threads);

		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("titleJSP");

		//転送先に要求を転送する
		dispatcher.forward(req,res);
	}
}
