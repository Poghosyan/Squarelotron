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
        int[][] result = new int[size][size];
        int ringAdjust = ring - 1;
        for (int i = ringAdjust; i < size - ringAdjust; ++i) {
            for (int j = size - 1 - ringAdjust, k = ringAdjust; k < size - ringAdjust; --j, ++k) {
                if (i < ring || i >= size - ring) {
                    result[k][i] = squarelotron[j][i];
                } else if (k < ring || k >= size - ring) {
                    result[k][i] = squarelotron[j][i];
                } else {
                    result[i][k] = squarelotron[i][k];
                }
            }
        }
        return new Squarelotron(result, size);
    }

    public Squarelotron mainDiagonalFlip(int ring) {
        int[][] result = new int[size][size];
        int ringAdjust = ring - 1;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i < ring || i >= size - ring) {
                    result[i][j] = squarelotron[j][i];
                } else if (j < ring || j >= size - ring) {
                    result[i][j] = squarelotron[j][i];
                } else {
                    result[i][j] = squarelotron[i][j];
                }
            }
        }
        return new Squarelotron(result, size);
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
