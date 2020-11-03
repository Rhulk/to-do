package com.ecarvajal.service;


import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emails {
	private final Properties props;

	public Emails() {
		props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		props.put("from", "todo.develop.gestion@gmail.com");
		props.put("username", "todo.develop.gestion@gmail.com");
		props.put("password", "08180818");
	}

	public void enviar(String to, String subject, String content) throws MessagingException {
		

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(props.getProperty("from")));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setText(content);
		Transport.send(message);

		System.out.println("﹐ensaje enviado!");
	}

	public void main() throws MessagingException {
		Emails e = new Emails();
		e.enviar("quique1904@gmail.com", "Hola Mundo! Soy un mensaje!", "ejemplo de email enviado con Jakarta Mail");
	}
	/*
	 * Recupera los distintos jar que se usan con su ubicacion
	 */
	public  void test () {

		ClassLoader cl = ClassLoader.getSystemClassLoader();

	    URL[] urls = ((URLClassLoader)cl).getURLs();

	    for(URL url: urls){
	    	System.out.println(url.getFile());
	    }
	         
	}
}