import org.junit.Test;

import static org.junit.Assert.*;

public class HumanTest {

    @Test
    public void testHorizontalWinCase() {

        Human human = new Human(new int[][]{{2, 2, 0}, {1, 1, 0}, {1, 0, 0}});
        assertTrue(human.horizontalWinCase());
        human = new Human(new int[][]{{1, 1, 0}, {2, 2, 0}, {1, 0, 0}});
        assertTrue(human.horizontalWinCase());
        human = new Human(new int[][]{{1, 0, 0}, {1, 1, 0}, {2, 2, 0}});
        assertTrue(human.horizontalWinCase());

        human = new Human(new int[][]{{0, 2, 2}, {1, 1, 0}, {1, 0, 0}});
        assertTrue(human.horizontalWinCase());
        human = new Human(new int[][]{{1, 1, 0}, {0, 2, 2}, {1, 0, 0}});
        assertTrue(human.horizontalWinCase());
        human = new Human(new int[][]{{1, 0, 0}, {1, 1, 0}, {0, 2, 2}});
        assertTrue(human.horizontalWinCase());

        human = new Human(new int[][]{{0, 2, 0}, {0, 1, 0}, {1, 0, 0}});
        assertFalse(human.horizontalWinCase());
    }

    @Test
    public void testVerticalWinCase() {

        Human human = new Human(new int[][]{{2, 0, 0}, {2, 1, 1}, {0, 1, 0}});
        assertTrue(human.verticalWinCase());
        human = new Human(new int[][]{{1, 2, 0}, {1, 2, 0}, {0, 0, 1}});
        assertTrue(human.verticalWinCase());
        human = new Human(new int[][]{{1, 0, 2}, {1, 1, 2}, {0, 0, 0}});
        assertTrue(human.verticalWinCase());

        human = new Human(new int[][]{{0, 1, 1}, {2, 1, 0}, {2, 0, 0}});
        assertTrue(human.verticalWinCase());
        human = new Human(new int[][]{{1, 0, 1}, {0, 2, 2}, {0, 2, 1}});
        assertTrue(human.verticalWinCase());
        human = new Human(new int[][]{{1, 0, 0}, {1, 1, 2}, {0, 0, 2}});
        assertTrue(human.verticalWinCase());

        human = new Human(new int[][]{{0, 2, 0}, {0, 1, 0}, {1, 0, 0}});
        assertFalse(human.verticalWinCase());
    }

    @Test
    public void testDiagonalWinCase() {

        Human human = new Human(new int[][]{{2, 0, 0}, {1, 2, 1}, {0, 1, 0}});
        assertTrue(human.diagonalWinCase());
        human = new Human(new int[][]{{1, 1, 2}, {1, 2, 0}, {0, 0, 1}});
        assertTrue(human.diagonalWinCase());

        human = new Human(new int[][]{{0, 1, 1}, {1, 2, 0}, {0, 0, 2}});
        assertTrue(human.diagonalWinCase());
        human = new Human(new int[][]{{1, 0, 0}, {0, 2, 0}, {2, 1, 1}});
        assertTrue(human.diagonalWinCase());

        human = new Human(new int[][]{{0, 2, 0}, {0, 1, 0}, {1, 0, 0}});
        assertFalse(human.diagonalWinCase());
    }

    @Test
    public void testBlockWin() {

        //Horizontal blocks
        Human human = new Human(new int[][]{{1, 1, 0}, {2, 0, 0}, {0, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 0}, {1, 1, 0}, {0, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 0}, {0, 0, 0}, {1, 1, 0}});
        assertTrue(human.blockWins());

        human = new Human(new int[][]{{0, 1, 1}, {0, 2, 0}, {0, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 0}, {0, 1, 1}, {0, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 0}, {0, 0, 0}, {0, 1, 1}});
        assertTrue(human.blockWins());

        //Vertical blocks
        human = new Human(new int[][]{{1, 0, 0}, {1, 2, 0}, {0, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 1, 0}, {0, 1, 0}, {0, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 1}, {0, 0, 1}, {0, 0, 0}});
        assertTrue(human.blockWins());

        human = new Human(new int[][]{{0, 0, 0}, {1, 2, 0}, {1, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 0}, {0, 1, 0}, {0, 1, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 0}, {0, 0, 1}, {0, 0, 1}});
        assertTrue(human.blockWins());

        //Diagonal Block
        human = new Human(new int[][]{{1, 0, 0}, {2, 1, 0}, {0, 0, 0}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 1}, {0, 1, 0}, {0, 0, 0}});
        assertTrue(human.blockWins());

