package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0,1),
    NORTHEAST(1,1),
    EAST(1,0),
    SOUTHEAST(1,-1),
    SOUTH(0,-1),
    SOUTHWEST(-1,-1),
    WEST(-1,0),
    NORTHWEST(-1,1),
    NONE(0,0);

    private final int dx;
    private final int dy;
    Direction(int dx, int dy){
        this.dx=dx;
        this.dy=dy;
    }
    public float getAngle(){
        float degreX=0f;
        float degreY=0f;
        if(this.equals(SOUTHWEST))
            return 135f;
        if(dx!=0)
            degreX = (float)Math.toDegrees(Math.asin(dx));
        if(dy!=0)
            degreY = (float)Math.toDegrees(Math.acos(dy));
        if(dx==0||dy==0)
            return 360-degreX+degreY;
        else
            return 360-(degreX+degreY)/2;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Direction combine(Direction other){

        int x=this.dx+other.getDx();
        int y=this.dy+other.getDy();
        for(Direction direc : Direction.values())
            if(direc.getDx()==x && direc.getDy() ==y)
                return direc;
        return NONE;

    }
    public static Direction fromAngle(float angle){
        switch((int) angle){
            case 0: return Direction.NORTH;
            case 90: return Direction.EAST;
            case 270: return Direction.WEST;
            case 180: return Direction.SOUTH;
            case 45: return Direction.NORTHEAST;
            case 315: return Direction.NORTHWEST;
            case 135: return Direction.SOUTHEAST;
            case 225: return Direction.SOUTHWEST;
            default: return Direction.NONE;
        }

    }
}
