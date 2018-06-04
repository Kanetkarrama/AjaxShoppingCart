/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Timestamp of cart that page was last updated with
var lastCartUpdate = 0;

/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function addToCart(itemCode) {

 var req = newXMLHttpRequest();

 req.onreadystatechange = getReadyStateHandler(req, updateCart);
 // calls the update method so that the cart total and rest values are also updated once an element in added in the cart
 req.open("POST", "cart.do", true);
 //cart.do is an association with the web browser. This is handled in web.xml
 req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 req.send("action=add&item="+itemCode); // action starting with add is captured in servlet which called the add method defined in cart class. This add the content in cart
}


/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
function updateCart(cartXML) {
 var cart = cartXML.getElementsByTagName("cart")[0];
 var generated = cart.getAttribute("generated");
 if (generated > lastCartUpdate) {// checks if the cart is in sync with the latest changes on the cart
   lastCartUpdate = generated;
   var contents = document.getElementById("contents");
   contents.innerHTML = "";

   var items = cart.getElementsByTagName("item");
   for (var I = 0 ; I < items.length ; I++) {

     var item = items[I];
     var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
     var quantity = item.getElementsByTagName("quantity")[0].firstChild.nodeValue;

     var listItem = document.createElement("li");
     listItem.appendChild(document.createTextNode(name+" x "+quantity));
     contents.appendChild(listItem);
   }

 }

 document.getElementById("total").innerHTML = cart.getAttribute("total");
 // this will only update the html with id = "total"
}


