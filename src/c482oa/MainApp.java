package c482oa;

import c482oa.resources.InHouse;
import c482oa.resources.Outsourced;
import c482oa.resources.Part;
import c482oa.resources.Product;
import javafx.scene.control.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
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

/**
 *
 * @author austinjohnson
 */
public class MainApp extends Application {
    //Global lists and variables
    ObservableList<Part> partList = FXCollections.observableArrayList();
    ObservableList<Product> productList = FXCollections.observableArrayList();
    int curID = 1;
    
    //Private Tables
    private TableView<Part> partsTable;
    private TableView<Product> productsTable;
    
    @Override
    public void start(Stage applicationStage) { 
        applicationStage.setTitle("Inventory Management System"); // Set window's title
        mainForm(applicationStage);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    ///Main Form
    //Generates the main page
    void mainForm(Stage applicationStage) {
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
        
        root.add(partPane, 0,1);
        root.add(productPane, 1, 1);
        
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show(); 
    }
    
    //Helper functions for mainForm
    public GridPane partPaneGenerator(Stage applicationStage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        
        ///Fill the top
        //Create a hbox container
        HBox top = new HBox(200);
        
        //Create the Label
        Label title = new Label("Parts");
        
        //Create search box
        TextField search = new TextField();
        search.setPromptText("Search by Part ID or Name");
        search.setFocusTraversable(false);
        
        //TODO
        /*
        *
        * Make search work
        *
        */
        
        //Add items to top
        top.getChildren().addAll(title, search);
        
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
        
        partsTable.setItems(partList);
        
        
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
            Part p = partsTable.getSelectionModel().getSelectedItem();
            if (p instanceof InHouse) {
                modifyPartForm(applicationStage, (InHouse) p);
            } else if (p instanceof Outsourced) {
                modifyPartForm(applicationStage, (Outsourced) p);
            }

        };
        modifyBtn.setOnAction(modEvent);
        Button deleteBtn = new Button("Delete");
        EventHandler<ActionEvent> delEvent = (ActionEvent e) -> {
            Part p = partsTable.getSelectionModel().getSelectedItem();
            int i = partList.indexOf(p);
            partList.remove(i, i+1);

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
    public GridPane partPaneGenerator() {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        
        ///Fill the top
        //Create a hbox container
        HBox top = new HBox(200);
        
        //Create the Label
        Label title = new Label("Parts");
        
        //Create search box
        TextField search = new TextField();
        search.setPromptText("Search by Part ID or Name");
        search.setFocusTraversable(false);
        
        //TODO
        /*
        *
        * Make search work
        *
        */
        
        //Add items to top
        top.getChildren().addAll(title, search);
        
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
        
        partsTable.setItems(partList);
        
        
        ///Fill the bottom
        //Create a hbox container
        HBox bottom = new HBox(10);
        bottom.setPadding(new Insets(0, 0, 0, 225));
        
        //Create buttoms
        Button addBtn = new Button("Add");
        EventHandler<ActionEvent> addEvent = (ActionEvent e) -> {
        };
        addBtn.setOnAction(addEvent);
        
        //Add buttons to bottom
        bottom.getChildren().add(addBtn);
        
        //Add all contents to Parts Pane
        pane.add(top, 0, 0);
        pane.add(partsTable, 0, 1);
        pane.add(bottom, 0, 2);
        
        //Return the Parts Pane
        return pane;
    }

    public GridPane productPaneGenerator(Stage applicationStage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        
        ///Fill the top
        //Create a hbox container
        HBox top = new HBox(180);
        
        //Create the Label
        Label title = new Label("Products");
        
        //Create search box
        TextField search = new TextField();
        search.setPromptText("Search by Part ID or Name");
        search.setFocusTraversable(false);
        
        //TODO
        /*
        *
        * Make search work
        *
        */
        
        //Add items to top
        top.getChildren().addAll(title, search);
        
        ///Fill the middle
        //Populate the Parts Table Columns
        if (productsTable == null) {
            productsTable = new TableView();
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
            productsTable.setMinSize(400, 150);        
            productsTable.getColumns().addAll(productIDCol, productNameCol, invLevelCol, priceCol);
        }
        
        productsTable.setItems(productList);
        
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
            Product p = productsTable.getSelectionModel().getSelectedItem();
            //modifyProductForm(applicationStage, p);

        };
        modifyBtn.setOnAction(modEvent);
        Button deleteBtn = new Button("Delete");
        EventHandler<ActionEvent> delEvent = (ActionEvent e) -> {
            Product p = productsTable.getSelectionModel().getSelectedItem();
            int i = productList.indexOf(p);
            productList.remove(i, i+1);

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
    
    ///Parts Forms
    //Add Part Form
    void addPartForm(Stage applicationStage) {
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
        idField.setEditable(false);
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
            int tempInv = Integer.parseInt(invField.getText());
            int tempMax = Integer.parseInt(maxField.getText());
            int tempMin = Integer.parseInt(minField.getText());
            if (tempMax > tempInv && tempInv > tempMin) {
                if (inHouse.isSelected()) {
                    partList.add(new InHouse(curID, nameField.getText(), Double.parseDouble(costField.getText()),
                    tempInv, tempMin, tempMax, Integer.parseInt(machineField.getText())));
                    curID++;
                    mainForm(applicationStage);
                } else if (outsourced.isSelected()) {
                    partList.add(new Outsourced(curID, nameField.getText(), Double.parseDouble(costField.getText()),
                    tempInv, tempMin, tempMax, machineField.getText()));
                    curID++;
                    mainForm(applicationStage);
                }
            } else {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Inventory must be larger than min, and smaller than max.");
                error.showAndWait();
                addPartForm(applicationStage);
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
    void modifyPartForm(Stage applicationStage, InHouse curPart) {
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
        idField.setEditable(false);
        idField.setMinWidth(150);
        TextField nameField = new TextField(curPart.getName());
        TextField invField = new TextField(Integer.toString(curPart.getStock()));
        TextField costField = new TextField(Double.toString(curPart.getPrice()));
        TextField maxField = new TextField(Integer.toString(curPart.getMax()));
        TextField minField = new TextField(Integer.toString(curPart.getMin()));
        TextField machineField = new TextField(Integer.toString(curPart.getMachineID()));
        
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
            int tempInv = Integer.parseInt(invField.getText());
            int tempMax = Integer.parseInt(maxField.getText());
            int tempMin = Integer.parseInt(minField.getText());
            if (tempMax > tempInv && tempInv > tempMin) {
                if (inHouse.isSelected()) {
                    partList.add(new InHouse(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                    tempInv, tempMin, tempMax, Integer.parseInt(machineField.getText())));
                    partList.remove(curPart);
                    mainForm(applicationStage);
                } else if (outsourced.isSelected()) {
                    partList.add(new Outsourced(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                    tempInv, tempMin, tempMax, machineField.getText()));
                    partList.remove(curPart);
                    mainForm(applicationStage);
                }
            } else {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Inventory must be larger than min, and smaller than max.");
                error.showAndWait();
                modifyPartForm(applicationStage, curPart);
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
    void modifyPartForm(Stage applicationStage, Outsourced curPart) {
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
        idField.setEditable(false);
        idField.setMinWidth(150);
        TextField nameField = new TextField(curPart.getName());
        TextField invField = new TextField(Integer.toString(curPart.getStock()));
        TextField costField = new TextField(Double.toString(curPart.getPrice()));
        TextField maxField = new TextField(Integer.toString(curPart.getMax()));
        TextField minField = new TextField(Integer.toString(curPart.getMin()));
        TextField machineField = new TextField(curPart.getCompany());
        
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
            int tempInv = Integer.parseInt(invField.getText());
            int tempMax = Integer.parseInt(maxField.getText());
            int tempMin = Integer.parseInt(minField.getText());
            if (tempMax > tempInv && tempInv > tempMin) {
                if (inHouse.isSelected()) {
                    partList.add(new InHouse(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                    tempInv, tempMin, tempMax, Integer.parseInt(machineField.getText())));
                    partList.remove(curPart);
                    mainForm(applicationStage);
                } else if (outsourced.isSelected()) {
                    partList.add(new Outsourced(curPart.getId(), nameField.getText(), Double.parseDouble(costField.getText()),
                    tempInv, tempMin, tempMax, machineField.getText()));
                    partList.remove(curPart);
                    mainForm(applicationStage);
                }
            } else {
                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Input not valid");
                error.setContentText("Inventory must be larger than min, and smaller than max.");
                error.showAndWait();
                modifyPartForm(applicationStage, curPart);
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

    ///Products Forms
    void addProductForm(Stage applicationStage) {
        GridPane root = new GridPane();
        root.getStyleClass().add("productFormPane");
        GridPane left = new GridPane();
        GridPane right = new GridPane();
        
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
        
        Label id = new Label("ID");
        Label name = new Label("Name");
        Label inv = new Label("Inv");
        Label price = new Label("Price");
        Label max = new Label("Max");
        Label min = new Label("Min");
        
        TextField idField = new TextField("Auto Gen - Disabled");
        idField.setEditable(false);
        TextField nameField = new TextField();
        TextField invField = new TextField();
        TextField priceField = new TextField();
        TextField maxField = new TextField();
        TextField minField = new TextField();
        
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
        
        
        left.add(leftInner, 0, 1);
        
        GridPane partPane = partPaneGenerator();
        partPane.getStyleClass().add("addProductTablePane");
        right.add(partPane, 0,0);
       
        //Add GridPanes to root, then add to scene
        root.add(left, 0, 0);
        root.add(right, 1, 0);
        root.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());

        //Change the scene
        Scene tmp = new Scene(root);
        applicationStage.setScene(tmp);
        applicationStage.show();
        
        
    }
}
