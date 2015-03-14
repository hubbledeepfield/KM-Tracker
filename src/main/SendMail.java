package main;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

public class SendMail {
	
	public SendMail() throws IOException{
		final String username = Config.username;
		final String password = Config.password;
		
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
			
		});
		
		try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Config.username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(Config.recepient));
            message.setSubject("FC Einheit Rudolstadt report");
            message.setText("Recent match: " + Config.newLine + LastMatchResult.getMatch() + Config.newLine + Config.newLine + "Current position: " + Config.newLine + CurrentStandings.getCurrentPosition());

            Transport.send(message);

            System.out.println("Report sent succesfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}

}
