package models;

import filters.SpamFilter;
import main.Main;
import main.emails.ReceivedEmail;
import main.emails.SentEmail;

public abstract class EmailHandler {
    public static void sendEmail(String email, String subject, String text) {
        SentEmail sentEmail = new SentEmail(email, subject, text);
        Main.sent_emails.add(sentEmail);
    }

    public static void receiveEmail(String email, String subject, String text, String name) {
        ReceivedEmail receivedEmail = new ReceivedEmail(email, subject, text, name);
        if(SpamFilter.checkForSpam(email, text, subject, name)) {
            receivedEmail.isSpam = true;
            Main.spam_emails.add(receivedEmail);
            ReceivedEmail.spam_count++;
        } else {
            Main.received_emails.add(receivedEmail);
            ReceivedEmail.received_count++;
        }
    }

    public static void markAsStarred(boolean b, ReceivedEmail email) {
        if(b) {
            email.isStarred = true;
            Main.starred_emails.add(email);
            ReceivedEmail.starred_count++;
        } else {
            email.isStarred = false;
            Main.starred_emails.remove(email);
            ReceivedEmail.starred_count--;
        }
    }

    public static void markAsLiked(boolean b, ReceivedEmail email) {
        if(b) {
            email.isLiked = true;
            Main.liked_emails.add(email);
            ReceivedEmail.liked_count++;
        } else {
            email.isLiked = false;
            Main.liked_emails.remove(email);
            ReceivedEmail.liked_count--;
        }
    }

    public static void markAsSpam(ReceivedEmail email) {
        Main.received_emails.remove(email);
        if(email.isStarred) {
            Main.starred_emails.remove(email);
            ReceivedEmail.starred_count--;
            email.isStarred = false;
        }
        if(email.isLiked) {
            Main.liked_emails.remove(email);
            ReceivedEmail.liked_count--;
            email.isLiked = false;
        }
        email.isSpam = true;
        Main.spam_emails.add(email);
        ReceivedEmail.spam_count++;
        ReceivedEmail.received_count--;
    }
}
