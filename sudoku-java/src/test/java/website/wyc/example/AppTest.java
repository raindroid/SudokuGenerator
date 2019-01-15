package website.wyc.example;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import website.wyc.example.controllers.SuHolesGenerator;
import website.wyc.example.controllers.SuSolver;
import website.wyc.example.models.SuBoard;

import static org.junit.Assert.assertTrue;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void boardSetup() {
        SuBoard a = new SuBoard();
        a.set(0,0,4);
        System.out.println(a.toString());
    }

    @Test
    public void boardGenerator() {
        System.out.println(SuSolver.generateDirectly().toStringP());
    }

    @Test
    public void boardHoles() {
        long avg = 0;
        int count = 100;    //生成数量
        for (int i = 0; i < count; i++) {
            long startTime = System.currentTimeMillis();
            System.out.println(
                    SuHolesGenerator.setBoard(
                            SuSolver.generateDirectly())    //创建终盘
                            .generate(45)         //设置挖洞数量
                            .toStringP());
            avg += System.currentTimeMillis() - startTime;
        }
        System.out.println(avg / count);
    }

    @Test
    public void peep() {
        Deque<Integer> a = new LinkedList<>();
        a.add(0);
        a.add(1);
        a.add(2);
        System.out.println(a);
        System.out.println(a.pop());
        System.out.println(a);
    }
}
