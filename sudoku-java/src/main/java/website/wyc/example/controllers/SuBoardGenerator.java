package website.wyc.example.controllers;

import java.util.List;
import java.util.Set;

import website.wyc.example.models.SuBoard;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SuBoardGenerator {
    public static final int n = 9;
    public static final int box_size = 3;
    public static SuBoardGenerator setBoard(SuBoard board) {
        return new SuBoardGenerator(board);
    }
    public static SuBoard generateDirectly() {
        return new SuBoardGenerator().generate();
    }

    private SuBoard board;
    private SuBoardGenerator() {
        this.board = new SuBoard();
    }

    private SuBoardGenerator(SuBoard board) {
        this.board = board;
    }

    private List<Set<Integer>> usedInRow, usedInCol, usedInBox;



    public SuBoard generate() {
        board.clear();

        return board;
    }

}
