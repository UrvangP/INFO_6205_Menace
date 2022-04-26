import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtilsTest {
    @Test
    public void testResetBoardCase() {
        Utils util = new Utils();
        int[][] compare = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] toCompare = util.resetBoard();
        assertTrue(Arrays.deepEquals(compare, toCompare));

        compare = new int[][]{{1, 0, 1}, {0, 1, 0}, {0, 2, 0}};
        int[][] toCompare1 = util.resetBoard();
        assertFalse(Arrays.deepEquals(compare, toCompare1));
    }

    @Test
    public void testConcatenateCase() {
        Utils util = new Utils();
        List<Integer> compare = new ArrayList<>();
        compare.add(1);
        compare.add(2);
        compare.add(4);
        String toCompare = util.concatenateMe(compare);
        assertEquals(toCompare, "124");

        compare.add(3);
        compare.add(2);
        compare.add(4);
        toCompare = util.concatenateMe(compare);
        assertNotEquals(toCompare, "124333");
    }

    @Test
    public void testRandomColorCase() {
        Utils util = new Utils();
        List<Integer> compare = new ArrayList<>();
        compare.add(1);
        compare.add(2);
        compare.add(3);
        int toCompare = util.getRandomAvailableColor(compare);
        assertTrue(compare.contains(toCompare));

        compare.add(5);
        assertFalse(compare.contains(7));
    }

    @Test
    public void testSerial2Dto1DCase() {
        Utils util = new Utils();
        int[][] compare = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String toCompare = util.getSerialized2Dto1D(compare);
        assertEquals(toCompare, "123456789");

        compare = new int[][]{{0, 0, 3}, {4, 5, 6}, {7, 8, 9}};
        toCompare = util.getSerialized2Dto1D(compare);
        assertNotEquals(toCompare, "123456789");
    }
}
