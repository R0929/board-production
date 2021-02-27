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
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");
		
		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		// int i = Integer.parseInt(req.getParameter("id"));
		String t=req.getParameter("text");

		//ArrayList���g�p
		ArrayList<ResponseBean> responses = new ArrayList<ResponseBean>();
		DBAccess orcl = new DBAccess();

		//���X�g�ɒǉ�����
		orcl.ResGet(t);

		responses = orcl.getResponse();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//responses�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("responses",responses);
		
		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		RequestDispatcher dispatcher=
			req.getRequestDispatcher("responseslist");
		
		//�]����ɗv����]������
		dispatcher.forward(req,res);
	}
}
