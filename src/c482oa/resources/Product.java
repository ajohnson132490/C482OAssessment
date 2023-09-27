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
    /**
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @return the stock
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * 
     * @param stock the new stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * 
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * 
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * 
     * @return the max stock
     */
    public int getMax() {
        return max;
    }
    
    /**
     * 
     * @param max the new max stock
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * 
     * @return the min stock
     */
    public int getMin(){
        return min;
    }
    
    /**
     * 
     * @param min the new min stock
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * 
     * @return all associated parts
     */
    public ObservableList<Part> getParts() {
        return parts;
    }
    
    /**
     * 
     * @param parts associated parts list
     */
    public void setParts(ObservableList<Part> parts) {
        this.parts = parts;
    }
    
    /**
     * 
     * @param p new part to add to parts list 
     */
    public void addPart(Part p) {
        parts.add(p);
    }
    
    /**
     * 
     * @param p part to remove from parts list
     */
    public void removePart(Part p) {
        parts.remove(p);
    }
    
    
    
}
