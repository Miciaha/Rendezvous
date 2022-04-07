package com.miciaha.inventorymanager.inventoryitems.commands;

import com.miciaha.inventorymanager.interfaces.Command;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import com.miciaha.inventorymanager.utilities.Alerts;

public class ModifyProduct {
    protected Product product;
    protected Command<Product> command;
    protected String commandName;

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

    protected boolean prodIsNull(Product product) {
        return product == null;
    }

    public void runCommand() {
        if (prodIsNull(product)) {
            new Alerts.CustomAlert.CommandErrorAlert(commandName, "No product selected.");
        } else {
            new Alerts.CustomAlert.ConfirmationAlert<>(command, commandName);
        }
    }

    public Command<Product> delete(){
        return new DeleteProduct(product);
    }

    public static class DeleteProduct implements Command<Product> {
        Product selectedProduct;

        public DeleteProduct(Product product) {
            this.selectedProduct = product;
        }

        @Override
        public boolean execute() {
            return Inventory.deleteProduct(selectedProduct);
        }
    }
}