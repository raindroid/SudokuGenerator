package website.wyc.example.models;

import java.util.Arrays;

/**
 * @author Rain
 * @version 0.0.1
 */
public class SuBoard {
    public static final int n = 9;  //size
    private int[][] b = new int[9][9];
    private Status status = new Status();

    public class Status{
        public boolean solved = false;
        public boolean generated = false;
    }

    public SuBoard() {
    }
    public SuBoard(SuBoard board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                b[i][j] = board.b[i][j];
        }
        status.solved = board.status.solved;
        status.generated = board.status.solved;
    }

    @Override
    public SuBoard clone(){
        return new SuBoard(this);
    }

    public void setB(int[][] b) {
        this.b = b;
    }
    public int set(int i, int j, int k) {
        int m = b[i][j];
        b[i][j] = k;
        return m;
    }
    public int get(int i, int j) {
        return b[i][j];
    }
    public int[][] getB() {
        return b.clone();
    }
    public void clear() {
        b = new int[9][9];
        status.solved = false;
        status.generated = false;
    }

    public String toStringP() {
        StringBuilder arrays = new StringBuilder();
        for (int i = 0; i < 9; i++)
            arrays.append('\t' + Arrays.toString(b[i]) + '\n');
        return "SuBoard{" +
                "b=\n" + arrays.toString() +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder arrays = new StringBuilder();
        for (int i = 0; i < 9; i++)
            arrays.append(Arrays.toString(b[i]));
        return "SuBoard{" +
                "b=" + arrays.toString() +
                '}';
    }
}
