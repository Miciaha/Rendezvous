package com.miciaha.inventorymanager.utilities;

import com.miciaha.inventorymanager.InventoryApplication;
import com.miciaha.inventorymanager.interfaces.FormEditor;
import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import com.miciaha.inventorymanager.utilities.fields.FieldTracker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FormManager {

    public static class Form {
        protected Button callBtn;
        protected String winTitle;
        protected String stylesheet;
        protected String formFile;
        protected double winSizeX;
        protected double winSizeY;
        protected FXMLLoader loadedForm;

        public Form() {
        }

        public void openForm() {
            Scene primaryStage = callBtn.getScene();
            this.loadedForm = new FXMLLoader(InventoryApplication.class.getResource(formFile));
            try {
                Scene scene = new Scene(loadedForm.load(), winSizeX, winSizeY);
                scene.getStylesheets().add(String.valueOf(InventoryApplication.class.getResource(stylesheet)));

                Stage form = new Stage();
                form.setTitle(winTitle);
                form.initModality(Modality.WINDOW_MODAL);
                form.initOwner(primaryStage.getWindow());
                form.setScene(scene);
                form.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PartForm extends Form{

        public PartForm(Button callBtn){
            this.winSizeX = 300;
            this.winSizeY = 550;
            this.winTitle = "Part Form";
            this.stylesheet = "main.css";
            this.formFile = "part-view.fxml";
            this.callBtn = callBtn;
        }
    }

    public static class CreatePartForm extends PartForm{

        public CreatePartForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Part Form";
            openForm();
        }
    }

    public static class EditPartForm extends PartForm{

        public EditPartForm(Button callBtn, Part editPart) {
            super(callBtn);
            this.winTitle = "Edit Part Form";

            openForm();
            FormEditor<Part> controller = loadedForm.getController();
            controller.initData(editPart);
        }
    }

    public static class ProductForm extends Form{

        public ProductForm(Button callBtn){
            this.winSizeX = 820;
            this.winSizeY = 650;
            this.winTitle = "Product Form";
            this.stylesheet = "main.css";
            this.formFile = "product-view.fxml";
            this.callBtn = callBtn;
        }
    }

    public static class CreateProductForm extends ProductForm{

        public CreateProductForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Product Form";
            openForm();
        }
    }

    public static class EditProductForm extends ProductForm{

        public EditProductForm(Button callBtn, Product editProduct){
            super(callBtn);
            this.winTitle = "Edit Product Form";

            openForm();
            FormEditor<Product> controller = loadedForm.getController();
            controller.initData(editProduct);
        }
    }

    public static void closeForm(Button button) {
        FieldTracker.Fields.clear();
        Stage btnStage = (Stage) button.getScene().getWindow();
        btnStage.close();
    }

}
