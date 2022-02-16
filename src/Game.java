import java.util.Random;
import java.util.Scanner;

public class Game {

    // constants
    private static int WIDTH;
    private final static String WALL_CHARACTER = "M";
    private final static String EMPTY_CHARACTER = " ";
    private boolean SHRINK = false;

    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private Monster monster;
    private Treasure treasure;
    private boolean isOver;

    public void run() {
        setUp();
        while (!isOver) {
            printWorld();
            move();
        }
        printWorld();
    }
    public void setWIDTH(int num) {WIDTH = num;}
    public void setSHRINK(boolean bool) {SHRINK = bool;}
    public void setISOVER() {
        if (hero.getX() < 0 || hero.getX() >= WIDTH
                || hero.getY() < 0 || hero.getY() >= WIDTH) {
            System.out.println(hero.getName() + " touched lava! You lose.");
            isOver = true;
        } else if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
            System.out.println(hero.getName() + ", the monster found you! You lose.");
            isOver = true;
        } else if (hero.getX() == treasure.getX() && hero.getY() == treasure.getY()) {
            System.out.println(hero.getName() + " found the treasure! You win.");
            isOver = true;
        }
    }

    private void setUp() {
        System.out.print("What is the name of your hero?: ");
        String name = Input.getWord(console);
        System.out.println("What symbol would you like to represent your hero?");
        char symbol = Input.getChar(console);
        System.out.println("How big would you like the map to be? [10-100]");
        this.setWIDTH(Input.getInt(console, 10, 100));
        System.out.println("Race the lava to the treasure!");

        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);
        hero = new Hero(name, symbol, x, y);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY());
        treasure = new Treasure(x, y);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while ((x == hero.getX() && y == hero.getY()) || (x == treasure.getX() && y == treasure.getY()));
        monster = new Monster(x, y);
    }

    private void printWorld() {
        // top wall border
        setWIDTH( SHRINK ? WIDTH - 1 : WIDTH);
        setSHRINK(!SHRINK);
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++) {
            // left wall border
            System.out.print(WALL_CHARACTER);
            for (int col = 0; col < WIDTH; col++) {
                if (row == monster.getY() && col == monster.getX()) {
                    System.out.print(monster.getSymbol());
                } else if (row == hero.getY() && col == hero.getX()) {
                    System.out.print(hero.getSymbol());
                } else if (row == treasure.getY() && col == treasure.getX()) {
                    System.out.print("T");
                } else {
                    System.out.print(EMPTY_CHARACTER);
                }
            }

            // right wall border
            System.out.println(WALL_CHARACTER);
        }

        // bottom wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));
        if (!isOver) {setISOVER();}
    }

    private void move() {
        if (!isOver){
            System.out.print(hero.getName() + ", move [WASD]: ");
            String move = console.nextLine().trim().toUpperCase();

            if (move.length() != 1) {
                return;
            }

            switch (move.charAt(0)) {
                case 'W':
                    hero.moveUp();
                    break;
                case 'A':
                    hero.moveLeft();
                    break;
                case 'S':
                    hero.moveDown();
                    break;
                case 'D':
                    hero.moveRight();
                    break;
            }
            monster.move(hero.getX(), hero.getY());
            setISOVER();
        }
    }
}
