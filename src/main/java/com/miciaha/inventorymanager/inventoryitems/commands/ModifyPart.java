package com.miciaha.inventorymanager.inventoryitems.commands;

import com.miciaha.inventorymanager.interfaces.Command;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import com.miciaha.inventorymanager.utilities.Alerts;

public class ModifyPart {
    protected Part selectedPart;
    protected Command<Part> command;
    protected String commandName;

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


    protected boolean prodIsNull(Part part) {
        return part == null;
    }

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


    public static class DeletePart implements Command<Part> {
        Part selectedPart;

        public DeletePart(Part part) {
            this.selectedPart = part;
        }

        @Override
        public boolean execute() {
            return Inventory.deletePart(selectedPart);
        }

    }

    public static class UpdatePart implements Command<Part>{

        @Override
        public boolean execute() {
            return false;
        }
    }
}

