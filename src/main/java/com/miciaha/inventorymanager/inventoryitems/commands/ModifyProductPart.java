package com.miciaha.inventorymanager.inventoryitems.commands;

import com.miciaha.inventorymanager.interfaces.Command;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import com.miciaha.inventorymanager.utilities.Alerts;
import javafx.collections.ObservableList;

public class ModifyProductPart {
    protected Command<Part> command;
    protected String commandName;
    Part part;
    ObservableList<Part> productPartList;

    public ModifyProductPart(Part part, ObservableList<Part> partList, CommandType command) {
        this.part = part;
        this.productPartList = partList;

        if (command == CommandType.DELETE) {
            this.command = deleteProductPart();
            this.commandName = "delete product part";
        }

        runCommand();
    }

    public void runCommand() {
        if (prodIsNull(part)) {
            new Alerts.CustomAlert.CommandErrorAlert(commandName, "Part not selected");
        } else {
            new Alerts.CustomAlert.ConfirmationAlert<>(command, commandName);
        }
    }

    protected boolean prodIsNull(Part part) {
        return part == null;
    }


    private Command<Part> deleteProductPart(){
        return new RemoveProductPart(productPartList, part);
    }

    private static class RemoveProductPart implements Command<Part> {
        Part part;
        ObservableList<Part> productPartList;
        public RemoveProductPart(ObservableList<Part> prodPartList, Part part){
            this.productPartList = prodPartList;
            this.part = part;
        }

        @Override
        public boolean execute() {
            return productPartList.remove(part);
        }
    }
}
