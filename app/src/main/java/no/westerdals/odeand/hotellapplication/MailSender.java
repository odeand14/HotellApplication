package no.westerdals.odeand.hotellapplication;

// Created by Andreas Ødegaard on 24.05.2017.


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Session session;
    private String emailSubject, emailContent, emailGuest;

    public MailSender(Context context, String emailGuest, String emailSubject, String emailContent) {
        this.context = context;
        this.emailGuest = emailGuest;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
    }

    @Override
    protected Void doInBackground(Void... params) {

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailConfig.EMAIL_ACCOUNT, MailConfig.EMAIL_PASSWORD);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailConfig.EMAIL_ACCOUNT));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailGuest));
            message.setSubject(emailSubject);
            message.setText(emailContent);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Toast.makeText(context, "Confirmation email sent!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
