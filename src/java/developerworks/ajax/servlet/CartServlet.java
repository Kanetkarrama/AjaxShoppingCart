
package developerworks.ajax.servlet;

import developerworks.ajax.store.Cart;
import javax.servlet.http.*;

import java.util.Enumeration;

public class CartServlet extends HttpServlet {

  /**
   * Updates Cart, and outputs XML representation of contents
   */
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {

    Enumeration headers = req.getHeaderNames();
    while (headers.hasMoreElements()) {
      String header  =(String) headers.nextElement();
      System.out.println(header+": "+req.getHeader(header));
    }// will let us know if the server is connected. Gives particular print statetments on the netbeans console

    Cart cart = getCartFromSession(req);

    String action = req.getParameter("action");
    String item = req.getParameter("item");
    //capture actions performed on the catalog and store the item and action values in item and action string respectivey
    if ((action != null)&&(item != null)) {

      if ("add".equals(action)) {
        cart.addItem(item);

      } else if ("remove".equals(action)) {
        cart.removeItems(item);

      }
    }

    String cartXml = cart.toXml(); //uses toXML method from cart.java to convert java object into xml
    res.setContentType("text/xml"); // specifying that return file in in xml
    res.getWriter().write(cartXml);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
    // Bounce to post, for debugging use
    // Hit this servlet directly from the browser to see XML
    doPost(req,res);
  }

  private Cart getCartFromSession(HttpServletRequest req) {
 // check if cart was created for the particular user
    HttpSession session = req.getSession(true);
    Cart cart = (Cart)session.getAttribute("cart");
   
    if (cart == null) {// if no cart for the user, new cart gets created
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    return cart;
  }
}
