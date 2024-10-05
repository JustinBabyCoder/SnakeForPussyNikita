import java.util.Scanner;

public class inputRead implements Runnable {
    private static Scanner scanner = new Scanner(System.in);
    Snake snake;
    public inputRead(Snake snake)
    {
        this.snake = snake;
    }
    private static char input;
    public char getInput()
    {
        return input;
    }
    @Override
    public void run(){
        while (!snake.getIsDeath())
        {
            input = (char) scanner.next().charAt(0);
        }
    }

}
