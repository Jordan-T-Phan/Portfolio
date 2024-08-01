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


public class DatabaseSQLiteControllerMod_Library1 implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField mod_IdTextField, acc_IdTextField, hours_PlayedTextField;


    @FXML
    Label footerLabel;
    @FXML
    TableColumn mod_Id = new TableColumn("Mod ID");
    TableColumn acc_Id = new TableColumn("Account ID");

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
    private ObservableList<Account> acc_data;
    private ObservableList<Game> game_data;
    private ObservableList<Mod> mod_data;
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
//            SQLiteConfig config = new SQLiteConfig();
//            config.enforceForeignKeys(true);
            System.out.println("Connection Created");
            connection = DriverManager.getConnection(databaseURL);
        } catch (SQLException ex) {}
        return connection;
    }
    
    public DatabaseSQLiteControllerMod_Library1() throws SQLException {
        this.acc_data = FXCollections.observableArrayList();
        this.game_data = FXCollections.observableArrayList();
        this.mod_data = FXCollections.observableArrayList();
        this.genre_data = FXCollections.observableArrayList();
        this.gameGenre_data = FXCollections.observableArrayList();
        this.mod_genre_data = FXCollections.observableArrayList();
        this.game_library_data = FXCollections.observableArrayList();
        this.mod_library_data = FXCollections.observableArrayList();
    }

   //intitalize account columsn
     private void initializeColumns() {
      //private int account_id;
    //private String fName;
    //private String lName;
   // private String username;
    //private String password;
    //private String displayName;
    //private int creditNumber;
        acc_Id = new TableColumn("Account ID");
        acc_Id.setMinWidth(50);
        acc_Id.setCellValueFactory(new PropertyValueFactory<Mod_Library, Integer>("acc_id"));

        mod_Id= new TableColumn("Mod ID");
        mod_Id.setMinWidth(100);
        mod_Id.setCellValueFactory(new PropertyValueFactory<Mod_Library, String>("mod_id"));
       TableColumn  hours_played= new TableColumn("Hours Played");
       hours_played.setMinWidth(100);
        hours_played.setCellValueFactory(new PropertyValueFactory<Mod_Library, String>("hours_played"));

        
        tableView.setItems(mod_library_data);
        tableView.getColumns().addAll(acc_Id,mod_Id,hours_played);

        
        //tableView.setOpacity(0.3);
        // Allow for the values in each cell to be changable 
    }
    
   
//load all data
    public void loadData() throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        
        if (getCurrentTable().equals("transactions_mod_library")) {
            client_loadData();
            return;
        }
 
            try {

            // create a connection to the database
            conn = getConnection();

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM game_store.mod_library;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Mod_Library mod_Library;
                mod_Library = new Mod_Library(rs.getInt("acc_id"),rs.getInt("mod_id"),rs.getInt("hours_played"));
                //account = new Account(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("password"),rs.getString("displayName"),rs.getInt("creditNumber"));
                //game = new Game(rs.getInt("id"),rs.getString("title"),rs.getFloat("price"),rs.getFloat("salePercent"), rs.getFloat("userRating"),rs.getString("ageRating"),rs.getInt("downloads"),rs.getBoolean("deckCompatible"),rs.getString("publisher"));
//      movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("rating"));
              //  System.out.println(movie.getId() + " - " + movie.getTitle() + " - " + movie.getRating() + " - " + movie.getYear());
              //System.out.println(mod_Library.getAcc_id() + " - " + mod_Library.getMod_id()+" - "+mod_Library.getHours_played());
                      
              
              mod_library_data.add(mod_Library);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + modlibrary " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Library1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + modlibrary + connection.close " + ex.getMessage());
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

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM game_store.mod_library where acc_id = " + acc_id;
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Mod_Library mod_Library;
                mod_Library = new Mod_Library(rs.getInt("acc_id"),rs.getInt("mod_id"),rs.getInt("hours_played"));
                //account = new Account(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("password"),rs.getString("displayName"),rs.getInt("creditNumber"));
                //game = new Game(rs.getInt("id"),rs.getString("title"),rs.getFloat("price"),rs.getFloat("salePercent"), rs.getFloat("userRating"),rs.getString("ageRating"),rs.getInt("downloads"),rs.getBoolean("deckCompatible"),rs.getString("publisher"));
