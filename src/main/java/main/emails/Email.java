package main.emails;

import main.entities.models.TimeHandler;

public abstract class Email {
    private final int id;
    private final String subject;
    public String text;
    private final String date;
    private final String time;
    public boolean isViewed;

    public Email(String text, String subject, boolean isViewed) {
        this.text = text;
        this.subject = subject;
        this.date = TimeHandler.getDate();
        this.time = TimeHandler.getTime();
        this.isViewed = isViewed;
        this.id = ReceivedEmail.received_count + SentEmail.sent_count + ReceivedEmail.spam_count;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    abstract public String getEmail();
}
