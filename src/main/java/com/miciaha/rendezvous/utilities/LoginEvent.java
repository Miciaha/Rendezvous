package com.miciaha.rendezvous.utilities;

import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The type Login event.
 */
public class LoginEvent implements Serializable {

    private String username;
    private Timestamp loginDateTime;
    private boolean attempt;

    /**
     * Instantiates a new Login event.
     */
    public LoginEvent() {

    }

    /**
     * Instantiates a new Login event.
     *
     * @param username  the username
     * @param localTime the local time
     * @param status    the status
     */
    public LoginEvent(String username, Timestamp localTime, boolean status) {
        this.username = username;
        this.loginDateTime = localTime;
        this.attempt = status;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets login date time.
     *
     * @return the login date time
     */
    public Timestamp getLoginDateTime() {
        return loginDateTime;
    }

    /**
     * Get attempt string string.
     *
     * @return the string
     */
    public String getAttemptString() {
        if (attempt) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    /**
     * Gets attempt.
     *
     * @return the attempt
     */
    public boolean getAttempt() {
        return attempt;
    }

    /**
     * Sets login date time.
     *
     * @param loginDateTime the login date time
     */
    public void setLoginDateTime(Timestamp loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) {
        this.attempt = success;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Log.
     *
     * @param username the username
     * @param success  the success
     */
    public static void Log(String username, boolean success) {
        Timestamp currentTime = LocaleHandler.DateTimeHelper.localDateToTimestampUTC(LocalDateTime.now());

        LoginEvent login = new LoginEvent(username, currentTime, success);

        try {
            FileOutputStream fos = new FileOutputStream("login_activity.txt", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(login);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Get login events observable list.
     *
     * @return the observable list
     */
    public static ObservableList<LoginEvent> getLoginEvents() {
        ObservableList<LoginEvent> events = FXCollections.observableArrayList();
        LoginEvent loginEvent = null;
        try (FileInputStream fis = new FileInputStream("login_activity.txt")) {
            boolean cont = true;
            while (cont) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                loginEvent = (LoginEvent) ois.readObject();

                if (loginEvent != null) {
                    events.add(loginEvent);
                } else {
                    cont = false;
                }
            }
        } catch (IOException | ClassNotFoundException ignored) {
            System.out.println("Reached end of file.");
        }
        return events;
    }

}
