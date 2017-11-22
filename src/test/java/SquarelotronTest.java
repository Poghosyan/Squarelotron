import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquarelotronTest {

    private final int MAX_SQUARELOTRON_DIMENSION = 8;
    private int[][][] rotationHolder;

    @Before
    public void setUp() {
        int[][] sizeFourRotateOne = new int[4][4];
        int[][] sizeFourRotateTwo = new int[4][4];
        int[][] sizeFourRotateThree = new int[4][4];
        int[][] sizeFourRotateFour = new int[4][4];
        rotationHolder = new int[4][][];

        for (int i = 0; i < 4; ++i) {
            sizeFourRotateOne[i] = new int[]{4 * i + 1, 4 * i + 2, 4 * i + 3, 4 * i + 4};
            sizeFourRotateTwo[i] = new int[]{13 + i, 9 + i, 5 + i, 1 + i};
            sizeFourRotateThree[i] = new int[]{16 - 4 * i, 15 - 4 * i, 14 - 4 * i, 13 - 4 * i};
            sizeFourRotateFour[i] = new int[]{4 - i, 8 - i, 12 - i, 16 - i};
        }

        rotationHolder[0] = sizeFourRotateOne;
        rotationHolder[1] = sizeFourRotateTwo;
        rotationHolder[2] = sizeFourRotateThree;
        rotationHolder[3] = sizeFourRotateFour;
    }

    @Test
    public void upsideDownFlip() throws Exception {
        for (int i = 3; i < MAX_SQUARELOTRON_DIMENSION; ++i) {
            Squarelotron squarelotron = new Squarelotron(i);
            int size = squarelotron.getSize();
            int ringLimit = (int) Math.ceil((double) squarelotron.getSize() / 2.0);
            checkUpsideDownFlip(ringLimit, squarelotron, size);
        }
    }

    @Test
    public void mainDiagonalFlip() throws Exception {
        int[][] result;
        for (int k = 3; k < MAX_SQUARELOTRON_DIMENSION; ++k) {
            Squarelotron squarelotron = new Squarelotron(k);
            int size = squarelotron.getSize();
            int ringLimit = (int) Math.ceil((double) squarelotron.getSize() / 2.0);

            for (int ring = 1; ring <= ringLimit; ++ring) {
                result = squarelotron.mainDiagonalFlip(ring).getSquarelotron();
                for (int i = 0; i < size; ++i) {
                    for (int j = 0; j < size; ++j) {
                        if ((i == ring - 1 && j >= ring - 1 && j <= size - ring) ||
                            (i == size - ring && j >= ring - 1 && j <= size - ring) ||
                            (j == ring - 1 && i >= ring - 1 && i <= size - ring) ||
                            (j == size - ring && i >= ring - 1 && i <= size - ring)) {
                            assertEquals("Ring # " + ring + " Size: " + size, i * size + j + 1, result[j][i]);
                        } else {
                            assertEquals("Ring # " + ring + " Size: " + size, squarelotron.getSquarelotron()[j][i], result[j][i]);
                        }
                        System.out.print(result[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    @Test
    public void rotateRight() throws Exception {
        for (int turns = 1; turns <= Squarelotron.MAX_TURN; ++turns) {
            Squarelotron clockwise = new Squarelotron(4);
            Squarelotron counterClockwise = new Squarelotron(4);
            clockwise.rotateRight(turns);
            counterClockwise.rotateRight(-turns);
            for (int i = 0; i < 4; ++i) {
                if (turns != 4) {
                    assertArrayEquals(rotationHolder[turns][i], clockwise.getSquarelotron()[i]);
                } else {
                    assertArrayEquals(rotationHolder[0][i], clockwise.getSquarelotron()[i]);
                }
                assertArrayEquals(rotationHolder[4 - turns][i], counterClockwise.getSquarelotron()[i]);
            }
        }
    }

    private void checkUpsideDownFlip(int ringLimit, Squarelotron squarelotron, int size) {
        for (int ring = 1; ring <= ringLimit; ++ring) {
            int[][] result = squarelotron.upsideDownFlip(ring).getSquarelotron();
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    System.out.print(result[i][j] + " ");
                    if ((i == ring - 1 && j >= ring - 1 && j <= size - ring) ||
                        (i == size - ring && j >= ring - 1 && j <= size - ring) ||
                        (j == ring - 1 && i >= ring - 1 && i <= size - ring) ||
                        (j == size - ring && i >= ring - 1 && i <= size - ring)) {
                        assertEquals("Ring # " + ring + " Size: " + size, (size - i - 1) * size + j + 1, result[i][j]);
                    } else {
                        assertEquals("Ring # " + ring + " Size: " + size, squarelotron.getSquarelotron()[j][i], result[j][i]);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

}