

package com.mycompany.databaseexample;



import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.DRIVER;
import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.databaseURL;
import static com.mycompany.databaseexample.DatabaseSQLiteControllerGame1.getConnection;
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




public class DatabaseSQLiteControllerMod1 implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField nameTextField, publisherTextField, priceTextField, game_IdTextField;


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
//            MysqlDataSource dataSource = new MysqlDataSource();
//            dataSource.setUser(username);
//            dataSource.setPassword(password);
//            dataSource.setServerName(databaseURL);
            //SQLiteConfig config = new SQLiteConfig();
            //config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(databaseURL);
            System.out.println("Connection created.");
        } catch (SQLException ex) {}
        return connection;
    }
    
    public DatabaseSQLiteControllerMod1() throws SQLException {
        this.acc_data = FXCollections.observableArrayList();
        this.game_data = FXCollections.observableArrayList();
        this.mod_data = FXCollections.observableArrayList();
        this.genre_data = FXCollections.observableArrayList();
        this.gameGenre_data = FXCollections.observableArrayList();
        this.mod_genre_data = FXCollections.observableArrayList();
        this.game_library_data = FXCollections.observableArrayList();
        this.mod_library_data = FXCollections.observableArrayList();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("class for name!");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        id = new TableColumn("ID");
        id.setMinWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<Mod, Integer>("id"));

        TableColumn name = new TableColumn("name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<Mod, String>("name"));

        TableColumn publisher = new TableColumn("Publisher");
        publisher.setMinWidth(100);
        publisher.setCellValueFactory(new PropertyValueFactory<Mod, String>("publisher"));

        TableColumn price = new TableColumn("Price");
       price.setMinWidth(100);
        price.setCellValueFactory(new PropertyValueFactory<Mod, String>("price"));
     TableColumn game_Id = new TableColumn("Game ID");
        game_Id.setMinWidth(100);
        game_Id.setCellValueFactory(new PropertyValueFactory<Mod, String>("game_id"));
    
        tableView.setItems(mod_data);
        tableView.getColumns().addAll(id, name,publisher,price,game_Id);

        
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

            System.out.println("Connection to SQLite has been established 1.");
            String sql = "SELECT * FROM game_store.mod;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            System.out.println("Connection to SQLite has been established 2.");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established 3.");

            while (rs.next()) {
                Mod mod;
                mod = new Mod(rs.getInt("id"),rs.getString("title"),rs.getString("publisher"),Math.round((rs.getFloat("price")*100))/((float)100.0),rs.getInt("game_id"));
                //account = new Account(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("password"),rs.getString("displayName"),rs.getInt("creditNumber"));
                //game = new Game(rs.getInt("id"),rs.getString("title"),rs.getFloat("price"),rs.getFloat("salePercent"), rs.getFloat("userRating"),rs.getString("ageRating"),rs.getInt("downloads"),rs.getBoolean("deckCompatible"),rs.getString("publisher"));

              System.out.println(mod.getId() + " - " + mod.getName() + " - " + mod.getPublisher() + " - " + mod.getPrice() + " - " + mod.getGame_id());
                      
              
              mod_data.add(mod);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + mod " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + mod + connection.close " + ex.getMessage());
            }
        }

         
         
    }


    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Mod Database");

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
    public void insert(String title, String publisher, float price, int game_id) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

            conn = getConnection();

            System.out.println("Connection to SQLite has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO game_store.mod(title,publisher,price,game_id) VALUES(?,?,?,?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
            pstmt.setString(1, title);
            pstmt.setString(2, publisher);
            pstmt.setFloat(3, price);
            pstmt.setInt(4, game_id);
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
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
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
  
        mod_data.add(new Mod(last_inserted_id,title, publisher, price, game_id));
    }

    public void handleAddMod(ActionEvent actionEvent) {

        System.out.println("Name: " + nameTextField.getText() + "\nPublisher " + publisherTextField.getText() + "\nPrice: " + priceTextField.getText() + "\nGame_ID: " + game_IdTextField.getText());

        try {
            // insert a new rows
            insert(nameTextField.getText(), publisherTextField.getText(), Float.parseFloat(priceTextField.getText()), Integer.parseInt(game_IdTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddaccount " + ex.toString());
        }

        nameTextField.setText("");
        publisherTextField.setText("");
        priceTextField.setText("");
        game_IdTextField.setText("");
        footerLabel.setText("Record inserted into table successfully!");
    }
     public void handleAddMod_toClient(ActionEvent actionEvent) {

      //  System.out.println("Title: " + titleTextField.getText() + "\nPublisher: " + publisherTextField.getText() + "\nOriginal Price: " + orig_priceTextField.getText() + "\nSale Percent: " + salePercentTextField.getText() + "\nUser Rating: " + userRatingTextField.getText() + "\nDownloads: " + downloadsTextField.getText());

        try {
             if (tableView.getSelectionModel().getSelectedItem() != null) {
             Mod mod = (Mod) tableView.getSelectionModel().getSelectedItem();
             add_mod_to_client_library(mod.getId());
             
             }
            // insert a new rows
           // insert(titleTextField.getText(),publisherTextField.getText(),Float.parseFloat(orig_priceTextField.getText()), Float.parseFloat(salePercentTextField.getText()), Float.parseFloat(userRatingTextField.getText()),Integer.parseInt(downloadsTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddgame " + ex.toString());
        }

        nameTextField.setText("");
        publisherTextField.setText("");
        priceTextField.setText("");
        game_IdTextField.setText("");
        

        footerLabel.setText("Record inserted into table successfully!");
    }
     
       public void add_mod_to_client_library(int mod_id) throws SQLException{
    
    
    Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            int acc_id = MainController.getLoggedIn_Acc_ID();
            // create a connection to the database
             conn = getConnection();
              String sql = "INSERT INTO `game_store`.`mod_library`(acc_id,mod_id,hours_played) VALUES(?,?,?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
           
            pstmt.setInt(1, acc_id);
            pstmt.setInt(2, mod_id);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();
            //System.out.println("Before generated keys");
            ResultSet rs = pstmt.getGeneratedKeys();
            //System.out.println("Before rs.next");
            

        } catch (SQLException e) {
            System.out.println("add game + insertinto" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            System.out.println("Record Deleted Successfully");
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("added a game to game_library " + ex.getMessage());
            }
        }
    
    }

    
    
    
    @FXML

    private void CreateSQLiteTable() {
        // SQL statement for creating a new table
//        String sql ="Create TABLE IF NOT EXISTS Mod (\n"
//        + "	id integer PRIMARY KEY,\n"
//        + "	name text NOT NULL,\n"
//        + "	publisher text NOT NULL,\n"
//        + "	price real NOT NULL,\n"
//        + "	game_id text NOT NULL,\n"
//        + "	FOREIGN KEY(game_id) REFERENCES Game(id) ON DELETE CASCADE ON UPDATE CASCADE\n"
//                
//        + ");";
        
        String sql = "CREATE TABLE IF NOT EXISTS `mod` (\n"
            + "`id` int NOT NULL AUTO_INCREMENT,\n"
            + "`title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,\n"
            + "`publisher` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,\n"
            + "`price` double NOT NULL,\n"
            + "`game_id` int NOT NULL,\n"
            + "PRIMARY KEY (`id`),\n"
            + "KEY `mod game_id_idx` (`game_id`),\n"
            + "CONSTRAINT `mod game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n"
          + ") ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(int id, int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = getConnection();

            String sql = "DELETE FROM game_store.mod WHERE id=" + Integer.toString(id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("Delete Mod");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Mod mod = (Mod) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + mod.getId());
                //System.out.println("Title: " + movie.getTitle());
                //System.out.println("Year: " + movie.getYear());
                //System.out.println("Rating: " + movie.getRating());
                deleteRecord(mod.getId(), selectedIndex);
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
        Mod mod = (Mod) tableView.getSelectionModel().getSelectedItem();
        System.out.println("ID: " + mod.getId());
       // System.out.println("Title: " + movie.getTitle());
        //System.out.println("Year: " + movie.getYear());
      //  System.out.println("Rating: " + movie.getRating());

        nameTextField.setText(mod.getName());
        publisherTextField.setText(mod.getPublisher());
        priceTextField.setText(Float.toString(mod.getPrice()));
        game_IdTextField.setText(Integer.toString(mod.getGame_id()));
        //ratingTextField.setText(movie.getRating());

        String content = "Id= " + mod.getId(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Mod> search(String _name,String _publisher, String _price, String _game_id)throws SQLException {
   // String _title, String _year, String _rating) throws SQLException {
        ObservableList<Mod> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.mod where true";
        if (!_name.isEmpty()) {
            sql += " and title like '%" + _name + "%'";
        }
        if (!_publisher.isEmpty()) {
            sql += " and publisher like '%" + _publisher + "%'";
        }
    if (!_price.isEmpty()) {
            sql += " and price like '%" + _price + "%'";
        }
    if (!_game_id.isEmpty()) {
            sql += " and game_id like '%" + _game_id + "%'";
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

            int recordId = rs.getInt("id");
            String name = rs.getString("title");
            String publisher = rs.getString("publisher");
            float price = Float.parseFloat(rs.getString("price"));
            int game_id = Integer.parseInt(rs.getString("game_id"));
           
                   // String title = rs.getString("title");
                 //   int year = Integer.parseInt(rs.getString("year"));
                  

                    Mod mod = new Mod(recordId, name, publisher, price, game_id);
                    searchResult.add(mod);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _name = nameTextField.getText().trim();
        String _publisher = publisherTextField.getText().trim();
        String _price = priceTextField.getText().trim();
        String _game_id = game_IdTextField.getText().trim();
        ObservableList<Mod> tableItems = search(_name, _publisher, _price, _game_id);
        tableView.setItems(tableItems);

    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(mod_data);
    }


    public void update(String name, String publisher, String price, String game_id, int selectedIndex, int id) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = getConnection();
            String sql = "UPDATE game_store.mod SET title = ?, publisher = ?, price = ?, game_id = ? Where id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, publisher);
            pstmt.setString(3, price);
            pstmt.setString(4, game_id);
            pstmt.setString(5, Integer.toString(id));
            System.out.println("Hey buddy");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("update updateaccount" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod1.class.getName()).log(Level.SEVERE, null, ex);
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
                Mod mod = (Mod) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + mod.getId());

                try {
                    // insert a new rows
                    update(nameTextField.getText(),publisherTextField.getText(),priceTextField.getText(),game_IdTextField.getText(), selectedIndex, mod.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                nameTextField.setText("");
                publisherTextField.setText("");
                priceTextField.setText("");
                game_IdTextField.setText("");

                footerLabel.setText("Record updated successfully!");
               mod_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(mod_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("title: " + nameTextField.getText() + "\nPublisher: " + publisherTextField.getText() + "\nPrice: " + priceTextField.getText() + "\nGame ID: " + game_IdTextField.getText());

        try {
            // insert a new row
            insert(nameTextField.getText(), publisherTextField.getText(), Float.parseFloat(priceTextField.getText()), Integer.parseInt(game_IdTextField.getText()));

            System.out.println("Data was inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

             nameTextField.setText("");
            publisherTextField.setText("");
            priceTextField.setText("");
            game_IdTextField.setText("");

        footerLabel.setText("Record inserted into table successfully!");

    }

    @FXML
    private void sidebarDeleteRecord() {
        System.out.println("Delete Mod");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Mod mod = (Mod) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + mod.getId());
                System.out.println("Name: " + mod.getName());
                System.out.println("Publisher: " + mod.getPublisher());
                System.out.println("Price: " + mod.getPrice());
                System.out.println("Game ID: " + mod.getGame_id());
                deleteRecord(mod.getId(), selectedIndex);
            }

        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
       String _name = nameTextField.getText().trim();
        String _publisher = publisherTextField.getText().trim();
        String _price = priceTextField.getText().trim();
        String _game_id = game_IdTextField.getText().trim();
        ObservableList<Mod> tableItems = search(_name,_publisher,_price,_game_id);
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
                Mod mod = (Mod) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + mod.getId());

                try {
                    // insert a new rows
                    update(nameTextField.getText(), publisherTextField.getText(), priceTextField.getText(), game_IdTextField.getText(), selectedIndex, mod.getId());

                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    System.out.println("sidebarupdaterecord" + ex.toString());
                }

                nameTextField.setText("");
                publisherTextField.setText("");
                priceTextField.setText("");
                game_IdTextField.setText("");

                footerLabel.setText("Record updated successfully!");
                mod_data.clear();
                loadData();
                tableView.refresh();
            }

        }
    }
    
}
