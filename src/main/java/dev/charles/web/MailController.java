package dev.charles.web;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {
	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo("layedia08@gmail.com");
			helper.setText("Démonstration");
			helper.setSubject("Email de réinitialisation");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Echec de l'envoie du mail...";
		}
		sender.send(message);
		return "Mail envoyé avec succés!"; 
	}
}
