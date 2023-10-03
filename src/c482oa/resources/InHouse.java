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
    private int machineId;
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    /**
     * 
     * @return machine id
     */
    public int getMachineId() {
        return this.machineId;
    }
    
    /**
     * 
     * @param machineId new machine id
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
}
