/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells so make a stack LIFO
        ArrayList<MazeCell> send = new ArrayList<>();
        Stack<MazeCell> s = new Stack<>();
        // Start is equal to last spot in the array
        MazeCell track = maze.getEndCell();
        // Make sure the current is not the start and only check within the while if the parent is the start cell and
        // push that as well.
        while(track != maze.getStartCell()) {
            s.push(track);
            track = track.getParent();
            if(track == maze.getStartCell()) {
                s.push(track);
            }

        }
        while(!s.isEmpty()) {
            send.add(s.pop());
        }


        return send;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        MazeCell current = maze.getStartCell();
        Stack<MazeCell> ctv = new Stack<>();
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        while(current != maze.getEndCell()) {
            int row = current.getRow();
            int col = current.getCol();
            if(maze.isValidCell(row-1, col)) {
                if(!maze.getCell(row-1, col).isExplored()) {
                    if(maze.getCell(row-1, col) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    maze.getCell(row-1, col).setParent(current);
                    maze.getCell(row-1, col).setExplored(true);
                    ctv.push(maze.getCell(row-1, col));
                }
            }
            if(maze.isValidCell(row, col+1)) {
                if(!maze.getCell(row, col+1).isExplored()) {
                    if(maze.getCell(row, col+1) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    maze.getCell(row, col+1).setParent(current);
                    ctv.push(maze.getCell(row, col+1));
                    maze.getCell(row, col+1).setExplored(true);
                }
            }
            if(maze.isValidCell(row+1, col)) {
                if(!maze.getCell(row+1, col).isExplored()) {
                    if(maze.getCell(row+1, col) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    maze.getCell(row+1, col).setParent(current);
                    maze.getCell(row+1, col).setExplored(true);
                    ctv.push(maze.getCell(row+1, col));

                }
            }
            if(maze.isValidCell(row, col-1)) {
                if(!maze.getCell(row, col-1).isExplored()) {
                    if(maze.getCell(row, col-1) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    maze.getCell(row, col-1).setParent(current);
                    maze.getCell(row, col-1).setExplored(true);
                    ctv.push(maze.getCell(row, col-1));

                }
            }
            // Change current to next value in cells to visit
            current = ctv.pop();
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        MazeCell current = maze.getStartCell();
        Queue<MazeCell> ctv = new LinkedList<>();

        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        while(current != maze.getEndCell()) {
            int row = current.getRow();
            int col = current.getCol();

            if(maze.isValidCell(row-1, col)) {
                if(!maze.getCell(row-1, col).isExplored()) {
                    // Makes sure end has a parent cell
                    if(maze.getCell(row-1, col) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    // Sets the parent and puts it in the explored section as well as cells to add
                    maze.getCell(row-1, col).setParent(current);
                    maze.getCell(row-1, col).setExplored(true);
                    ctv.add(maze.getCell(row-1, col));
                }
            }

            if(maze.isValidCell(row, col+1)) {
                if(!maze.getCell(row, col+1).isExplored()) {
                    if(maze.getCell(row, col+1) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    maze.getCell(row, col+1).setParent(current);
                    ctv.add(maze.getCell(row, col+1));
                    maze.getCell(row, col+1).setExplored(true);
                }
            }

            if(maze.isValidCell(row+1, col)) {
                if(!maze.getCell(row+1, col).isExplored()) {
                    if(maze.getCell(row+1, col) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    maze.getCell(row+1, col).setParent(current);
                    maze.getCell(row+1, col).setExplored(true);
                    ctv.add(maze.getCell(row+1, col));

                }
            }

            if(maze.isValidCell(row, col-1)) {
                if(!maze.getCell(row, col-1).isExplored()) {
                    if(maze.getCell(row, col-1) == maze.getEndCell() && maze.getEndCell().isExplored() == false) {
                        maze.getEndCell().setParent(current);
                    }
                    maze.getCell(row, col-1).setParent(current);
                    maze.getCell(row, col-1).setExplored(true);
                    ctv.add(maze.getCell(row, col-1));

                }
            }
            // Change current to next value in cells to visit
            current = ctv.remove();
        }
        return getSolution();
    }


    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
