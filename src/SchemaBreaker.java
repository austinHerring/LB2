import java.util.HashMap;
import java.util.LinkedList;

/**
 * Attempts to learn what a specific key is given
 * a list of websites and their following password using LB2. It will guess the
 * password, then be informed of the correct one if it was wrong
 *
 * @author Austin Herring
 * @version 1.0
 */
public class SchemaBreaker {

    private HashMap<String,LetterConfirmation> guessedMapping;
    private HashMap<String,String> history;
    private LinkedList<String> guessedKey;
    private String currentChallenge;
    private String currentPassword;

    SchemaBreaker() {
        guessedKey = new LinkedList<String>();
        guessedMapping = new HashMap<String, LetterConfirmation>();
        history = new HashMap<String, String>();
        for (int i = 65; i < 91; i++) {
            guessedMapping.put(String.valueOf((char)i),
                    new LetterConfirmation(""));
        }

    }

    public void takeChallenge(String currentChallenge) {
        this.currentChallenge = currentChallenge.toUpperCase();
    }

    public void takeFeedback(String password) {
        currentPassword = password;
        history.put(currentChallenge, password.toUpperCase());
        updateMapping();
        updateKey();
    }

    public String guess(String website) {
        String guess = "";
        if (history.keySet().contains(website.toUpperCase())) {
            return history.get(website.toUpperCase());
        } else {
            for (int i = 0; i < website.length(); i++) {
                guess += guessedMapping.get(website.substring(i, i + 1).toUpperCase()).getValue();
            }
            return guess;
        }
    }

    private void updateMapping() {
        LetterConfirmation ins;
        LetterConfirmation passwordLetterPtr;
        String challengeLetterPtr;
        String unConfirmedLetters = "";
        String confirmedLetters = "";
        boolean equalLengths = currentChallenge.length() == currentPassword.length();
        for (int i = 0; i < currentChallenge.length(); i++) {
            challengeLetterPtr = currentChallenge.substring(i, i + 1).toUpperCase();
            passwordLetterPtr = guessedMapping.get(challengeLetterPtr);
            if (!passwordLetterPtr.isConfirmed() && equalLengths) {
                ins = guessedMapping.get(challengeLetterPtr);
                ins.setValue(currentPassword.substring(i, i + 1).toUpperCase());
                ins.setConfirmed();
                //, new LetterConfirmation(currentPassword.substring(i, i + 1).toUpperCase(), true));
                confirmedLetters += challengeLetterPtr;
            } else if (!passwordLetterPtr.isConfirmed()) {
                unConfirmedLetters += challengeLetterPtr;
            } else {
                confirmedLetters += challengeLetterPtr;
            }
        }

        if (unConfirmedLetters.length() == 1) {
            ins = guessedMapping.get(unConfirmedLetters);
            ins.setConfirmed();
            ins.setValue("");
        } else if (unConfirmedLetters.length() > 1) {
            String newLetterMappings = currentPassword;
            String letterToRemove;
            for (int i = 0; i < confirmedLetters.length(); i++) {
                letterToRemove = guessedMapping.get(confirmedLetters.substring(i, i + 1)).getValue();
                newLetterMappings = newLetterMappings.replaceFirst("" + letterToRemove, "");
            }

            int randomPicker;
            for (int k = 0; k < unConfirmedLetters.length(); k++) {
                randomPicker = k % newLetterMappings.length();
                guessedMapping.get(unConfirmedLetters.substring(k, k + 1))
                        .setValue(newLetterMappings.substring(randomPicker, randomPicker+ 1));
            }
        }
    }

    private void updateKey() {
        //TODO Implement key finder
        guessedKey.add("A");
    }



}
