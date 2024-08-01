package com.mycompany.databaseexample;



import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.DRIVER;
import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.databaseURL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;


public class MainController {
    
    private static String LoggedIn_username;
    private static String LoggedIn_password;
    private static int LoggedIn_access;
    private static int LoggedIn_acc_ID;
    
    private static String currentTable = "";

    @FXML
    VBox root;
    @FXML
    VBox contentPane;
    private static boolean signedIn = false;
    


    @FXML
    private TextField usernameTextField, passwordTextField;
    
    @FXML
    MenuBar menu;
    
    @FXML
    Menu databases;
    
    @FXML
    Menu transactions;
    
    public static String getCurrentTable() {
        System.out.println("Got the current table: " + currentTable);
        return currentTable;
    }
    
    public static String getLoggedIn_Username() {
        return LoggedIn_username;
    }
    public static String getLoggedIn_Password() {
        return LoggedIn_password;
    }
    public static int getLoggedIn_Access() {
        return LoggedIn_access;
    }
    public static int getLoggedIn_Acc_ID() {
        return LoggedIn_acc_ID;
    }
    
    public static String setLoggedIn_Username(String username) {
        LoggedIn_username = username;
        return LoggedIn_username;
    }
    public static String setLoggedIn_Password(String password) {
        LoggedIn_password = password;
        return LoggedIn_password;
    }
    public static int setLoggedIn_Access(int access) {
        LoggedIn_access = access;
        return LoggedIn_access;
    }
    public static int setLoggedIn_Acc_ID(int id) {
        LoggedIn_acc_ID = id;
        return LoggedIn_acc_ID;
    }
    
    @FXML
    private void signInAttempt() throws SQLException {
        
       

        Statement stmt = null;
        Connection connection = null;
        try {

            // create a connection to the database
            Class.forName(DRIVER);
            
        try {
                connection = DriverManager.getConnection(databaseURL);
                System.out.println("Connection created.");
        } catch (SQLException ex) {}

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM game_store.account_login where username = '" + usernameTextField.getText() +  "' AND password = '" + passwordTextField.getText() + "';";
            // Ensure we can query the actors table
            // ' UNION Select * into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/sd1.txt' from game_store.account_login where 1=1 or '' = '
            // ' or 1=1 or '' = ' 
            // ' or ''='
            System.out.println(sql);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                signedIn = true;
                setLoggedIn_Username(rs.getString("username"));
                setLoggedIn_Password(rs.getString("password"));
                setLoggedIn_Access(rs.getInt("admin_access"));
                setLoggedIn_Acc_ID(rs.getInt("id"));
                
                System.out.println("Sign In Success");
                usernameTextField.setText("Sign In Success!");
                menu.setDisable(false);
                menu.setVisible(true);
                if (rs.getInt("admin_access") == 0) {
                    databases.setDisable(true);
                    databases.setVisible(false);
                    System.out.println("Database menu disabled");
                    transactions_account_login();
                }
                else {
                    account_login();
                }
                return;
            }
            
            while (rs.next()) {
                Account_Login detail;
                detail = new Account_Login(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getInt("admin_access"));
                System.out.println(detail.getId() + " - " + detail.getUsername() + " - " + detail.getPassword() + " - " + detail.getAccess());
              
                if (usernameTextField.getText().equals(detail.getUsername()) && passwordTextField.getText().equals(detail.getPassword())) {
                    signedIn = true;
                    
                    setLoggedIn_Username(detail.getUsername());
                    setLoggedIn_Password(detail.getPassword());
                    setLoggedIn_Access(detail.getAccess());
                    setLoggedIn_Acc_ID(detail.getId());
                    

                    System.out.println("Sign In Success");
                    usernameTextField.setText("Sign In Success!");
                    menu.setDisable(false);
                    menu.setVisible(true);
                    if (detail.getAccess() == 0) {
                        databases.setDisable(true);
                        databases.setVisible(false);
                        System.out.println("Database menu disabled");
                        transactions_account_login();
                    }
                    else {
                        account_login();
                    }
                    return;
                } 
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + account " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountLogin1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
         
        //contentPane.lookup("#visibleBar").setVisible(false);
        //contentPane.lookup("#visibleBar").setDisable(true);
        signedIn = false;
        System.out.println("Sign In Failure");
        usernameTextField.setText("Sign In Failure");
        menu.setDisable(true);
        menu.setVisible(false);
        databases.setDisable(true);
        databases.setVisible(false);
        
        
    }

    @FXML
    private void gotoMain() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("main.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            menu.setDisable(true);
            menu.setVisible(false);
            
            databases.setDisable(true);
            databases.setVisible(false);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }

//    @FXML
//    private void accounts() throws IOException {
//        
//        if (signedIn == false) {
//            return;
//        }
//
//        try {
//            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_Accounts.fxml"));
//            contentPane.getChildren().clear();
//            contentPane.getChildren().add(newLoadedPane);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//       
//    }
    
    @FXML
    private void account_login() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "account_login";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_AccountLogin.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
    
        @FXML
    private void transactions_account_login() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "transactions_account_login";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Transactions_AccountLogin.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
       

    }
    
    
    @FXML
    private void account_detail() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "account_detail";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_AccountDetail.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
       

    }
    @FXML
    private void transactions_account_detail() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "transactions_account_detail";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Transactions_AccountDetail.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
       

    }
    
    

    
    @FXML
    private void games() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "games";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_Games.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    private void transactions_games() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "transactions_games";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Transactions_Games.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void mods() throws IOException {

        try {
            currentTable = "mods";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_Mods.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
        @FXML
    private void transactions_mods() throws IOException {

        try {
            currentTable = "transactions_mods";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Transactions_Mods.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void genre() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "genre";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_Genre.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void mod_genre() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "mod_genre";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_ModGenre.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void game_library() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "game_library";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_GameLibrary.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void transactions_game_library() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "transactions_game_library";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Transactions_GameLibrary.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void mod_library() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "mod_library";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_ModLibrary.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void transactions_mod_library() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "transactions_mod_library";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Transactions_ModLibrary.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void game_genre() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "game_genre";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_GameGenre.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void audit() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {
            currentTable = "audit";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("DatabaseSQLite_Audit.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void transactions() throws IOException {
        
        if (signedIn == false) {
            return;
        }

        try {

            currentTable = "transactions";
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Transactions.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(newLoadedPane);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    

    

    
    @FXML
    private void close() throws IOException {

        System.out.println("Quetting!!");
        Platform.exit();

    }
}
