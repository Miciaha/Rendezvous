package com.miciaha.inventorymanager.inventory;
import com.miciaha.inventorymanager.inventory.parts.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    static private final ObservableList<Part> allParts = FXCollections.observableArrayList();
    static private final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    static public void addPart(Part part){
        allParts.add(part);
    }

    static public void addProduct(Product product){
        allProducts.add(product);
    }

    static public Part lookupPart(int partId){
        for(Part part: allParts){
            if(part.getId() == partId){
                return part;
            }
        }
        return null;
    }

    static public Product lookupProduct(int productId){
        for(Product product: allProducts){
            if(product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    static public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> newList = FXCollections.observableArrayList();

        for(Part part: allParts){
            if(part.getName().startsWith(partName)){
                newList.add(part);
            }
        }
        return newList;
    }

    static public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> newList = FXCollections.observableArrayList();

        for(Product product: allProducts){
            if(product.getName().startsWith(productName)){
                newList.add(product);
            }
        }
        return newList;
    }

    static public void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    static public void updateProduct(int index, Product newProduct){
        allProducts.set(index,newProduct);
    }

    static public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    static public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    static public ObservableList<Part> getAllParts(){
        return allParts;
    }

    static public ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
