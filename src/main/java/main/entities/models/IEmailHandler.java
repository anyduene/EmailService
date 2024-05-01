package main.entities.models;

import main.emails.ReceivedEmail;
import main.emails.SentEmail;

public interface IEmailHandler {
    public ReceivedEmail findReceivedEmailById(int emailId);
    public SentEmail findSentEmailById(int emailId);

    public void sendEmail(String email, String subject, String text);
    public void receiveEmail(String email, String subject, String text, String name);

    public void markAsStarred(ReceivedEmail email);
    public void markAsLiked(ReceivedEmail email);
    public void markAsSpam(ReceivedEmail email);
    public void recoverFromSpam(ReceivedEmail email);
}
