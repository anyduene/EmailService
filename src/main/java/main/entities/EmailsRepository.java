package main.entities;

import main.emails.ReceivedEmail;
import main.emails.SentEmail;

import java.util.ArrayList;
import java.util.List;

public class EmailsRepository implements IEmailsRepository {
    public static final List<SentEmail> sent_emails = new ArrayList<>();
    public static final List<ReceivedEmail> received_emails = new ArrayList<>();
    public static final List<ReceivedEmail> starred_emails = new ArrayList<>();
    public static final List<ReceivedEmail> liked_emails = new ArrayList<>();
    public static final List<ReceivedEmail> spam_emails = new ArrayList<>();


    @Override
    public List<ReceivedEmail> getReceivedEmails() {
        return received_emails;
    }

    @Override
    public void addReceivedEmail(ReceivedEmail email) {
        received_emails.add(email);
    }

    @Override
    public List<SentEmail> getSentEmails() {
        return sent_emails;
    }

    @Override
    public void addSentEmail(SentEmail email) {
        sent_emails.add(email);
    }

    @Override
    public List<ReceivedEmail> getSpamEmails() {
        return spam_emails;
    }

    @Override
    public void addSpamEmail(ReceivedEmail email) {
        spam_emails.add(email);
    }

    @Override
    public List<ReceivedEmail> getStarredEmails() {
        return starred_emails;
    }

    @Override
    public void addStarredEmail(ReceivedEmail email) {
        starred_emails.add(email);
    }

    @Override
    public List<ReceivedEmail> getLikedEmails() {
        return liked_emails;
    }

    @Override
    public void addLikedEmail(ReceivedEmail email) {
        liked_emails.add(email);
    }
}
