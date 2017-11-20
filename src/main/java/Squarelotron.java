//Given a string s find the alphabetically last substring in the alphabetically ordered set of substrings of s

public class Squarelotron {
    public static final int MAX_TURN = 4;
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

    //TODO fix so it works with negative rotations as well
    public void rotateRight(int numberOfTurns) {
        int trueTurns = numberOfTurns % MAX_TURN;
        int ringLimit = (int) Math.ceil((double) size / 2.0);
        int [] topRow, rightColumn, bottomRow, leftColumn;

        for (int turn = 0; turn < trueTurns; ++turn) {
            for (int ring = 1; ring <= ringLimit; ++ring) {
                int ringLength = size - (2 * (ring - 1));
                topRow = new int[ringLength];
                rightColumn = new int[ringLength];
                bottomRow = new int[ringLength];
                leftColumn = new int[ringLength];

                for (int  start = (ring - 1), k = 0; start <= size - ring; ++start, ++k) {
                    topRow[k] = squarelotron[ring - 1][start];
                    leftColumn[k] = squarelotron[start][ring - 1];
                    bottomRow[k] = squarelotron[size - ring][start];
                    rightColumn[k] = squarelotron[start][size - ring];
                }

                for (int j = 0, start = ring - 1, end = size - ring; start <= size - ring; ++j, ++start, --end) {
                    squarelotron[start][ring - 1] = bottomRow[j];
                    squarelotron[start][size - ring] = topRow[j];
                    squarelotron[ring - 1][end] = leftColumn[j];
                    squarelotron[size - ring][end] = rightColumn[j];
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
