package c482oa;

import c482oa.resources.*;
import java.util.Optional;
import javafx.scene.control.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

//JavaDoc foler is located in C482OAssessment/dist/javadoc


/**
 *
 * @author austinjohnson
 */
public class Inventory extends Application {
    //Global lists and variables
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private int curID = 1;
    private int curProdID = 1;
    
    //Private Tables
    private TableView<Part> partsTable;
    private TableView<Part> subPartsTable;
    private TableView<Product> productsTable;
    private TableView<Part> subProductsTable;
    
    /**
    * Adds a new part to the global part list "allParts"
    * 
    * <p>
    * FUTURE ENHANCEMENTS: Add a return value to signify that the
    * part was successfully added.
    * <p>
    * RUNTIME ERROR: None
    *
    * @param  newPart  the part to be added to allParts
    */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    
    /**
    * Adds a new part to the global product list "allProducts"
    * 
    * <p>
    * FUTURE ENHANCEMENTS: Add a return value to signify that the
    * product was successfully added.
    * <p>
    * RUNTIME ERROR: None
    *
    * @param  newProduct  the product to be added to allProducts
    */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    /**
    * Finds a specific part in the allParts list
    * using the unique id.
    * 
    * <p>
    * FUTURE ENHANCEMENT: Use a faster search algorithm to find the part.
    * <p>
    * LOGIC ERROR: Used "greater than" rather than "less than" on the for
    * loop, causing it not not search any products.
    *
    * @param  partId  the id of the part to be found
    * @return the part whose id is partId
    */
    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }
    
    /**
    * Finds a specific part in the allParts list
    * using the name.
    * 
    * <p>
    * FUTURE ENHANCEMENT: Use a faster search algorithm to find the part.
    * <p>
    * LOGIC ERROR: Used "greater than" rather than "less than" on the for
    * loop, causing it not not search any products.
    *
    * @param  partName  the name of the part to be found
    * @return a list of all the parts whose name contains the partName
    */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> tmp = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())) {
                tmp.add(allParts.get(i));
            }
        }
        return tmp;
    }
    
    /**
    * Finds a specific part in the allProducts list
    * using the unique id.
    * 
    * <p>
    * FUTURE ENHANCEMENTS: Use a faster search algorithm to find the part.
    * <p>
    * LOGIC ERROR: Used "greater than" rather than "less than" on the for
    * loop, causing it not not search any products.
    *
    * @param  productId  the id of the product to be found
    * @return the part whose id is productId
    */
    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }
    
    /**
    * Finds a specific part in the allProducts list
    * using the name.
    * 
    * 
    * <p>
    * FUTURE ENHANCEMENT: Use a faster search algorithm to find the part.
    * <p>
    * LOGIC ERROR: Used "greater than" rather than "less than" on the for
    * loop, causing it not not search any products.
    *
    * @param  productName  the name of the product to be found
    * @return a list of all the products whose name contains the productName
    */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> tmp = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().contains(productName)) {
                tmp.add(allProducts.get(i));
            }
        }
        return tmp;
    }
    
    /**
    * Update the id of a given part and add that part
    * to the allParts list.
    * 
    * <p>
    * FUTURE ENHANCEMENT: Change the implementation of this function
    * to allow the update function to choose in-house or outsourced part
    * to further simplify the individual page's code.
    * <p>
    * RUNTIME ERROR: When I tried to use the function, it shot
    * out an error with the original way that I was trying to remove
    * the old part. So I utilized the deletePart and lookupPart
    * functions instead of modifying the allParts list directly.
    *
    * @param  index  the id of the current part
    * @param selectedPart the updated part to be added to allParts list
    */
    public static void updatePart(int index, Part selectedPart) {
        deletePart(lookupPart(index));
        selectedPart.setId(index);
        addPart(selectedPart);
    }
    
    /**
    * Update the id of a given product and add that product
    * to the allProducts list.
    * 
    * <p>
    * FUTURE ENHANCEMENT: Add a return value to signify that the function
    * ran successfully.
    * <p>
    * RUNTIME ERROR: When I tried to use the function, it shot
    * out an error with the original way that I was trying to remove
    * the old product. So I utilized the deleteProduct and lookupProduct
    * functions instead of modifying the allProducts list directly.
    *
    * @param  index  the id of the current product
    * @param selectedProduct the updated part to be added to allProducts list
    */
    public static void updateProduct(int index, Product selectedProduct) {
        deleteProduct(lookupProduct(index));
        selectedProduct.setId(index);
        addProduct(selectedProduct);
    }
    
    /**
    * Removes a given item from the allParts list
    * 
    * <p>
    * FUTURE ENHANCEMENTS: It's perfect
    * <p> 
    * RUNTIME ERROR: None
    *
    * @param  selectedPart  the part to be removed
    * @return true if part was found in allParts and removed
    */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }
    
    /**
    * Removes a given item from the allProducts list
    * 
    * <p>
    * FUTURE ENHANCEMENTS: It's perfect
    * <p>
    * RUNTIME ERROR: None
    *
    * @param  selectedProduct  the product to be removed
    * @return true if part was found in allParts and removed
    */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
    
    /**
    * Returns the allParts list
    * 
    * <p>
    * FUTURE ENHANCEMENTS: It's perfect
    * <p>
    * RUNTIME ERROR: None
    *
    * @return allParts list
    */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    /**
    * Returns the allProducts list
    * 
    * <p>
    * FUTURE ENHANCEMENTS: It's perfect
    * <p>
    * RUNTIME ERROR: None
    *
    * @return allParts list
    */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    
    /**
    * Sets the window title and loads the main form.
    * 
    * <p>
    * This method always returns immediately, whether or not the 
    * main form loads.
    *
    * @param  applicationStage  the top level container of the GUI
    */
    @Override
    public void start(Stage applicationStage) { 
        applicationStage.setTitle("Inventory Management System"); // Set window's title
        mainForm(applicationStage);
    }
    
    /**
    * Main function that starts the program accounting for
    * command line arguments.
    *
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
    * This is the home page of the application. It contains an overview of all
    * products and parts, as well as options to add, modify, or delete all
    * products and parts.
    * 
    * <p>
    * This method utilizes helper functions to generate the parts table and product table
    * to limit the number of lines per function in the program.
    * <p>
    * FUTURE ENHANCEMENTS: Make the products and parts tables different tabs, allowing
    * for more information on each part and product to be displayed.
    * <p>
    * LOGIC ERROR: When making the exit button, I tried to just add it 
    * to the root, but I couldn't get the placement correct. What I had 
    * to do was add the button to an HBox, and add padding to the HBox.
    *
    * @param  applicationStage  the top level container of the GUI
    * @see partPaneGenerator(Stage applicationStage)
    * @see productPaneGenerator(Stage applicationStage)
    */
    public void mainForm(Stage applicationStage) {
        //Create Root Pane
        GridPane root = new GridPane();
        root.getStyleClass().add("mainFormPane");
      
        //Add a label to the root pane
        Label title = new Label("Inventory Management System");
        root.getChildren().add(title);
        
        //Parts Table
        GridPane partPane = partPaneGenerator(applicationStage);  
        partPane.getStyleClass().add("mainFormTablePane");
        
        //Products Table
        GridPane productPane = productPaneGenerator(applicationStage);
        productPane.getStyleClass().add("mainFormTablePane");
        
        HBox lower = new HBox();
        lower.setPadding(new Insets(0,0,0,400));
        Button exitBtn = new Button("Exit");
        EventHandler<ActionEvent> exit = (ActionEvent e) -> {
            Platform.exit();
        };
        exitBtn.setOnAction(exit);
        lower.getChildren().add(exitBtn);
        
        
        root.add(partPane, 0,1);
        root.add(productPane, 1, 1);
        root.add(lower, 1, 2);
        
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show(); 
    }
    
    /**
    * This is one of the helper functions for the main form. It generates a table
    * based on the global list "partList".
    * <p>
    * It also give the user the ability to add, modify, or delete parts.
    * <p>
    * FUTURE ENHANCEMENT: Filter parts by in-house or outsourced. Also display the machine 
    * id or company name.
    * <p>
    * LOGIC ERROR: When making the modify part button, I tried to pass the part as a
    * generic part class, but because all parts are either in-house or outsourced,
    * I couldn't pass the part to the modify part form, so I had make an overloaded
    * method for in-house or outsourced parts.
    *
    * @param  applicationStage  the top level container of the GUI
    * @return table of all parts
    * @see mainForm(Stage applicationStage)
    */
    public GridPane partPaneGenerator(Stage applicationStage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        
        ///Fill the top
        //Create a hbox container
        HBox top = new HBox(20);
        
        //Create the Label
        Label title = new Label("Parts");
        title.setPadding(new Insets(0, 125, 0, 0));
        
        //Create search box
        TextField search = new TextField();
        search.setPromptText("Search by Part ID or Name");
        search.setFocusTraversable(false);
        search.setFocusTraversable(false);
        Button searchBtn = new Button("Search");
        
        //Add items to top
        top.getChildren().addAll(title, search, searchBtn);
        
        ///Fill the middle
        //Populate the Parts Table Columns
        if (partsTable == null) {
            partsTable = new TableView();
            TableColumn partIDCol = new TableColumn("Part ID");
            partIDCol.setMaxWidth(75);
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn partNameCol = new TableColumn("Part Name");
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn invLevelCol = new TableColumn("Inventory Level");
            invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            invLevelCol.setMinWidth(100);
            TableColumn priceCol = new TableColumn("Price/ Cost per Unit");
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceCol.setMinWidth(140);
            partsTable.setMinSize(400, 150);        
            partsTable.getColumns().addAll(partIDCol, partNameCol, invLevelCol, priceCol);
        }
        
        partsTable.setItems(getAllParts());
        
        EventHandler<ActionEvent> searchEvent = (ActionEvent e) -> {
        //Search for name
        ObservableList<Part> tmp = lookupPart(search.getText());
        //Search for id 
        try {
            int id = Integer.parseInt(search.getText());
            tmp.add(lookupPart(id));
        } catch(NumberFormatException w) {
            //do nothing
        }
        if (tmp.size() == 0) {
            Alert error = new Alert(AlertType.ERROR);
            error.setHeaderText("No results found with that name/ID.");
            error.showAndWait();
        }
        partsTable.setItems(tmp);
        };
        searchBtn.setOnAction(searchEvent);
        
        
        ///Fill the bottom
        //Create a hbox container
        HBox bottom = new HBox(10);
        bottom.setPadding(new Insets(0, 0, 0, 225));
        
        //Create buttoms
        Button addBtn = new Button("Add");
        EventHandler<ActionEvent> addEvent = (ActionEvent e) -> {
            addPartForm(applicationStage);
        };
        addBtn.setOnAction(addEvent);
        Button modifyBtn = new Button("Modify");
        EventHandler<ActionEvent> modEvent = (ActionEvent e) -> {
            try {
                Part p = partsTable.getSelectionModel().getSelectedItem();
                if (p instanceof InHouse) {
                    modifyPartForm(applicationStage, (InHouse) p);
                } else if (p instanceof Outsourced) {
                    modifyPartForm(applicationStage, (Outsourced) p);
                }
            } catch (Exception x) {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("No Part Selected");
                error.showAndWait();
            }

        };
        modifyBtn.setOnAction(modEvent);
        Button deleteBtn = new Button("Delete");
        EventHandler<ActionEvent> delEvent = (ActionEvent e) -> {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Delete");
            confirm.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK && !deletePart(partsTable.getSelectionModel().getSelectedItem())) {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Part Not Deleted!");
                error.setContentText("The selected part was not found, and could not be deleted.");
                error.showAndWait();
            } else {
                mainForm(applicationStage);
            }
        };
        deleteBtn.setOnAction(delEvent);
        
        
        
        //Add buttons to bottom
        bottom.getChildren().addAll(addBtn, modifyBtn, deleteBtn);
        
        //Add all contents to Parts Pane
        pane.add(top, 0, 0);
        pane.add(partsTable, 0, 1);
        pane.add(bottom, 0, 2);
        
        //Return the Parts Pane
        return pane;
    }

    /**
    * This method generates a table that can add parts from the list "partList"
    * to a product's part list.
    * <p>
    * This is an overloaded version of partPaneGenerator specifically designed for the
    * add and modify product forms.
    * <p>
    * FUTURE ENHANCEMENT: Filter parts by in-house or outsourced. Also display the machine 
    * id or company name.
    * <p>
    * RUNTIME ERROR: When generating the parts pane for the add/modify product form
    * I ran into the issue of not being able to resize the table because I was trying
    * to use the same table that I use for the main form parts pane. To solve this, I
    * created a duplicate table, so that I could size it separately. 
    *
    * @param  p  the product that you are adding/modifying
    * @return table of all parts
    * @see partPaneGenerator(Stage applicationStage)
    */
    public GridPane partPaneGenerator(Product p) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        
        ///Fill the top
        //Create a hbox container
        HBox top = new HBox(20);
        
        //Create the Label
        Label title = new Label("Parts");
        title.setPadding(new Insets(0, 100, 0, 0));
        
        //Create search box
        TextField search = new TextField();
        search.setPromptText("Search by Part ID or Name");
        search.setFocusTraversable(false);
        Button searchBtn = new Button("Search");
        
        //Add items to top
        top.getChildren().addAll(title, search, searchBtn);
        
        ///Fill the middle
        //Populate the Parts Table Columns
        if (subPartsTable == null) {
            subPartsTable = new TableView();
            TableColumn partIDCol = new TableColumn("Part ID");
            partIDCol.setMaxWidth(75);
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn partNameCol = new TableColumn("Part Name");
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn invLevelCol = new TableColumn("Inventory Level");
            invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            invLevelCol.setMinWidth(100);
            TableColumn priceCol = new TableColumn("Price/ Cost per Unit");
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceCol.setMinWidth(140);
            subPartsTable.setMinSize(400, 150);  
            subPartsTable.setMaxSize(400, 150);
            subPartsTable.getColumns().addAll(partIDCol, partNameCol, invLevelCol, priceCol);
        }
        
        subPartsTable.setItems(getAllParts());
        
        EventHandler<ActionEvent> searchEvent = (ActionEvent e) -> {
        //Search for name
        ObservableList<Part> tmp = lookupPart(search.getText());
        //Search for id 
        try {
            int id = Integer.parseInt(search.getText());
            tmp.add(lookupPart(id));
        } catch(NumberFormatException w) {
            //do nothing
        }
        if (tmp.size() == 0) {
            Alert error = new Alert(AlertType.ERROR);
            error.setHeaderText("No results found with that name/ID.");
            error.showAndWait();
        }
        subPartsTable.setItems(tmp);
        };
        searchBtn.setOnAction(searchEvent);
        
        
        
        ///Fill the bottom
        //Create a hbox container
        HBox bottom = new HBox(10);
        bottom.setPadding(new Insets(0, 0, 0, 350));
        
        //Create buttoms
        Button addBtn = new Button("Add");
        EventHandler<ActionEvent> addEvent = (ActionEvent e) -> {
            p.addAssociatedPart(subPartsTable.getSelectionModel().getSelectedItem());
        };
        addBtn.setOnAction(addEvent);
        
        //Add buttons to bottom
        bottom.getChildren().add(addBtn);
        
        //Add all contents to Parts Pane
        pane.add(top, 0, 0);
        pane.add(subPartsTable, 0, 1);
        pane.add(bottom, 0, 2);
        
        //Return the Parts Pane
        return pane;
    }

    /**
    * This is one of the helper functions for the main form. It generates a table
    * based on the global list "productList".
    * <p>
    * It also give the user the ability to add, modify, or delete products.
    * <p>
    * FUTURE ENHANCEMENTS: Allow users to click on a product and see all related
    * parts at a glance.
    * <p>
    * LOGIC ERROR: When I initially created this function, I had not yet made
    * the modify product function, which led to a logic error. I fixed this 
    * by creating the modify product function.
    *
    * @param  applicationStage  the top level container of the GUI
    * @return table of all products
    * @see mainForm(Stage applicationStage)
    */
    public GridPane productPaneGenerator(Stage applicationStage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        
        ///Fill the top
        //Create a hbox container
        HBox top = new HBox(20);
        
        //Create the Label
        Label title = new Label("Products");
        title.setPadding(new Insets(0, 105, 0, 0));
        
        //Create search box
        TextField search = new TextField();
        search.setPromptText("Search by Product ID or Name");
        search.setFocusTraversable(false);
        Button searchBtn = new Button("Search");
        
        //Add items to top
        top.getChildren().addAll(title, search, searchBtn);
        
        ///Fill the middle
        //Populate the Parts Table Columns
        if (productsTable == null) {
            productsTable = new TableView();
            TableColumn productIDCol = new TableColumn("Product ID");
            productIDCol.setMaxWidth(75);
            productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn productNameCol = new TableColumn("Product Name");
            productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            productNameCol.setMinWidth(95);

            TableColumn invLevelCol = new TableColumn("Inventory Level");
            invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            invLevelCol.setMinWidth(95);
            TableColumn priceCol = new TableColumn("Price/ Cost per Unit");
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceCol.setMinWidth(130);
            productsTable.setMinSize(400, 150);        
            productsTable.getColumns().addAll(productIDCol, productNameCol, invLevelCol, priceCol);
        }
        
        productsTable.setItems(getAllProducts());
        
        EventHandler<ActionEvent> searchEvent = (ActionEvent e) -> {
        //Search for name
        ObservableList<Product> tmp = lookupProduct(search.getText());
        //Search for id 
        try {
            int id = Integer.parseInt(search.getText());
            tmp.add(lookupProduct(id));
        } catch(NumberFormatException w) {
            //do nothing
        }
        if (tmp.isEmpty()) {
            if (!search.getText().equalsIgnoreCase("")) {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("No results found with that name/ID.");
                error.showAndWait();
            }
        }
        productsTable.setItems(tmp);
        };
        searchBtn.setOnAction(searchEvent);
        
        ///Fill the bottom
        //Create a hbox container
        HBox bottom = new HBox(10);
        bottom.setPadding(new Insets(0, 0, 0, 225));
        
        //Create buttoms
        Button addBtn = new Button("Add");
        EventHandler<ActionEvent> addEvent = (ActionEvent e) -> {
            addProductForm(applicationStage);
        };
        addBtn.setOnAction(addEvent);
        Button modifyBtn = new Button("Modify");
        EventHandler<ActionEvent> modEvent = (ActionEvent e) -> {
            try {
                Product p = productsTable.getSelectionModel().getSelectedItem();
                modifyProductForm(applicationStage, p);
            } catch (Exception x) {
            }

        };
        modifyBtn.setOnAction(modEvent);
        Button deleteBtn = new Button("Delete");
        EventHandler<ActionEvent> delEvent = (ActionEvent e) -> {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Delete");
            confirm.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK && !productsTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()) {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Product Not Deleted!");
                error.setContentText("The selected product has associated parts. Please remove all"
                        + "associated parts before deleting this product.");
                error.showAndWait();
            } else if (result.get() == ButtonType.OK) {
                deleteProduct(productsTable.getSelectionModel().getSelectedItem());
            }
        };
        deleteBtn.setOnAction(delEvent);
        
        //Add buttons to bottom
        bottom.getChildren().addAll(addBtn, modifyBtn, deleteBtn);
        
        //Add all contents to Products Pane
        pane.add(top, 0, 0);
        pane.add(productsTable, 0, 1);
        pane.add(bottom, 0, 2);
        
        //Return the Products Pane
        return pane;
    }
    
    /**
    * This form allows the user to add a new part to the "partList" list.
    * The part is not saved until the user presses the save button.
    * <p>
    * FUTURE ENHANCEMENT: Allow a photo of the part to be uploaded.
    * <p>
    * RUNTIME ERROR: I ran into an issue with getting the add part form to 
    * update based on in-house or outsourced with machine id or company name.
    * Instead of changing what was displayed, it added both the machine id and
    * company name. The way I solved this was by clearing and re-rendering
    * the whole pane based on which radio button was pressed.
    *
    * @param  applicationStage  the top level container of the GUI
    * @see partPaneGenerator(Stage applicationStage)
    */
    public void addPartForm(Stage applicationStage) {
        GridPane root = new GridPane();
        root.getStyleClass().add("partFormPane");
      
        ///Top H box
        HBox upper = new HBox(50);
        
        //Label
        Label title = new Label("Add Part");
        
        //Radio Buttons
        final ToggleGroup group = new ToggleGroup();
        RadioButton inHouse = new RadioButton("In-House");
        inHouse.setToggleGroup(group);
        RadioButton outsourced = new RadioButton("Outsourced");
        outsourced.setToggleGroup(group);
        upper.getChildren().addAll(title, inHouse, outsourced);
        
        ///GridPane of information
        //Make info gridpane
        GridPane infoPane = new GridPane();
        infoPane.setPadding(new Insets (10,10,10,10));
        infoPane.setVgap(5);
        infoPane.setHgap(15);
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(100);
            infoPane.getColumnConstraints().add(column);
        }
        //Generate all Labels
        Label id = new Label("ID");
        Label name = new Label("Name");
        Label inv = new Label("Inv");
        Label cost = new Label("Price/Cost");
        Label max = new Label("Max");
        Label min = new Label("Min");
        Label machine = new Label("Machine ID");
        Label company = new Label("Company Name");
        
        //Generate all text boxes
        TextField idField = new TextField("Auto Gen - Disabled");
        idField.setDisable(true);
        idField.setMinWidth(150);
        TextField nameField = new TextField();
        TextField invField = new TextField();
        TextField costField = new TextField();
        TextField maxField = new TextField();
        TextField minField = new TextField();
        TextField machineField = new TextField();
        
        //Add all info to pane
        infoPane.add(id, 0, 0);
        infoPane.add(idField, 1, 0);
        infoPane.add(name, 0, 1);
        infoPane.add(nameField, 1, 1);
        infoPane.add(inv, 0, 2);
        infoPane.add(invField, 1, 2);
        infoPane.add(cost, 0, 3);
        infoPane.add(costField, 1, 3);
        infoPane.add(max, 0, 4);
        infoPane.add(maxField, 1, 4);
        infoPane.add(min, 2, 4);
        infoPane.add(minField, 3, 4);
        infoPane.add(machine, 0, 5);
        infoPane.add(machineField, 1, 5);
        
        //Check for inhouse or outsourced change
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
            RadioButton rb = (RadioButton)group.getSelectedToggle();
            
            if (rb != null) {
                String s = rb.getText();
                if (s.equals("In-House")) {
                    infoPane.getChildren().clear();
                    infoPane.add(id, 0, 0);
                    infoPane.add(idField, 1, 0);
                    infoPane.add(name, 0, 1);
                    infoPane.add(nameField, 1, 1);
                    infoPane.add(inv, 0, 2);
                    infoPane.add(invField, 1, 2);
                    infoPane.add(cost, 0, 3);
                    infoPane.add(costField, 1, 3);
                    infoPane.add(max, 0, 4);
                    infoPane.add(maxField, 1, 4);
                    infoPane.add(min, 2, 4);
                    infoPane.add(minField, 3, 4);
                    infoPane.add(machine, 0, 5);
                    infoPane.add(machineField, 1, 5);
                } else {
                    infoPane.getChildren().clear();
                    infoPane.add(id, 0, 0);
                    infoPane.add(idField, 1, 0);
                    infoPane.add(name, 0, 1);
                    infoPane.add(nameField, 1, 1);
                    infoPane.add(inv, 0, 2);
                    infoPane.add(invField, 1, 2);
                    infoPane.add(cost, 0, 3);
                    infoPane.add(costField, 1, 3);
                    infoPane.add(max, 0, 4);
                    infoPane.add(maxField, 1, 4);
                    infoPane.add(min, 2, 4);
                    infoPane.add(minField, 3, 4);
                    infoPane.add(company, 0, 5);
                    infoPane.add(machineField, 1, 5);
                }
            }
        });
        
        //Add the lower box
        HBox lower = new HBox(15);
        lower.setPadding(new Insets(0, 0, 0, 250));
        Button saveBtn = new Button("Save");
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            if (inHouse.isSelected()) {
                System.out.println("Try validating");
                if (validateInput(costField.getText(), invField.getText(), 
                minField.getText(), maxField.getText(), machineField.getText())) {
                    System.out.println("Success!");
                    addPart(new InHouse(curID, nameField.getText(), Double.parseDouble(costField.getText()),
                    Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                            Integer.parseInt(maxField.getText()), Integer.parseInt(machineField.getText())));
                    curID++;
                    mainForm(applicationStage);
                } else {
                    addPartForm(applicationStage);
                }
            } else if (outsourced.isSelected()) {
                if (validateInput(costField.getText(), invField.getText(), 
                minField.getText(), maxField.getText())) {
                    addPart(new Outsourced(curID, nameField.getText(), Double.parseDouble(costField.getText()),
                    Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                            Integer.parseInt(maxField.getText()), machineField.getText()));
                    curID++;
                    mainForm(applicationStage);
                } else {
                    addPartForm(applicationStage);
                }
            }
            
        };
        saveBtn.setOnAction(event);
        Button cancelBtn = new Button("Cancel");
        EventHandler<ActionEvent> event2 = (ActionEvent e) -> {
            mainForm(applicationStage);
        };
        cancelBtn.setOnAction(event2);
        
        //Add buttons to lower hbox
        lower.getChildren().addAll(saveBtn, cancelBtn);

        //Add all components to root pane
        root.add(upper, 0, 0);
        root.add(infoPane, 0, 1);
        root.add(lower, 0, 2);
        
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        
        //Change the scene
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show(); 
    }

    /**
    * This form allows the user to modify existing in-house parts. It pre-populates
    * all fields based on the part's current values.
    * <p>
    * FUTURE ENHANCEMENT: Show the difference between the old part info
    * and the new part info, then make the user confirm changes before
    * it saves.
    * <p>
    * LOGIC ERROR: When I tried to change an in-house part to an outsourced
    * part, there wasn't a class modifier I could use to change it over, so
    * to change from in-house to outsourced, I had to add the new part to the
    * list, taking all of the current parts information, then delete the current
    * part to make sure I have no duplicates.
    *
    * @param  applicationStage  the top level container of the GUI
    * @param  curPart           the current part being modified
    * @see partPaneGenerator(Stage applicationStage)
    */
    public void modifyPartForm(Stage applicationStage, InHouse curPart) {
        GridPane root = new GridPane();
        root.getStyleClass().add("partFormPane");
      
        ///Top H box
        HBox upper = new HBox(50);
        
        //Label
        Label title = new Label("Modify Part");
        
        //Radio Buttons
        final ToggleGroup group = new ToggleGroup();
        RadioButton inHouse = new RadioButton("In-House");
        inHouse.setToggleGroup(group);
        inHouse.setSelected(true);
        RadioButton outsourced = new RadioButton("Outsourced");
        outsourced.setToggleGroup(group);
        upper.getChildren().addAll(title, inHouse, outsourced);
        
        ///GridPane of information
        //Make info gridpane
        GridPane infoPane = new GridPane();
        infoPane.setPadding(new Insets (10,10,10,10));
        infoPane.setVgap(5);
        infoPane.setHgap(15);
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(100);
            infoPane.getColumnConstraints().add(column);
        }
        //Generate all Labels
        Label id = new Label("ID");
        Label name = new Label("Name");
        Label inv = new Label("Inv");
        Label cost = new Label("Price/Cost");
        Label max = new Label("Max");
        Label min = new Label("Min");
        Label machine = new Label("Machine ID");
        Label company = new Label("Company Name");
        
        //Generate all text boxes
        TextField idField = new TextField(Integer.toString(curPart.getId()));
        idField.setDisable(true);
        idField.setMinWidth(150);
        TextField nameField = new TextField(curPart.getName());
        TextField invField = new TextField(Integer.toString(curPart.getStock()));
        TextField costField = new TextField(Double.toString(curPart.getPrice()));
        TextField maxField = new TextField(Integer.toString(curPart.getMax()));
        TextField minField = new TextField(Integer.toString(curPart.getMin()));
        TextField machineField = new TextField(Integer.toString(curPart.getMachineId()));
        
        //Add all info to pane
        infoPane.add(id, 0, 0);
        infoPane.add(idField, 1, 0);
        infoPane.add(name, 0, 1);
        infoPane.add(nameField, 1, 1);
        infoPane.add(inv, 0, 2);
        infoPane.add(invField, 1, 2);
        infoPane.add(cost, 0, 3);
        infoPane.add(costField, 1, 3);
        infoPane.add(max, 0, 4);
        infoPane.add(maxField, 1, 4);
        infoPane.add(min, 2, 4);
        infoPane.add(minField, 3, 4);
        infoPane.add(machine, 0, 5);
        infoPane.add(machineField, 1, 5);
        
        //Check for inhouse or outsourced change
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
            RadioButton rb = (RadioButton)group.getSelectedToggle();
            
            if (rb != null) {
                String s = rb.getText();
                if (s.equals("In-House")) {
                    infoPane.getChildren().clear();
                    infoPane.add(id, 0, 0);
                    infoPane.add(idField, 1, 0);
                    infoPane.add(name, 0, 1);
                    infoPane.add(nameField, 1, 1);
                    infoPane.add(inv, 0, 2);
                    infoPane.add(invField, 1, 2);
                    infoPane.add(cost, 0, 3);
                    infoPane.add(costField, 1, 3);
                    infoPane.add(max, 0, 4);
                    infoPane.add(maxField, 1, 4);
                    infoPane.add(min, 2, 4);
                    infoPane.add(minField, 3, 4);
                    infoPane.add(machine, 0, 5);
                    infoPane.add(machineField, 1, 5);
                } else {
                    infoPane.getChildren().clear();
                    infoPane.add(id, 0, 0);
                    infoPane.add(idField, 1, 0);
                    infoPane.add(name, 0, 1);
                    infoPane.add(nameField, 1, 1);
                    infoPane.add(inv, 0, 2);
                    infoPane.add(invField, 1, 2);
                    infoPane.add(cost, 0, 3);
                    infoPane.add(costField, 1, 3);
                    infoPane.add(max, 0, 4);
                    infoPane.add(maxField, 1, 4);
                    infoPane.add(min, 2, 4);
                    infoPane.add(minField, 3, 4);
                    infoPane.add(company, 0, 5);
                    infoPane.add(machineField, 1, 5);
                }
            }
        });
        
        //Add the lower box
        HBox lower = new HBox(15);
        lower.setPadding(new Insets(0, 0, 0, 250));
        Button saveBtn = new Button("Save");
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            if (inHouse.isSelected()) {
                    if (validateInput(costField.getText(), invField.getText(), 
                            minField.getText(), maxField.getText(), machineField.getText())) {
                    updatePart(curPart.getId(), new InHouse(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                    Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                            Integer.parseInt(maxField.getText()), Integer.parseInt(machineField.getText())));
                    mainForm(applicationStage);
                } else {
                        modifyPartForm(applicationStage, curPart);
                    }
            } else if (outsourced.isSelected()) {
                if (validateInput(costField.getText(), invField.getText(), 
                        minField.getText(), maxField.getText(), machineField.getText())) {
                    updatePart(curPart.getId(), new Outsourced(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                            Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                            Integer.parseInt(maxField.getText()), machineField.getText()));
                    mainForm(applicationStage);
                } else {
                    modifyPartForm(applicationStage, curPart);
                }
            }
        };
        saveBtn.setOnAction(event);
        Button cancelBtn = new Button("Cancel");
        EventHandler<ActionEvent> event2 = (ActionEvent e) -> {
            mainForm(applicationStage);
        };
        cancelBtn.setOnAction(event2);
        
        //Add buttons to lower hbox
        lower.getChildren().addAll(saveBtn, cancelBtn);

        //Add all components to root pane
        root.add(upper, 0, 0);
        root.add(infoPane, 0, 1);
        root.add(lower, 0, 2);
        
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        
        //Change the scene
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show(); 
    }

    /**
    * This form allows the user to modify existing outsourced parts. It pre-populates
    * all fields based on the part's current values.
    * <p>
    * FUTURE ENHANCEMENT: Show the difference between the old part info
    * and the new part info, then make the user confirm changes before
    * it saves.
    * <p>
    * LOGIC ERROR: When I tried to change an outsourced part to an in-house
    * part, there wasn't a class modifier I could use to change it over, so
    * to change from outsourced to in-house, I had to add the new part to the
    * list, taking all of the current parts information, then delete the current
    * part to make sure I have no duplicates.
    *
    * @param  applicationStage  the top level container of the GUI
    * @param  curPart           the current part being modified
    * @see partPaneGenerator(Stage applicationStage)
    */
    public void modifyPartForm(Stage applicationStage, Outsourced curPart) {
        GridPane root = new GridPane();
        root.getStyleClass().add("partFormPane");
      
        ///Top H box
        HBox upper = new HBox(50);
        
        //Label
        Label title = new Label("Modify Part");
        
        //Radio Buttons
        final ToggleGroup group = new ToggleGroup();
        RadioButton inHouse = new RadioButton("In-House");
        inHouse.setToggleGroup(group);
        RadioButton outsourced = new RadioButton("Outsourced");
        outsourced.setToggleGroup(group);
        outsourced.setSelected(true);
        upper.getChildren().addAll(title, inHouse, outsourced);
        
        ///GridPane of information
        //Make info gridpane
        GridPane infoPane = new GridPane();
        infoPane.setPadding(new Insets (10,10,10,10));
        infoPane.setVgap(5);
        infoPane.setHgap(15);
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(100);
            infoPane.getColumnConstraints().add(column);
        }
        //Generate all Labels
        Label id = new Label("ID");
        Label name = new Label("Name");
        Label inv = new Label("Inv");
        Label cost = new Label("Price/Cost");
        Label max = new Label("Max");
        Label min = new Label("Min");
        Label machine = new Label("Machine ID");
        Label company = new Label("Company Name");
        
        //Generate all text boxes
        TextField idField = new TextField(Integer.toString(curPart.getId()));
        idField.setDisable(true);
        idField.setMinWidth(150);
        TextField nameField = new TextField(curPart.getName());
        TextField invField = new TextField(Integer.toString(curPart.getStock()));
        TextField costField = new TextField(Double.toString(curPart.getPrice()));
        TextField maxField = new TextField(Integer.toString(curPart.getMax()));
        TextField minField = new TextField(Integer.toString(curPart.getMin()));
        TextField machineField = new TextField(curPart.getCompanyName());
        
        //Add all info to pane
        infoPane.add(id, 0, 0);
        infoPane.add(idField, 1, 0);
        infoPane.add(name, 0, 1);
        infoPane.add(nameField, 1, 1);
        infoPane.add(inv, 0, 2);
        infoPane.add(invField, 1, 2);
        infoPane.add(cost, 0, 3);
        infoPane.add(costField, 1, 3);
        infoPane.add(max, 0, 4);
        infoPane.add(maxField, 1, 4);
        infoPane.add(min, 2, 4);
        infoPane.add(minField, 3, 4);
        infoPane.add(company, 0, 5);
        infoPane.add(machineField, 1, 5);
        
        //Check for inhouse or outsourced change
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
            RadioButton rb = (RadioButton)group.getSelectedToggle();
            
            if (rb != null) {
                String s = rb.getText();
                if (s.equals("In-House")) {
                    infoPane.getChildren().clear();
                    infoPane.add(id, 0, 0);
                    infoPane.add(idField, 1, 0);
                    infoPane.add(name, 0, 1);
                    infoPane.add(nameField, 1, 1);
                    infoPane.add(inv, 0, 2);
                    infoPane.add(invField, 1, 2);
                    infoPane.add(cost, 0, 3);
                    infoPane.add(costField, 1, 3);
                    infoPane.add(max, 0, 4);
                    infoPane.add(maxField, 1, 4);
                    infoPane.add(min, 2, 4);
                    infoPane.add(minField, 3, 4);
                    infoPane.add(machine, 0, 5);
                    infoPane.add(machineField, 1, 5);
                } else {
                    infoPane.getChildren().clear();
                    infoPane.add(id, 0, 0);
                    infoPane.add(idField, 1, 0);
                    infoPane.add(name, 0, 1);
                    infoPane.add(nameField, 1, 1);
                    infoPane.add(inv, 0, 2);
                    infoPane.add(invField, 1, 2);
                    infoPane.add(cost, 0, 3);
                    infoPane.add(costField, 1, 3);
                    infoPane.add(max, 0, 4);
                    infoPane.add(maxField, 1, 4);
                    infoPane.add(min, 2, 4);
                    infoPane.add(minField, 3, 4);
                    infoPane.add(company, 0, 5);
                    infoPane.add(machineField, 1, 5);
                }
            }
        });
        
        //Add the lower box
        HBox lower = new HBox(15);
        lower.setPadding(new Insets(0, 0, 0, 250));
        Button saveBtn = new Button("Save");
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            int tempInv = Integer.parseInt(invField.getText());
            int tempMax = Integer.parseInt(maxField.getText());
            int tempMin = Integer.parseInt(minField.getText());
            if (inHouse.isSelected()) {
                    if (validateInput(costField.getText(), invField.getText(), 
                            minField.getText(), maxField.getText(), machineField.getText())) {
                    updatePart(curPart.getId(), new InHouse(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                    tempInv, tempMin, tempMax, Integer.parseInt(machineField.getText())));
                    mainForm(applicationStage);
                } else {
                        modifyPartForm(applicationStage, curPart);
                    }
            } else if (outsourced.isSelected()) {
                if (validateInput(costField.getText(), invField.getText(), 
                        minField.getText(), maxField.getText(), machineField.getText())) {
                    updatePart(curPart.getId(), new Outsourced(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                            tempInv, tempMin, tempMax, machineField.getText()));
                    mainForm(applicationStage);
                } else {
                    modifyPartForm(applicationStage, curPart);
                }
            }
            
        };
        saveBtn.setOnAction(event);
        Button cancelBtn = new Button("Cancel");
        EventHandler<ActionEvent> event2 = (ActionEvent e) -> {
            mainForm(applicationStage);
        };
        cancelBtn.setOnAction(event2);
        
        //Add buttons to lower hbox
        lower.getChildren().addAll(saveBtn, cancelBtn);

        //Add all components to root pane
        root.add(upper, 0, 0);
        root.add(infoPane, 0, 1);
        root.add(lower, 0, 2);
        
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        
        //Change the scene
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show(); 
    }

    /**
    * This form allows the user to add a new product.
    * <p>
    * This function utilizes partPaneGenerator to generate the
    * list of parts available for the product.
    * <p>
    * FUTURE ENHANCEMENT: Allow the user to add custom fields to a
    * product such as profit margin or retail price vs cost.
    * <p>
    * RUNTIME ERROR: When generating the product pane for the add/modify product form
    * I ran into the issue of not being able to resize the table because I was trying
    * to use the same table that I use for the main form products pane. To solve this, I
    * created a duplicate table, so that I could size it separately. 
    *
    * @param  applicationStage  the top level container of the GUI
    * @see partPaneGenerator(Product p)
    */
    public void addProductForm(Stage applicationStage) {
        //Create main grid panes
        GridPane root = new GridPane();
        root.getStyleClass().add("productFormPane");
        GridPane left = new GridPane();
        left.setMinWidth(390);
        GridPane right = new GridPane();
        
        //Create new product
        Product p = new Product(0, "null", 1, 1.0, 1, 1);
        
        ///Left GridPane
        //Add title
        HBox top = new HBox();
        Label title = new Label("Add Product");
        top.getChildren().add(title);
        left.add(top, 0, 0);
        
        //Add Form
        GridPane leftInner = new GridPane();
        leftInner.setVgap(15);
        leftInner.setHgap(10);
        
        //Labels for form
        Label id = new Label("ID");
        Label name = new Label("Name");
        Label inv = new Label("Inv");
        Label price = new Label("Price");
        Label max = new Label("Max");
        Label min = new Label("Min");
        
        //Text Fields for form
        TextField idField = new TextField("Auto Gen - Disabled");
        idField.setMaxWidth(150);
        idField.setDisable(true);
        TextField nameField = new TextField();
        nameField.setMaxWidth(150);
        TextField invField = new TextField();
        invField.setMaxWidth(100);
        TextField priceField = new TextField();
        priceField.setMaxWidth(100);
        TextField maxField = new TextField();
        maxField.setMaxWidth(100);
        TextField minField = new TextField();
        minField.setMaxWidth(100);
        
        //Add parts to form
        leftInner.add(id, 0, 0);
        leftInner.add(idField, 1, 0);
        leftInner.add(name, 0, 1);
        leftInner.add(nameField, 1, 1);
        leftInner.add(inv, 0, 2);
        leftInner.add(invField, 1,2);
        leftInner.add(price, 0, 3);
        leftInner.add(priceField, 1, 3);
        leftInner.add(max, 0, 4);
        leftInner.add(maxField, 1, 4);
        leftInner.add(min, 2, 4);
        leftInner.add(minField, 3, 4);
        
        //Add form to left side
        left.add(leftInner, 0, 1);
        
        ///Right Side
        //Add parts pane for item selections to upper right
        GridPane partPane = partPaneGenerator(p);
        partPane.getStyleClass().add("addProductTablePane");
        right.add(partPane, 0,0);
        
        //Add pane with current product parts lower right
        GridPane productPane = new GridPane();
        productPane.setVgap(10);
        
        //Populate the Parts Table Columns
        if (subProductsTable == null) {
            subProductsTable = new TableView();
            TableColumn productIDCol = new TableColumn("Part ID");
            productIDCol.setMaxWidth(75);
            productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn productNameCol = new TableColumn("Part Name");
            productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn invLevelCol = new TableColumn("Inventory Level");
            invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            invLevelCol.setMinWidth(100);
            TableColumn priceCol = new TableColumn("Price/ Cost per Unit");
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceCol.setMinWidth(140);
            subProductsTable.setMinSize(400, 150);        
            subProductsTable.getColumns().addAll(productIDCol, productNameCol, invLevelCol, priceCol);
        }
        
        subProductsTable.setItems(p.getAllAssociatedParts());
        
        ///Fill the bottom
        //Create a hbox container
        HBox ubottom = new HBox(10);
        ubottom.setPadding(new Insets(0, 0, 0, 225));
        HBox bbottom = new HBox(50);
        bbottom.setPadding(new Insets(0, 0, 0, 225));
        
        //Create buttoms
        Button removeBtn = new Button("Remove Associated Part");
        EventHandler<ActionEvent> remEvent = (ActionEvent e) -> {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Action");
            confirm.setContentText("Are you sure you want to remove this associated part?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                p.deleteAssociatedPart(subProductsTable.getSelectionModel().getSelectedItem());
            }
        };
        removeBtn.setOnAction(remEvent);
        Button saveBtn = new Button("Save");
        EventHandler<ActionEvent> sveEvent = (ActionEvent e) -> {
            if (validateInput(priceField.getText(), invField.getText(), minField.getText(), maxField.getText())) {
                //Get all data and add it to the new part
                p.setId(curProdID);
                p.setName(nameField.getText());
                p.setStock(Integer.parseInt(invField.getText()));
                p.setPrice(Double.parseDouble(priceField.getText()));
                p.setMax(Integer.parseInt(maxField.getText()));
                p.setMin(Integer.parseInt(minField.getText()));
                addProduct(p);

                curProdID++;
                mainForm(applicationStage);
            } else {
                addProductForm(applicationStage);
            }
            
        };
        saveBtn.setOnAction(sveEvent);
        Button cancelBtn = new Button("Cancel");
        EventHandler<ActionEvent> canEvent = (ActionEvent e) -> {
            mainForm(applicationStage);
        };
        cancelBtn.setOnAction(canEvent);
        
        
        //Add buttons to bottom
        ubottom.getChildren().addAll(removeBtn);
        bbottom.getChildren().addAll(saveBtn, cancelBtn);

        
        //Add all contents to Products Pane
        productPane.add(subProductsTable, 0, 1);
        productPane.add(ubottom, 0, 2);
        productPane.add(bbottom, 0, 3);
        right.add(productPane, 0, 1);

       
        //Add GridPanes to root, then add to scene
        root.add(left, 0, 0);
        root.add(right, 1, 0);
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());

        //Change the scene
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show();
        
        
    }

    /**
    * This form allows the user to modify an existing product.
    * It pre-populates all fields with the current product data.
    * <p>
    * This function utilizes partPaneGenerator to generate the
    * list of parts available for the product.
    * <p>
    * FUTURE ENHANCEMENT: Make the user confirm any changes before saving
    * the changes and returning to the home screen.
    * <p>
    * RUNTIME ERROR: In the table with the current products associated parts, I had
    * trouble adding or removing parts, so I decided for modifying products, that I 
    * wouldn't use the product pane generator function, and would build it separately
    * in the function to allow for greater control.
    *
    * @param  applicationStage  the top level container of the GUI
    * @param  p                 the product being modified
    * @see partPaneGenerator(Product p)
    */
    public void modifyProductForm(Stage applicationStage, Product p) {
        //Create main grid panes
        GridPane root = new GridPane();
        root.getStyleClass().add("productFormPane");
        GridPane left = new GridPane();
        left.setMinWidth(390);
        GridPane right = new GridPane();
        
        ///Left GridPane
        //Add title
        HBox top = new HBox();
        Label title = new Label("Modify Product");
        top.getChildren().add(title);
        left.add(top, 0, 0);
        
        //Add Form
        GridPane leftInner = new GridPane();
        leftInner.setVgap(15);
        leftInner.setHgap(10);
        
        //Labels for form
        Label id = new Label("ID");
        Label name = new Label("Name");
        Label inv = new Label("Inv");
        Label price = new Label("Price");
        Label max = new Label("Max");
        Label min = new Label("Min");
        
        //Text Fields for form
        TextField idField = new TextField(Integer.toString(p.getId()));
        idField.setMaxWidth(150);
        idField.setDisable(true);
        TextField nameField = new TextField(p.getName());
        nameField.setMaxWidth(150);
        TextField invField = new TextField(Integer.toString(p.getStock()));
        invField.setMaxWidth(100);
        TextField priceField = new TextField(Double.toString(p.getPrice()));
        priceField.setMaxWidth(100);
        TextField maxField = new TextField(Integer.toString(p.getMax()));
        maxField.setMaxWidth(100);
        TextField minField = new TextField(Integer.toString(p.getMin()));
        minField.setMaxWidth(100);
        
        //Add parts to form
        leftInner.add(id, 0, 0);
        leftInner.add(idField, 1, 0);
        leftInner.add(name, 0, 1);
        leftInner.add(nameField, 1, 1);
        leftInner.add(inv, 0, 2);
        leftInner.add(invField, 1,2);
        leftInner.add(price, 0, 3);
        leftInner.add(priceField, 1, 3);
        leftInner.add(max, 0, 4);
        leftInner.add(maxField, 1, 4);
        leftInner.add(min, 2, 4);
        leftInner.add(minField, 3, 4);
        
        //Add form to left side
        left.add(leftInner, 0, 1);
        
        ///Right Side
        //Add parts pane for item selections to upper right
        GridPane partPane = partPaneGenerator(p);
        partPane.getStyleClass().add("addProductTablePane");
        right.add(partPane, 0,0);
        
        //Add pane with current product parts lower right
        GridPane productPane = new GridPane();
        productPane.setVgap(10);
        
        //Populate the Parts Table Columns
        if (subProductsTable == null) {
            subProductsTable = new TableView();
            TableColumn productIDCol = new TableColumn("Part ID");
            productIDCol.setMaxWidth(75);
            productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn productNameCol = new TableColumn("Part Name");
            productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn invLevelCol = new TableColumn("Inventory Level");
            invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            invLevelCol.setMinWidth(100);
            TableColumn priceCol = new TableColumn("Price/ Cost per Unit");
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceCol.setMinWidth(140);
            subProductsTable.setMinSize(400, 150);        
            subProductsTable.getColumns().addAll(productIDCol, productNameCol, invLevelCol, priceCol);
        }
        
        subProductsTable.setItems(p.getAllAssociatedParts());
        
        ///Fill the bottom
        //Create a hbox container
        HBox ubottom = new HBox(10);
        ubottom.setPadding(new Insets(0, 0, 0, 225));
        HBox bbottom = new HBox(50);
        bbottom.setPadding(new Insets(0, 0, 0, 225));
        
        //Create buttoms
        Button removeBtn = new Button("Remove Associated Part");
        EventHandler<ActionEvent> remEvent = (ActionEvent e) -> {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Action");
            confirm.setContentText("Are you sure you want to remove this associated part?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                p.deleteAssociatedPart(subProductsTable.getSelectionModel().getSelectedItem());
            }
        };
        removeBtn.setOnAction(remEvent);
        Button saveBtn = new Button("Save");
        EventHandler<ActionEvent> sveEvent = (ActionEvent e) -> {
            if (validateInput(priceField.getText(), invField.getText(), minField.getText(), maxField.getText())) {
                //Get all data and add it to the new part
                p.setName(nameField.getText());
                p.setStock(Integer.parseInt(invField.getText()));
                p.setPrice(Double.parseDouble(priceField.getText()));
                p.setMax(Integer.parseInt(maxField.getText()));
                p.setMin(Integer.parseInt(minField.getText()));
                deleteProduct(p);
                addProduct(p);
                mainForm(applicationStage);
            } else {
                addProductForm(applicationStage);
            }
            
        };
        saveBtn.setOnAction(sveEvent);
        Button cancelBtn = new Button("Cancel");
        EventHandler<ActionEvent> canEvent = (ActionEvent e) -> {
            mainForm(applicationStage);
        };
        cancelBtn.setOnAction(canEvent);
        
        
        //Add buttons to bottom
        ubottom.getChildren().addAll(removeBtn);
        bbottom.getChildren().addAll(saveBtn, cancelBtn);

        
        //Add all contents to Products Pane
        productPane.add(subProductsTable, 0, 1);
        productPane.add(ubottom, 0, 2);
        productPane.add(bbottom, 0, 3);
        right.add(productPane, 0, 1);

       
        //Add GridPanes to root, then add to scene
        root.add(left, 0, 0);
        root.add(right, 1, 0);
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());

        //Change the scene
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show();
    }
    
    /**
    * This function validates the input for in-house parts. It checks
    * that all numeric fields are numeric, and that the inventory
    * is greater than or equal to the min, and less than or equal to the
    * max.
    * <p>
    * If the inventory level is incorrect, it will specifically mention
    * that in the error message. Otherwise, it will tell the user to make
    * sure that the inventory, price, max, and min fields are all numeric.
    * <p>
    * FUTURE ENHANCEMENT: Give a more specific error as to which input threw the
    * exception.
    * <p>
    * RUNTIME ERROR: I originally had it the inventory checking using greater than or equal to
    * and less than or equal to, but I realized that the way I wrote the function,
    * I didn't need the "or equal to" portion, so I dropped it.
    *
    * @param  price         the price string that should convert to a double
    * @param  stock         the inventory string that should convert to an int
    * @param min            the inventory min that should convert to an int
    * @param max            the inventory max that should convert to an int
    * @param machineId      the machine id that should convert to an int
    * @return true if all inputs are valid, false if there is an input not valid
    */
    public boolean validateInput(String price, String stock, String min, String max, String machineId) {
        try {
            double DPrice = Double.parseDouble(price);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify a number has been entered in the PRICE field.");
                error.showAndWait();
            return false;
        }
        try {
            int IStock = Integer.parseInt(stock);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify that a number has been entered in the INV field");
                error.showAndWait();
            return false;
        }
        try {
            int IMin = Integer.parseInt(min);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify that a number has been entered in the MIN field.");
                error.showAndWait();
            return false;
        }
        try {
            int IMax = Integer.parseInt(max);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify that a number has been entered in the MAX field.");
                error.showAndWait();
            return false;
        }
        try {
            int IMachineId = Integer.parseInt(machineId);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify that a number has been entered in the MACHINE ID field.");
                error.showAndWait();
            return false;
        }
            
            if (Integer.parseInt(stock) > Integer.parseInt(max) || Integer.parseInt(stock) < Integer.parseInt(min)) {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Inventory must be larger than min, and smaller than max.");
                error.showAndWait();
                return false;
            } else {
                return true;
            }
    }
    
    /**
    * This function validates the input for outsourced parts and products. It checks
    * that all numeric fields are numeric, and that the inventory
    * is greater than or equal to the min, and less than or equal to the
    * max.
    * <p>
    * If the inventory level is incorrect, it will specifically mention
    * that in the error message. Otherwise, it will tell the user to make
    * sure that the inventory, price, max, and min fields are all numeric.
    * <p>
    * FUTURE ENHANCEMENT: Give a more specific error as to which input threw the
    * exception.
    * <p>
    * RUNTIME ERROR: I originally had it the inventory checking using greater than or equal to
    * and less than or equal to, but I realized that the way I wrote the function,
    * I didn't need the "or equal to" portion, so I dropped it.
    *
    * @param  price         the price string that should convert to a double
    * @param  stock         the inventory string that should convert to an int
    * @param min            the inventory min that should convert to an int
    * @param max            the inventory max that should convert to an int
    * @return true if all inputs are valid, false if there is an input not valid
    */
    public boolean validateInput(String price, String stock, String min, String max) {
        try {
            double DPrice = Double.parseDouble(price);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify a number has been entered in the PRICE field.");
                error.showAndWait();
            return false;
        }
        try {
            int IStock = Integer.parseInt(stock);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify that a number has been entered in the INV field");
                error.showAndWait();
            return false;
        }
        try {
            int IMin = Integer.parseInt(min);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify that a number has been entered in the MIN field.");
                error.showAndWait();
            return false;
        }
        try {
            int IMax = Integer.parseInt(max);
            } catch (Exception e) {
           Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Please verify that a number has been entered in the MAX field.");
                error.showAndWait();
            return false;
        }   
            if (Integer.parseInt(stock) > Integer.parseInt(max) || Integer.parseInt(stock) < Integer.parseInt(min)) {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Inventory must be larger than min, and smaller than max.");
                error.showAndWait();
                return false;
            } else {
                return true;
            }
    }
}
