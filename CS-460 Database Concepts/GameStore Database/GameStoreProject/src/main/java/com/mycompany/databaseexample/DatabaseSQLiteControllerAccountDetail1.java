package com.mycompany.databaseexample;




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




public class DatabaseSQLiteControllerAccountDetail1 implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField first_nameTextField, last_nameTextField, display_nameTextField, balanceTextField;


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
        System.out.println("Initialize done");
    }

    public static final String databaseURL = "jdbc:mysql://localhost:3306/game_store?user=root&password=mypassword";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /* Connect to a sample database
     */
    private ObservableList<Account> acc_data;
    private ObservableList<Game> game_data;
    private ObservableList<Mod> mod_data;
    private ObservableList<Account_Detail> accountDetail_data;
    private ObservableList<Genre> genre_data;
    private ObservableList<GameGenre> gameGenre_data;
    private ObservableList<Mod_Genre> mod_genre_data;
    private ObservableList<Game_Library> game_library_data;
    private ObservableList<Mod_Library> mod_library_data;
    


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
    
    public DatabaseSQLiteControllerAccountDetail1() throws SQLException {
        this.acc_data = FXCollections.observableArrayList();
        this.game_data = FXCollections.observableArrayList();
        this.mod_data = FXCollections.observableArrayList();
        this.genre_data = FXCollections.observableArrayList();
        this.gameGenre_data = FXCollections.observableArrayList();
        this.mod_genre_data = FXCollections.observableArrayList();
        this.game_library_data = FXCollections.observableArrayList();
        this.mod_library_data = FXCollections.observableArrayList();
        this.accountDetail_data = FXCollections.observableArrayList();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("class for name!");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   //intitalize account columsn
     private void initializeColumns() {
        id = new TableColumn("ID");
        id.setMinWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<Account_Detail, Integer>("id"));

        TableColumn first_name = new TableColumn("First Name");
        first_name.setMinWidth(100);
        first_name.setCellValueFactory(new PropertyValueFactory<Account_Detail, String>("first_name"));

        TableColumn last_name = new TableColumn("Last Name");
        last_name.setMinWidth(100);
        last_name.setCellValueFactory(new PropertyValueFactory<Account_Detail, String>("last_name"));

        TableColumn display_name = new TableColumn("Display Name");
        display_name.setMinWidth(100);
        display_name.setCellValueFactory(new PropertyValueFactory<Account_Detail, String>("display_name"));
        
        TableColumn balance = new TableColumn("Balance");
        balance.setMinWidth(100);
        balance.setCellValueFactory(new PropertyValueFactory<Account_Detail, String>("balance"));
    
        tableView.setItems(accountDetail_data);
        tableView.getColumns().addAll(id, first_name,last_name,display_name,balance);
        System.out.println("Initialize Columns done");
    }
    
   
