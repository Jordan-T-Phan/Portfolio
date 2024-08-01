package com.mycompany.databaseexample;



import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.DRIVER;
import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.databaseURL;
import static com.mycompany.databaseexample.MainController.getCurrentTable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.sqlite.SQLiteConfig;


public class DatabaseSQLiteControllerAudit implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField acc_idTextField, usernameBeforeTextField, usernameAfterTextField, passwordBeforeTextField,passwordAfterTextField, modified_userTextField, modified_dateTextField, actionTextField;


    @FXML
    Label footerLabel;
    @FXML
    TableColumn id = new TableColumn("ID");
    @FXML
    TableColumn acc_id = new TableColumn("Account ID");

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            
            System.out.println("initialize " + ex.toString());
        }
        initializeColumns();
        CreateSQLiteTable();
    }



    /* Connect to a sample database
     */
    private ObservableList<Acc_Login_Audit> acc_login_audit_data;
    


    /*
       ArrayList: Resizable-array implementation of the List interface. 
       Implements all optional list operations, and permits all elements, including null.
       ObservableList: A list that allows listeners to track changes when they occur
    */
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName(DRIVER);
        Connection connection = null;
        try {
//            SQLiteConfig config = new SQLiteConfig();
//            config.enforceForeignKeys(true);
            System.out.println("Connection Created");
            connection = DriverManager.getConnection(databaseURL);
        } catch (SQLException ex) {}
        return connection;
    }
    
    public DatabaseSQLiteControllerAudit() throws SQLException {
        this.acc_login_audit_data = FXCollections.observableArrayList();

    }

   //intitalize account columsn
     private void initializeColumns() {

        id = new TableColumn("ID");
        id.setMinWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, Integer>("id"));

        acc_id = new TableColumn("Account ID");
        acc_id.setMinWidth(100);
        acc_id.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, Integer>("acc_id"));
        
        TableColumn usernameBefore = new TableColumn("Username Before");
        usernameBefore.setMinWidth(100);
        usernameBefore.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, String>("usernameBefore"));
        
        TableColumn usernameAfter = new TableColumn("Username After");
        usernameAfter.setMinWidth(100);
        usernameAfter.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, String>("usernameAfter"));
        
        TableColumn passwordBefore = new TableColumn("Password Before");
        passwordBefore.setMinWidth(100);
        passwordBefore.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, String>("passwordBefore"));
        
        TableColumn passwordAfter = new TableColumn("Password After");
        passwordAfter.setMinWidth(100);
        passwordAfter.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, String>("passwordAfter"));
        
        TableColumn  modified_user = new TableColumn("Modified User");
        modified_user.setMinWidth(100);
        modified_user.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, String>("modified_user"));
        
        TableColumn  modified_date = new TableColumn("Modified Date");
        modified_date.setMinWidth(100);
        modified_date.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, String>("modified_date"));
        
        TableColumn  action = new TableColumn("Action");
        action.setMinWidth(100);
        action.setCellValueFactory(new PropertyValueFactory<Acc_Login_Audit, Character>("action"));
        

        
        tableView.setItems(acc_login_audit_data);
        tableView.getColumns().addAll(id,acc_id,usernameBefore, usernameAfter, passwordBefore, passwordAfter, modified_user, modified_date, action);

        
        //tableView.setOpacity(0.3);
        // Allow for the values in each cell to be changable 
    }
    
   
//load all data
    public void loadData() throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        
 
            try {

            // create a connection to the database
            conn = getConnection();

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM game_store.acc_login_audit;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Acc_Login_Audit acc_login_audit;
                acc_login_audit = new Acc_Login_Audit(rs.getInt("id"), rs.getInt("acc_id"),rs.getString("usernameBefore"),rs.getString("usernameAfter"), rs.getString("passwordBefore"),rs.getString("passwordAfter"),rs.getString("modified_user"),rs.getString("modified_date"),rs.getString("action").charAt(0));
                   // rs.getInt("acc_id"),rs.getInt("mod_id"),rs.getInt("hours_played")
                      
              
              acc_login_audit_data.add(acc_login_audit);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + acc_login_audit " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + acc_login_audit + connection.close " + ex.getMessage());
            }
        }
         
         
    }

   


    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Account Login Audit Database");

        //Setting the font of the text 
        text.setFont(Font.font("Edwardian Script ITC", 55));

        //Setting the position of the text 
