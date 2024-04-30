package main.entities.repositories;

import main.emails.ReceivedEmail;

import java.util.List;

public interface IReceivedEmailsRepository {
    public List<ReceivedEmail> getReceivedEmails();
    public void addReceivedEmail(ReceivedEmail email);

    public List<ReceivedEmail> getSpamEmails();
    public void addSpamEmail(ReceivedEmail email);

    public List<ReceivedEmail> getStarredEmails();
    public void addStarredEmail(ReceivedEmail email);

    public List<ReceivedEmail> getLikedEmails();
    public void addLikedEmail(ReceivedEmail email);

    public void remove(String list, ReceivedEmail email);
}
