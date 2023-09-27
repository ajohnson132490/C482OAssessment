/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c482oa.resources;

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
    
    /**
     * 
     * @return company name
     */
    public String getCompany() {
        return this.company;
    }
    
    /**
     * 
     * @param company new company name
     */
    public void setCompany(String company) {
        this.company = company;
    }
    
}
