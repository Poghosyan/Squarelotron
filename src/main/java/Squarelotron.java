//Given a string s find the alphabetically last substring in the alphabetically ordered set of substrings of s

public class Squarelotron {
    private final int MAX_TURN = 4;
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
        for (int i = 0; i < size; ++i) {
            for (int j = size - 1, k = 0; k < size; --j, ++k) {
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

    //TODO This doesnt work for inner rings, fix it
    public void rotateRight(int numberOfTurns) {
        int trueTurns = numberOfTurns % MAX_TURN;
        int ringLimit = (int) Math.ceil((double) size / 2.0);
        int [] topRow, rightColumn, bottomRow, leftColumn;
        for (int turn = 0; turn < trueTurns; ++turn) {
            for (int i = 1; i <= ringLimit; ++i) {
                topRow = new int[size - (2 * (i - 1))];
                rightColumn = new int[size - (2 * (i - 1))];
                bottomRow = new int[size - (2 * (i - 1))];
                leftColumn = new int[size - (2 * (i - 1))];

                for (int j = 0; j < size; ++j) {
                    topRow[j] = squarelotron[i - 1][j];
                    leftColumn[j] = squarelotron[j][i - 1];
                    bottomRow[j] = squarelotron[size - i][j];
                    rightColumn[j] = squarelotron[j][size - i];
                }

                squarelotron[i - 1] = leftColumn;
                squarelotron[size - i] = rightColumn;

                for (int j = 0; j < size; ++j) {
                    squarelotron[j][i - 1] = bottomRow[j];
                    squarelotron[j][size - i] = topRow[j];
                }
            }
        }
    }

    public Squarelotron mainDiagonalFlip(int ring) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if ((i >= ring - 1 && i <= size - ring) && (j >= ring - 1 && j <= size - ring)) {
                    result[i][j] = squarelotron[j][i];
                } else {
                    result[i][j] = squarelotron[i][j];
                }
            }
        }
        return new Squarelotron(result, size);
    }

    public int[][] getSquarelotron() {
        return squarelotron;
    }

    public int getSize() {
        return size;
    }


}
