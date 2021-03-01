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
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");

		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
        String ti=req.getParameter("title");
        String c=req.getParameter("details");

		//ArrayList���g�p
        ArrayList<ThreadBean> threads = new ArrayList<ThreadBean>();
		DBAccess orcl = new DBAccess();

		//���X�g�ɒǉ�����
		orcl.ThreadGet(ti,c);

		threads = orcl.getThread();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//threads�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("threads",threads);

		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("titleJSP");

		//�]����ɗv����]������
		dispatcher.forward(req,res);
	}
}
