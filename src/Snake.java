
import java.util.*;

public class Snake {
    Scanner scan = new Scanner(System.in);
    private boolean isDeath;
    static Random rand = new Random();
    private static int lenghtSnake = 3;
    Score score = new Score();
    ArrayList<Integer> coordSnakePart = new ArrayList<>();

    public boolean getIsDeath() {
        return isDeath;
    }


    private static int sizeX;
    private static int sizeY;

    private static char[][] map;
    private char input;
    private int posXSnake;
    private int posYSnake;


    class Score {
        private int score;
        private int posXScore;
        private int posYScore;

        public void spawnScore() {
            boolean haveScore = false;
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    if (map[i][j] == '!') {
                        haveScore = true;
                        break;
                    }

                }

            }
            if (!haveScore) {
                posXScore = rand.nextInt(sizeX - 2) + 1;
                posYScore = rand.nextInt(sizeY - 2) + 1;
                map[posYScore][posXScore] = '!';
            }


        }

        public void checkUpScore() {
            if (posXSnake == posXScore && posYSnake == posYScore) {
                lenghtSnake++;


                System.out.println("                                            lengthSnake-" + lenghtSnake);
                spawnScore();
            }

        }
    }

    public void movable() throws InterruptedException {
        coordSnakePart.add(0, posYSnake);
        coordSnakePart.add(1, posXSnake);

        if (coordSnakePart.size() > lenghtSnake * 2) {

            coordSnakePart.subList(lenghtSnake * 2, coordSnakePart.size()).clear();
            map[coordSnakePart.get(coordSnakePart.size() - 2)][coordSnakePart.get(coordSnakePart.size() - 1)] = ' ';


        }


        switch (input) {
            case 'a': {
                if (map[posYSnake][posXSnake - 1] != '#') {
                    if(map[posYSnake][posXSnake - 1] == 'o')
                    {
                        isDeath=true;
                        break;
                    }
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posXSnake--;

                } else if (map[posYSnake][posXSnake - 1] == '#') {
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posXSnake = sizeX - 2;
                }
                break;
            }
            case 'd': {
                if (map[posYSnake][posXSnake + 1] != '#') {
                    if(map[posYSnake][posXSnake + 1] == 'o')
                    {
                        isDeath=true;
                        break;
                    }
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posXSnake++;

                } else if (map[posYSnake][posXSnake + 1] == '#') {
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posXSnake = 1;
                }
                break;
            }
            case 'w': {
                if (map[posYSnake - 1][posXSnake] != '#') {
                    if(map[posYSnake-1][posXSnake ] == 'o')
                    {
                        isDeath=true;
                        break;
                    }
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posYSnake--;

                } else if (map[posYSnake - 1][posXSnake] == '#') {
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posYSnake = sizeY - 2;
                }
                break;
            }
            case 's': {
                if (map[posYSnake + 1][posXSnake] != '#') {
                    if(map[posYSnake+1][posXSnake ] == 'o')
                    {
                        isDeath=true;
                        break;
                    }
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posYSnake++;

                } else if (map[posYSnake + 1][posXSnake] == '#') {
                    coordSnakePart.add(posYSnake);
                    coordSnakePart.add(posXSnake);
                    posYSnake = 1;
                }
                break;
            }


        }
        score.checkUpScore();

        map[posYSnake][posXSnake] = 'o';
    }


    public void createMap(int sizeY, int sizeX) {
        this.sizeY = sizeY;
        this.sizeX = sizeX;


        map = new char[sizeY][sizeX];

        posXSnake = sizeX / 2;
        posYSnake = sizeY / 2;

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (i == 0 || i == sizeY - 1 || j == 0 || j == sizeX - 1) {
                    map[i][j] = '#';
                } else {
                    map[i][j] = ' ';
                }
            }

        }

        map[posYSnake][posXSnake] = 'o';


    }


    public void printMap() {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Snake snake = new Snake();
        snake.createMap(10, 20);

        inputRead inputCore = new inputRead(snake);
        Thread thread1 = new Thread(inputCore);
        thread1.start();
        while (!snake.isDeath) {
            snake.input = inputCore.getInput();

            snake.movable();
            snake.score.spawnScore();
            Thread.sleep(500);
            snake.printMap();

        }
        System.out.println("Вы проиграли сори ^_^");
    }
}
