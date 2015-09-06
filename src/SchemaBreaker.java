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

    private HashMap<String,String> data;
    private HashMap<String,LetterConfirmation> guessedMapping;
    private LinkedList<String> guessedKey;
    private String currentChallenge;
    private String currentPassword;

    SchemaBreaker() {
        guessedKey = new LinkedList<String>();
        data = new HashMap<String, String>();
        guessedMapping = new HashMap<String, LetterConfirmation>();
        for (int i = 65; i < 91; i++) {
            guessedMapping.put(String.valueOf((char)i),
                    new LetterConfirmation(""));
        }

    }

    public void takeChallenge(String currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    public void takeFeedback(String password) {
        data.put(currentChallenge, password);
        currentPassword = password;
        updateMapping();
        updateKey();
    }

    public String guess(String website) {
        //PasswordGenerator generator = new PasswordGenerator(guessedKey);

            //return generator.generate(website);
            String guess = "";
            for (int i = 0; i < website.length(); i++) {
                guess += guessedMapping.get(website.substring(i,i+1).toUpperCase()).getValue();
            }
            return guess;
    }

    private void updateMapping() {
        if (currentChallenge.length() == currentPassword.length()) {
            for (int i = 0; i < currentChallenge.length(); i++) {
                guessedMapping.put(currentChallenge.substring(i,i+1).toUpperCase(),
                        new LetterConfirmation(currentPassword.substring(i,i+1).toUpperCase(), true));
            }
        } else {
            LetterConfirmation currentGuess;
            for (int i = 0; i < currentChallenge.length(); i++) {
                currentGuess = guessedMapping.get(currentChallenge.substring(i,i+1));
                if (!currentGuess.isConfirmed()) {
                    //TODO implement mapp updated for password < challenge
                }
            }
        }
    }

    private void updateKey() {
        //TODO Implement key finder
        guessedKey.add("A");
    }



}
