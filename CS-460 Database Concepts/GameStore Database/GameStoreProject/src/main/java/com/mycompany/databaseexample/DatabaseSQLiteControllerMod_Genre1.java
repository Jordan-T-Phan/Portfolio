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


public class DatabaseSQLiteControllerMod_Genre1 implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField mod_IdTextField, genre_IdTextField;


    @FXML
    Label footerLabel;
    @FXML
    TableColumn mod_Id = new TableColumn("Mod ID");
    TableColumn genre_Id = new TableColumn("Genre ID");
   

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
    
    public DatabaseSQLiteControllerMod_Genre1() throws SQLException {
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
        mod_Id = new TableColumn("Mod ID");
        mod_Id.setMinWidth(50);
        mod_Id.setCellValueFactory(new PropertyValueFactory<Mod_Genre, Integer>("mod_id"));
        genre_Id = new TableColumn("Genre ID");
       genre_Id.setMinWidth(50);
        genre_Id.setCellValueFactory(new PropertyValueFactory<Mod_Genre, Integer>("genre_id"));

       
    
        tableView.setItems(mod_genre_data);
        tableView.getColumns().addAll(mod_Id,genre_Id);

        
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
            String sql = "SELECT * FROM game_store.mod_genre;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Mod_Genre mod_Genre;
                mod_Genre = new Mod_Genre(rs.getInt("mod_id"),rs.getInt("genre_id"));
              System.out.println(mod_Genre.getMod_id() + " - " + mod_Genre.getGenre_id());
                      
              
              mod_genre_data.add(mod_Genre);
              
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(" loaddata + modgenre " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Genre1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("loaddata + modgenre + connection.close " + ex.getMessage());
            }
        }
          
         
         
    }


    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Mod Genre Database");

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
     * Insert a new row into the mod_genre table
     *
     * @param mod_id
     * @param genre_id
     * @throws java.sql.SQLException
     */
    
    public void insert(int mod_id, int genre_id) throws SQLException{
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

            conn = getConnection();

            System.out.println("Connection to SQLite has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO game_store.mod_genre(mod_id,genre_id) VALUES(?,?)";
            //System.out.println("Before prepare statement");
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Before set strings");
            pstmt.setString(1, Integer.toString((mod_id)));
            pstmt.setString(2, Integer.toString(genre_id));
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
            Logger.getLogger(DatabaseSQLiteControllerMod_Genre1.class.getName()).log(Level.SEVERE, null, ex);
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
  
        mod_genre_data.add(new Mod_Genre(mod_id,genre_id));
    }

    public void handleAddMod_Genre(ActionEvent actionEvent) {

        System.out.println("Mod ID: " + mod_IdTextField.getText() + "\nGenre ID: " + genre_IdTextField.getText());

        try {
            // insert a new rows
            insert(Integer.parseInt(mod_IdTextField.getText()), Integer.parseInt(genre_IdTextField.getText()));
            System.out.println("Data was inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("handleaddaccount " + ex.toString());
        }

        mod_IdTextField.setText("");
        genre_IdTextField.setText("");
        footerLabel.setText("Record inserted into table successfully!");
    }
    @FXML
    private void CreateSQLiteTable() {
        // SQL statement for creating a new table
//        String sql ="Create TABLE IF NOT EXISTS Mod_Genre (\n"
//        + "	mod_id INTEGER,\n"
//        + "	genre_id Integer,\n"
//        + "	FOREIGN KEY(genre_id) REFERENCES Genre(id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
//        + "	FOREIGN KEY(mod_id) REFERENCES Mod(id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
//        + "	PRIMARY KEY(mod_id,genre_id)\n"
//        + ");";

        String sql = "CREATE TABLE IF NOT EXISTS `mod_genre` (\n"
        + "`mod_id` int NOT NULL,\n"
        + "`genre_id` int NOT NULL,\n"
        + "PRIMARY KEY (`mod_id`,`genre_id`),\n"
        + "KEY `mod_genre_genre_id_idx` (`genre_id`),\n"
        + "CONSTRAINT `mod_genre_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\n"
        + "CONSTRAINT `mod_genre_mod_id` FOREIGN KEY (`mod_id`) REFERENCES `mod` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
       System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Genre1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(int mod_id, int genre_id,int selectedIndex ) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = getConnection();

            String sql = "DELETE FROM game_store.mod_genre WHERE mod_id=" + Integer.toString(mod_id)+" AND genre_id="+Integer.toString(genre_id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("deleterecord + deletefrom" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Genre1.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("Delete Mod_Genre");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Mod_Genre mod_genre = (Mod_Genre) tableView.getSelectionModel().getSelectedItem();
                System.out.println("MOD ID: " + mod_genre.getMod_id());
                System.out.println("Genre ID: " + mod_genre.getGenre_id());
                //System.out.println("Title: " + movie.getTitle());
                //System.out.println("Year: " + movie.getYear());
                //System.out.println("Rating: " + movie.getRating());
                deleteRecord(mod_genre.getMod_id(),mod_genre.getGenre_id() , selectedIndex);
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
        Mod_Genre mod_genre = (Mod_Genre) tableView.getSelectionModel().getSelectedItem();
        System.out.println("ID: " + mod_genre.getMod_id());
       // System.out.println("Title: " + movie.getTitle());
        //System.out.println("Year: " + movie.getYear());
      //  System.out.println("Rating: " + movie.getRating());

          mod_IdTextField.setText(Integer.toString(mod_genre.getMod_id()));
        genre_IdTextField.setText(Integer.toString(mod_genre.getGenre_id()));
        //ratingTextField.setText(movie.getRating());

        //String content = "Id= " + mod.getId(); 

    }

@SuppressWarnings("empty-statement")
    public ObservableList<Mod_Genre> search(String _mod_id,String _genre_id)throws SQLException {
   // String _title, String _year, String _rating) throws SQLException {
        ObservableList<Mod_Genre> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from game_store.mod_genre where true";
        if (!_mod_id.isEmpty()) {
            sql += " and mod_id like '%" + _mod_id + "%'";
        }
        if (!_genre_id.isEmpty()) {
            sql += " and genre_id like '%" + _genre_id + "%'";
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
            int genre_id = rs.getInt("genre_id");
            int mod_id= rs.getInt("mod_id");
            
           
                   // String title = rs.getString("title");
                 //   int year = Integer.parseInt(rs.getString("year"));
                  

                    Mod_Genre mod_genre = new Mod_Genre(mod_id,genre_id);
                    searchResult.add(mod_genre);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("search " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Genre1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchResult;
    }

    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        String _mod_id=mod_IdTextField.getText().trim();
        String _genre_id=genre_IdTextField.getText().trim();
       
        ObservableList<Mod_Genre> tableItems = search(_mod_id, _genre_id);
        tableView.setItems(tableItems);

    }

    @FXML
    private void handleShowAllRecords(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(mod_genre_data);
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
    public void update(String mod_id, String genre_id, int selectedIndex) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = getConnection();
            String sql = "UPDATE game_store.mod_genre SET mod_id = ?, genre_id = ? Where id = ?";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, mod_id);
            pstmt.setString(2, genre_id);
        
            System.out.println("Hey buddy");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("update updateaccount" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerMod_Genre1.class.getName()).log(Level.SEVERE, null, ex);
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
                Mod_Genre mod_genre = (Mod_Genre) tableView.getSelectionModel().getSelectedItem();
                System.out.println("MOD ID: " + mod_genre.getMod_id());
                System.out.println("Genre ID: " + mod_genre.getGenre_id());

                try {
                    // insert a new rows
                    update(mod_IdTextField.getText(),genre_IdTextField.getText(),selectedIndex);
                    
                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    
                    System.out.println("Handleupdaterecord " + ex.toString());
                }
                mod_IdTextField.setText("");
               genre_IdTextField.setText("");
                

                footerLabel.setText("Record updated successfully!");
               mod_genre_data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

    @FXML
    private void sidebarShowAllRecords() {
        tableView.setItems(mod_genre_data);
    }


    @FXML
    private void sidebarAddNewRecord() {
        System.out.println("Mod ID: " + mod_IdTextField.getText() + "\nGenre Id: " + genre_IdTextField.getText());
        

        try {
            // insert a new row
            insert(Integer.parseInt(mod_IdTextField.getText()), Integer.parseInt(genre_IdTextField.getText()));

            System.out.println("Data was inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Sidebaraddnewrecord " + ex.toString());
        }

             mod_IdTextField.setText("");
            genre_IdTextField.setText("");
                

        footerLabel.setText("Record inserted into table successfully!");

    }

    @FXML
    private void sidebarDeleteRecord() {
        System.out.println("Delete Mod_Genre");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                System.out.println(index);
                Mod_Genre mod_genre = (Mod_Genre) tableView.getSelectionModel().getSelectedItem();
                System.out.println("MOD ID: " + mod_genre.getMod_id());
                System.out.println("Genre ID: " + mod_genre.getGenre_id());
               
                deleteRecord(mod_genre.getMod_id(),mod_genre.getGenre_id(), selectedIndex);
            }

        }
    }

    @FXML
    private void sidebarSearch() throws SQLException {
        String _mod_id=mod_IdTextField.getText().trim();
        String _genre_id=genre_IdTextField.getText().trim();
  ObservableList<Mod_Genre> tableItems = search(_mod_id, _genre_id);
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
                Mod_Genre mod_genre = (Mod_Genre) tableView.getSelectionModel().getSelectedItem();
                //System.out.println("ID: " + mod_genre.getId());

                try {
                    // insert a new rows
                    update(mod_IdTextField.getText(),genre_IdTextField.getText(),selectedIndex);

                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    System.out.println("sidebarupdaterecord" + ex.toString());
                }

               mod_IdTextField.setText("");
                genre_IdTextField.setText("");
               
             

                footerLabel.setText("Record updated successfully!");
                mod_genre_data.clear();
                loadData();
                tableView.refresh();
            }

        }
    }
    
}
