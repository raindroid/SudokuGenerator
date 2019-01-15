package website.wyc.example.controllers;

import java.util.Random;

import website.wyc.example.models.SuBoard;

/**
 * @author Rain
 * @version 0.0.1
 */
public class SuHolesGenerator {
    public static final int n = 9;
    public static final int BOX_SIZE = 3;
    public static final int ATTEMPTS = 100000;
    private static final Random rand = new Random();
    public static SuHolesGenerator setBoard(SuBoard board) {
        return new SuHolesGenerator(board);
    }

    private SuBoard board;

    private SuHolesGenerator(SuBoard board) {
        this.board = board;
    }

    private SuSolver solver;

    private boolean generateHoles(int holesLeft) {
        if (holesLeft == 0) return true;
        for (int count = 0; count < ATTEMPTS; count++) {
            int x = rand.nextInt(n);
            int y = rand.nextInt(n);
            if (board.get(x, y) == 0) continue;
            int k = board.set(x, y, 0);
            if (solver.setBoard(board.clone()).solveCompletely() == 1 &&
                    generateHoles(holesLeft - 1)) {
//                System.out.println("Find a good spot at (" + x + ", " + y + ") holesLeft = " + holesLeft);
                return true;
            }
            board.set(x, y, k);
        }
        System.out.println("Attempts exceed limit [" + ATTEMPTS + "]");
        return false;
    }

    public SuBoard generate(int holesCount) {
        solver = SuSolver.getSolver(board);
        board.getStatus().generated = false;
        board.getStatus().solved = false;
        if (!solver.solve().getStatus().solved){
            System.out.println("The board is unsolvable");
            return board;
        }
        if (generateHoles(holesCount))
            board.getStatus().generated = true;
        return board;
    }
}
