/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c482oa.resources;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author austinjohnson
 */
public class Product {
    private int id;
    private String name;
    private int stock;
    private double price;
    private int max;
    private int min;
    private ObservableList<Part> parts = FXCollections.observableArrayList();
    
    public Product(int id, String name, int stock, double price, int max, int min,
            ObservableList<Part> parts) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.max = max;
        this.min = min;
        this.parts = parts;
    }
    
    public Product() {
        this.id = 0;
        this.name = "null";
        this.stock = 1;
        this.price = 0;
        this.max = 1;
        this.min = 0;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    
    public int getMin(){
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    
    public ObservableList<Part> getParts() {
        return parts;
    }
    public void setParts(ObservableList<Part> parts) {
        this.parts = parts;
    }
    public void addPart(Part p) {
        parts.add(p);
    }
    public void removePart(Part p) {
        parts.remove(p);
    }
    
    
    
}