//        text.setX(600);
//        text.setY(100);
        //Setting the linear gradient 
        Stop[] stops = new Stop[]{
            new Stop(0, Color.DARKSLATEBLUE),
            new Stop(1, Color.RED)
        };
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        //Setting the linear gradient to the text 
        text.setFill(linearGradient);
        // Add the child to the grid
        vBox.getChildren().add(text);

    }

    
    public void insert(int acc_id, String usernameBefore,String usernameAfter, String passwordBefore,String passwordAfter, String modified_user, String modified_date, char action) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

            conn = getConnection();

            System.out.println("Connection to SQLite has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO game_store.acc_login_audit(acc_id,usernameBefore,usernameAfter,passwordBefore,passwordAfter,modified_user,modified_date,action) VALUES(?,?,?,?,?,?,?,?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
            pstmt.setInt(1, acc_id);
            pstmt.setString(2, usernameBefore);
            pstmt.setString(3, usernameAfter);
            pstmt.setString(4, passwordBefore);
            pstmt.setString(5, passwordAfter);
            pstmt.setString(6, modified_user);
            pstmt.setString(7, modified_date);
            pstmt.setString(8, Character.toString(action));
            pstmt.executeUpdate();
            //System.out.println("Before generated keys");
            ResultSet rs = pstmt.getGeneratedKeys();
            //System.out.println("Before rs.next");
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("insert pstmt " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("insert connectionclosed " + ex.getMessage());
            }
        }
        System.out.println("last_inserted_id " + last_inserted_id);
  
        acc_login_audit_data.add(new Acc_Login_Audit(last_inserted_id,acc_id,usernameBefore,usernameAfter,passwordBefore,passwordAfter,modified_user,modified_date,action));
    }

    public void handleAddAcc_Login_Audit(ActionEvent actionEvent) {

        System.out.println("Account ID: " + acc_idTextField.getText() + "\nUsername Before: " + usernameBeforeTextField.getText() + "\nUsername After: " + usernameAfterTextField.getText() + "\nPassword Before: " + passwordBeforeTextField.getText()+ "\nPassword After: " + passwordAfterTextField.getText() + "\nModified User: " + modified_userTextField.getText() + "\nModified Date: " + modified_dateTextField.getText() + "\nAction: " + actionTextField.getText());

        try {
            // insert a new rows
            insert(Integer.parseInt(acc_idTextField.getText()), usernameBeforeTextField.getText(),usernameAfterTextField.getText(), passwordBeforeTextField.getText(),passwordBeforeTextField.getText(), modified_userTextField.getText(), modified_dateTextField.getText(), actionTextField.getText().charAt(0));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddaudit " + ex.toString());
        }

        acc_idTextField.setText("");
        usernameBeforeTextField.setText("");
        usernameAfterTextField.setText("");
        passwordBeforeTextField.setText("");
        passwordAfterTextField.setText("");
        modified_userTextField.setText("");
        modified_dateTextField.setText("");
        actionTextField.setText("");
       

        footerLabel.setText("Record inserted into table successfully!");
    }
    
    
    
    
    @FXML
    private void CreateSQLiteTable() {
        // SQL statement for creating a new table
//        String sql = "CREATE TABLE IF NOT EXISTS Mod_Library (\n"
//        + "	acc_id	INTEGER,\n"
//        + "	mod_id	INTEGER,\n"
//        + "	Hours_played INTEGER NOT NULL,\n"
//        + "	PRIMARY KEY(Acc_id,Mod_id),\n"
//        + "	FOREIGN KEY(Acc_id) REFERENCES Account(id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
//        + "	FOREIGN KEY(mod_id) REFERENCES Mod(id) ON DELETE CASCADE ON UPDATE CASCADE\n"
//        + ");";

        String sql = "CREATE TABLE IF NOT EXISTS `acc_login_audit` (\n"
        + "`id` int NOT NULL AUTO_INCREMENT,\n"
        + "`acc_id` int DEFAULT NULL,\n"
        + "`usernameBefore` varchar(45) DEFAULT NULL,\n"
        + "`usernameAfter` varchar(45) DEFAULT NULL,\n"
        + "`passwordBefore` varchar(45) DEFAULT NULL,\n"
        + "`passwordAfter` varchar(45) DEFAULT NULL,\n"
        + "`modified_user` varchar(45) DEFAULT NULL,\n"
        + "`modified_date` timestamp(6) NULL DEFAULT NULL,\n"
        + "`action` varchar(1) DEFAULT NULL,\n"
        + "PRIMARY KEY (`id`)\n"
        + ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";


        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(int id, int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = getConnection();

            String sql = "DELETE FROM game_store.acc_login_audit WHERE id=" + Integer.toString(id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Delete Record Bozo");
        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            tableView.getItems().remove(selectedIndex);
            System.out.println("Record Deleted Successfully");
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("deleterecord tableview.getitems.remove " + ex.getMessage());
            }
        }

    }
  @FXML
    private void handleDeleteAction(ActionEvent event) throws IOException {
        System.out.println("Delete Account Login Audit");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Acc_Login_Audit acc_login_audit = (Acc_Login_Audit) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + acc_login_audit.getId());
                //System.out.println("Title: " + movie.getTitle());
                //System.out.println("Year: " + movie.getYear());
                //System.out.println("Rating: " + movie.getRating());
                deleteRecord(acc_login_audit.getId(), selectedIndex);
            }

        }
    }

    

    Integer index = -1;

    @FXML
    private void showRowData() {

        index = tableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        System.out.println("showRowData");
        System.out.println(index);
        Acc_Login_Audit acc_login_audit = (Acc_Login_Audit) tableView.getSelectionModel().getSelectedItem();
        System.out.println("Id: " + acc_login_audit.getId());
       // System.out.println("Title: " + movie.getTitle());
        //System.out.println("Year: " + movie.getYear());
      //  System.out.println("Rating: " + movie.getRating());

        acc_idTextField.setText(Integer.toString(acc_login_audit.getAcc_id()));
        usernameBeforeTextField.setText(acc_login_audit.getUsernameBefore());
        usernameAfterTextField.setText(acc_login_audit.getUsernameAfter());
        passwordBeforeTextField.setText(acc_login_audit.getPasswordBefore());
        passwordAfterTextField.setText(acc_login_audit.getPasswordAfter());
        modified_userTextField.setText(acc_login_audit.getModified_user());
        modified_dateTextField.setText(acc_login_audit.getModified_date());
        actionTextField.setText(Character.toString(acc_login_audit.getAction()));
      
        //ratingTextField.setText(movie.getRating());

        String content = "Id: " + acc_login_audit.getId(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Acc_Login_Audit> search(String _acc_id, String _usernameBefore,String _usernameAfter, String _passwordBefore,String _passwordAfter, String _modified_user, String _modified_date, String _action)throws SQLException {
   // String _title, String _year, String _rating) throws SQLException {
        ObservableList<Acc_Login_Audit> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.acc_login_audit where true";
        if (!_acc_id.isEmpty()) {
            sql += " and acc_id like '%" + _acc_id + "%'";
        }
        if (!_usernameBefore.isEmpty()) {
            sql += " and usernameBefore like '%" + _usernameBefore + "%'";
        }
        if (!_usernameAfter.isEmpty()) {
            sql += " and usernameAfter like '%" + _usernameAfter + "%'";
        }
        if (!_passwordBefore.isEmpty()) {
            sql += " and passwordBefore like '%" + _passwordBefore + "%'";
        }
        if (!_modified_user.isEmpty()) {
            sql += " and modified_user like '%" + _modified_user + "%'";
        }
        if (!_modified_date.isEmpty()) {
            sql += " and modified_date like '%" + _modified_date + "%'";
        }
        if (!_action.isEmpty()) {
            sql += " and action like '%" + _action + "%'";
        }


      

        System.out.println(sql);
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a ResultSet

            ResultSet rs = stmt.executeQuery(sql);
            // checking if ResultSet is empty
            if (rs.next() == false) {
                System.out.println("ResultSet in empty");
            } else {
                // loop through the result set
                do {
                    int recordId = Integer.parseInt(rs.getString("id"));
                    int acc_id = Integer.parseInt(rs.getString("acc_id"));
                    String usernameBefore = rs.getString("usernameBefore");
                    String usernameAfter = rs.getString("usernameAfter");
                    String passwordBefore = rs.getString("passwordBefore");
                    String passwordAfter = rs.getString("passwordAfter");
                    String modified_user = rs.getString("modified_user");
                    String modified_date = rs.getString("modified_date");
                    char action = rs.getString("action").charAt(0);
         
                  

                    Acc_Login_Audit acc_login_audit = new Acc_Login_Audit(recordId,acc_id,usernameBefore,usernameAfter,passwordBefore,passwordAfter,modified_user,modified_date,action);
                    searchResult.add(acc_login_audit);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _acc_id = acc_idTextField.getText().trim();
        String _usernameBefore = usernameBeforeTextField.getText().trim();
        String _usernameAfter = usernameAfterTextField.getText().trim();
        String _passwordBefore = passwordBeforeTextField.getText().trim();
        String _passwordAfter = passwordAfterTextField.getText().trim();
        String _modified_user = modified_userTextField.getText().trim();
        String _modified_date = modified_dateTextField.getText().trim();
        String _action = actionTextField.getText().trim();

        ObservableList<Acc_Login_Audit> tableItems = search(_acc_id,_usernameBefore,_usernameAfter,_passwordBefore,_passwordAfter,_modified_user,_modified_date,_action);
        tableView.setItems(tableItems);

    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(acc_login_audit_data);
    }


    public void update(String acc_id, String usernameBefore, String usernameAfter, String passwordBefore, String passwordAfter, String modified_user, String modified_date, String action, int selectedIndex, int id) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = getConnection();
            String sql = "UPDATE game_store.acc_login_audit SET acc_id = ?, usernameBefore = ?,usernameAfter = ?, passwordBefore = ?,passwordAfter = ?, modified_user = ?, modified_date = ?, action = ?  Where id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, acc_id);
            pstmt.setString(2, usernameBefore);
            pstmt.setString(3, usernameAfter);
            pstmt.setString(4, passwordBefore);
            pstmt.setString(5, passwordAfter);
            pstmt.setString(6, modified_user);
            pstmt.setString(7, modified_date);
            pstmt.setString(8, action);
            pstmt.setString(9, Integer.toString(id));
 
            System.out.println("Hey buddy");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("update updateacc_login_audit" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                System.out.println("update connectionclosed " + ex.getMessage());
            }
        }

    }

    @FXML
    private void handleUpdateRecord(ActionEvent event) throws IOException, SQLException {

        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Handle update record execute");
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println(index);
                Acc_Login_Audit acc_login_audit = (Acc_Login_Audit) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + acc_login_audit.getId());

                try {
                    // insert a new rows
                    update(acc_idTextField.getText(),usernameBeforeTextField.getText(),usernameAfterTextField.getText(),passwordBeforeTextField.getText(),passwordAfterTextField.getText(),modified_userTextField.getText(),modified_dateTextField.getText(),actionTextField.getText(),selectedIndex, acc_login_audit.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                acc_idTextField.setText("");
                usernameBeforeTextField.setText("");
                usernameAfterTextField.setText("");
                passwordBeforeTextField.setText("");
                passwordAfterTextField.setText("");
                modified_userTextField.setText("");
                modified_dateTextField.setText("");
                actionTextField.setText("");

                footerLabel.setText("Record updated successfully!");
                acc_login_audit_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(acc_login_audit_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("Account ID: " + acc_idTextField.getText() + "\nUsername Before: " + usernameBeforeTextField.getText() + "\nUsername After: " + usernameAfterTextField.getText() + "\nPassword Before: " + passwordBeforeTextField.getText()+ "\nPassword After: " + passwordAfterTextField.getText() + "\nModified User: " + modified_userTextField.getText() + "\nModified Date: " + modified_dateTextField.getText() + "\nAction: " + actionTextField.getText());

        try {
            // insert a new row
            insert(Integer.parseInt(acc_idTextField.getText()), usernameBeforeTextField.getText(),usernameAfterTextField.getText(),passwordBeforeTextField.getText(),passwordAfterTextField.getText(),modified_userTextField.getText(),modified_dateTextField.getText(),actionTextField.getText().charAt(0));

            System.out.println("Data was inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

        acc_idTextField.setText("");
        usernameBeforeTextField.setText("");
        usernameAfterTextField.setText("");
        passwordBeforeTextField.setText("");
        passwordAfterTextField.setText("");
        modified_userTextField.setText("");
        modified_dateTextField.setText("");
        actionTextField.setText("");

        footerLabel.setText("Record inserted into table successfully!");

    }

    @FXML
    private void sidebarDeleteRecord() {
        System.out.println("Delete Acc_Login_Audit");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Acc_Login_Audit acc_login_audit = (Acc_Login_Audit) tableView.getSelectionModel().getSelectedItem();
                System.out.println("Id: " + acc_login_audit.getId());
                System.out.println("Account ID: " + acc_login_audit.getAcc_id());
                System.out.println("Username Before: " + acc_login_audit.getUsernameBefore());
                System.out.println("Username After: " + acc_login_audit.getUsernameAfter());
                System.out.println("Password Before: " + acc_login_audit.getPasswordBefore());
                System.out.println("Password After: " + acc_login_audit.getPasswordAfter());
                System.out.println("Modified User: " + acc_login_audit.getModified_user());
                System.out.println("Modified Date: " + acc_login_audit.getModified_date());
                System.out.println("Action: " + acc_login_audit.getAction());
                
                deleteRecord(acc_login_audit.getId(), selectedIndex);
            }

        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
        String _acc_id = acc_idTextField.getText().trim();
        String _usernameBefore = usernameBeforeTextField.getText().trim();
        String _usernameAfter = usernameAfterTextField.getText().trim();
        String _passwordBefore = passwordBeforeTextField.getText().trim();
        String _passwordAfter = passwordAfterTextField.getText().trim();
        String _modified_user = modified_userTextField.getText().trim();
        String _modified_date = modified_dateTextField.getText().trim();
        String _action = actionTextField.getText().trim();
        ObservableList<Acc_Login_Audit> tableItems = search(_acc_id,_usernameBefore,_usernameAfter,_passwordBefore,_passwordAfter,_modified_user,_modified_date,_action);
        tableView.setItems(tableItems);
    }

    
     @FXML
    private void sidebarUpdateRecord() throws SQLException {

        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println(index);
              Acc_Login_Audit acc_login_audit = (Acc_Login_Audit) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + acc_login_audit.getId());

                try {
                    // insert a new rows
                     update(acc_idTextField.getText(),usernameBeforeTextField.getText(),usernameAfterTextField.getText(),passwordBeforeTextField.getText(),passwordAfterTextField.getText(),modified_userTextField.getText(),modified_dateTextField.getText(),actionTextField.getText(),selectedIndex, acc_login_audit.getId());

                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    System.out.println("sidebarupdaterecord" + ex.toString());
                }

                acc_idTextField.setText("");
                usernameBeforeTextField.setText("");
                usernameAfterTextField.setText("");
                passwordBeforeTextField.setText("");
                passwordAfterTextField.setText("");
                modified_userTextField.setText("");
                modified_dateTextField.setText("");
                actionTextField.setText("");

                footerLabel.setText("Record updated successfully!");
                acc_login_audit_data.clear();
                loadData();
                tableView.refresh();
            }

        }
    }
    
}