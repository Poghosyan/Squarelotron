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

    public Squarelotron upsideDownFlip(int ring) {
        return new Squarelotron(0);
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
