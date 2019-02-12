package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import Objects.CommentaryTurn;
import Objects.SummaryDay;
import Objects.Incident;
import Objects.SummaryMonth;
import Objects.SummaryYear;
import Objects.Product;
import Objects.User;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConnectionDataBase {
    
    private static final String URL = "jdbc:derby://localhost:1527//Proyecto";
    private static final String USERNAME = "proyecto";
    private static final String PASSWORD = "123";
    
    private Connection connection;
   
    private PreparedStatement dataUsers;
    private PreparedStatement eventsUser;
    private PreparedStatement commentaryUser;
   
    private PreparedStatement summaryMonth;
    private PreparedStatement summaryYear;
    private PreparedStatement summaryDay;
        
    public ConnectionDataBase(){
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch(SQLException sqlException){
            System.out.println(sqlException);
            System.exit(1);
        }
    }
    
    public void searchDataUserOfTheTurn(String turn){
        try {
            dataUsers = connection.prepareStatement(
                    "SELECT Users.id_user,Users.turn_user, Users.surnames_user, Users.name_user, Users.dni_user, Users.address_user, Users.phone_user, Users.login_user, Users.password_user\n" +
                    "FROM Users WHERE Users.turn_user = '"+turn+"' AND Users.status_user = 'active'") ;
        
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User dataUserFound(){
        try (ResultSet resultSet = dataUsers.executeQuery()) {
            List<User> resultsSelectUser = new ArrayList<>();
            while (resultSet.next()) {
                resultsSelectUser.add(new User(
                        resultSet.getInt("id_user"),
                        resultSet.getString("turn_user"),
                        resultSet.getString("surnames_user"),
                        resultSet.getString("name_user"),
                        resultSet.getString("dni_user"),
                        resultSet.getString("address_user"),
                        resultSet.getString("phone_user"),
                        resultSet.getString("login_user"),
                        resultSet.getString("password_user")));
            }
            User user;
            if(resultsSelectUser.isEmpty()){
                user = new User(0,"No Data","No Data","No Data","No Data","No Data","No Data","Usuario","Usuario");
            }
            else{
                user = resultsSelectUser.get(0);
            }
            return user;
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return null;
    }
    
    public void incidentUserTurn(String turn, String date){
        try {
            eventsUser = connection.prepareStatement(
            "SELECT products.name_product, incidents.cost_incident\n " +
            "FROM Users INNER JOIN (Products INNER JOIN Incidents ON Products.id_product = Incidents.id_product) ON Users.id_user = Incidents.id_user \n" +
            "WHERE users.turn_user = '"+turn+"' AND users.status_user = 'active' AND incidents.date_incident = '"+date+"'");
        
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Incident> eventsTurnTable(){
        try(ResultSet resultGasto = eventsUser.executeQuery()){
            List<Incident> eventsTurnList = new ArrayList<>();
            while (resultGasto.next()){
                eventsTurnList.add(new Incident(
                    resultGasto.getString("name_product"),
                    resultGasto.getFloat("cost_incident")));
                }
            if(eventsTurnList.isEmpty()){
                eventsTurnList.add(new Incident("",0));
            }
            return eventsTurnList;
        } 
        catch(SQLException sqlException){
            System.out.println(sqlException);
        }
        return null;
    }
    
    public void commentaryUser(String turn,String date){
        try {
            commentaryUser = connection.prepareStatement(
                    "SELECT commentary_incident "+
                    "FROM Incidents INNER JOIN Users ON Incidents.id_user = Users.id_user "+
                    "WHERE incidents.type_incident = 'comment' AND users.status_user = 'active' "+
                    "AND users.turn_user = '"+turn+"' AND incidents.date_incident = '"+date+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<CommentaryTurn> commentaryTurnTable(){
        try(ResultSet resultComentario = commentaryUser.executeQuery()){
            List<CommentaryTurn> commentaryTurnList = new ArrayList<>();
            while (resultComentario.next()){
                commentaryTurnList.add(new CommentaryTurn(resultComentario.getString("commentary_incident")));
            }
            if(commentaryTurnList.isEmpty()){
                commentaryTurnList.add(new CommentaryTurn(""));
            }
            return commentaryTurnList;
        } 
        catch(SQLException sqlException){
            System.out.println(sqlException);
        }
        return null;
    }
    
    public int verifyProduct(String name_product){
        try {
            PreparedStatement verefyProduct = connection.prepareStatement("SELECT id_product, name_product FROM products WHERE name_product = '"+name_product+"'");
            
            ResultSet resultProduct = verefyProduct.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultProduct.next()){
                productList.add(new Product(
                resultProduct.getInt("id_product"),
                resultProduct.getString("name_product")));
            }
            
            if(!productList.isEmpty()){
                Product product = productList.get(0);
                return product.getId_product();
            }
            else{
                PreparedStatement newProduct = connection.prepareStatement("INSERT INTO products(name_product) VALUES (?)");
                newProduct.setString(1,name_product);
                newProduct.executeUpdate();
                verefyProduct = connection.prepareStatement("SELECT id_product, name_product FROM products WHERE name_product = '"+name_product+"'");
                ResultSet resultProductAux = verefyProduct.executeQuery();
                List<Product> productListAux = new ArrayList<>();
                while(resultProductAux.next()){
                    productListAux.add(new Product(
                    resultProductAux.getInt("id_product"),
                    resultProductAux.getString("name_product")));
                }
                Product productAux = productListAux.get(0);
                return productAux.getId_product();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void insertEvent(int id_user,String date_incident,String name_product,float cost_incident){
        int id_product = verifyProduct(name_product);
        try {
            PreparedStatement newEventUser = connection.prepareStatement("INSERT INTO incidents(id_user,id_product,date_incident,type_incident,cost_incident) VALUES (?,?,?,?,?)");
            newEventUser.setInt(1,id_user);
            newEventUser.setInt(2,id_product);
            newEventUser.setString(3,date_incident);
            newEventUser.setString(4,"event");
            newEventUser.setFloat(5,cost_incident);

            if (newEventUser.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Los datos han sido ingresados con éxito", "Operación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido realizar la ingresar de los datos\n"
                        + "Inténtelo nuevamente.", "Error en la operación",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido realizar la actualización de los datos\n"
                    + "Inténtelo nuevamente.\n"
                    + "Error: " + e, "Error en la operación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public List<String> productsList(){
        try {
            PreparedStatement productsData = connection.prepareStatement("SELECT name_product FROM products");
            ResultSet resultSetproduct = productsData.executeQuery();
            List<String> productList = new ArrayList<>();
                while(resultSetproduct.next()){
                    productList.add(resultSetproduct.getString("name_product"));
                }
            return productList;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void insertComment(int id_user,String date_incident,String commentay_incident) {
        try {
            PreparedStatement newEventUser = connection.prepareStatement("INSERT INTO incidents(id_user,date_incident,type_incident,commentary_incident) VALUES (?,?,?,?)");
            newEventUser.setInt(1,id_user);
            newEventUser.setString(2,date_incident);
            newEventUser.setString(3,"comment");
            newEventUser.setString(4,commentay_incident);

            if (newEventUser.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Los datos han sido ingresados con éxito", "Operación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido realizar la ingresar de los datos\n"
                        + "Inténtelo nuevamente.", "Error en la operación",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido realizar la actualización de los datos\n"
                    + "Inténtelo nuevamente.\n"
                    + "Error: " + e, "Error en la operación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Float costTurnUser(int id_user,String date_incident){
        try {
            PreparedStatement costTurnUser = connection.prepareStatement("SELECT SUM(cost_incident) cost_turn FROM incidents \n" +
            "WHERE incidents.id_user = "+id_user+" AND incidents.DATE_INCIDENT = '"+date_incident+"'");
            ResultSet resultCostTurn = costTurnUser.executeQuery();
            List<Float> costList = new ArrayList<>();
            while(resultCostTurn.next()){
                costList.add(resultCostTurn.getFloat("cost_turn"));
            }
            if(!costList.isEmpty()){
                return costList.get(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } 
        float empty = 0;
        return empty;
    }
    
    public void editDataUser(int id_user,String name_user,String surname_user,String dni_user,String address_user,String phone_user){
        try {
            PreparedStatement upgradeDataUser = connection.prepareStatement("UPDATE users SET name_user = '"+name_user+
                    "', surnames_user = '"+surname_user+
                    "', dni_user = '"+dni_user+
                    "', address_user = '"+address_user+
                    "', phone_user = '"+phone_user+"' WHERE id_user = "+id_user+"");
            upgradeDataUser.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editPasswordUser(int id_user, String new_password_user){
        try {
            PreparedStatement upgradePasswordUser = connection.prepareStatement("UPDATE users SET password_user = '"+new_password_user+"' WHERE id_user = "+id_user+"");
            upgradePasswordUser.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setSummaryYear() {
        try {
            summaryYear = connection.prepareStatement("SELECT YEAR(date_incident) AS number_year,SUM(cost_incident) AS summary FROM incidents GROUP BY YEAR(date_incident) ORDER BY YEAR(date_incident)  ASC");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SummaryYear> summaryYearTable(){
        try {
            ResultSet resultSummary = summaryYear.executeQuery();
            List<SummaryYear> summaryYearList = new ArrayList<>();
            while (resultSummary.next()){
                summaryYearList.add(new SummaryYear(resultSummary.getInt("number_year"),resultSummary.getFloat("summary")));
            }
            return summaryYearList;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setSummaryMonth(int year){
        try {
            summaryMonth = connection.prepareStatement("SELECT MONTH(date_incident) AS month,SUM(cost_incident) AS summary FROM incidents WHERE YEAR(date_incident) = "+year+" GROUP BY month(date_incident) ORDER BY month(date_incident)  ASC");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public List<SummaryMonth> summaryMonthTable(){
        try {
            ResultSet resultSummary = summaryMonth.executeQuery();
            List<SummaryMonth> summaryMontList = new ArrayList<>();
            while (resultSummary.next()){
                summaryMontList.add(new SummaryMonth(resultSummary.getInt("month"),resultSummary.getFloat("summary")));
            }
            return summaryMontList;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setSummaryDay(int year,int month){
        try {
            summaryDay = connection.prepareStatement("SELECT DAY(date_incident) AS number_day, SUM(cost_incident) AS summary FROM incidents WHERE YEAR(date_incident) = "+year+" AND MONTH(date_incident) = "+month+" GROUP BY DAY(date_incident) ORDER BY DAY(date_incident) ASC");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SummaryDay> summaryDayTable(){
        try {
            ResultSet resultSummary = summaryDay.executeQuery();
            List<SummaryDay> summaryDayList = new ArrayList<>();
            while (resultSummary.next()){
                summaryDayList.add(new SummaryDay(resultSummary.getInt("number_day"),resultSummary.getFloat("summary")));
            }
            return summaryDayList;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void incidentStatisticUserTurn(String turn, String date){
        try {
            dataUsers = connection.prepareStatement("SELECT Users.id_user,Users.turn_user, Users.surnames_user, Users.name_user, Users.dni_user, Users.address_user, Users.phone_user, Users.login_user, Users.password_user FROM users INNER JOIN incidents ON users.id_user = incidents.id_user WHERE incidents.DATE_INCIDENT = '"+date+"' AND users.TURN_USER = '"+turn+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void incidentStatisticEventTurn(String turn, String date){
        try {
            eventsUser = connection.prepareStatement(
            "SELECT products.name_product, incidents.cost_incident\n " +
            "FROM Users INNER JOIN (Products INNER JOIN Incidents ON Products.id_product = Incidents.id_product) ON Users.id_user = Incidents.id_user \n" +
            "WHERE users.turn_user = '"+turn+"' AND incidents.date_incident = '"+date+"'");
        
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commentStatisticEventTurn(String turn, String date){
        try {
            commentaryUser = connection.prepareStatement(
                "SELECT commentary_incident "+
                "FROM Incidents INNER JOIN Users ON Incidents.id_user = Users.id_user "+
                "WHERE incidents.type_incident = 'comment' AND users.turn_user = '"+turn+"'"+
                "AND incidents.date_incident = '"+date+"'");
        
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        try{
            connection.close();
        }
        catch(SQLException sqlException){
            System.out.println(sqlException);
        }
    }
}
