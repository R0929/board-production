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
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");

		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		// int i = Integer.parseInt(req.getParameter("id"));
		String t=req.getParameter("text");
		String n=req.getParameter("name");
		System.out.println(th_id); 

		DBAccess orcl = new DBAccess();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//responses�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("responses",orcl.ResGet(th_id,t,n));
		
		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		// RequestDispatcher dispatcher=
		// 	req.getRequestDispatcher("addResponse");


		// //�]����ɗv����]������
		// dispatcher.forward(req,res);
		res.sendRedirect("addResponse?th_id="+th_id);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		DBAccess orcl = new DBAccess();

		th_id = Integer.parseInt(req.getParameter("th_id"));

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//responses�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("responses",orcl.ThidSelect(th_id));
		req.setAttribute("threads",orcl.TitleSelect(th_id));
		
		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("responseslist");

		//�]����ɗv����]������
		dispatcher.forward(req,res);

	}
}