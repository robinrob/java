import java.util.Random;

public class RandomTester
{

    private Random rand;

    private int[] random_array;

    public RandomTester()
    {
        rand = new Random(5);
        random_array = new int[6];
    }

    
    public int randomNumber(int max)
    {
        return rand.nextInt(max);
    }
     
    public int[] randomNumbers()
    {
        for (int i = 0; i < random_array.length; i++){
            random_array[i] = rand.nextInt(48) + 1;
            for (int j = 0; j < i; j++){
                if (random_array[i] == random_array[j]){
                    random_array[i] = rand.nextInt(48) + 1;
                }
            }
        }
        return random_array;
    }
    
    public void printRandomNumbers()
    {
        randomNumbers();
        System.out.println("--------------------------------");
        System.out.println("Six random numbers: ");
        for (int i = 0; i < random_array.length; i++){
            System.out.println(random_array[i]);
        }
    }
    
    public void randomResponse()
    {
        int number = randomNumber(3);
        if (number == 0){
            System.out.println("Yes");
        }
        else if (number == 1){
            System.out.println("No");
        }
            else {
                System.out.println("Maybe!");
            }
    }
    
    
}