package c482oa;

import javafx.scene.control.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;




/**
 *
 * @author austinjohnson
 */
public class MainApp extends Application {
    //Private Tables
    private TableView partsTable = new TableView();
    private TableView productsTable = new TableView();
    @Override
    public void start(Stage applicationStage) {
        //Create Root Pane
        GridPane root = new GridPane();
        root.getStyleClass().add("mainFormPane");
      
        //Add a label to the root pane
        Label title = new Label("Inventory Management System");
        root.getChildren().add(title);
        
        //Parts Table
        GridPane partPane = partPaneGenerator();                   // Create an empty pane   
        partPane.getStyleClass().add("mainFormTablePane");
        
        //Products Table
        GridPane productPane = productPaneGenerator();                   // Create an empty pane 
        productPane.getStyleClass().add("mainFormTablePane");
      
        //Create the Main Form Scene
        Scene scene = new Scene(root); // Create scene containing the grid pane
        scene.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        root.add(partPane, 0,1);
        root.add(productPane, 1, 1);
        
        //Get the window to show
        applicationStage.setScene(scene);    // Set window's scene  
        applicationStage.setTitle("Inventory Management System"); // Set window's title
        applicationStage.show();             // Display window
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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
        
        //Add items to top
        top.getChildren().addAll(title, search);
        
        //Fill the middle
        //Populate the Parts Table Columns
        TableColumn partIDCol = new TableColumn("Part ID");
        partIDCol.setMaxWidth(75);
        TableColumn partNameCol = new TableColumn("Part Name");
        TableColumn invLevelCol = new TableColumn("Inventory Level");
        invLevelCol.setMinWidth(100);
        TableColumn priceCol = new TableColumn("Price/ Cost per Unit");
        priceCol.setMinWidth(150);
        partsTable.setMinSize(400, 150);        
        partsTable.getColumns().addAll(partIDCol, partNameCol, invLevelCol, priceCol);
        
        //TODO
        /*
        *
        * Add data from a global list of parts to the table
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
        
        //Add all contents to Parts Pane
        pane.add(top, 0, 0);
        pane.add(partsTable, 0, 1);
        pane.add(bottom, 0, 2);
        
        //Return the Parts Pane
        return pane;
    }
    
    public GridPane productPaneGenerator() {
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
        
        //Return the Parts Pane
        return pane;
    }
}
