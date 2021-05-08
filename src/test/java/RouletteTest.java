import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RouletteTest {

    Roulette roulette;
    ArrayList<Integer> values;

    @AfterEach
    public void rouletteNull(){
        roulette = null;
    }

    @BeforeEach
    public void initValues(){
        values = new ArrayList<Integer>();
    }

    @AfterEach
    public void valuesNull(){
        values = null;
    }

    @ParameterizedTest
    @ValueSource(ints = {3})
    @Timeout(value = 22000, unit = TimeUnit.MILLISECONDS)
    public void rouletteSpinTest(int randomNumber){
        long start = System.currentTimeMillis();
        roulette = new Roulette(randomNumber);

        roulette.spin();

        long end = System.currentTimeMillis();
        long duration = end - start;

        Assertions.assertTrue(duration > 19000);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 42})
    void resultTest(int randomNumber){
        roulette = new Roulette(randomNumber);
        roulette.result();
        int result = roulette.result;

        Assertions.assertTrue(result >= 0);
        Assertions.assertTrue(result <= 36);
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 52, 31, 22, 15, 18})
    void rouletteColorTest(int randomNumber){
        roulette = new Roulette(randomNumber);
        roulette.result();
        int result = roulette.result;
        Assertions.assertTrue((result % 2 == 0 && roulette.color.equals("Black"))
                || (result % 2 == 1 && roulette.color.equals("Red"))
                || (result == 0 && roulette.color.equals("Green")));
    }
}
