package main.emails;

public class SentEmail extends Email {
    public final String receiver_email;
    public static int sent_count = 0;

    public SentEmail(String receiverEmail, String subject, String text) {
        super(text, subject, true);
        this.receiver_email = receiverEmail;
        sent_count++;
    }

    public String getEmail() {
        return receiver_email;
    }
}
