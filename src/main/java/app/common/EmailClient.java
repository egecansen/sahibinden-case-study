package app.common;

import context.ContextStore;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import utils.Printer;
import java.util.Properties;

public class EmailClient {

    private final Printer log = new Printer(EmailClient.class);
    private final boolean keepLogs = Boolean.parseBoolean(ContextStore.get("keep-email-logs", "true"));

    public void sendEmail(String host, String subject, String content, String contentType, String receiver, String sender, String password, Multipart attachment) {
        Properties properties = new Properties();
        properties.putAll(System.getProperties());

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        session.setDebug(keepLogs);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(subject);

            // Now set the actual message
            message.setContent(content, contentType);
            if (attachment != null)
                message.setContent(attachment);

            if (keepLogs) log.info("Sending...");
            Transport.send(message);// Send message
            if (keepLogs) log.success("Sent message successfully!");
        } catch (MessagingException mex) {
            log.error(mex.getMessage(), mex);
        }
    }

}
