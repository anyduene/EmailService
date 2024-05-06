package main.entities.models;

import main.entities.filters.ISpamFilter;
import main.emails.ReceivedEmail;
import main.emails.SentEmail;
import main.entities.repositories.IReceivedEmailsRepository;
import main.entities.repositories.ISentEmailsRepository;

import java.util.List;

public class EmailHandler implements IEmailHandler {
    private final IReceivedEmailsRepository receivedEmailsRepository;
    private final ISentEmailsRepository sentEmailsRepository;
    private final ISpamFilter spamFilter;

    public EmailHandler(IReceivedEmailsRepository receivedEmailsRepository, ISentEmailsRepository sentEmailsRepository, ISpamFilter spamFilter) {
        this.receivedEmailsRepository = receivedEmailsRepository;
        this.sentEmailsRepository = sentEmailsRepository;
        this.spamFilter = spamFilter;
    }

    @Override
    public ReceivedEmail findReceivedEmailById(int emailId) {
        List<ReceivedEmail> receivedEmails = receivedEmailsRepository.getReceivedEmails();
        List<ReceivedEmail> spamEmails = receivedEmailsRepository.getSpamEmails();
        for(ReceivedEmail email : receivedEmails) {
            if(email.getId() == emailId) {
                return email;
            }
        }
        for(ReceivedEmail email : spamEmails) {
            if(email.getId() == emailId) {
                return email;
            }
        }
        return null;
    }

    @Override
    public SentEmail findSentEmailById(int emailId) {
        List<SentEmail> sentEmails = sentEmailsRepository.getSentEmails();
        for(SentEmail email : sentEmails) {
            if(email.getId() == emailId) {
                return email;
            }
        }
        return null;
    }

    @Override
    public void sendEmail(String email, String subject, String text) {
        SentEmail sentEmail = new SentEmail(email, subject, text);
        sentEmailsRepository.addSentEmail(sentEmail);
    }

    @Override
    public void receiveEmail(String email, String subject, String text, String name) {
        ReceivedEmail receivedEmail = new ReceivedEmail(email, subject, text, name);
        if(spamFilter.checkForSpam(email, text, subject, name)) {
            receivedEmail.isSpam = true;
            receivedEmailsRepository.addSpamEmail(receivedEmail);
            ReceivedEmail.spam_count++;
        } else {
            receivedEmailsRepository.addReceivedEmail(receivedEmail);
            ReceivedEmail.received_count++;
        }
    }

    @Override
    public void markAsStarred(ReceivedEmail email) {
        if(!email.isStarred) {
            email.isStarred = true;
            receivedEmailsRepository.addStarredEmail(email);
            ReceivedEmail.starred_count++;
        } else {
            email.isStarred = false;
            receivedEmailsRepository.remove("starred", email);
            ReceivedEmail.starred_count--;
        }
    }

    @Override
    public void markAsLiked(ReceivedEmail email) {
        if(!email.isLiked) {
            email.isLiked = true;
            receivedEmailsRepository.addLikedEmail(email);
            ReceivedEmail.liked_count++;
        } else {
            email.isLiked = false;
            receivedEmailsRepository.remove("liked", email);
            ReceivedEmail.liked_count--;
        }
    }

    @Override
    public void markAsSpam(ReceivedEmail email) {
        receivedEmailsRepository.remove("received", email);
        if(email.isStarred) {
            receivedEmailsRepository.remove("starred", email);
            ReceivedEmail.starred_count--;
            email.isStarred = false;
        }
        if(email.isLiked) {
            receivedEmailsRepository.remove("liked", email);
            ReceivedEmail.liked_count--;
            email.isLiked = false;
        }
        email.isSpam = true;
        receivedEmailsRepository.addSpamEmail(email);
        ReceivedEmail.spam_count++;
        ReceivedEmail.received_count--;
    }

    @Override
    public void recoverFromSpam(ReceivedEmail email) {
        receivedEmailsRepository.remove("spam", email);
        email.isSpam = false;
        receivedEmailsRepository.addReceivedEmail(email);
        ReceivedEmail.received_count++;
        ReceivedEmail.spam_count--;
    }
}