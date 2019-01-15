package website.wyc.example.controllers;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import website.wyc.example.models.SuBoard;

/**
 * @author Admin
 * @version $Rev$
 */
public class SuSolver {
    public static final int n = 9;
    public static final int BOX_SIZE = 3;
    private static final Random rand = new Random();
    public static SuSolver getSolver(SuBoard board) {
        return new SuSolver(board);
    }
    public static SuBoard generateDirectly() {
        return new SuSolver().solve();
    }

    private SuBoard board;
    private SuSolver() {
        this.board = new SuBoard();
    }

    private SuSolver(SuBoard board) {
        this.board = board;
    }

    private List<Set<Integer>> usedInRow, usedInCol, usedInBox;

    private int getBoxId(int i, int j) {
        return (i / BOX_SIZE) * BOX_SIZE + (j / BOX_SIZE);
    }

    public SuSolver setBoard(SuBoard board) {
        this.board = board;
        return this;
    }

    //用缓存检查位置是否可以放入值k
    private boolean checkAvalibility(int i, int j, int k) {
        return !(usedInRow.get(i).contains(k) || usedInCol.get(j).contains(k) ||
                usedInBox.get(getBoxId(i, j)).contains(k));
    }

    //递归单个格子
    private boolean solveCell(int cellNum) {
        if (cellNum == n * n) return true;  //reach the last cell
        int i = cellNum / 9, j = cellNum % 9;
        if (board.get(i, j) != 0)  return solveCell(cellNum + 1);
        List<Integer> numList = new ArrayList<>(9);
        for (int k = 1; k <= n; k++) {
            if (checkAvalibility(i, j, k))
                numList.add(k);
        }
        while (numList.size() > 0) {
            int k = numList.get(rand.nextInt(numList.size()));
            board.set(i, j, k);
            usedInRow.get(i).add(k);
            usedInCol.get(j).add(k);
            usedInBox.get(getBoxId(i, j)).add(k);
            if (solveCell(cellNum + 1)) return true;
            board.set(i, j, 0);
            usedInRow.get(i).remove(k);
            usedInCol.get(j).remove(k);
            usedInBox.get(getBoxId(i, j)).remove(k);

            numList.remove(0);
        }
        return false;
    }

    public SuBoard solve() {
        usedInRow = new ArrayList<>(9);
        usedInCol = new ArrayList<>(9);
        usedInBox = new ArrayList<>(9);
        int [][] readBoard = board.getB();
        for (int i = 0; i < n; i++) {
            usedInRow.add(i, new HashSet<Integer>(9));
            usedInCol.add(i, new HashSet<Integer>(9));
            usedInBox.add(i, new HashSet<Integer>(9));
            for (int j = 0; j < n; j++) {
                if (readBoard[i][j] != 0) usedInRow.get(i).add(readBoard[i][j]);
                if (readBoard[j][i] != 0) usedInCol.get(i).add(readBoard[j][i]);
                int boxX = (i / 3) * 3 + j / 3,
                        boxY = (i % 3) * 3 + j % 3;
                if (readBoard[boxX][boxY] != 0) usedInRow.get(i).add(readBoard[boxX][boxY]);
            }
        }
        if (solveCell(0))
            board.getStatus().solved = true;
        return board;
    }

    public int solveCompletely() {
        Deque<SuBoard> solvingList = new LinkedList<>();
        solvingList.add(board.clone());
        int s = 0;
        while(solvingList.size() > 0) {
            SuBoard tempBoard = solvingList.pop();
            int cellNum = 0;
            while(cellNum < n * n && tempBoard.get(cellNum / 9, cellNum % 9) != 0 ) ++cellNum;
            if (cellNum == n * n) s++;
            else {
                //looking for a solution
                for (int k = 0; k < n; k++) {
                    boolean ok = true;
                    for (int i = 0; i < n; i++) {
                        int boxId = getBoxId(cellNum / 9, cellNum % 9);
                        int boxX = (boxId / 3) * 3 + i / 3;
                        int boxY = (boxId % 3) * 3 + i / 3;
                        if (tempBoard.get(cellNum / 9, i) == k ||
                                tempBoard.get(i, cellNum % 9) == k ||
                                tempBoard.get(boxX, boxY) == k) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        SuBoard newBoard = tempBoard.clone();
                        newBoard.set(cellNum / 9, cellNum % 9, k);
                        solvingList.add(newBoard);
                    }
                }
            }
        }
        return s;
    }

    @Override
    protected void finalize() throws Throwable {
        board = null;
        super.finalize();
    }
}
