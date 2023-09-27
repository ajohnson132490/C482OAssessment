/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c482oa.resources;

/**
 *
 * @author austinjohnson
 */
public class InHouse extends Part {
    private int machineID;
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }
    
    /**
     * 
     * @return machine id
     */
    public int getMachineID() {
        return this.machineID;
    }
    
    /**
     * 
     * @param machineID new machine id
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
}
