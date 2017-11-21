import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquarelotronTest {

    private final int MAX_SQUARELOTRON_DIMENSION = 8;
    private int[][] sizeFourRotateOne;
    private int[][] sizeFourRotateTwo;
    private int[][] sizeFourRotateThree;
    private int[][] sizeFourRotateFour;
    private int[][][] rotationHolder;

    @Before
    public void setUp() {
        sizeFourRotateOne = new int[4][4];
        sizeFourRotateTwo = new int[4][4];
        sizeFourRotateThree = new int[4][4];
        sizeFourRotateFour = new int[4][4];
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

            for (int ringNumber = 1; ringNumber <= ringLimit; ++ringNumber) {
                result = squarelotron.mainDiagonalFlip(ringNumber).getSquarelotron();
                for (int i = 0; i < size; ++i) {
                    for (int j = 0; j < size; ++j) {
                        if ((i == ringNumber - 1 || i == size - ringNumber) || (j == ringNumber - 1 || j == size - ringNumber)) {
                            assertEquals("Ring # " + ringNumber + " Size: " + size, i * size + j + 1, result[j][i]);
                        }
                    }
                }
            }
        }
    }

    //TODO Fix test for both clockwise and counterclockwise rotations
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
        for (int ringNumber = 1; ringNumber < ringLimit; ++ringNumber) {
            int[][] result = squarelotron.upsideDownFlip(ringNumber).getSquarelotron();
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if ((i == ringNumber - 1 || i == size - ringNumber) || (j == ringNumber - 1 || j == size - ringNumber)) {
                        assertEquals("Ring # " + ringNumber + " Size: " + size, (size - i - 1) * size + j + 1, result[i][j]);
                    }
                }
            }
        }
    }

}