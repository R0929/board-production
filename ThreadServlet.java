import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ThreadBean;

public class ThreadServlet extends HttpServlet {
    public void doPost(HttpServletRequest req,HttpServletResponse res)
        throws IOException,ServletException{
            res.setCharacterEncoding("Windows-31J");
                DBAccess dba=new DBAccess();
                ArrayList List = new ArrayList();
                
               	String title = req.getParameter("title");
				String tag = req.getParameter("tag");
				String comment = req.getParameter("comment");

                ThreadBean bean = new ThreadBean();

                bean.setTitle(title);
                bean.setTag(tag);
                bean.setComment(comment);

                dba.ThreadGet(title,tag,comment);

                req.setAttribute("title",title);
                req.setAttribute("tag",tag);
                req.setAttribute("ThreadID",ThreadID);
                req.setAttribute("comment",comment);
    
                /%!--送信先のURL--%/
                RequestDispatcher ResThread=req.getRequestDispather("Response.jsp");
                RequestDispatcher topPage=req.getRequestDispather("title.jsp");

                ResThread.forward(req,res);
                topPage.forward(req,res);
        }
}
