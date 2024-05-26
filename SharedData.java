package sms.first;

public class SharedData {
    private static String buttonText;

    public static String getButtonText() {
        return buttonText;
    }

    public static void setButtonText(String text) {
        buttonText = text;
    }
}
