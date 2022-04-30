package com.miciaha.rendezvous.entities.reports;

public class CustomerAppointment {
    private int count;
    private String type;
    private String month;

    /**
     * Gets month.
     *
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Instantiates a new Customer appointment.
     *
     * @param type  the type
     * @param month the month
     */
    protected CustomerAppointment(String type, String month) {
        this.type = type;
        this.month = month;
    }

    /**
     * Instantiates a new Customer appointment.
     *
     * @param combinedValues the combined values of type and month
     * @param count          the count
     */
    public CustomerAppointment(String combinedValues, int count) {
        String[] splitValues = combinedValues.split(";");
        this.type = splitValues[0];
        this.month = splitValues[1];
        this.count = count;

    }
}
