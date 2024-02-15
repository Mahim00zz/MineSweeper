/*
 * Video:https://youtu.be/-xyj4VShaz4
 * 
 * Name: Mahim Rahaman
 * Description: This is project 2 a Mine Sweeper grid.
 * Date created: 11/21/23
 * Date Modified: 11/28/23
 * Email: Mahim.Rahaman@lc.cuny.edu
 * 
 * 
 */

import java.util.Random;

public class Grid{

    private int[][] countGrid;
    private int numRows;
    private int numColumns;
    private int numBombs;
    private boolean[][] bombGrid;
    public Grid(){
        numRows = 10;
        numColumns = 10;
        numBombs = 25;

        countGrid = new int[numRows][numColumns];
        bombGrid = new boolean[numRows][numColumns];

        createBombGrid();
        createCountGrid();


    }

    public Grid(int rows, int columns){

        numRows = rows;
        numColumns = columns;
        numBombs = 25;

        countGrid = new int[numRows][numColumns];
        bombGrid = new boolean[numRows][numColumns];

        createBombGrid();
        createCountGrid();
    }

    public Grid(int rows, int columns, int bombs){
        numRows = rows;
        numColumns = columns;
        numBombs = bombs;
        countGrid = new int[numRows][numColumns];
        bombGrid = new boolean[numRows][numColumns];

        createBombGrid();
        createCountGrid();
        
    }

    public int getNumRows(){
        return numRows;
    }

    public int getNumColumns(){
        return numColumns;
    }

    public int getNumBombs(){
        return numBombs;
    }

    public boolean[][] getBombGrid(){
        boolean[][] copyGrid = new boolean[numRows][numColumns];

        for(int i = 0; i < countGrid.length; i++) {
            for(int j = 0; j < countGrid[i].length; j++) {
                copyGrid[i][j] = bombGrid[i][j];
            }
        }
        return copyGrid;
    }

    public int[][] getCountGrid(){
        int[][] copyGrid = new int[numRows][numColumns];

        for(int i = 0; i < countGrid.length; i++) {
            for(int j = 0; j < countGrid[i].length; j++) {
                copyGrid[i][j] = countGrid[i][j];
            }
        }

        return copyGrid;
    }

    public boolean isBombAtLocation(int row, int column){
        if(bombGrid[row][column] == true) {
            return true;
        }
        return false;
    }

    public int getCountAtLocation(int row, int column){
        if((row < 0 || row >= countGrid.length) || (column < 0 || column >= countGrid[row].length)) {
            return -1;
        }

        return countGrid[row][column];
    }


    private void createBombGrid(){

        Random rnd = new Random();
        int counter = 0;
        for(int i = 0; i < bombGrid.length; i++) {
            for (int j = 0; j < bombGrid[i].length; j++) {
                bombGrid[i][j] = false;
            }
        }

        while(counter < numBombs){
            int coord1 = rnd.nextInt(numRows);
            int coord2 = rnd.nextInt(numColumns);
            if(bombGrid[coord1][coord2] == false) {
                bombGrid[coord1][coord2] = true;
                counter++;
            }
        }



    }


    private void createCountGrid(){

        for(int i = 0; i < countGrid.length; i++) {
            for (int j = 0; j < countGrid[i].length; j++) {
                countGrid[i][j] = 0;
                if (bombGrid[i][j] == true) {
                    countGrid[i][j] = 1;
                }
            }
        }




        for(int rows = 0; rows < bombGrid.length; rows++) {
            for (int columns = 0; columns < countGrid[rows].length; columns++) {

                if(rows + 1 < bombGrid.length) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows + 1][columns] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows + 1][columns] == true) {
                        countGrid[rows][columns]++;
                    }
                }

                if(rows - 1 >= 0) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows - 1][columns] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows - 1][columns] == true) {
                        countGrid[rows][columns]++;
                    }
                }

                if(rows + 1 < bombGrid.length && columns + 1 < bombGrid[rows].length) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows + 1][columns + 1] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows + 1][columns + 1] == true) {
                        countGrid[rows][columns]++;
                    }
                }

                if(rows - 1 >= 0 && columns - 1 >= 0) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows - 1][columns - 1] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows - 1][columns - 1] == true) {
                        countGrid[rows][columns]++;
                    }
                }

                if(columns + 1 < bombGrid[rows].length) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows][columns + 1] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows][columns + 1] == true) {
                        countGrid[rows][columns]++;
                    }
                }

                if(columns - 1 >= 0) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows][columns - 1] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows][columns - 1] == true) {
                        countGrid[rows][columns]++;
                    }
                }

                if(rows + 1 < bombGrid.length && columns - 1 >= 0) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows + 1][columns - 1] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows + 1][columns - 1] == true) {
                        countGrid[rows][columns]++;
                    }
                }

                if(rows - 1 >= 0 && columns + 1 < bombGrid[rows].length) {
                    if (bombGrid[rows][columns] == false && bombGrid[rows - 1][columns + 1] == true) {
                        countGrid[rows][columns]++;
                    }
                    else if(bombGrid[rows][columns] == true && bombGrid[rows - 1][columns + 1] == true) {
                        countGrid[rows][columns]++;
                    }
                }


            }
        }

    }




    public static void main(String[] args) {
        Grid grid = new Grid(6, 5);

        boolean[][]bombGrid = grid.getBombGrid();
        int [][] countGrid = grid.getCountGrid();

        for(int i = 0; i < bombGrid.length; i++){
            for(int j = 0; j < bombGrid[i].length; j++){
                System.out.print(bombGrid[i][j] + " ");
            }
            System.out.println();
        }

        for(int i = 0; i < countGrid.length; i++){
            for(int j = 0; j < countGrid[i].length; j++){
                System.out.print(countGrid[i][j] + " ");
            }
            System.out.println();
        }


    }
}