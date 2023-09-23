/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c482oa.resources;

import c482oa.resources.Part;

/**
 *
 * @author austinjohnson
 */
public class Outsourced extends Part {
    String company;
    
    public Outsourced(int id, String name, double price, int stock, int min, int max, String company) {
        super(id, name, price, stock, min, max);
        this.company = company;
    }
    
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
}
