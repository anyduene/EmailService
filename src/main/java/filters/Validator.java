package filters;

public abstract class Validator {

    public static boolean validateEmail(String email) {
        return !email.matches("^[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean fieldCheck(String text1, String text2, String text3) {
        return text1.isEmpty() || text2.isEmpty() || text3.isEmpty();
    }
}
