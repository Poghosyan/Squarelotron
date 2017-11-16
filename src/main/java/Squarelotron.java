//Given a string s find the alphabetically last substring in the alphabetically ordered set of substrings of s

public class Squarelotron {
    private int[][] squarelotron;

    private int size;

    public Squarelotron(int n) {
        squarelotron = new int[n][n];
        size = n;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                squarelotron[i][j] = i * n + j + 1;
            }
        }
    }

    public Squarelotron(int[][] sq, int size) {
        squarelotron = sq;
        this.size = size;
    }

    public Squarelotron upsideDownFlip(int ring) {
        int [][] result = squarelotron;
        for (int i = 0; i < size; ++i) {
            for (int j = size - 1, k = 0; k < size; --j, ++k) {
                result[i][k] = squarelotron[i][j];
            }
        }
        return new Squarelotron(result, size);
    }

    public Squarelotron mainDiagonalFlip(int ring) {

        return new Squarelotron(0);
    }

    public void rotateRight(int numberOfTurns) {

    }

    public int[][] getSquarelotron() {
        return squarelotron;
    }

    public int getSize() {
        return size;
    }


}
