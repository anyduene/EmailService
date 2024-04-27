package filters;

public abstract class ConfidentialDataManager {
    public static String checkForNumber(String text) {
        String phoneNumberPattern = "\\b(\\d{3})\\d{5}(\\d{2})\\b";
        text =  text.replaceAll(phoneNumberPattern, "$1#####$2");

        phoneNumberPattern = "\\b(\\d{5})\\d{5}(\\d{2})\\b";
        text = text.replaceAll(phoneNumberPattern, "$1#####$2");

        String phoneNumberPattern3 = "\\b(\\d{3})\\((\\d{2})\\)\\d{5}(\\d{2})\\b";
        return text.replaceAll(phoneNumberPattern3, "$1($2)#####$3");
    }

    public static String checkForEmail(String text) {
        String emailPattern = "([a-zA-Z0-9._]+)@([a-zA-Z]+\\.[a-zA-Z]{2,6})";
        return text.replaceAll(emailPattern, "####@$2");
    }

    public static String checkForCardNumber(String text) {
        String cardNumberPattern = "\\b(\\d{4})\\d{8}(\\d{4})\\b";
        return text.replaceAll(cardNumberPattern, "$1####$2");
    }

    public static String complexCheck(String text) {
        return checkForCardNumber(checkForEmail(checkForNumber(text)));
    }
}
