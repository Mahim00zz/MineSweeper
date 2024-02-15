import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper implements ActionListener {
    JOptionPane optionPane;
    JFrame myFrame;
    Grid grid = new Grid(3, 3, 1);
    JButton[][] buttons;
    int[][] countGrid;
    boolean[][] bombGrid;
    int cellsLeft = grid.getNumRows() * grid.getNumColumns() - grid.getNumBombs();

    String winOrLose = "";

    public static void main(String[] args) {
        Minesweeper mineSweeper = new Minesweeper();
        mineSweeper.BoardBuilder();
   
    }

    public void BoardBuilder() {
        myFrame = new JFrame();
        myFrame.setTitle("Minesweeper");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setSize(750, 750);
        myFrame.setLayout(new GridLayout(grid.getNumRows(), grid.getNumColumns()));

        buttons = new JButton[grid.getNumRows()][grid.getNumColumns()];

        for (int i = 0; i < grid.getCountGrid().length; i++) {
            for (int j = 0; j < grid.getCountGrid()[i].length; j++) {
                buttons[i][j] = new JButton();
                myFrame.add(buttons[i][j]);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(new Color(34, 139, 34));
            }
        }

        myFrame.setVisible(true);

        countGrid = new int[grid.getNumRows()][grid.getNumColumns()];
        for (int i = 0; i < grid.getCountGrid().length; i++) {
            for (int j = 0; j < grid.getCountGrid()[i].length; j++) {
                countGrid[i][j] = grid.getCountGrid()[i][j];
            }
        }

        bombGrid = new boolean[grid.getNumRows()][grid.getNumColumns()];
        for (int i = 0; i < grid.getBombGrid().length; i++) {
            for (int j = 0; j < grid.getBombGrid()[i].length; j++) {
                bombGrid[i][j] = grid.getBombGrid()[i][j];
            }
        }

       
        // COUNT GRID
        for (int i = 0; i < grid.getCountGrid().length; i++) {
            for (int j = 0; j < grid.getCountGrid()[i].length; j++) {
                System.out.print(countGrid[i][j] + " ");
            }
            System.out.println();
        }

        // BOMB GRID
        for (int i = 0; i < grid.getBombGrid().length; i++) {
            for (int j = 0; j < grid.getBombGrid()[i].length; j++) {
                System.out.print(bombGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[][] num = new String[grid.getNumRows()][grid.getNumColumns()];

        for (int i = 0; i < grid.getCountGrid().length; i++) {
            for (int j = 0; j < grid.getCountGrid()[i].length; j++) {
                if (e.getSource() == buttons[i][j]) {
                    num[i][j] = String.valueOf(countGrid[i][j]);
                    buttons[i][j].setText(num[i][j]);
                    buttons[i][j].setFont(new Font("SansSerif", Font.BOLD, 30));
                    buttons[i][j].setEnabled(false);

                    // CHECK CONDITIONS
                    if (bombGrid[i][j]) {
                        System.out.println("You lost");
                        disableGame();
                        winOrLose = "You lost!";
                        resetGame(winOrLose);
                    }

                    if (!bombGrid[i][j]) {
                        cellsLeft--;
                        System.out.println("Cells left: " + cellsLeft);
                        if (cellsLeft == 0) {
                            disableGame();
                            winOrLose = "You won congrats!";
                            resetGame(winOrLose);
                        }
                    }
                }
            }
        }
    }

    public void disableGame() {
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon("bomb.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (bombGrid[i][j]) {
                    buttons[i][j].setIcon(imageIcon);
                } else {
                    buttons[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }

    public void resetGame(String s) {
        int num = JOptionPane.showConfirmDialog(null, s + " Would you like to play again?", "Play again",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (num == 0) {
            myFrame.dispose();
            grid = new Grid(10, 10, 25);
            BoardBuilder();
        } else if (num == 1) {
            System.exit(0);
        } else {
            System.exit(0);
        }
    }
}