        human = new Human(new int[][]{{0, 0, 0}, {2, 1, 0}, {0, 0, 1}});
        assertTrue(human.blockWins());
        human = new Human(new int[][]{{2, 0, 0}, {0, 1, 0}, {1, 0, 0}});
        assertTrue(human.blockWins());

        human = new Human(new int[][]{{1, 2, 0}, {0, 0, 0}, {0, 1, 0}});
        assertFalse(human.blockWins());

    }

    @Test
    public void testFork() {

        Human human = new Human(new int[][]{{2, 0, 1}, {0, 1, 0}, {0, 0, 2}});
        assertTrue(human.fork());
        human = new Human(new int[][]{{2, 0, 0}, {0, 1, 0}, {1, 0, 2}});
        assertTrue(human.fork());

        human = new Human(new int[][]{{1, 0, 2}, {0, 1, 0}, {2, 0, 0}});
        assertTrue(human.fork());
        human = new Human(new int[][]{{0, 0, 2}, {0, 1, 0}, {2, 0, 1}});
        assertTrue(human.fork());

        human = new Human(new int[][]{{0, 0, 2}, {0, 1, 0}, {2, 1, 0}});
        assertFalse(human.fork());
    }

    @Test
    public void testForkBlock() {
        Human human = new Human(new int[][]{{1, 0, 0}, {0, 2, 0}, {0, 0, 1}});
        assertTrue(human.forkBlock());
        human = new Human(new int[][]{{0, 0, 1}, {0, 2, 0}, {1, 0, 0}});
        assertTrue(human.forkBlock());

        human = new Human(new int[][]{{0, 0, 1}, {0, 2, 0}, {0, 1, 0}});
        assertFalse(human.forkBlock());
    }

    @Test
    public void testCenter() {
        Human human = new Human(new int[][]{{0, 0, 1}, {0, 0, 0}, {0, 0, 0}});
        assertTrue(human.isCenterEmpty());

        human = new Human(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        assertFalse(human.isCenterEmpty());
    }

    @Test
    public void testSide() {
        Human human = new Human(new int[][]{{0, 0, 1}, {0, 0, 0}, {0, 0, 0}});
        assertTrue(human.isSideEmpty());

        human = new Human(new int[][]{{0, 1, 0}, {2, 0, 2}, {0, 1, 0}});
        assertFalse(human.isSideEmpty());
    }

    @Test
    public void testCorner() {
        Human human = new Human(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        assertTrue(human.isCornerEmpty());

        human = new Human(new int[][]{{1, 0, 2}, {0, 0, 0}, {2, 0, 1}});
        assertFalse(human.isCornerEmpty());
    }

    @Test
    public void testHumanStrat() {
        Human human = new Human(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        assertTrue(human.humanStrat());

        human = new Human(new int[][]{{1, 2, 1}, {2, 1, 2}, {1, 2, 1}});
        assertFalse(human.humanStrat());
    }

    @Test
    public void testPlayCase() {
        Human human = new Human(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        human.play();
        assertTrue(human.humanStrat());
    }

    @Test
    public void testOppCorner() {
        Human human = new Human(new int[][]{{0, 0, 1}, {1, 2, 0}, {0, 0, 0}});
        assertTrue(human.oppositeCorner());

        human = new Human(new int[][]{{1, 0, 0}, {0, 2, 1}, {0, 0, 0}});
        assertTrue(human.oppositeCorner());

        human = new Human(new int[][]{{0, 0, 0}, {1, 2, 0}, {0, 0, 1}});
        assertTrue(human.oppositeCorner());

        human = new Human(new int[][]{{0, 0, 0}, {0, 2, 1}, {1, 0, 0}});
        assertTrue(human.oppositeCorner());

        human = new Human(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 0, 0}});
        assertFalse(human.oppositeCorner());


    }
}
