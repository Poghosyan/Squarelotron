import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mher on 11/12/2017.
 */
public class SquarelotronTest {

    private Squarelotron squarelotron;
    @Before
    public void setUp() throws Exception {
        squarelotron = new Squarelotron(3);
    }

    @Test
    public void upsideDownFlip() throws Exception {
        for (int i = 0; i < squarelotron.getSize(); ++i) {
            for (int j = 0; j < squarelotron.getSize(); ++j) {
                assertEquals((squarelotron.getSize() - i - 1) * squarelotron.getSize() + j + 1,
                        squarelotron.getSquarelotron()[i][j]);
            }
        }
    }

    @Test
    public void mainDiagonalFlip() throws Exception {
    }

    @Test
    public void rotateRight() throws Exception {
    }

}