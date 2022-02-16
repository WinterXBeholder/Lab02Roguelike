public class Monster {

    private final char symbol = 'X';

    private int x;
    private int y;
    private boolean isAlive = true;
    private boolean lastDirection = false;

    public Monster(int x, int y) {
        this.x = x;
        this.y = y;
    }
    private void setLASTDIRECTION() { lastDirection = !lastDirection;}
    public char getSymbol() {
        return symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void kill() { isAlive = false; }

//    private void moveLeft() {
//        x--;
//    }
//
//    private void moveRight() {
//        x++;
//    }
//
//    private void moveUp() {
//        y--;
//    }
//
//    private void moveDown() { y++; }

    public void move( int heroX, int heroY ) {
        if ( heroX != x && (lastDirection || heroY == y)) {
            x += (heroX - x)/Math.abs(heroX - x);
        } else if (heroY != y && (!lastDirection || heroX == x)){
            y += (heroY - y)/Math.abs(heroY - y);
        }
        setLASTDIRECTION();
    }
}
