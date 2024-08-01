package com.mycompany.databaseexample;


import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.DRIVER;
import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.databaseURL;
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


public class DatabaseSQLiteControllerGame1 implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField titleTextField, publisherTextField, orig_priceTextField, salePercentTextField, userRatingTextField, downloadsTextField;


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



public static Connection getConnection() throws ClassNotFoundException {
    Class.forName(DRIVER);
    Connection connection = null;
    try {
        
        connection = DriverManager.getConnection(databaseURL);
    } catch (SQLException ex) {}
    return connection;
}
    /* Connect to a sample database
     */
    private ObservableList<Account> acc_data;
    private ObservableList<Game> game_data;
    private ObservableList<Mod> mod_data;
    private ObservableList<GameGenre> gameGenre_data;
    private ObservableList<Genre> genre_data;
    private ObservableList<Mod_Genre> mod_genre_data;
    private ObservableList<Game_Library> game_library_data;
    private ObservableList<Mod_Library> mod_library_data;
   


    /*
       ArrayList: Resizable-array implementation of the List interface. 
       Implements all optional list operations, and permits all elements, including null.
       ObservableList: A list that allows listeners to track changes when they occur
    */
    
    
    public DatabaseSQLiteControllerGame1() throws SQLException {
        this.acc_data = FXCollections.observableArrayList();
        this.game_data = FXCollections.observableArrayList();
        this.mod_data = FXCollections.observableArrayList();
        this.gameGenre_data = FXCollections.observableArrayList();
        this.genre_data = FXCollections.observableArrayList();
        this.mod_genre_data = FXCollections.observableArrayList();
        this.game_library_data = FXCollections.observableArrayList();
        this.mod_library_data = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("class for name!");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
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
        id.setCellValueFactory(new PropertyValueFactory<Game, Integer>("id"));

        TableColumn title = new TableColumn("Title");
        title.setMinWidth(100);
        title.setCellValueFactory(new PropertyValueFactory<Game, String>("title"));
        
        TableColumn publisher = new TableColumn("Publisher");
        publisher.setMinWidth(200);
        publisher.setCellValueFactory(new PropertyValueFactory<Game,String>("publisher"));

        TableColumn orig_price = new TableColumn("Original Price");
        orig_price.setMinWidth(100);
        orig_price.setCellValueFactory(new PropertyValueFactory<Game, Float>("orig_price"));

        TableColumn salePercent = new TableColumn("Sale Percent");
        salePercent.setMinWidth(100);
        salePercent.setCellValueFactory(new PropertyValueFactory<Game, Float>("salePercent"));

        TableColumn userRating = new TableColumn("User Rating");
        userRating.setMinWidth(100);
        userRating.setCellValueFactory(new PropertyValueFactory<Game, Float>("userRating"));
        
        TableColumn downloads = new TableColumn("Downloads");
        downloads.setMinWidth(200);
        downloads.setCellValueFactory(new PropertyValueFactory<Game,Integer>("downloads"));
        
        

        
        
        tableView.setItems(game_data);
        tableView.getColumns().addAll(id, title, publisher, orig_price, salePercent, userRating, downloads);

        
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
            String sql = "SELECT * FROM game_store.game;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Game game;
                game = new Game(rs.getInt("id"),rs.getString("title"),rs.getString("publisher"),rs.getFloat("orig_price"),rs.getFloat("salePercent"), rs.getFloat("userRating"),rs.getInt("downloads"));

                game_data.add(game);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + game" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + game + connection.close " + ex.getMessage());
            }
        }
         
         
         
         
    }


    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Game Database");

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
     * Insert a new row into the games table
     *
     * @param title
     * @param publisher
     * @param orig_price
     * @param salePercent
     * @param userRating
     * @param downloads
     * @throws java.sql.SQLException
     */
    public void insert(String title, String publisher, float orig_price, float salePercent, float userRating, int downloads) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

             conn = getConnection();

            System.out.println("Connection to SQLite has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO Game(title,publisher,orig_price,salePercent,userRating,downloads) VALUES(?,?,?,?,?,?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
            pstmt.setString(1, title);
            pstmt.setString(2, publisher);
            pstmt.setFloat(3, orig_price);
            pstmt.setFloat(4, salePercent);
            pstmt.setFloat(5, userRating);
            pstmt.setInt(6, downloads);
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
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
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
  
        game_data.add(new Game(last_inserted_id,title,publisher,orig_price,salePercent,userRating,downloads));
    }

    public void handleAddGame(ActionEvent actionEvent) throws SQLException{

        System.out.println("Title: " + titleTextField.getText() + "\nPublisher: " + publisherTextField.getText() + "\nOriginal Price: " + orig_priceTextField.getText() + "\nSale Percent: " + salePercentTextField.getText() + "\nUser Rating: " + userRatingTextField.getText() + "\nDownloads: " + downloadsTextField.getText());

        try {
            // insert a new rows
            insert(titleTextField.getText(),publisherTextField.getText(),Float.parseFloat(orig_priceTextField.getText()), Float.parseFloat(salePercentTextField.getText()), Float.parseFloat(userRatingTextField.getText()),Integer.parseInt(downloadsTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddgame " + ex.toString());
        }

        titleTextField.setText("");
        publisherTextField.setText("");
        orig_priceTextField.setText("");
        salePercentTextField.setText("");
        userRatingTextField.setText("");
        downloadsTextField.setText("");
        

        footerLabel.setText("Record inserted into table successfully!");
    }
    public void handleAddGame_toClient(ActionEvent actionEvent) {

      //  System.out.println("Title: " + titleTextField.getText() + "\nPublisher: " + publisherTextField.getText() + "\nOriginal Price: " + orig_priceTextField.getText() + "\nSale Percent: " + salePercentTextField.getText() + "\nUser Rating: " + userRatingTextField.getText() + "\nDownloads: " + downloadsTextField.getText());

        try {
             if (tableView.getSelectionModel().getSelectedItem() != null) {
             Game game = (Game) tableView.getSelectionModel().getSelectedItem();
             add_game_to_client_library(game.getId());
             
             }
            // insert a new rows
           // insert(titleTextField.getText(),publisherTextField.getText(),Float.parseFloat(orig_priceTextField.getText()), Float.parseFloat(salePercentTextField.getText()), Float.parseFloat(userRatingTextField.getText()),Integer.parseInt(downloadsTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddgame " + ex.toString());
        }

        titleTextField.setText("");
        publisherTextField.setText("");
        orig_priceTextField.setText("");
        salePercentTextField.setText("");
        userRatingTextField.setText("");
        downloadsTextField.setText("");
        

        footerLabel.setText("Record inserted into table successfully!");
    }
    @FXML

    private void CreateSQLiteTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS game_store.game (\n"
        + "    id int NOT NULL AUTO_INCREMENT,\n"
        + "    title text NOT NULL,\n"
        + "    publisher text NOT NULL,\n"
        + "    orig_price double NOT NULL,\n"
        + "    salePercent double NOT NULL,\n"
        + "     userRating double NOT NULL,\n"
        + "    downloads int NOT NULL,\n"
        + "    PRIMARY KEY (id)"
        + ") ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        try (Connection  conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void add_game_to_client_library(int game_id) throws SQLException{
    
    
    Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            int acc_id = MainController.getLoggedIn_Acc_ID();
            // create a connection to the database
             conn = getConnection();
              String sql = "INSERT INTO `game_store`.`game_library`(acc_id,game_id,hours_played) VALUES(?,?,?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
           
            pstmt.setInt(1, acc_id);
            pstmt.setInt(2, game_id);
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

    public void deleteRecord(int id, int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
             conn = getConnection();

            String sql = "DELETE FROM game_store.game WHERE id=" + Integer.toString(id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("Delete Game");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Game game = (Game) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + game.getId());
                //System.out.println("Title: " + movie.getTitle());
                //System.out.println("Year: " + movie.getYear());
                //System.out.println("Rating: " + movie.getRating());
                deleteRecord(game.getId(), selectedIndex);
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
        Game game = (Game) tableView.getSelectionModel().getSelectedItem();
        System.out.println("ID: " + game.getId());

        titleTextField.setText(game.getTitle());
        publisherTextField.setText(game.getPublisher());
        orig_priceTextField.setText(Float.toString(game.getOrig_price()));
        salePercentTextField.setText(Float.toString(game.getSalePercent()));
        userRatingTextField.setText(Float.toString(game.getUserRating()));
        downloadsTextField.setText(Integer.toString(game.getDownloads()));


        String content = "Id= " + game.getId(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Game> search(String _title, String _publisher, String _orig_price, String _salePercent, String _userRating,String _downloads)throws SQLException {
   // String _title, String _year, String _rating) throws SQLException {
        ObservableList<Game> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.game where true";
        if (!_title.isEmpty()) {
            sql += " and title like '%" + _title + "%'";
        }
        if (_publisher.isEmpty()) {
            sql += " and publisher like '%" + _publisher + "%'";
        }
        if (!_orig_price.isEmpty()) {
            sql += " and orig_price like '%" + _orig_price + "%'";
        }
        if (!_salePercent.isEmpty()) {
            sql += " and salePercent like '%" + _salePercent + "%'";
        }
        if (!_userRating.isEmpty()) {
            sql += " and userRating like '%" + _userRating + "%'";
        }
        if (!_downloads.isEmpty()) {
            sql += " and downloads like '%" + _downloads + "%'";
        }



      

        System.out.println(sql);
        try (Connection  conn = getConnection();
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
            String title = rs.getString("title");
            String publisher = rs.getString("publisher");
            float orig_price = Float.parseFloat(rs.getString("orig_price"));
            float salePercent = Float.parseFloat(rs.getString("salePercent"));
            float userRating = Float.parseFloat(rs.getString("userRating"));
            int downloads = Integer.parseInt(rs.getString("downloads"));

            Game game = new Game(recordId,title,publisher,orig_price,salePercent,userRating,downloads);
            searchResult.add(game);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _title = titleTextField.getText().trim();
        String _publisher = publisherTextField.getText().trim();
        String _orig_price = orig_priceTextField.getText().trim();
        String _salePercent = salePercentTextField.getText().trim();
        String _userRating = userRatingTextField.getText().trim();
        String _downloads = downloadsTextField.getText().trim();


        ObservableList<Game> tableItems = search(_title,_publisher, _orig_price, _salePercent, _userRating, _downloads);
        tableView.setItems(tableItems);

    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(game_data);
    }

    /**
     * Update a record in the accounts table
     *
     * @param title
     * @param publisher
     * @param orig_price
     * @param salePercent
     * @param userRating
     * @param downloads
     * 
     * @throws java.sql.SQLException
     */
    public void update(String title, String publisher,String orig_price, String salePercent, String userRating, String downloads,  int selectedIndex, int id) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
             conn = getConnection();
            String sql = "UPDATE game_store.game SET title = ?, publisher = ?, orig_price = ?, salePercent = ?, userRating = ?, downloads = ? Where id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
             pstmt.setString(2, publisher);
            pstmt.setString(3, orig_price);
            pstmt.setString(4, salePercent);
            pstmt.setString(5, userRating);
            pstmt.setString(6, downloads);
            pstmt.setString(7, Integer.toString(id));

            System.out.println("Hey buddy");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("update updategame" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGame1.class.getName()).log(Level.SEVERE, null, ex);
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
                Game game = (Game) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + game.getId());

                try {
                    // insert a new rows
                    update(titleTextField.getText(),publisherTextField.getText(),orig_priceTextField.getText(),salePercentTextField.getText(),userRatingTextField.getText(),downloadsTextField.getText(), selectedIndex, game.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                titleTextField.setText("");
                publisherTextField.setText("");
                orig_priceTextField.setText("");
                salePercentTextField.setText("");
                userRatingTextField.setText("");
                downloadsTextField.setText("");
                

                footerLabel.setText("Record updated successfully!");
                game_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(game_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("Title: " + titleTextField.getText() + "\nPublisher: " + publisherTextField.getText() + "\nOriginal Price: " + orig_priceTextField.getText() + "\nSale Percent: " + salePercentTextField.getText() + "\nUser Rating: " + userRatingTextField.getText() + "\nDownloads: " + downloadsTextField.getText());

        try {
            // insert a new row
            insert(titleTextField.getText(), publisherTextField.getText(),Float.parseFloat(orig_priceTextField.getText()), Float.parseFloat(salePercentTextField.getText()), Float.parseFloat(userRatingTextField.getText()),Integer.parseInt(downloadsTextField.getText()));

            System.out.println("Data was inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

                titleTextField.setText("");
                publisherTextField.setText("");
                orig_priceTextField.setText("");
                salePercentTextField.setText("");
                userRatingTextField.setText("");
                downloadsTextField.setText("");


        footerLabel.setText("Record inserted into table successfully!");

    }

    @FXML
    private void sidebarDeleteRecord() {
        System.out.println("Delete Game");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Game game = (Game) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + game.getId());
                System.out.println("Title: " + game.getTitle());
                System.out.println("Publisher: " + game.getPublisher());
                System.out.println("Original Price: " + game.getOrig_price());
                System.out.println("Sale Percent: " + game.getSalePercent());
                System.out.println("User Rating: " + game.getUserRating());
                System.out.println("Downloads: " + game.getDownloads());
                
                deleteRecord(game.getId(), selectedIndex);
            }
        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
        String _title = titleTextField.getText().trim();
        String _publisher = publisherTextField.getText().trim();
        String _orig_price = orig_priceTextField.getText().trim();
        String _salePercent = salePercentTextField.getText().trim();
        String _userRating = userRatingTextField.getText().trim();
        String _downloads = downloadsTextField.getText().trim();
        
        ObservableList<Game> tableItems = search(_title,_publisher, _orig_price, _salePercent, _userRating, _downloads);
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
                Game game = (Game) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + game.getId());

                try {
                    // insert a new rows
                    update(titleTextField.getText(),publisherTextField.getText(),orig_priceTextField.getText(),salePercentTextField.getText(),userRatingTextField.getText(),downloadsTextField.getText(), selectedIndex, game.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Sidebarupdaterecord " + ex.toString());
                }
                titleTextField.setText("");
                publisherTextField.setText("");
                orig_priceTextField.setText("");
                salePercentTextField.setText("");
                userRatingTextField.setText("");
                downloadsTextField.setText("");
                

                footerLabel.setText("Record updated successfully!");
                game_data.clear();
                loadData();
                tableView.refresh();
            }

        }
    }
    
}
