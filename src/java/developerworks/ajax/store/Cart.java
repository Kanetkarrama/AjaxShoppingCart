/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package developerworks.ajax.store;

/**
 * Java object that lives on the server
 * @author Rama
 */
import java.math.BigDecimal;
import java.util.*;
 
/**
 * A very simple shopping Cart
 */
public class Cart {

  private HashMap<Item,Integer> contents;

  /**
   * Creates a new Cart instance
   */
  public Cart() {
    contents = new HashMap<Item,Integer>();
  }

  /**
   * Adds a named item to the cart
   * @param itemName The name of the item to add to the cart
   */
  public void addItem(String itemCode) {

    Catalog catalog = new Catalog();

    if (catalog.containsItem(itemCode)) { // check if item is present in the catalog
      Item item = catalog.getItem(itemCode);

      int newQuantity = 1;
      if (contents.containsKey(item)) {
        Integer currentQuantity = contents.get(item);
        newQuantity += currentQuantity.intValue();
      } // if item is already in the cart. Increment the item count

      contents.put(item, new Integer(newQuantity)); // add new item
    }
  }

  /**
   * Removes the named item from the cart
   * @param itemName Name of item to remove
   */
  public void removeItems(String itemCode) {

    contents.remove(new Catalog().getItem(itemCode));
  }

  /**
   * @return XML representation of cart contents
   */
  public String toXml() {// convert the java object of contents to xml. Here this is done manually. This can be acheived by importing xml libraries.
    StringBuffer xml = new StringBuffer();
    xml.append("<?xml version=\"1.0\"?>\n");
    xml.append("<cart generated=\""+System.currentTimeMillis()+"\" total=\""+getCartTotal()+"\">\n");
    // generated tag is used in updateCart to update the latest changes on the cart
    for (Iterator<Item> I = contents.keySet().iterator() ; I.hasNext() ; ) {
      Item item = I.next();
      int itemQuantity = contents.get(item).intValue();

      xml.append("<item code=\""+item.getCode()+"\">\n");
      xml.append("<name>");
      xml.append(item.getName());
      xml.append("</name>\n");
      xml.append("<quantity>");
      xml.append(itemQuantity);
      xml.append("</quantity>\n");
      xml.append("</item>\n");
    }
    
    xml.append("</cart>\n");
    return xml.toString();
  }// these tags are created systematically and are used in cart.js to modify cart data without refreshing the whole page

  private String getCartTotal() {
    int total = 0;

    for (Iterator<Item> I = contents.keySet().iterator() ; I.hasNext() ; ) {
      Item item = I.next();
      int itemQuantity = contents.get(item).intValue();

      total += (item.getPrice() * itemQuantity);
    }

    return "$"+new BigDecimal(total).movePointLeft(2);
  }
}
