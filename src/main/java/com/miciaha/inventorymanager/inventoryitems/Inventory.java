package com.miciaha.inventorymanager.inventoryitems;
import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The type Inventory.
 */
public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Add part.
     *
     * @param part the part
     */
    public static void addPart(Part part){
        allParts.add(part);
    }

    /**
     * Add product.
     *
     * @param product the product
     */
    public static void addProduct(Product product){
        allProducts.add(product);
    }

    /**
     * Lookup part part.
     *
     * @param partId the part id
     * @return the part
     */
    public static Part lookupPart(int partId){
        for(Part part: allParts){
            if(part.getId() == partId){
                return part;
            }
        }
        return null;
    }

    /**
     * Lookup product product.
     *
     * @param productId the product id
     * @return the product
     */
    public static Product lookupProduct(int productId){
        for(Product product: allProducts){
            if(product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    /**
     * Lookup part observable list.
     *
     * @param partName the part name
     * @return the observable list
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> newList = FXCollections.observableArrayList();

        for(Part part: allParts){
            if(part.getName().startsWith(partName)){
                newList.add(part);
            }
        }
        return newList;
    }

    /**
     * Lookup product observable list.
     *
     * @param productName the product name
     * @return the observable list
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> newList = FXCollections.observableArrayList();

        for(Product product: allProducts){
            if(product.getName().startsWith(productName)){
                newList.add(product);
            }
        }
        return newList;
    }

    /**
     * Update part.
     *
     * @param index        the index
     * @param selectedPart the selected part
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     * Update product.
     *
     * @param index      the index
     * @param newProduct the new product
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index,newProduct);
    }

    /**
     * Delete part boolean.
     *
     * @param selectedPart the selected part
     * @return the boolean
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /**
     * Delete product boolean.
     *
     * @param selectedProduct the selected product
     * @return the boolean
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     * Get all parts observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Get all products observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
