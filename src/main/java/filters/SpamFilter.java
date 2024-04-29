package filters;

import main.Main;
import main.emails.ReceivedEmail;
import main.entities.IReceivedEmailsRepository;
import main.entities.ISpamFilterRepository;

public abstract class SpamFilter {
    private static ISpamFilterRepository spamFilterRepository;
    private static IReceivedEmailsRepository receivedEmailsRepository;

    public SpamFilter(ISpamFilterRepository spamFilterRepository, IReceivedEmailsRepository receivedEmailsRepository) {
        SpamFilter.spamFilterRepository = spamFilterRepository;
        SpamFilter.receivedEmailsRepository = receivedEmailsRepository;
    }

    private static boolean checkDomain(String sender_email) {
        for(String domain : spamFilterRepository.getWhiteListDomains()) {
            if(sender_email.contains(domain)) {
                return false;
            }
        }

        for(String domain : spamFilterRepository.getBlackListDomains()) {
            if(sender_email.contains(domain)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkContent(String text) {
        for(String word : spamFilterRepository.getBlackListContent()) {
            if(text.contains(word)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkDuplicate(String text) {
        for(int i = 0; i < ReceivedEmail.received_count; i++) {
            if(receivedEmailsRepository.getReceivedEmails().get(i).getText().equals(text)) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkForSpam(String sender_email, String text, String subject, String sender_name) {
        return checkDomain(sender_email) || checkContent(text) || checkContent(subject) || checkContent(sender_name) || checkDuplicate(text);
    }
}
