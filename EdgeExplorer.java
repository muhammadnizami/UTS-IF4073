package nizami_13512501.UTS;

import android.util.Pair;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nim_13512501 on 05/10/16.
 */
public class EdgeExplorer {
    private int x;
    private int y;
    private int direction;

    public static final int DIRECTION_NORTH = 1;
    public static final int DIRECTION_NORTHEAST = 2;
    public static final int DIRECTION_EAST = 3;
    public static final int DIRECTION_SOUTHEAST = 4;
    public static final int DIRECTION_SOUTH = 5;
    public static final int DIRECTION_SOUTHWEST = 6;
    public static final int DIRECTION_WEST = 7;
    public static final int DIRECTION_NORTHWEST = 8;
    public EdgeExplorer(int x,int y,int direction){
        this.setX(x);
        this.setY(y);
        this.setDirection(direction);
    }

    public EdgeExplorer(EdgeExplorer edgeExplorer){
        this.setX(edgeExplorer.getX());
        this.setY(edgeExplorer.getY());
        this.setDirection(edgeExplorer.getDirection());
    }

    public EdgeExplorer next(){
        EdgeExplorer retval = new EdgeExplorer(this);
        retval.moveForwards();
        return retval;
    }

    public void moveForwards(){
        switch(getDirection()){
            case 1:
                setY(getY() - 1);
                break;
            case 2:
                setY(getY() - 1);
                setX(getX() + 1);
                break;
            case 3:
                setX(getX() + 1);
                break;
            case 4:
                setX(getX() + 1);
                setY(getY() + 1);
                break;
            case 5:
                setY(getY() + 1);
                break;
            case 6:
                setY(getY() + 1);
                setX(getX() - 1);
                break;
            case 7:
                setX(getX() - 1);
                break;
            case 8:
                setX(getX() - 1);
                setY(getY() - 1);
                break;
        }
    }

    public void move(int direction){
        this.setDirection(direction);
        moveForwards();
    }

    public void turnRight(){
        setDirection(getDirection() + 1);
        while (getDirection() > 8)
            setDirection(getDirection() - 8);
    }

    public void turnLeft(){
        setDirection(getDirection() - 1);
        while (getDirection() <=0)
            setDirection(getDirection() + 8);
    }

    public Pair<Integer,Integer> leftSideCoordinates(){
        switch(getDirection()){
            case 1:
                return new Pair<>(getX()-1, getY());
            case 2:
                return new Pair<>(getX() -1, getY() -1);
            case 3:
                return new Pair<>(getX(), getY() -1);
            case 4:
                return new Pair<>(getX() +1, getY() -1);
            case 5:
                return new Pair<>(getX() +1, getY());
            case 6:
                return new Pair<>(getX() +1, getY() +1);
            case 7:
                return new Pair<>(getX(), getY() +1);
            case 8:
                return new Pair<>(getX() -1, getY());
            default:
                return null;
        }
    }

    public boolean leftSideValue(boolean[][] booleanMatrix){
        Pair<Integer,Integer> coordinate = leftSideCoordinates();
        if (coordinate.first<0||coordinate.second<0||
                coordinate.first>=booleanMatrix.length||coordinate.second>=booleanMatrix[0].length)
            return false;
        return booleanMatrix[coordinate.first][coordinate.second];
    }

    public boolean leftObliqueSideValue(boolean[][] booleanMatrix){
        EdgeExplorer leftObliqueEdgeExplorer = new EdgeExplorer(this);
        leftObliqueEdgeExplorer.turnLeft();
        leftObliqueEdgeExplorer.moveForwards();
        return leftObliqueEdgeExplorer.atValue(booleanMatrix);
    }

    public boolean forwardsValue(boolean[][] booleanMatrix){
        EdgeExplorer next = this.next();
        return next.atValue(booleanMatrix);
    }

    public boolean atValue(boolean[][] booleanMatrix){
        if (getX() <0|| getY() <0|| getX() >=booleanMatrix.length|| getY() >=booleanMatrix[0].length)
            return false;
        return booleanMatrix[getX()][getY()];    }

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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * I.S. berada di x dan y yang di dalam booleanMatrix
     * F.S. booleanMatrix[x][y] == true atau x dan y di luar booleanMatrix
     * bila F.S. x dan y di luar booleanMatrix return false, else return true
     * @param booleanMatrix
     */
    public boolean scanLinePulau(boolean[][] booleanMatrix){
        while (getX() >=0 && getY() >=0 && getX() <booleanMatrix.length && getY() <booleanMatrix[0].length){
            if (atValue(booleanMatrix))
                return true;
            moveForwards();
        }
        return false;
    }

    /**
     * I.S. x,y di "pulau"
     * F.S. "arah" sedemikian hingga tangan kiri di "laut"
     * @param booleanMatrix
     * @return false jika tidak menemukan arah
     */
    public boolean scanDirectionEdge(boolean [][] booleanMatrix){
        int i=0;
        while ((!forwardsValue(booleanMatrix)  || leftObliqueSideValue(booleanMatrix)) && i<8){
            turnRight();
            i++;
        }
        return i<8;
    }

    public Integer[] getChainCode(boolean[][] booleanMatrix){
        int begin_x = getX();
        int begin_y = getY();
        int begin_dir = getDirection();
        List<Integer> chainCode = new LinkedList<>();
        do{
            scanDirectionEdge(booleanMatrix);
            chainCode.add(getDirection());
            moveForwards();
        }while (getX()!=begin_x||getY()!=begin_y);
        return chainCode.toArray(new Integer[chainCode.size()]);
    }

    public static Integer[] getChainCode(boolean[][] booleanMatrix, int x, int y, int scanDirection){
        EdgeExplorer edgeExplorer = new EdgeExplorer(x,y,scanDirection);
        edgeExplorer.scanLinePulau(booleanMatrix);
        edgeExplorer.scanDirectionEdge(booleanMatrix);
        Integer [] chainCode = edgeExplorer.getChainCode(booleanMatrix);
        return chainCode;
    }

    public String toString(){
        return "(("+getX()+", "+getY()+"), "+getDirection()+")";
    }
}
