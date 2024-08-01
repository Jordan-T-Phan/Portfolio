package com.mycompany.databaseexample;



import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.DRIVER;
import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.databaseURL;
import static com.mycompany.databaseexample.MainController.getCurrentTable;
import static com.mycompany.databaseexample.MainController.getLoggedIn_Access;
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




public class DatabaseSQLiteControllerAccountLogin1 implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField usernameTextField, passwordTextField, accessTextField;


    @FXML
    Label footerLabel;
    @FXML
    TableColumn id = new TableColumn("ID");
    
   

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


    private ObservableList<Account_Login> accountLogin_data;
    


    /*
       ArrayList: Resizable-array implementation of the List interface. 
       Implements all optional list operations, and permits all elements, including null.
       ObservableList: A list that allows listeners to track changes when they occur
    */
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName(DRIVER);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseURL);
            System.out.println("Connection created.");
        } catch (SQLException ex) {}
        return connection;
    }
    
    public DatabaseSQLiteControllerAccountLogin1() throws SQLException {
        this.accountLogin_data = FXCollections.observableArrayList();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("class for name!");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   //intitalize account columsn
     private void initializeColumns() {
        id = new TableColumn("ID");
        id.setMinWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<Account_Login, Integer>("id"));

        TableColumn username = new TableColumn("Username");
        username.setMinWidth(100);
        username.setCellValueFactory(new PropertyValueFactory<Account_Login, String>("username"));

        TableColumn password = new TableColumn("Password");
        password.setMinWidth(100);
        password.setCellValueFactory(new PropertyValueFactory<Account_Login, String>("password"));

        TableColumn admin_access = new TableColumn("Admin Access");
        admin_access.setMinWidth(100);
        admin_access.setCellValueFactory(new PropertyValueFactory<Account_Login, Integer>("admin_access"));
    
        tableView.setItems(accountLogin_data);
        tableView.getColumns().addAll(id, username,password, admin_access);
    }
    
   
