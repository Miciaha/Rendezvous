package com.miciaha.inventorymanager.inventoryitems.commands;

import com.miciaha.inventorymanager.interfaces.Command;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import com.miciaha.inventorymanager.utilities.Alerts;
import javafx.collections.ObservableList;

/**
 * The type Modify product part.
 */
public class ModifyProductPart {
    /**
     * The Command.
     */
    protected Command<Part> command;
    /**
     * The Command name.
     */
    protected String commandName;
    /**
     * The Part.
     */
    Part part;
    /**
     * The Product part list.
     */
    ObservableList<Part> productPartList;

    /**
     * Instantiates a new Modify product part.
     *
     * @param part     the part
     * @param partList the part list
     * @param command  the command
     */
    public ModifyProductPart(Part part, ObservableList<Part> partList, CommandType command) {
        this.part = part;
        this.productPartList = partList;

        if (command == CommandType.DELETE) {
            this.command = deleteProductPart();
            this.commandName = "delete product part";
        }

        runCommand();
    }

    /**
     * Run command.
     */
    public void runCommand() {
        if (prodIsNull(part)) {
            new Alerts.CustomAlert.CommandErrorAlert(commandName, "Part not selected");
        } else {
            new Alerts.CustomAlert.ConfirmationAlert<>(command, commandName);
        }
    }

    /**
     * Prod is null boolean.
     *
     * @param part the part
     * @return the boolean
     */
    protected boolean prodIsNull(Part part) {
        return part == null;
    }


    private Command<Part> deleteProductPart(){
        return new RemoveProductPart(productPartList, part);
    }

    private static class RemoveProductPart implements Command<Part> {
        /**
         * The Part.
         */
        Part part;
        /**
         * The Product part list.
         */
        ObservableList<Part> productPartList;

        /**
         * Instantiates a new Remove product part.
         *
         * @param prodPartList the prod part list
         * @param part         the part
         */
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
