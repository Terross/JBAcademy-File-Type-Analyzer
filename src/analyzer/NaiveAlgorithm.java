package analyzer;

public class NaiveAlgorithm implements Algorithm{

    private boolean findSubString(String text, String pattern) {
        boolean result = false;

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            result = true;
            for (int j = 0; j < pattern.length() && result; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    result = false;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean execute(String text, String pattern) {
        return findSubString(text, pattern);
    }
}