//load all data
    public void loadData() throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        
        if (getCurrentTable().equals("transactions_account_detail")) {
            client_loadData();
            return;
        }
 

         try {

            // create a connection to the database
            conn = getConnection();

            System.out.println("Connection to SQLite has been established 1.");
            String sql = "SELECT * FROM game_store.account_detail;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            System.out.println("Connection to SQLite has been established 2.");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established 3.\nConnected to MySQL.");

            while (rs.next()) {
                Account_Detail detail;
                detail = new Account_Detail(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("display_name"),(double) Math.round((rs.getDouble("balance")*100))/((float)100.0));
                System.out.println(detail.getId() + " - " + detail.getFirst_name() + " - " + detail.getLast_name() + " - " + detail.getDisplay_name() + " - " + detail.getBalance());
                accountDetail_data.add(detail);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + detail " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
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
    public void client_loadData() throws SQLException{
        
        Connection conn = null;
        Statement stmt = null;
 int acc_id = MainController.getLoggedIn_Acc_ID();

         try {

            // create a connection to the database
            conn = getConnection();

            System.out.println("Connection to SQLite has been established 1.");
            String sql = "SELECT * FROM game_store.account_detail where id = " + acc_id;
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            System.out.println("Connection to SQLite has been established 2.");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established 3.\nConnected to MySQL.");

            while (rs.next()) {
                Account_Detail detail;
                detail = new Account_Detail(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("display_name"),(double) Math.round((rs.getDouble("balance")*100))/((float)100.0));
                System.out.println(detail.getId() + " - " + detail.getFirst_name() + " - " + detail.getLast_name() + " - " + detail.getDisplay_name() + " - " + detail.getBalance());
                accountDetail_data.add(detail);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + detail " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
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
        Text text = new Text("The Account Detail Database");

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
    
    /**
     * Insert a new row into the movies table
     *
     * @param first_name
     * @param last_name
     * @param display_name
     * @param balance
     * @throws java.sql.SQLException
     */
    public void insert(String first_name, String last_name, String display_name, double balance) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

            conn = getConnection();

            System.out.println("Connection to MySQL has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO game_store.account_detail(first_name,last_name,display_name,balance) VALUES(?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, display_name);
            pstmt.setDouble(4, balance);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("insert pstmt " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
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
  
        accountDetail_data.add(new Account_Detail(last_inserted_id, first_name, last_name, display_name, balance));
    }

    public void handleAddAccountDetail(ActionEvent actionEvent) {

        System.out.println("First Name: " + first_nameTextField.getText() + "\nLast Name: " + last_nameTextField.getText() + "\nDisplay Name: " + display_nameTextField.getText() + "\nBalance: " + balanceTextField.getText());

        try {
            // insert new rows
            insert(first_nameTextField.getText(), last_nameTextField.getText(), display_nameTextField.getText(), Double.parseDouble(balanceTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddaccountdetail " + ex.toString());
        }

        first_nameTextField.setText("");
        last_nameTextField.setText("");
        display_nameTextField.setText("");
        balanceTextField.setText("");
        footerLabel.setText("Record inserted into table successfully!");
    }
    @FXML
    private void CreateSQLiteTable() {
        
        String sql = "CREATE TABLE IF NOT EXISTS `account_detail` (\n" +
        "  `id` int NOT NULL,\n" +
        "  `first_name` varchar(25) NOT NULL,\n" +
        "  `last_name` varchar(25) NOT NULL,\n" +
        "  `display_name` varchar(25) NOT NULL,\n" +
        "  `balance` double NOT NULL,\n" +
        "  PRIMARY KEY (`id`),\n" +
        "  CONSTRAINT `account_detail_id` FOREIGN KEY (`id`) REFERENCES `account_login` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createmysqltable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(int id, int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = getConnection();

            String sql = "DELETE FROM game_store.account_detail WHERE id=" + Integer.toString(id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
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
                Account_Detail detail = (Account_Detail) tableView.getSelectionModel().getSelectedItem();
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
        Account_Detail detail = (Account_Detail) tableView.getSelectionModel().getSelectedItem();
        System.out.println("ID: " + detail.getId());
       // System.out.println("Title: " + movie.getTitle());
        //System.out.println("Year: " + movie.getYear());
      //  System.out.println("Rating: " + movie.getRating());

        first_nameTextField.setText(detail.getFirst_name());
        last_nameTextField.setText(detail.getLast_name());
        display_nameTextField.setText(detail.getDisplay_name());
        balanceTextField.setText(Double.toString(detail.getBalance()));
        //ratingTextField.setText(movie.getRating());

        String content = "Id= " + detail.getId(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Account_Detail> search(String _first_name, String _last_name, String _display_name, String _balance)throws SQLException {
   // String _title, String _year, String _rating) throws SQLException {
        ObservableList<Account_Detail> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.account_detail where true";
        if (!_first_name.isEmpty()) {
            sql += " and first_name like '%" + _first_name + "%'";
        }
        if (!_last_name.isEmpty()) {
            sql += " and last_name like '%" + _last_name + "%'";
        }
        if (!_display_name.isEmpty()) {
                sql += " and display_name like '%" + _display_name + "%'";
            }
    if (!_balance.isEmpty()) {
            sql += " and balance like '%" + _balance + "%'";
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
                        String first_name = rs.getString("first_name");
                        String last_name = rs.getString("last_name");
                        String display_name = rs.getString("display_name");
                        double balance = Double.parseDouble(rs.getString("balance"));

                        Account_Detail detail = new Account_Detail(recordId, first_name, last_name, display_name, balance);
                        searchResult.add(detail);
                    } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _first_name = first_nameTextField.getText().trim();
        String _last_name = last_nameTextField.getText().trim();
        String _display_name = display_nameTextField.getText().trim();
        String _balance = balanceTextField.getText().trim();
        ObservableList<Account_Detail> tableItems = search(_first_name, _last_name, _display_name, _balance);
        tableView.setItems(tableItems);

    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(accountDetail_data);
    }


    public void update(String first_name, String last_name, String display_name, String balance, int selectedIndex, int id) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = getConnection();
            String sql = "UPDATE game_store.account_detail SET first_name = ?, last_name = ?, display_name = ?, balance = ? Where id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, display_name);
            pstmt.setString(4, balance);
            pstmt.setString(5, Integer.toString(id));
            System.out.println("Update success!");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("update updateaccount" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAccountDetail1.class.getName()).log(Level.SEVERE, null, ex);
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
                Account_Detail detail = (Account_Detail) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + detail.getId());

                try {
                    // insert a new rows
                    update(first_nameTextField.getText(),last_nameTextField.getText(),display_nameTextField.getText(),balanceTextField.getText(), selectedIndex, detail.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                first_nameTextField.setText("");
                last_nameTextField.setText("");
                display_nameTextField.setText("");
                balanceTextField.setText("");

                footerLabel.setText("Record updated successfully!");
               accountDetail_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(accountDetail_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("First Name: " + first_nameTextField.getText() + "\nLast Name: " + last_nameTextField.getText() + "\nDisplay Name: " + display_nameTextField.getText() + "\nBalance: " + balanceTextField.getText());

        try {
            // insert a new row
            insert(first_nameTextField.getText(), last_nameTextField.getText(), display_nameTextField.getText(), Double.parseDouble(balanceTextField.getText()));

            System.out.println("Data was inserted successfully!");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

            first_nameTextField.setText("");
            last_nameTextField.setText("");
            display_nameTextField.setText("");
            balanceTextField.setText("");

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
                Account_Detail detail = (Account_Detail) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + detail.getId());
                deleteRecord(detail.getId(), selectedIndex);
            }

        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
        String _first_name = first_nameTextField.getText().trim();
        String _last_name = last_nameTextField.getText().trim();
        String _display_name = display_nameTextField.getText().trim();
        String _balance = balanceTextField.getText().trim();
        ObservableList<Account_Detail> tableItems = search(_first_name, _last_name, _display_name, _balance);
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
                Account_Detail detail = (Account_Detail) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + detail.getId());

                try {
                    // insert a new rows
                    update(first_nameTextField.getText(),last_nameTextField.getText(),display_nameTextField.getText(),balanceTextField.getText(), selectedIndex, detail.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                first_nameTextField.setText("");
                last_nameTextField.setText("");
                display_nameTextField.setText("");
                balanceTextField.setText("");

                footerLabel.setText("Record updated successfully!");
               accountDetail_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }
    
}
