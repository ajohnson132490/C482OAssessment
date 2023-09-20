package c482oa;

import c482oa.resources.InHouse;
import c482oa.resources.Outsourced;
import c482oa.resources.Part;
import javafx.scene.control.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
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
    int curID = 0;
    
    //Private Tables
    private TableView partsTable;
    private TableView productsTable = new TableView();
    
    @Override
    public void start(Stage applicationStage) { 
        applicationStage.setTitle("Inventory Management System"); // Set window's title
        addPartForm(applicationStage);
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
        
        //Fill the middle
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
        priceCol.setMinWidth(145);
        partsTable.setMinSize(400, 150);        
        partsTable.getColumns().addAll(partIDCol, partNameCol, invLevelCol, priceCol);
        }
        
        
        //TODO
        /*
        *
        * Add data from a global list of parts to the table
        *
        */
        
        partsTable.setItems(partList);
        
        
        ///Fill the bottom
        //Create a hbox container
        HBox bottom = new HBox(10);
        bottom.setPadding(new Insets(0, 0, 0, 225));
        
        //Create buttoms
        Button addBtn = new Button("Add");
        Button modifyBtn = new Button("Modify");
        Button deleteBtn = new Button("Delete");
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            addPartForm(applicationStage);
        };
        addBtn.setOnAction(event);
        
        
        //Add buttons to bottom
        bottom.getChildren().addAll(addBtn, modifyBtn, deleteBtn);
        
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
        
        //Fill the middle
        //Populate the Products Table Columns
        TableColumn partIDCol = new TableColumn("Part ID");
        partIDCol.setMaxWidth(75);
        TableColumn partNameCol = new TableColumn("Product Name");
        TableColumn invLevelCol = new TableColumn("Inventory Level");
        invLevelCol.setMinWidth(100);
        TableColumn priceCol = new TableColumn("Price/ Cost per Unit");
        priceCol.setMinWidth(150);
        productsTable.setMinSize(400, 150);        
        productsTable.getColumns().addAll(partIDCol, partNameCol, invLevelCol, priceCol);
        
        //TODO
        /*
        *
        * Add data from a global list of products to the table
        *
        */
        
        ///Fill the bottom
        //Create a hbox container
        HBox bottom = new HBox(10);
        bottom.setPadding(new Insets(0, 0, 0, 225));
        
        //Create buttoms
        Button addBtn = new Button("Add");
        Button modifyBtn = new Button("Modify");
        Button deleteBtn = new Button("Delete");
        
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
            column.setMinWidth(75);
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
        
        //
        HBox lower = new HBox(15);
        lower.setPadding(new Insets(0, 0, 0, 250));
        Button saveBtn = new Button("Save");
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            if (inHouse.isSelected()) {
                partList.add(new InHouse(curID, nameField.getText(), Double.parseDouble(costField.getText()),
                Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText())));
                curID++;
                mainForm(applicationStage);
            } else if (outsourced.isSelected()) {
                partList.add(new Outsourced(curID, nameField.getText(), Double.parseDouble(costField.getText()),
                Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText())));
                curID++;
                mainForm(applicationStage);
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
}
