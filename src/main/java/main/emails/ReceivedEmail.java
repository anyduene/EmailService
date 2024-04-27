package main.emails;

public class ReceivedEmail extends Email {
    public static int received_count = 0;
    public static int spam_count = 0;
    public static int starred_count = 0;
    public static int liked_count = 0;

    public boolean isSpam;
    public boolean isStarred;
    public boolean isLiked;

    private final String sender_email;
    private final String sender_name;

    public ReceivedEmail(String sender, String subject, String text, String name) {
        super(text, subject, false);
        this.sender_email = sender;
        this.sender_name = name;
        this.isSpam = false;
        this.isLiked = false;
        this.isStarred = false;
        this.isViewed = false;
    }

    public String getEmail() {
        return sender_email;
    }

    public String getName() {
        return sender_name;
    }
}
