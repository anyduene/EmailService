package main.entities.filters;

import main.emails.ReceivedEmail;
import main.entities.repositories.IReceivedEmailsRepository;
import main.entities.repositories.ISpamFilterRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpamFilter implements ISpamFilter {
    private static ISpamFilterRepository spamFilterRepository;
    private static IReceivedEmailsRepository receivedEmailsRepository;

    public SpamFilter(ISpamFilterRepository spamFilterRepository, IReceivedEmailsRepository receivedEmailsRepository) {
        SpamFilter.spamFilterRepository = spamFilterRepository;
        SpamFilter.receivedEmailsRepository = receivedEmailsRepository;
    }

    @Override
    public boolean checkDomain(String sender_email) {
        List<String> whiteListDomains = spamFilterRepository.getWhiteListDomains();
        List<String> blackListDomains = spamFilterRepository.getBlackListDomains();

        for(String domain : whiteListDomains) {
            if(sender_email.contains(domain)) {
                return false;
            }
        }

        for(String domain : blackListDomains) {
            if(sender_email.contains(domain)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkContent(String text) {
        for(String word : spamFilterRepository.getBlackListContent()) {
            if(text.contains(word)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkDuplicate(String text) {
        for(ReceivedEmail email : receivedEmailsRepository.getSpamEmails()) {
            if(email.getText().equals(text)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkForSpam(String sender_email, String text, String subject, String sender_name) {
        return checkDomain(sender_email) || checkContent(text) || checkContent(subject) || checkContent(sender_name) || checkDuplicate(text);
    }
}
