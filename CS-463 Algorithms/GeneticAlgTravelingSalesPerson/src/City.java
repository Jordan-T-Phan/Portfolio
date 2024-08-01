public class City {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public City(int x, int y){
        this.x = x;
        this.y = y;



    }
    public static double Distance(City one, City two) {

        int xDistance = Math.abs(one.getX() - two.getX());
        int yDistance = Math.abs(one.getX() - two.getY());
        return Math.sqrt(xDistance*xDistance+yDistance*yDistance);


    }
}