//      movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("rating"));
              //  System.out.println(movie.getId() + " - " + movie.getTitle() + " - " + movie.getRating() + " - " + movie.getYear());
              //System.out.println(mod_Library.getAcc_id() + " - " + mod_Library.getMod_id()+" - "+mod_Library.getHours_played());
                      
              
              mod_library_data.add(mod_Library);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + modlibrary " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Library1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + modlibrary + connection.close " + ex.getMessage());
            }
        }
         
         
    }


    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Mod Library Database");

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
     * @param username
     * @param password
     * @param displayName
     * @param creditNumber
     * @throws java.sql.SQLException
     */

    /**
     * Insert a new row into the movies table
     *
     * @param title
     * @param year
     * @param rating
     * @throws java.sql.SQLException
     */
    public void insert(int acc_id, int mod_id, int hours_played) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

            conn = getConnection();

            System.out.println("Connection to SQLite has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO game_store.mod_library(acc_id,mod_id,hours_played) VALUES(?,?,?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
            pstmt.setInt(1, acc_id);
            pstmt.setInt(2, mod_id);
            pstmt.setInt(3, hours_played);
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
            Logger.getLogger(DatabaseSQLiteControllerMod_Library1.class.getName()).log(Level.SEVERE, null, ex);
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
  
        mod_library_data.add(new Mod_Library(acc_id,mod_id,hours_played));
    }

    public void handleAddMod_Library(ActionEvent actionEvent) {

        System.out.println("Account ID: " + acc_IdTextField.getText() + "\nMod ID " + mod_IdTextField.getText() + "\nUsername: " + hours_PlayedTextField.getText());

        try {
            // insert a new rows
            insert(Integer.parseInt(acc_IdTextField.getText()), Integer.parseInt(mod_IdTextField.getText()), Integer.parseInt(hours_PlayedTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddaccount " + ex.toString());
        }

        acc_IdTextField.setText("");
        mod_IdTextField.setText("");
        hours_PlayedTextField.setText("");
       

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
        String sql = "CREATE TABLE IF NOT EXISTS `mod_library` (\n"
        + "`acc_id` int NOT NULL,\n"
        + "`mod_id` int NOT NULL,\n"
        + "`hours_played` int NOT NULL,\n"
        + "PRIMARY KEY (`acc_id`,`mod_id`),\n"
        + "KEY `mod_library_mod_id_idx` (`mod_id`),\n"
        + "CONSTRAINT `mod_library_acc_id` FOREIGN KEY (`acc_id`) REFERENCES `account_login` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\n"
        + "CONSTRAINT `mod_library_mod_id` FOREIGN KEY (`mod_id`) REFERENCES `mod` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Library1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(int acc_id, int mod_id, int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = getConnection();

            String sql = "DELETE FROM game_store.mod_library WHERE acc_id=" + Integer.toString(acc_id)+" AND mod_id="+ Integer.toString(mod_id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Delete Record Bozo");
        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Library1.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("Delete Mod Library");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Mod_Library mod_library = (Mod_Library) tableView.getSelectionModel().getSelectedItem();
                System.out.println("acc ID: " + mod_library.getAcc_id());
                System.out.println("mod ID: " + mod_library.getMod_id());
                //System.out.println("Title: " + movie.getTitle());
                //System.out.println("Year: " + movie.getYear());
                //System.out.println("Rating: " + movie.getRating());
                deleteRecord(mod_library.getAcc_id(),mod_library.getMod_id(), selectedIndex);
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
        Mod_Library mod_library = (Mod_Library) tableView.getSelectionModel().getSelectedItem();
        System.out.println("acc ID: " + mod_library.getAcc_id());
        System.out.println("mod ID: " + mod_library.getMod_id());
       // System.out.println("Title: " + movie.getTitle());
        //System.out.println("Year: " + movie.getYear());
      //  System.out.println("Rating: " + movie.getRating());

        acc_IdTextField.setText(Integer.toString(mod_library.getAcc_id()));
        mod_IdTextField.setText(Integer.toString(mod_library.getMod_id()));
      
        //ratingTextField.setText(movie.getRating());

        String content = "acc Id= " + mod_library.getAcc_id()+"mod Id= " + mod_library.getMod_id(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Mod_Library> search(String _acc_id, String _mod_id, String _hours_played)throws SQLException {
   // String _title, String _year, String _rating) throws SQLException {
        ObservableList<Mod_Library> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.mod_library where true";
        if (!_acc_id.isEmpty()) {
            sql += " and acc_id like '%" + _acc_id + "%'";
        }
        if (!_mod_id.isEmpty()) {
            sql += " and mod_id like '%" + _mod_id + "%'";
        }
    if (!_hours_played.isEmpty()) {
            sql += " and hours_played like '%" + _hours_played + "%'";
        }
    
        //if (!_year.isEmpty()) {
           // sql += " and year ='" + _year + "'";
       // }

      

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

                    int acc_id = Integer.parseInt(rs.getString("acc_id"));
                    int mod_id = Integer.parseInt(rs.getString("mod_id"));
                    int hours_played = Integer.parseInt(rs.getString("hours_played"));
         
                   // String title = rs.getString("title");
                 //   int year = Integer.parseInt(rs.getString("year"));
                  

                    Mod_Library mod_library = new Mod_Library(acc_id,mod_id,hours_played);
                    searchResult.add(mod_library);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Library1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _acc_id = acc_IdTextField.getText().trim();
        String _mod_id = mod_IdTextField.getText().trim();
        String _hours_played = hours_PlayedTextField.getText().trim();
        ObservableList<Mod_Library> tableItems = search(_acc_id,_mod_id,_hours_played);
        tableView.setItems(tableItems);

    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(mod_library_data);
    }

    /**
     * Update a record in the accounts table
     *
     * @param fName
     * @param lName
     * @param username
     * @param password
     * @param displayName
     * @param creditNumber
     * @throws java.sql.SQLException
     */
    public void update(String acc_id, String mod_id, String hours_played, int selectedIndex) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = getConnection();
            String sql = "UPDATE game_store.mod_library SET hours_played = ? Where acc_id = ? AND mod_id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, hours_played);
            pstmt.setString(2, acc_id);
            pstmt.setString(3, mod_id);

            System.out.println("Hey buddy");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("update updateaccount" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Library1.class.getName()).log(Level.SEVERE, null, ex);
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
                Mod_Library mod_library = (Mod_Library) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + mod_library.getAcc_id());

                try {
                    // insert a new rows
                    update(acc_IdTextField.getText(),mod_IdTextField.getText(),hours_PlayedTextField.getText(),selectedIndex);
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                acc_IdTextField.setText("");
        mod_IdTextField.setText("");
        hours_PlayedTextField.setText("");

                footerLabel.setText("Record updated successfully!");
                mod_library_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(mod_library_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("Acc Id " + acc_IdTextField.getText() + "\nmod Id: " + mod_IdTextField.getText() + "\nhours played: " + hours_PlayedTextField.getText());

        try {
            // insert a new row
            insert(Integer.parseInt(acc_IdTextField.getText()),Integer.parseInt(mod_IdTextField.getText()),Integer.parseInt(hours_PlayedTextField.getText()));

            System.out.println("Data was inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

                acc_IdTextField.setText("");
        mod_IdTextField.setText("");
        hours_PlayedTextField.setText("");

        footerLabel.setText("Record inserted into table successfully!");

    }

    @FXML
    private void sidebarDeleteRecord() {
        System.out.println("Delete Mod_Library");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Mod_Library mod_library = (Mod_Library) tableView.getSelectionModel().getSelectedItem();
                System.out.println("Acc Id: " + mod_library.getAcc_id());
                System.out.println("Mod Id: " + mod_library.getMod_id());
                System.out.println("Hours Played: " + mod_library.getHours_played());
                
                deleteRecord(mod_library.getAcc_id(),mod_library.getMod_id(), selectedIndex);
            }

        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
        String _acc_id = acc_IdTextField.getText().trim();
        String _mod_id = mod_IdTextField.getText().trim();
        String _hours_played = hours_PlayedTextField.getText().trim();
        ObservableList<Mod_Library> tableItems = search(_acc_id,_mod_id,_hours_played);
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
              Mod_Library mod_library = (Mod_Library) tableView.getSelectionModel().getSelectedItem();
                System.out.println("acc ID: " + mod_library.getAcc_id());
                System.out.println("mod ID: " + mod_library.getMod_id());

                try {
                    // insert a new rows
                     update(acc_IdTextField.getText(),mod_IdTextField.getText(),hours_PlayedTextField.getText(),selectedIndex);

                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    System.out.println("sidebarupdaterecord" + ex.toString());
                }

                acc_IdTextField.setText("");
        mod_IdTextField.setText("");
        hours_PlayedTextField.setText("");

                footerLabel.setText("Record updated successfully!");
                mod_library_data.clear();
                loadData();
                tableView.refresh();
            }

        }
    }
    
}
