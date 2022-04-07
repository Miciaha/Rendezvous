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

/**
 * The type Form manager handles the creation and removal of view forms.
 * All logic required to open and close stages in addition to the primary stage is located in this class.
 */
public class FormManager {

    /**
     * The type Form.
     */
    public static class Form {
        /**
         * The Call btn.
         */
        protected Button callBtn;
        /**
         * The Win title.
         */
        protected String winTitle;
        /**
         * The Stylesheet.
         */
        protected String stylesheet;
        /**
         * The Form file.
         */
        protected String formFile;
        /**
         * The Win size x.
         */
        protected double winSizeX;
        /**
         * The Win size y.
         */
        protected double winSizeY;
        /**
         * The Loaded form.
         */
        protected FXMLLoader loadedForm;

        /**
         * Instantiates a new Form.
         */
        public Form() {
        }

        /**
         * Open form.
         */
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

    /**
     * The type Part form.
     */
    public static class PartForm extends Form{

        /**
         * Instantiates a new Part form.
         *
         * @param callBtn the call btn
         */
        public PartForm(Button callBtn){
            this.winSizeX = 300;
            this.winSizeY = 550;
            this.winTitle = "Part Form";
            this.stylesheet = "main.css";
            this.formFile = "part-view.fxml";
            this.callBtn = callBtn;
        }
    }

    /**
     * The type Create part form.
     */
    public static class CreatePartForm extends PartForm{

        /**
         * Instantiates a new Create part form.
         *
         * @param callBtn the call btn
         */
        public CreatePartForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Part Form";
            openForm();
        }
    }

    /**
     * The type Edit part form.
     */
    public static class EditPartForm extends PartForm{

        /**
         * Instantiates a new Edit part form.
         *
         * @param callBtn  the call btn
         * @param editPart the edit part
         */
        public EditPartForm(Button callBtn, Part editPart) {
            super(callBtn);
            this.winTitle = "Edit Part Form";

            openForm();
            FormEditor<Part> controller = loadedForm.getController();
            controller.initData(editPart);
        }
    }

    /**
     * The type Product form.
     */
    public static class ProductForm extends Form{

        /**
         * Instantiates a new Product form.
         *
         * @param callBtn the call btn
         */
        public ProductForm(Button callBtn){
            this.winSizeX = 820;
            this.winSizeY = 650;
            this.winTitle = "Product Form";
            this.stylesheet = "main.css";
            this.formFile = "product-view.fxml";
            this.callBtn = callBtn;
        }
    }

    /**
     * The type Create product form.
     */
    public static class CreateProductForm extends ProductForm{

        /**
         * Instantiates a new Create product form.
         *
         * @param callBtn the call btn
         */
        public CreateProductForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Product Form";
            openForm();
        }
    }

    /**
     * The type Edit product form.
     */
    public static class EditProductForm extends ProductForm{

        /**
         * Instantiates a new Edit product form.
         *
         * @param callBtn     the call btn
         * @param editProduct the edit product
         */
        public EditProductForm(Button callBtn, Product editProduct){
            super(callBtn);
            this.winTitle = "Edit Product Form";

            openForm();
            FormEditor<Product> controller = loadedForm.getController();
            controller.initData(editProduct);
        }
    }

    /**
     * Close form.
     *
     * @param button the button
     */
    public static void closeForm(Button button) {
        FieldTracker.Fields.clear();
        Stage btnStage = (Stage) button.getScene().getWindow();
        btnStage.close();
    }

}
