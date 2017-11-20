import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquarelotronTest {

    private final int MAX_SQUARELOTRON_DIMENSION = 8;

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
        for (int k = 1; k < MAX_SQUARELOTRON_DIMENSION; ++k) {
            Squarelotron clockwise = new Squarelotron(k);
            Squarelotron counterCloclwise = new Squarelotron(k);
            int size = clockwise.getSize();
            for (int turns = 1; turns <= Squarelotron.MAX_TURN; ++turns) {
                clockwise.rotateRight(turns);
                counterCloclwise.rotateRight(-turns);
                for (int i = size - 1; i > -1; --i) {
                    for (int j = 0; j < size; ++j) {
                        assertEquals((size - 1 - i) * size + j + 1, clockwise.getSquarelotron()[i][j]);
                    }
                }
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