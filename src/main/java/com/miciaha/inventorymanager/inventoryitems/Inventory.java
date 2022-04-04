package com.miciaha.inventorymanager.inventoryitems;
import com.miciaha.inventorymanager.inventoryitems.parts.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part){
        allParts.add(part);
    }

    public static void addProduct(Product product){
        allProducts.add(product);
    }

    public static Part lookupPart(int partId){
        for(Part part: allParts){
            if(part.getId() == partId){
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId){
        for(Product product: allProducts){
            if(product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> newList = FXCollections.observableArrayList();

        for(Part part: allParts){
            if(part.getName().startsWith(partName)){
                newList.add(part);
            }
        }
        return newList;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> newList = FXCollections.observableArrayList();

        for(Product product: allProducts){
            if(product.getName().startsWith(productName)){
                newList.add(product);
            }
        }
        return newList;
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index,newProduct);
    }

    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
