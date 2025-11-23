package it.unibo.mvc;

import java.util.ArrayList;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String currentString = "";
    private ArrayList<String> history = new ArrayList<String>();

    /**
     * Sets the next string to print
     *
     * @param newString as the next string to print
     * @throws NullPointerException if the String is Null
     */
    public void setNextString(String newString) {
        if (newString.equals(null)) {
            throw new NullPointerException();
        } else {
            this.currentString = newString;
        }
    }

    /**
     * @return the next String to print
     */
    public String getNextString() {
        return this.currentString;
    }

    public ArrayList<String> getHistory() {
        return this.history;
    }

    @Override
    public void printOutput() {
        if (this.currentString.equals("")) {
            throw new IllegalStateException();
        } else {
            System.out.println(this.currentString); // NOPMD
            history.add(this.currentString);
        }
    }

}
