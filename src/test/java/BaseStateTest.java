import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BaseStateTest {

    @Test
    public void testBaseStateCase() {
        BaseState baseState = new BaseState();
        int[][] winWin = baseState.getWinWinStates();
        assertTrue(winWin.length > 0);
    }


    @Test
    public void testAddAllRotationsCase() {
        BaseState baseState = new BaseState();
        int[][] rotations = baseState.getAllRotations();
        assertTrue(rotations.length > 0);
    }

    @Test
    public void testCreateWinWinStatesCase() {
        BaseState baseState = new BaseState();
        int[][] winWin = baseState.getWinWinStates();
        assertTrue(winWin.length > 0);
    }

    @Test
    public void testIsAlreadyPresentCase() {
        BaseState baseState = new BaseState();
        assertTrue(baseState.getCombinations().size() > 0);
        assertTrue(baseState.isAlreadyPresent(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, "000000000"));
        assertFalse(baseState.isAlreadyPresent(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, "111111111"));
    }

    @Test
    public void testIsValidUniqueRotationCase() {
        BaseState baseState = new BaseState();
        assertFalse(baseState.isValidUniqueRotation("000000000"));
        assertTrue(baseState.isValidUniqueRotation("111111111"));
    }

    @Test
    public void testIsValidWinWinMoveCase() {
        BaseState baseState = new BaseState();
        assertFalse(baseState.isValidWinWinMove("1012000000"));
        assertTrue(baseState.isValidWinWinMove("111000220"));
    }

    @Test
    public void testComputeCase() {
        BaseState baseState = new BaseState();
        baseState.compute(new ArrayList<Integer>());
        assertTrue(baseState.getCombinations().size() > 0);
    }
}
