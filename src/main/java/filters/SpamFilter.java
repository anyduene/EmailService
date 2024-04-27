package filters;

import main.Main;

public abstract class SpamFilter {

    private static boolean checkDomain(String sender_email) {
        for(String domain : Main.white_list_domains) {
            if(sender_email.contains(domain)) {
                return false;
            }
        }

        for(String domain : Main.black_list_domains) {
            if(sender_email.contains(domain)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkContent(String text) {
        for(String word : Main.black_list_content) {
            if(text.contains(word)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkDuplicate(String text) {
        for(int i = 0; i < Main.received_emails.size(); i++) {
            if(Main.received_emails.get(i).getText().equals(text)) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkForSpam(String sender_email, String text, String subject, String sender_name) {
        return checkDomain(sender_email) || checkContent(text) || checkContent(subject) || checkContent(sender_name) || checkDuplicate(text);
    }
}
