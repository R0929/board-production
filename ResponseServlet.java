import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tera.*;

public class ResponseServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");
		
		//POST要求によって送信されたパラメータを取得する
		// int i = Integer.parseInt(req.getParameter("id"));
		String t=req.getParameter("text");

		//ArrayListを使用
		ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();
		DBAccess orcl = new DBAccess();

		//リストに追加する
		orcl.ResGet(t);

		responses = orcl.getResponse();

		//HttpServletRequestの実装クラスのインスタンスに
		//responsesという名前でデータを登録する
		req.setAttribute("responses",responses);
		
		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("responseslist");
		
		//転送先に要求を転送する
		dispatcher.forward(req,res);
	}
}
