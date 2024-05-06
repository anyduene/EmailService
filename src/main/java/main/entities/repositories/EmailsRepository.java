package main.entities.repositories;

import main.emails.ReceivedEmail;
import main.emails.SentEmail;

import java.util.ArrayList;
import java.util.List;

public class EmailsRepository implements IReceivedEmailsRepository, ISentEmailsRepository {
    private static final List<SentEmail> sent_emails = new ArrayList<>();
    private static final List<ReceivedEmail> received_emails = new ArrayList<>();
    private static final List<ReceivedEmail> starred_emails = new ArrayList<>();
    private static final List<ReceivedEmail> liked_emails = new ArrayList<>();
    private static final List<ReceivedEmail> spam_emails = new ArrayList<>();

    @Override
    public List<ReceivedEmail> getReceivedEmails() {
        return received_emails;
    }

    @Override
    public void addReceivedEmail(ReceivedEmail email) {
        received_emails.add(email);
    }

    @Override
    public List<SentEmail> getSentEmails() {
        return sent_emails;
    }

    @Override
    public void addSentEmail(SentEmail email) {
        sent_emails.add(email);
    }

    @Override
    public List<ReceivedEmail> getSpamEmails() {
        return spam_emails;
    }

    @Override
    public void addSpamEmail(ReceivedEmail email) {
        spam_emails.add(email);
    }

    @Override
    public List<ReceivedEmail> getStarredEmails() {
        return starred_emails;
    }

    @Override
    public void addStarredEmail(ReceivedEmail email) {
        starred_emails.add(email);
    }

    @Override
    public List<ReceivedEmail> getLikedEmails() {
        return liked_emails;
    }

    @Override
    public void addLikedEmail(ReceivedEmail email) {
        liked_emails.add(email);
    }

    @Override
    public void remove(SentEmail email) {
        sent_emails.remove(email);
    }

    @Override
    public void remove(String list, ReceivedEmail email) {
        switch (list) {
            case "received":
                received_emails.remove(email);
                break;
            case "starred":
                starred_emails.remove(email);
                break;
            case "liked":
                liked_emails.remove(email);
                break;
            case "spam":
                spam_emails.remove(email);
                break;
        }
    }

    public static void generateReceivedEmails() {
        List<String> names = List.of("Alice Johnson", "Bob Smith", "Charlie Brown", "Diana Miller", "Eva Wilson", "Frank Taylor", "Grace Anderson", "Henry Wong", "Ivy Nguyen", "Jack Doe");
        List<String> emails = List.of("john.doe@gmail.com", "emily.smith@gmail.com", "alexander.wong@gmail.com", "sarah.johnson@gmail.com", "michael.nguyen@gmail.com", "jessica.miller@gmail.com", "david.brown@gmail.com", "lisa.wilson@gmail.com", "matthew.taylor@gmail.com", "samantha.anderson@gmail.com");
        List<String> subjects = List.of("Exciting Updates Inside!", "Save the Date!", "Important Account Update", "Exclusive Offer Alert!", "Discover Our New Product", "Join Our Webinar!", "Limited Time Offer!", "Unlock Your Potential", "Stay Updated With Us", "Your Feedback Needed");
        List<String> texts = List.of("Hey there, We have some thrilling updates to share with you! Click below to discover the latest news and developments. For more information, you can also reach us at +1234567890.",
                "Hi, Mark your calendars! We have an upcoming event that you won't want to miss. Save the date and stay tuned for more details! Don't forget to register with your bank card ending in 5678901256789012.",
                "Hello, There's an important update regarding your account. Please read the attached message for more information. For assistance, please contact us at +1234567890.",
                "Hi there, Introducing our latest product! Click the link to learn more about how it can benefit you. For any questions, feel free to contact us at +1234567890. Pay conveniently with your bank card ending in 7890123478901234.",
                "Hi there, Introducing our latest product! Click the link to learn more about how it can benefit you. For any questions, feel free to contact us at +1234567890.",
                "Hey, We're hosting an exclusive webinar and you're invited! Reserve your spot now to gain valuable insights and knowledge. Secure your participation with your bank card ending in 2345678923456789.",
                "Hi, Act fast - our special offer is only available for a limited time! Click below to take advantage before it's too late. For inquiries, call us at +1234567890.",
                "Hello, We value your opinion! Please take a moment to share your feedback with us. Your input is greatly appreciated. Verify your identity by providing the full bank card number: 7890123578901235.",
                "Hi, Stay in the loop with the latest news and updates from us. Click below to see what's new. For any inquiries, feel free to contact us at +1234567890.",
                "Hello, We value your opinion! Please take a moment to share your feedback with us. Your input is greatly appreciated.");

        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 10);
            ReceivedEmail email = new ReceivedEmail(emails.get(random), subjects.get(random), texts.get(random), names.get(random));
            if(Math.random() <= 0.1) {
                email.isSpam = true;
                spam_emails.add(email);
            } else {
                received_emails.add(email);
            }
        }
    }

    public static void generateSentEmails() {
        List<String> emails = List.of("alice.johnson@gmail.com", "bob.smith@gmail.com", "charlie.brown@gmail.com", "diana.miller@gmail.com", "eva.wilson@gmail.com", "frank.taylor@gmail.com", "grace.anderson@gmail.com", "henry.wong@gmail.com", "ivy.nguyen@gmail.com", "jack.doe@gmail.com");
        List<String> subjects = List.of("Special Event Invitation", "Exclusive Sale Announcement", "Important Account Update", "New Product Launch", "Webinar Invitation", "Limited Time Offer", "Unlock Your Potential", "Latest News and Updates", "Customer Feedback Request", "Holiday Greetings");
        List<String> texts = List.of("Hi there, You are cordially invited to our special event! Join us for an unforgettable evening filled with excitement and surprises. Save the date and stay tuned for more details. We can't wait to celebrate with you!",
                "Hello, We have an exclusive announcement just for you! Get ready to shop our biggest sale of the year with incredible discounts on your favorite products. Don't miss out - mark your calendars and stay tuned for more details.",
                "Dear valued customer, We need to inform you about an important update regarding your account. Please review the attached message for detailed information. Should you have any questions or concerns, don't hesitate to reach out to us.",
                "Hey, Exciting news! We're thrilled to announce the launch of our latest product. Click the link below to discover how it can revolutionize your life. Be among the first to experience innovation at its finest.",
                "Hi, You're invited to our exclusive webinar where industry experts will share valuable insights and strategies. Reserve your spot now to gain actionable knowledge that will propel you towards success. Don't miss this opportunity!",
                "Hi there, Act fast! Our special offer is available for a limited time only. Click below to seize the opportunity and enjoy fantastic savings on select items. Hurry, before it's too late!",
                "Hello, Ready to unlock your full potential? Explore our resources designed to help you reach new heights and achieve your goals. Click the link below to discover how you can unleash your inner greatness.",
                "Hi, Stay informed with our latest news and updates. Click below to catch up on what's happening in our world. We're excited to share our journey with you!",
                "Dear valued customer, Your opinion matters to us! Please take a moment to share your feedback and help us serve you better. We appreciate your input and strive to exceed your expectations.",
                "Warmest greetings, Wishing you and your loved ones a joyous holiday season filled with laughter, love, and happiness. May this festive season bring you peace and prosperity. Happy holidays!");

        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * 10);
            SentEmail email = new SentEmail(emails.get(random), subjects.get(random), texts.get(random));
            sent_emails.add(email);
        }
    }
}
