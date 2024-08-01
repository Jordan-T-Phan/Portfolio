package com.mycompany.databaseexample;




import static com.mycompany.databaseexample.DatabaseSQLiteControllerAccountDetail1.getConnection;
import static com.mycompany.databaseexample.MainController.getCurrentTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.TextArea;
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




public class TransactionsController{

    @FXML
    private TableView tableView;

    @FXML
    private VBox vBox;

    @FXML
    private TextArea firstFilePrint, secondFilePrint;


    @FXML
    Label footerLabel;
    @FXML
    TableColumn id = new TableColumn("ID");
    
    private static String filePath = "C://Users/Jordan/Desktop/460GameProject/SQL for Transactions/"; 
    private static String existingPath = "C://ProgramData/MySQL/MySQL Server 8.0/Uploads/";
    // Change file paths in dirtyread, lostupdate
    public static void importSQL(Connection conn, InputStream in) throws SQLException, IOException
    {
        Scanner s = new Scanner(in);
        s.useDelimiter("(;(\r)?\n)|(--\n)");
        Statement st = null;
        try
        {
            st = conn.createStatement();
            while (s.hasNext())
            {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/"))
                {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0)
                {
                    st.execute(line);
                }
            }
        } 
        finally
        {
            
            if (st != null) st.close();
            in.close();
        }
        
    }
    
    public void readFiles1() throws IOException {
//        File file1 = new File(existingPath + "sd1.txt");
//        File file2 = new File(existingPath + "sd2.txt");
        
        String content1 = new String(Files.readAllBytes(Paths.get(existingPath + "sd1.txt")));
        firstFilePrint.setText(content1);
        System.out.println("Successfully Read First File!");
        
    }
    
    public void readFiles2() throws IOException {
//        File file1 = new File(existingPath + "sd1.txt");
//        File file2 = new File(existingPath + "sd2.txt");
        
        String content2 = new String(Files.readAllBytes(Paths.get(existingPath + "sd2.txt")));
        secondFilePrint.setText(content2);
        System.out.println("Successfully Read Second File!");
        
    }
    
    
    @FXML
    public void DeadLock1() throws FileNotFoundException, IOException{
        firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "deadlock1-delay.sql");
//            File existingTXT = new File(existingPath + "sd1.txt");
//            if (existingTXT.delete()) {
//                System.out.println("Deleted file");
//            }
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            //readFiles1();
            System.out.println("deadlock1.sql");
        } catch (SQLException e) {
            firstFilePrint.setText(e.getMessage());
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void DeadLock2() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "deadlock2-delay.sql");
//            File existingTXT = new File(existingPath + "sd2.txt");
//            if (existingTXT.delete()) {
//                System.out.println("Deleted file");
//            }
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            //readFiles2();
            System.out.println("deadlock2.sql");
        } catch (SQLException e) {
            firstFilePrint.setText(e.getMessage());
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    
//    @FXML
//    public void DeadLock1_2() throws FileNotFoundException, IOException{
//        try (Connection conn = getConnection()) {
//            File file = new File(filePath + "deadlock1-2.sql");
////            File existingTXT = new File(existingPath + "sd1.txt");
////            if (existingTXT.delete()) {
////                System.out.println("Deleted file");
////            }
//            InputStream targetStream = new FileInputStream(file);
//            importSQL(conn, targetStream);
//            //readFiles1();
//            System.out.println("deadlock1-2.sql");
//        } catch (SQLException e) {
//            System.out.println("createsqlitetable " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @FXML
//    public void DeadLock2_2() throws FileNotFoundException, IOException{
//        try (Connection conn = getConnection()) {
//            File file = new File(filePath + "deadlock2-2.sql");
////            File existingTXT = new File(existingPath + "sd2.txt");
////            if (existingTXT.delete()) {
////                System.out.println("Deleted file");
////            }
//            InputStream targetStream = new FileInputStream(file);
//            importSQL(conn, targetStream);
//            //readFiles2();
//            System.out.println("deadlock2-2.sql");
//        } catch (SQLException e) {
//            System.out.println("createsqlitetable " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @FXML
//    public void DeadLock1_3() throws FileNotFoundException, IOException{
//        try (Connection conn = getConnection()) {
//            File file = new File(filePath + "deadlock1-3.sql");
////            File existingTXT = new File(existingPath + "sd1.txt");
////            if (existingTXT.delete()) {
////                System.out.println("Deleted file");
////            }
//            InputStream targetStream = new FileInputStream(file);
//            importSQL(conn, targetStream);
//            //readFiles1();
//            System.out.println("deadlock1-3.sql");
//        } catch (SQLException e) {
//            System.out.println("createsqlitetable " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @FXML
//    public void DeadLock2_3() throws FileNotFoundException, IOException{
//        try (Connection conn = getConnection()) {
//            File file = new File(filePath + "deadlock2-3.sql");
////            File existingTXT = new File(existingPath + "sd2.txt");
////            if (existingTXT.delete()) {
////                System.out.println("Deleted file");
////            }
//            InputStream targetStream = new FileInputStream(file);
//            importSQL(conn, targetStream);
//            //readFiles2();
//            System.out.println("deadlock2-3.sql");
//        } catch (SQLException e) {
//            System.out.println("createsqlitetable " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @FXML
//    public void DeadLock1_4() throws FileNotFoundException, IOException{
//        try (Connection conn = getConnection()) {
//            File file = new File(filePath + "deadlock1-4.sql");
////            File existingTXT = new File(existingPath + "sd1.txt");
////            if (existingTXT.delete()) {
////                System.out.println("Deleted file");
////            }
//            InputStream targetStream = new FileInputStream(file);
//            importSQL(conn, targetStream);
//            //readFiles1();
//            System.out.println("deadlock1-4.sql");
//        } catch (SQLException e) {
//            System.out.println("createsqlitetable " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @FXML
//    public void DeadLock2_4() throws FileNotFoundException, IOException{
//        try (Connection conn = getConnection()) {
//            File file = new File(filePath + "deadlock2-4.sql");
////            File existingTXT = new File(existingPath + "sd2.txt");
////            if (existingTXT.delete()) {
////                System.out.println("Deleted file");
////            } 
//            InputStream targetStream = new FileInputStream(file);
//            importSQL(conn, targetStream);
//            //readFiles2();
//            System.out.println("deadlock2-4.sql");
//        } catch (SQLException e) {
//            System.out.println("createsqlitetable " + e.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    @FXML
    public void PhantomRead1() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "phantomread1.sql");
            File existingTXT1 = new File(existingPath + "sd1.txt");
            if (existingTXT1.delete()) {
                System.out.println("Deleted file");
            }
            File existingTXT2 = new File(existingPath + "sd2.txt");
            if (existingTXT2.delete()) {
                System.out.println("Deleted file");
            }
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            readFiles1();
            readFiles2();
