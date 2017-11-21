import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquarelotronTest {

    private final int MAX_SQUARELOTRON_DIMENSION = 8;
    private int[][] sizeFourRotateOne;
    private int[][] sizeFourRotateTwo;
    private int[][] sizeFourRotateThree;
    private int[][] sizeFourRotateFour;
    @Before
    public void setUp(){
        sizeFourRotateOne = new int[4][4];
        sizeFourRotateTwo = new int[4][4];
        sizeFourRotateThree = new int[4][4];
        sizeFourRotateFour = new int[4][4];

        //TODO Setup arrays to test against
    }

    @Test
    public void upsideDownFlip() throws Exception {
        for (int i = 1; i < MAX_SQUARELOTRON_DIMENSION; ++i) {
            Squarelotron squarelotron = new Squarelotron(i);
            int size = squarelotron.getSize();
            int ringLimit = (int) Math.ceil((double) squarelotron.getSize() / 2.0);
            for (int ring = 1; ring <= ringLimit; ++ring) {
                squarelotron.upsideDownFlip(ring);
                checkUpsideDownFlip(ringLimit, squarelotron, size);
            }
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
                        if ((i >= ringNumber - 1 && i <= size - ringNumber) && (j >= ringNumber - 1 && j <= size - ringNumber)) {
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
        for (int k = 3; k < MAX_SQUARELOTRON_DIMENSION; ++k) {
            Squarelotron clockwise = new Squarelotron(k);
            Squarelotron counterClockwise = new Squarelotron(k);

            for (int turns = 1; turns <= Squarelotron.MAX_TURN; ++turns) {
                clockwise.rotateRight(turns);
                counterClockwise.rotateRight(-turns);
                for (int i = 0; i < clockwise.getSize(); ++i) {
                    for (int j = 0; j < clockwise.getSize(); ++j) {
                        System.out.print(clockwise.getSquarelotron()[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    private void checkUpsideDownFlip(int ringLimit, Squarelotron squarelotron, int size) {
        for (int ringNumber = 1; ringNumber < ringLimit; ++ringNumber) {
            int[][] result = squarelotron.upsideDownFlip(ringNumber).getSquarelotron();
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if ((i >= ringNumber - 1 && i <= size - ringNumber) && (j >= ringNumber - 1 && j <= size - ringNumber)) {
                        assertEquals("Ring # " + ringNumber + " Size: " + size, (size - i - 1) * size + j + 1, result[i][j]);
                    }
                }
            }
        }
    }

}