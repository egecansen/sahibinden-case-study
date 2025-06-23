import context.ContextStore;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import utils.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void sendReportEmail() {
        new Printer(EmailClient.class).info("Sending the report email...");
        EmailClient emailClient = new EmailClient();
        String contentType = "text/html; charset=utf-8";
        String host = ContextStore.get("email-host").toString();
        String sender = ContextStore.get("sender-email").toString();
        String receiver = ContextStore.get("receiver-email").toString();
        String appPassword = ContextStore.get("email-application-password").toString();
        String subject = "AccuWeather Automation Report - Please find the attached test report.";
        try {
            String htmlContent = Files.readString(Paths.get("target/site/surefire-report.html"));
            emailClient.sendEmail(host,
                    subject,
                    htmlContent ,
                    contentType,
                    receiver,
                    sender,
                    appPassword,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Printer(EmailClient.class).info("Report email is sent!");
    }

}
