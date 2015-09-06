/**
 * Created by Austin on 9/6/15.
 */
public class LetterConfirmation {

    private String value;
    private boolean confirmed;

    LetterConfirmation(String value) {
        this.value = value;
        confirmed = false;
    }
    LetterConfirmation(String value, boolean confirmed) {
        this.value = value;
        this.confirmed = confirmed;
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
