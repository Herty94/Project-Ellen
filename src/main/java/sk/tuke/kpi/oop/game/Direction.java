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
        if(this.equals(NORTH))
            return 0f;
        else if(this.equals(EAST))
            return -90f;
        else if(this.equals(WEST))
            return 90f;
        else if(this.equals(SOUTH))
            return 180f;
        else if(this.equals(NORTHEAST))
            return -45f;
        else if(this.equals(NORTHWEST))
            return 45f;
        else if(this.equals(SOUTHWEST))
            return 135;
        else if(this.equals(SOUTHEAST))
            return -135f;
        else return 0;
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
        x=x<-1?-1:x;
        x=x>1?1:x;
        y=y<-1?-1:y;
        y=y>1?1:y;
        for(Direction direc : Direction.values())
            if(direc.getDx()==x && direc.getDy() ==y)
                return direc;
        return NONE;

    }
    public static Direction fromAngle(float angle){
        System.out.println("angle is this: "+angle);
        if(angle == 0f||angle==360f)
            return Direction.NORTH;
        else if(angle == 90f || angle == -270f)
            return Direction.WEST;
        else if(angle == -90f||angle == 270f)
            return Direction.EAST;
        else if(angle == 180f||angle ==-180f)
            return Direction.SOUTH;
        else if(angle == 135f|| angle==-225f)
            return Direction.SOUTHWEST;
        else if(angle == -135f || angle==225f)
            return Direction.SOUTHEAST;
        else if(angle == 45f || angle==-315f)
            return Direction.NORTHWEST;
        else if(angle == -45f|| angle==315f)
            return Direction.NORTHEAST;
        else
            return Direction.NONE;

    }
}
