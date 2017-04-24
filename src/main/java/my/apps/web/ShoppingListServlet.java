package my.apps.web;

import my.apps.db.CRUDOperations;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shoppingList")
public class ShoppingListServlet extends HttpServlet {

    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String SUMMARY_ACTION = "summary";
    private static final String REMOVE_ACTION = "remove";

    List<Product> products = new ArrayList<>();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        System.out.println("shopping action " + action);

        if (action != null && action.equals(LIST_ACTION)) {
            listAction(request, response);
        } else if (action != null && action.equals(ADD_ACTION)) {
            addAction(request, response);
        } else if (action != null && action.equals(SUMMARY_ACTION)) {
            summaryAction(request, response);
        }else if (action != null && action.equals(REMOVE_ACTION)) {
            removeAction(request, response);
        } else {
            listAction(request, response);
        }
    }

    private void addAction(HttpServletRequest request, HttpServletResponse response) {
        String produs = request.getParameter("produs").trim();
        String cantitate = request.getParameter("cantitate").trim();
        String persoana = request.getParameter("persoana").trim();

        Product product = new Product(produs, Integer.parseInt(cantitate), persoana);

        try {
            CRUDOperations.writeShoppingItem(product);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/shopping-list.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listAction(HttpServletRequest request, HttpServletResponse response) {
        String jsonResponse = "[";

        try {
            products = CRUDOperations.getShoppingItems();
            System.out.println("ITEM: " + products);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < products.size(); i++) {
            jsonResponse += products.get(i).toJson();
            if (i < products.size() - 1) {
                jsonResponse += ",";
            }
        }
        jsonResponse += "]";
        returnJsonResponse(response, jsonResponse);
    }

    private void removeAction(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        CRUDOperations.removeItem(Integer.parseInt(id));

        try {
            response.sendRedirect("/shopping-list.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void summaryAction(HttpServletRequest request, HttpServletResponse response) {
        String jsonResponse = "[";

        List<String> persons = CRUDOperations.getAllPersons();

        System.out.println("persons:" + persons.toString());

        for (int i = 0; i < persons.size(); i++) {
            String person = persons.get(i);
            double total = CRUDOperations.totalComanda(person);
            jsonResponse += "{\"nume\": \"" + person + "\", \"total\": " + total + "}";
            if (i < persons.size() - 1) {
                jsonResponse += ",";
            }
        }

        jsonResponse += "]";
        returnJsonResponse(response, jsonResponse);
    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}