//            String sql = "select * from game_store.game where `id` > 98;";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            secondFilePrint.setText(rs.toString());
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void PhantomRead2() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "phantomread2.sql");
//            File existingTXT = new File(existingPath + "sd2.txt");
//            if (existingTXT.delete()) {
//                System.out.println("Deleted file");
//            }
            
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
//            readFiles2();
            
            
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void Non_RepeatableRead1() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "non-repeatableread1.sql");
            File existingTXT1 = new File(existingPath + "sd1.txt");
            if (existingTXT1.delete()) {
                System.out.println("Deleted file 1");
            }
//            File existingTXT2 = new File(existingPath + "sd2.txt");
//            if (existingTXT2.delete()) {
//                System.out.println("Deleted file 2");
//            }
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            readFiles1();
//            readFiles2();
            String sql = "select `title` from game_store.game where `id` = (SELECT MAX(`id`) FROM game_store.game);";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                secondFilePrint.setText(rs.getString("title"));
            }
            
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void Non_RepeatableRead2() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "non-repeatableread2.sql");
            
            
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void LostUpdatePart1_1() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "lostupdatepart1-1.sql");
//            File existingTXT = new File(existingPath + "sd1.txt");
//            if (existingTXT.delete()) {
//                System.out.println("Deleted file");
//            }
            
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
//            readFiles1();
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void LostUpdatePart2_1() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "lostupdatepart2-1.sql");
//            File existingTXT = new File(existingPath + "sd1.txt");
//            if (existingTXT.delete()) {
//                System.out.println("Deleted file");
//            }
            
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
//            readFiles2();
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void LostUpdatePart1_2() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "lostupdatepart1-2.sql");
            File existingTXT = new File(existingPath + "sd1.txt");
            if (existingTXT.delete()) {
                System.out.println("Deleted file");
            }
            
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            readFiles1();
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void LostUpdatePart2_2() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "lostupdatepart2-2.sql");
            File existingTXT = new File(existingPath + "sd2.txt");
            if (existingTXT.delete()) {
                System.out.println("Deleted file");
            }
            
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            readFiles2();
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void DirtyReadPart1() throws FileNotFoundException, IOException {
 firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "dirtyreadpart1.sql");
            File existingTXT = new File(existingPath + "sd1.txt");
            if (existingTXT.delete()) {
                System.out.println("Deleted file");
            }
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            readFiles1();
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void DirtyReadPart2() throws FileNotFoundException, IOException{
         firstFilePrint.setText("");
        secondFilePrint.setText("");
        try (Connection conn = getConnection()) {
            File file = new File(filePath + "dirtyreadpart2.sql");
            File existingTXT = new File(existingPath + "sd2.txt");
            if (existingTXT.delete()) {
                System.out.println("Deleted file");
            }
            
            
            InputStream targetStream = new FileInputStream(file);
            importSQL(conn, targetStream);
            readFiles2();
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println("createsqlitetable " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseSQLiteControllerAudit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
