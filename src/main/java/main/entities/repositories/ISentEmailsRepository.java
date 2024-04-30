package main.entities.repositories;

import main.emails.SentEmail;

import java.util.List;

public interface ISentEmailsRepository {
    public List<SentEmail> getSentEmails();
    public void addSentEmail(SentEmail email);

    public void remove(SentEmail email);
}
