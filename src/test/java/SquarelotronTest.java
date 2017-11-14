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
            int ringLimit = (int)Math.ceil((double)squarelotron.getSize()/2.0);
            for (int ring = 1; ring < ringLimit; ++ring) {
                squarelotron.upsideDownFlip(ring);
                checkUpsideDownFlip(ringLimit, squarelotron, size);
            }
        }
    }

    @Test
    public void mainDiagonalFlip() throws Exception {

    }

    @Test
    public void rotateRight() throws Exception {
    }

    private void checkUpsideDownFlip(int ringLimit, Squarelotron squarelotron, int size) {
        for (int ringNumber = 1; ringNumber < ringLimit; ++ringNumber) {
            squarelotron.upsideDownFlip(ringNumber);
            for (int i = 0; i < size ; ++i) {
                for (int j = 0; j < size; ++j) {
                    if ((j >= ringNumber - 1 || j <= size - ringNumber) && (i >= ringNumber || i <= size - ringNumber)) {
                        assertEquals((size - i - 1) * size + j + 1, squarelotron.getSquarelotron()[i][j]);
                    }
                }
            }
        }
    }

}