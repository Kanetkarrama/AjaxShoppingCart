/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package developerworks.ajax.store;

/**
 * This is an item class which stores code, name, description and price for each item
 * @author Rama
 */
import java.math.BigDecimal;

public class Item {
  private String code;
  private String name;
  private String description;
  private int price;

  public Item(String code,String name,String description,int price) {
    this.code=code;
    this.name=name;
    this.description=description;
    this.price=price;
  }
// getter methods for attributes of item class
  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getPrice() {
    return price;
  }

  public String getFormattedPrice() {
    return "$"+new BigDecimal(price).movePointLeft(2);
  }
  @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (this == null) return false;
    if (!(o instanceof Item)) return false;
    return ((Item)o).getCode().equals(this.code);
  }
}

