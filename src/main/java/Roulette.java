import java.util.Random;

public class Roulette {
    private final Random random;
    public String color = "";
    public int result = -1;

    public Roulette(int randomNumber){
        this.random = new Random(randomNumber);
    }

    public void spin(){
        try
        {
            Thread.sleep(20000);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        result();
    }

    public void result(){
        result = random.nextInt(37);
        if (result % 2 == 0){
            color = "Black";
        }
        else {
            color = "Red";
        }
        if (result == 0){
            color = "Green";
        }
    }
}
