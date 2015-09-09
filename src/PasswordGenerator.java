/**
 * Generates passwords following the LB2 schema and a specific 3 word key
 *
 * @author Austin Herring
 * @version 1.0
 */
public class PasswordGenerator {
    private String key;

    PasswordGenerator(String word1, String word2, String word3) {
        key = (word1 + word2 + word3).toUpperCase();

    }

    public String generate(String challenge) {
        String password = "";
        int challengeLength = challenge.length();
        for (int i = 0; i < challengeLength; i++) {
            password += findMappedLetter(challenge.charAt(i), key);
        }
        return password;
    }

    private boolean isVowel(char letter) {
        return letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O'
                || letter == 'U';
    }

    private char findMappedLetter(char desiredLetter, String key) {
        int keyLength = key.length();
        int j = 0;
        while (j < keyLength) {
            if (key.charAt(j) == Character.toUpperCase(desiredLetter)) {
                for (int k = j + 1; k < 2 * keyLength; k++) {
                    if (!isVowel(key.charAt(k % keyLength))) {
                        return key.charAt(k % keyLength);
                    }
                }
            }
            j++;
        }
        return '\0';
    }
    public void changeKey(String newKey) {
        key = newKey.toUpperCase();

    }
}
