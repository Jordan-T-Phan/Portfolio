import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
//Jordan Phan Maze Algorithm Main
  //      CS 463-01
    //    2/7/2023
       // Prof. Samir Hamada




public class Main {
    public static void main(String[] args) {
        String[][] Maze = new String[4][4];
        for (int i = 0; i < Maze.length; i++) {
            for (int j = 0; j < Maze[0].length; j++) {
                Maze[i][j] = "UDLR";
                if (i == 0) {
                    Maze[i][j] = Maze[i][j].replace("U", "");
                }
                if (i == Maze.length-1) {
                    Maze[i][j] = Maze[i][j].replace("D", "");
                }
                if (j == 0) {
                    Maze[i][j] = Maze[i][j].replace("L", "");
                }
                if (j == Maze[0].length-1) {
                    Maze[i][j] = Maze[i][j].replace("R", "");
                }
            }

        }
        String mazeist = "";

        Maze[0][1] = Maze[0][1].replace("D","");
        Maze[0][2] = Maze[0][2].replace("D","");
        Maze[1][0] = Maze[1][0].replace("D","");
        Maze[1][1] = Maze[1][1].replace("D","");
        Maze[1][1] = Maze[1][1].replace("U","");
        Maze[1][2] = Maze[1][2].replace("U","");
        Maze[1][2] = Maze[1][2].replace("R","");
        Maze[1][3] = Maze[1][3].replace("L","");
        Maze[2][0] = Maze[2][0].replace("U","");
        Maze[2][0] = Maze[2][0].replace("D","");

        Maze[2][1] = Maze[2][1].replace("U","");
        Maze[2][2] = Maze[2][2].replace("D","");
        Maze[2][2] = Maze[2][2].replace("D","");
        Maze[2][3] = Maze[2][3].replace("D","");
        Maze[3][0] = Maze[3][0].replace("U","");
        Maze[3][2] = Maze[3][2].replace("U","");
        Maze[3][3] = Maze[3][3].replace("U","");
        Maze[3][3] += "E";
        Maze[0][0]+= "S";
        String path = "S";

        for(int i = 0; i < Maze.length;i++){
            for (int j = 0; j < Maze[0].length;j++){
                System.out.print(Maze[i][j] + " ");
                if (j==3){
                    System.out.println();
                }

            }

        }
        System.out.println();
        MazeAlgorithm.MazeAlgo(0,0,path,Maze);

    }
}