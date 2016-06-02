package com.one;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8650342143651793605L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �ռ��˵ĵ����ʼ� ID
		String to = "abcd@gmail.com";

		// �����˵ĵ����ʼ� ID
		String from = "web@gmail.com";

		// �������Ǵӱ����������͵����ʼ�
		String host = "localhost";

		// ��ȡϵͳ������
		Properties properties = System.getProperties();

		// �����ʼ�������
		properties.setProperty("mail.smtp.host", host);

		// ��ȡĬ�ϵ� Session ����
		Session session = Session.getDefaultInstance(properties);

		// ������Ӧ��������
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			// ����һ��Ĭ�ϵ� MimeMessage ����
			MimeMessage message = new MimeMessage(session);
			// ���� From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// ���� To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// ���� Subject: header field
			message.setSubject("This is the Subject Line!");
			// ��������ʵ����Ϣ
			message.setText("This is actual message");
			// ������Ϣ
			Transport.send(message);
			String title = "���͵����ʼ�";
			String res = "�ɹ�������Ϣ...";
			String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
			out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
					+ "<p align=\"center\">" + res + "</p>\n" + "</body></html>");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
