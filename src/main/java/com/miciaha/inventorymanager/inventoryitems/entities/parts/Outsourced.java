package com.miciaha.inventorymanager.inventoryitems.entities.parts;

/**
 * The Part type Outsourced.
 */
public class Outsourced extends Part implements PartType {
    private String companyName;

    /**
     * Instantiates a new Outsourced.
     *
     * @param id          the id
     * @param name        the name
     * @param price       the price
     * @param stock       the stock
     * @param min         the min
     * @param max         the max
     * @param CompanyName the company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String CompanyName) {
        super(id, name, price, stock, min, max);
        this.companyName = CompanyName;
    }

    /**
     * Set company name.
     *
     * @param name the name
     */
    public void setCompanyName(String name){
        companyName = name;
    }

    /**
     * Get company name string.
     *
     * @return the string
     */
    public String getCompanyName(){
        return companyName;
    }

    @Override
    public String getTypeDetail() {
        return getCompanyName();
    }
}
