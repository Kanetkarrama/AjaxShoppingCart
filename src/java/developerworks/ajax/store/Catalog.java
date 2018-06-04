/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package developerworks.ajax.store;

/**
 * This is the catalog class which display all the catalog items from which we can select item to add into out cart
 * @author Rama
 */
import developerworks.ajax.store.Item;
import java.util.*;

public class Catalog {

  private static Map<String,Item> items;

  static {//static runs one time for each class, so runs once for catalog 
   items = new HashMap<String,Item>(); 
   items.put("hat001",new Item("hat001","Hat","Stylish bowler hat (SALE!)",1999));
   items.put("dog001",new Item("dog001","Dog","Chocolate labrador puppy",7999));
   items.put("sou001",new Item("sou001","Soup","Can of tasty tomato soup",199));
   items.put("cha001",new Item("cha001","Chair","Swivelling office chair", 4999));
   items.put("str001",new Item("str001","String","Metric tonne of bailing twine", 1999));
   items.put("qua001",new Item("qua001","Quark","Everyone's favorite sub-atomic particle", 49));
  }

  public Collection<Item> getAllItems() {
    return items.values();
  }

  public boolean containsItem(String itemCode) {
    return items.containsKey(itemCode);
  }

  public Item getItem(String itemCode) {
    return items.get(itemCode);
  }

}

