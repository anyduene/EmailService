package main.entities;

import main.emails.ReceivedEmail;
import main.emails.SentEmail;

import java.util.List;

public interface IEmailsRepository {
    public List<ReceivedEmail> getReceivedEmails();
    public void addReceivedEmail(ReceivedEmail email);

    public List<SentEmail> getSentEmails();
    public void addSentEmail(SentEmail email);

    public List<ReceivedEmail> getSpamEmails();
    public void addSpamEmail(ReceivedEmail email);

    public List<ReceivedEmail> getStarredEmails();
    public void addStarredEmail(ReceivedEmail email);

    public List<ReceivedEmail> getLikedEmails();
    public void addLikedEmail(ReceivedEmail email);
}
