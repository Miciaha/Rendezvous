package com.miciaha.inventorymanager.inventoryitems;

import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void addAssociatedPart() {
        Product testProduct = new Product(1,"Test",3.33,2,1,10);
    }

    @Test
    void deleteAssociatedPart() {
    }

    @Test
    void getAllAssociatedParts() {
    }
}