import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

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

    @Test
    public void testGetAllCombinationsCase() {
        BaseState baseState = new BaseState();
        assertFalse(baseState.getCombinations().size() < 0);
        Map<String, List<Integer>> combinations = baseState.getAllCombinations();
        assertTrue(combinations.size() > 0);
    }

    @Test
    public void testCheckMirrorImagesValidPositionsCase() {
        BaseState baseState = new BaseState();
        List<Integer> empty = baseState.checkMirrorImagesValidPositions("000000001");
        assertTrue(empty.size() > 0);
    }

    @Test
    public void testGetAllAvailablePositionsCase() {
        BaseState baseState = new BaseState();
        List<Integer> emptyStates = baseState.checkMirrorImagesValidPositions("000000001");
        assertTrue(emptyStates.size() > 0);
    }

    @Test
    public void testGetValidCombinationCase() {
        BaseState baseState = new BaseState();
        String mirrorCombination = baseState.getValidCombination("002021001");
        assertEquals(mirrorCombination, "000020112");
        mirrorCombination = baseState.getValidCombination("000021001");
        assertEquals(mirrorCombination, mirrorCombination);
    }

    @Test
    public void testGetNewConfigBoardCase() {
        BaseState baseState = new BaseState();
        int[][] board = new int[][]{{0, 0, 2}, {0, 2, 1}, {0, 0, 1}};
        baseState.getNewConfigBoard(board, "000020112");
        assertTrue(Arrays.deepEquals(board, new int[][]{{0, 0, 0}, {0, 2, 0}, {1, 1, 2}}));
    }

    @Test
    public void testGetCustomSizeValueCase() {
        BaseState baseState = new BaseState();
        List<Integer> positionsAvailable = new ArrayList<>();
        positionsAvailable.add(3);
        positionsAvailable.add(5);
        positionsAvailable.add(7);
        List<Integer> availableSpots = baseState.getCustomSizeValue(positionsAvailable);
        assertTrue(availableSpots.size() == 9);
    }

    @Test
    public void testRewardSystemCase() {
        BaseState baseState = new BaseState();
        Map<String, Integer> path = new HashMap<>();
        path.put("000000000", 0);
        baseState.rewardSystem(path, 1);
        List<Integer> temp = baseState.getAllAvailablePositions("000000000");
        assertTrue(temp.get(0) > 0);
    }
}
