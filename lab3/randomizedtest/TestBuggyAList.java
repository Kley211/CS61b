package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove()
    {
        AList<Integer> aList = new AList<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        for(int i = 4;i <=6; i++)
        {
            aList.addLast(i);
            buggyAList.addLast(i);
        }
        assertEquals(aList.size(),buggyAList.size());

        for(int i = 0;i < aList.size();i++)
        {
            assertEquals(aList.removeLast(),buggyAList.removeLast());
        }
    }

    @Test
    public void randomizedTest()
    {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {

            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size(),B.size());
            }
            else if(operationNumber == 2)
            {
                int size = L.size();

                if(size > 0)
                {
                    assertEquals(L.getLast(),B.getLast());
                    assertEquals(L.removeLast(),B.removeLast());
                }
            }
        }
    }
}
