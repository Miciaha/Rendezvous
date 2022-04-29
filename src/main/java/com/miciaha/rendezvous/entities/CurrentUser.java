package com.miciaha.rendezvous.entities;

/**
 * The type Current user.
 */
public class CurrentUser {
    private static User currentUser = null;

    /**
     * Get name string.
     *
     * @return the string
     */
    public static String getName() {
        return currentUser.getUsername();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public static int getID() {
        return currentUser.getID();
    }

    /**
     * Set current user.
     *
     * @param user the user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
