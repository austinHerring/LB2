/**
 * Class that associates a letter that is being mapped to as confirmed or not confirmed
 *
 * @author Austin Herring
 * @version 1.0
 */
public class LetterConfirmation {

    private String value;
    private boolean confirmed;

    LetterConfirmation(String value) {
        this.value = value;
        confirmed = false;
    }

    public void setConfirmed() {
        confirmed = true;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