//load all data
    public void loadData() throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        
        if (getCurrentTable().equals("transactions_account_login")) {
            client_loadData();
            return;
        }
 

         try {

            // create a connection to the database
            conn = getConnection();

            System.out.println("Connection to SQLite has been established 1.");
            String sql = "SELECT * FROM game_store.account_login;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            System.out.println("Connection to SQLite has been established 2.");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established 3.\nConnected to MySQL.");

            while (rs.next()) {
                Account_Login detail;
                detail = new Account_Login(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getInt("admin_access"));
                System.out.println(detail.getId() + " - " + detail.getUsername() + " - " + detail.getPassword() + " - " + detail.getAccess());
                accountLogin_data.add(detail);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + detail " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + detail + connection.close " + ex.getMessage());
            }
        }

         
         
    }
     public void client_loadData() throws SQLException {

        Connection conn = null;
        Statement stmt = null;
 int acc_id = MainController.getLoggedIn_Acc_ID();

         try {

            // create a connection to the database
            conn = getConnection();

            System.out.println("Connection to SQLite has been established 1.");
            String sql = "SELECT * FROM game_store.account_login where id = " + acc_id;
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            System.out.println("Connection to SQLite has been established 2.");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established 3.\nConnected to MySQL.");

            while (rs.next()) {
                Account_Login detail;
                detail = new Account_Login(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getInt("admin_access"));
                System.out.println(detail.getId() + " - " + detail.getUsername() + " - " + detail.getPassword() + " - " + detail.getAccess());
                accountLogin_data.add(detail);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + detail " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + detail + connection.close " + ex.getMessage());
            }
        }

         
         
    }


    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Account Login Database");

        //Setting the font of the text 
        text.setFont(Font.font("Edwardian Script ITC", 55));

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
    
    /**
     * Insert a new row into the movies table
     *
     * @param username
     * @param password
     * @param admin_access
     * @throws java.sql.SQLException
     */
    public void insert(String username, String password, int admin_access) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

            conn = getConnection();

            System.out.println("Connection to MySQL has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO game_store.account_login(username,password,admin_access) VALUES(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, admin_access);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("insert pstmt " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
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
  
        accountLogin_data.add(new Account_Login(last_inserted_id, username, password, admin_access));
    }

    public void handleAddAccountLogin(ActionEvent actionEvent) {

        System.out.println("Username: " + usernameTextField.getText() + "\nPassword: " + passwordTextField.getText() + "\nAdmin Access: " + accessTextField.getText());

        try {
            // insert new rows
            
            insert(usernameTextField.getText(), passwordTextField.getText(), Integer.parseInt(accessTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddaccountdetail " + ex.toString());
        }

        usernameTextField.setText("");
        passwordTextField.setText("");
        accessTextField.setText("");
        footerLabel.setText("Record inserted into table successfully!");
    }
    @FXML

    private void CreateSQLiteTable() {

        String sql = "CREATE TABLE IF NOT EXISTS `account_login` (\n"
        + "`id` int NOT NULL AUTO_INCREMENT,\n"
        + "`username` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,\n"
        + "`password` varchar(25) NOT NULL,\n"
        + "`admin_access` tinyint NOT NULL,\n"
        + "PRIMARY KEY (`id`),\n"
        + "UNIQUE KEY `username_UNIQUE` (`username`)\n"
        + ") ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createmysqltable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(int id, int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = getConnection();

            String sql = "DELETE FROM game_store.account_login WHERE id=" + Integer.toString(id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("Delete Account Detail");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Account_Login detail = (Account_Login) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + detail.getId());
                deleteRecord(detail.getId(), selectedIndex);
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
        Account_Login detail = (Account_Login) tableView.getSelectionModel().getSelectedItem();
        System.out.println("ID: " + detail.getId());
        usernameTextField.setText(detail.getUsername());
        passwordTextField.setText(detail.getPassword());
        accessTextField.setText(Integer.toString(detail.getAccess()));
        String content = "Id= " + detail.getId(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Account_Login> search(String _username, String _password, String _admin_access)throws SQLException {
        ObservableList<Account_Login> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.account_login where true";
        if (!_username.isEmpty()) {
            sql += " and username like '%" + _username + "%'";
        }
        if (!_password.isEmpty()) {
            sql += " and password like '%" + _password + "%'";
        }
        if (!_admin_access.isEmpty()) {
                sql += " and admin_access like '%" + _admin_access + "%'";
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
                        int recordId = rs.getInt("id");
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        int admin_access = Integer.parseInt(rs.getString("admin_access"));

                        Account_Login detail = new Account_Login(recordId, username, password, admin_access);
                        searchResult.add(detail);
                    } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _username = usernameTextField.getText().trim();
        String _password = passwordTextField.getText().trim();
        String _admin_access = accessTextField.getText().trim();
        ObservableList<Account_Login> tableItems = search(_username, _password, _admin_access);
        tableView.setItems(tableItems);
    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(accountLogin_data);
    }


    public void update(String username, String password, String admin_access, int selectedIndex, int id) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = getConnection();
            String sql = "UPDATE game_store.account_login SET username = ?, password = ?, admin_access = ? Where id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, admin_access);
            pstmt.setString(4, Integer.toString(id));
            System.out.println("Update success!");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("update updateaccountlogin " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
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
                Account_Login detail = (Account_Login) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + detail.getId());

                try {
                    // insert a new rows
                    if (getLoggedIn_Access() == 0) {
                        update(usernameTextField.getText(),passwordTextField.getText(), "0", selectedIndex, detail.getId());
                    } else {
                        update(usernameTextField.getText(),passwordTextField.getText(), accessTextField.getText(), selectedIndex, detail.getId());
                    }
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                usernameTextField.setText("");
                passwordTextField.setText("");
                accessTextField.setText("");

                footerLabel.setText("Record updated successfully!");
               accountLogin_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(accountLogin_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("Username: " + usernameTextField.getText() + "\nPassword: " + passwordTextField.getText() + "\nAdmin Access: " + accessTextField.getText());

        try {
            // insert a new row
            insert(usernameTextField.getText(), passwordTextField.getText(), Integer.parseInt(accessTextField.getText()));

            System.out.println("Data was inserted successfully!");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

            usernameTextField.setText("");
            passwordTextField.setText("");
            accessTextField.setText("");

        footerLabel.setText("Record inserted into table successfully!");

    }

    @FXML
    private void sidebarDeleteRecord() {
        System.out.println("Delete Account Detail");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Account_Login detail = (Account_Login) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + detail.getId());
                deleteRecord(detail.getId(), selectedIndex);
            }

        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
        String _username = usernameTextField.getText().trim();
        String _password = passwordTextField.getText().trim();
        String _admin_access = accessTextField.getText().trim();
        ObservableList<Account_Login> tableItems = search(_username, _password, _admin_access);
        tableView.setItems(tableItems);
    }

    
     @FXML
    private void sidebarUpdateRecord() throws SQLException {
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Handle update record execute");
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println(index);
                Account_Login detail = (Account_Login) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + detail.getId());

                try {
                    // insert a new rows
                    update(usernameTextField.getText(),passwordTextField.getText(), accessTextField.getText(), selectedIndex, detail.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                usernameTextField.setText("");
                passwordTextField.setText("");
                accessTextField.setText("");

                footerLabel.setText("Record updated successfully!");
               accountLogin_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }
    
}
