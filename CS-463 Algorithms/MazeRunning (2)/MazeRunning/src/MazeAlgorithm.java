//Jordan Phan Maze Algorithm Maze Algorithm
//      CS 463-01
//    2/7/2023
// Prof. Samir Hamada
public class MazeAlgorithm {


    static void MazeAlgo(int currentRow, int currentCol, String path, String[][] Maze) {
       /* for (int i = 0; i < Maze.length; i++) {
            for (int j = 0; j < Maze[0].length; j++) {
                System.out.print(Maze[i][j] + " ");
                if (j == 3) {
                    System.out.println();
                }

            }
        }
        System.out.println();*/

        if (Maze[currentRow][currentCol].contains("X")) {
            System.out.println("Invalid: "+ path);
            return;
        }
        Maze[currentRow][currentCol] += "X";
      //  System.out.println(path + " " + Maze[currentRow][currentCol].contains("E"));

        if (Maze[currentRow][currentCol].contains("E")) {
            path += "E";
            System.out.println("Valid: " + path);
            Maze[currentRow][currentCol] = Maze[currentRow][currentCol].replace("X", "");
            return;
        }
        if (Maze[currentRow][currentCol].contains("U")) {
            path += "U";
            MazeAlgo(currentRow - 1, currentCol, path, Maze);
            path = path.substring(0,path.length()-1);
           // Maze[currentRow - 1][currentCol] = Maze[currentRow - 1][currentCol].replace("X", "");
        }

        if (Maze[currentRow][currentCol].contains("D")) {
            path += "D";
            MazeAlgo(currentRow + 1, currentCol, path, Maze);
            path = path.substring(0,path.length()-1);
           // Maze[currentRow + 1][currentCol] = Maze[currentRow + 1][currentCol].replace("X", "");
        }
        if (Maze[currentRow][currentCol].contains("L")) {
            path += "L";
            MazeAlgo(currentRow, currentCol - 1, path, Maze);
            path = path.substring(0,path.length()-1);
          //  Maze[currentRow][currentCol - 1] = Maze[currentRow][currentCol - 1].replace("X", "");
        }
        if (Maze[currentRow][currentCol].contains("R")) {
            path += "R";
            MazeAlgo(currentRow, currentCol + 1, path, Maze);
            path = path.substring(0,path.length()-1);
           // Maze[currentRow][currentCol+1] = Maze[currentRow][currentCol+1].replace("X", "");
        }
        Maze[currentRow][currentCol] = Maze[currentRow][currentCol].replace("X", "");

        return;

    }
}
