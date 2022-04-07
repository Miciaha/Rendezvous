package com.miciaha.inventorymanager.inventoryitems.commands;

import com.miciaha.inventorymanager.interfaces.Command;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import com.miciaha.inventorymanager.utilities.Alerts;

/**
 * The type Modify product.
 */
public class ModifyProduct {
    /**
     * The Product.
     */
    protected Product product;
    /**
     * The Command.
     */
    protected Command<Product> command;
    /**
     * The Command name.
     */
    protected String commandName;

    /**
     * Instantiates a new Modify product.
     *
     * @param product the product
     * @param command the command
     */
    public ModifyProduct(Product product, CommandType command){
        this.product = product;

        switch (command){
            case CREATE:
                break;
            case DELETE:
                this.command = delete();
                this.commandName = "delete product";
                break;
            case UPDATE:
                break;
        }
        runCommand();
    }

    /**
     * Prod is null boolean.
     *
     * @param product the product
     * @return the boolean
     */
    protected boolean prodIsNull(Product product) {
        return product == null;
    }

    /**
     * Run command.
     */
    public void runCommand() {
        if (prodIsNull(product)) {
            new Alerts.CustomAlert.CommandErrorAlert(commandName, "No product selected.");
        } else {
            new Alerts.CustomAlert.ConfirmationAlert<>(command, commandName);
        }
    }

    /**
     * Delete command.
     *
     * @return the command
     */
    public Command<Product> delete(){
        return new DeleteProduct(product);
    }

    /**
     * The type Delete product.
     */
    public static class DeleteProduct implements Command<Product> {
        /**
         * The Selected product.
         */
        Product selectedProduct;

        /**
         * Instantiates a new Delete product.
         *
         * @param product the product
         */
        public DeleteProduct(Product product) {
            this.selectedProduct = product;
        }

        @Override
        public boolean execute() {
            return Inventory.deleteProduct(selectedProduct);
        }
    }
}