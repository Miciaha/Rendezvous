package com.miciaha.inventorymanager.inventoryitems.commands;

import com.miciaha.inventorymanager.interfaces.Command;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import com.miciaha.inventorymanager.utilities.Alerts;

/**
 * The type Modify part.
 */
public class ModifyPart {
    /**
     * The Selected part.
     */
    protected Part selectedPart;
    /**
     * The Command.
     */
    protected Command<Part> command;
    /**
     * The Command name.
     */
    protected String commandName;

    /**
     * Instantiates a new Modify part.
     *
     * @param part    the part
     * @param command the command
     */
    public ModifyPart(Part part, CommandType command){
        this.selectedPart = part;

        switch (command){
            case CREATE:
                break;
            case DELETE:
                this.command = delete();
                this.commandName = "delete part";
                break;
            case UPDATE:
                break;
        }
        runCommand();
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

    /**
     * Run command.
     */
    public void runCommand() {
        if (prodIsNull(selectedPart)) {
            new Alerts.CustomAlert.CommandErrorAlert(commandName, "No part selected.");
        } else {
            new Alerts.CustomAlert.ConfirmationAlert<>(command, commandName);
        }
    }

    private Command<Part> delete(){
        return new ModifyPart.DeletePart(selectedPart);
    }


    /**
     * The type Delete part.
     */
    public static class DeletePart implements Command<Part> {
        /**
         * The Selected part.
         */
        Part selectedPart;

        /**
         * Instantiates a new Delete part.
         *
         * @param part the part
         */
        public DeletePart(Part part) {
            this.selectedPart = part;
        }

        @Override
        public boolean execute() {
            return Inventory.deletePart(selectedPart);
        }

    }

    /**
     * The type Update part.
     */
    public static class UpdatePart implements Command<Part>{

        @Override
        public boolean execute() {
            return false;
        }
    }
}

