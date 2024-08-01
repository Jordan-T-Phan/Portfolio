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


public class DatabaseSQLiteControllerGenre1 implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField nameTextField;


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
//        SQLiteConfig config = new SQLiteConfig();
//        config.enforceForeignKeys(true);
        connection = DriverManager.getConnection(databaseURL);
    } catch (SQLException ex) {}
    return connection;
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
    
    
    public DatabaseSQLiteControllerGenre1() throws SQLException {
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
        id = new TableColumn("ID");
        id.setMinWidth(50);
        id.setCellValueFactory(new PropertyValueFactory<Genre, Integer>("id"));

        TableColumn name = new TableColumn("name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<Genre, String>("name"));

       
        tableView.setItems(genre_data);
        tableView.getColumns().addAll(id, name);

        
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
            String sql = "SELECT * FROM game_store.genre;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Genre genre;
                genre = new Genre(rs.getInt("id"),rs.getString("name"));
          //      movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("rating"));
              //  System.out.println(movie.getId() + " - " + movie.getTitle() + " - " + movie.getRating() + " - " + movie.getYear());
              System.out.println(genre.getId() +" - " + genre.getName() );
                      
              
              genre_data.add(genre);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("loaddata + genre " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGenre1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + genre + connection.close " + ex.getMessage());
            }
        }
        
         
         
    }


    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Genre Database");

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
    public void insert(String name) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

          conn = getConnection();

            System.out.println("Connection to SQLite has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO game_store.genre(name) VALUES(?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
            pstmt.setString(1, name);
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
            Logger.getLogger(DatabaseSQLiteControllerGenre1.class.getName()).log(Level.SEVERE, null, ex);
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
  
        genre_data.add(new Genre(last_inserted_id,name));
    }

    public void handleAddGenre(ActionEvent actionEvent) {

        System.out.println("Name: " + nameTextField.getText());

        try {
            // insert a new rows
            insert(nameTextField.getText());
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddaccount " + ex.toString());
        }

        nameTextField.setText("");
        footerLabel.setText("Record inserted into table successfully!");
    }
    @FXML
    private void CreateSQLiteTable() {
        // SQL statement for creating a new table
//        String sql ="Create TABLE IF NOT EXISTS Genre (\n"
//        + "	id integer PRIMARY KEY,\n"
//        + "	name text NOT NULL UNIQUE\n"
//        + ");";
        String sql = "CREATE TABLE IF NOT EXISTS `genre` (\n"
        + "`id` int NOT NULL AUTO_INCREMENT,\n"
        + "`name` varchar(15) NOT NULL,\n"
        + "PRIMARY KEY (`id`),\n"
        + "UNIQUE KEY `name_UNIQUE` (`name`)\n"
        + ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGenre1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(int id, int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = getConnection();

            String sql = "DELETE FROM game_store.genre WHERE id=" + Integer.toString(id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGenre1.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("Delete Genre");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Genre genre = (Genre) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + genre.getId());
                //System.out.println("Title: " + movie.getTitle());
                //System.out.println("Year: " + movie.getYear());
                //System.out.println("Rating: " + movie.getRating());
                deleteRecord(genre.getId(), selectedIndex);
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
        Genre genre = (Genre) tableView.getSelectionModel().getSelectedItem();
        System.out.println("ID: " + genre.getId());
       // System.out.println("Title: " + movie.getTitle());
        //System.out.println("Year: " + movie.getYear());
      //  System.out.println("Rating: " + movie.getRating());

        nameTextField.setText(genre.getName());
       
        //ratingTextField.setText(movie.getRating());

        String content = "Id= " + genre.getId(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Genre> search(String _name)throws SQLException {
   // String _title, String _year, String _rating) throws SQLException {
        ObservableList<Genre> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.genre where true";
        if (!_name.isEmpty()) {
            sql += " and name like '%" + _name + "%'";
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
            String name = rs.getString("name");
           
           
                   // String title = rs.getString("title");
                 //   int year = Integer.parseInt(rs.getString("year"));
                  

                    Genre genre = new Genre(recordId, name);
                    searchResult.add(genre);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGenre1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _name = nameTextField.getText().trim();
       
        ObservableList<Genre> tableItems = search(_name);
        tableView.setItems(tableItems);

    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(genre_data);
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
    public void update(String name,int selectedIndex, int id) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = getConnection();
            String sql = "UPDATE game_store.genre SET name = ? Where id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
           
            System.out.println("Hey buddy");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("update updategenre" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerGenre1.class.getName()).log(Level.SEVERE, null, ex);
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
                Genre genre = (Genre) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + genre.getId());

                try {
                    // insert a new rows
                    update(nameTextField.getText(), selectedIndex, genre.getId());
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                nameTextField.setText("");
                

                footerLabel.setText("Record updated successfully!");
               genre_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(genre_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("name: " + nameTextField.getText());

        try {
            // insert a new row
            insert(nameTextField.getText());

            System.out.println("Data was inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

             nameTextField.setText("");
               

        footerLabel.setText("Record inserted into table successfully!");

    }

    @FXML
    private void sidebarDeleteRecord() {
        System.out.println("Delete Genre");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Genre genre = (Genre) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + genre.getId());
                System.out.println("Name: " + genre.getName());
               
                deleteRecord(genre.getId(), selectedIndex);
            }

        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
       String _name = nameTextField.getText().trim();
      
        ObservableList<Genre> tableItems = search(_name);
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
                Genre genre = (Genre) tableView.getSelectionModel().getSelectedItem();
                System.out.println("ID: " + genre.getId());

                try {
                    // insert a new rows
                    update(nameTextField.getText(),selectedIndex, genre.getId());

                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    System.out.println("sidebarupdaterecord" + ex.toString());
                }

               nameTextField.setText("");
                
                footerLabel.setText("Record updated successfully!");
                genre_data.clear();
                loadData();
                tableView.refresh();
            }

        }
    }
    
}
