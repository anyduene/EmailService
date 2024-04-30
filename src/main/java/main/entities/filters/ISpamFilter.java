package main.entities.filters;

public interface ISpamFilter {
    public boolean checkDomain(String sender_email);
    public boolean checkContent(String text);
    public boolean checkDuplicate(String text);
    public boolean checkForSpam(String sender_email, String text, String subject, String sender_name);
}
