package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    static private Coord size;
    static private ArrayList<Coord> allCoords;
    static private Random random = new Random();

    static void SetSize(Coord size)
    {
        Ranges.size=size;
        allCoords = new ArrayList<Coord>();
        for (int x =0; x<size.x;x++ )
            for (int y = 0;y<size.y;y++)
                allCoords.add(new Coord(x,y));
    }

    static public Coord GetSize()
    {
        return size;
    }
    static public ArrayList<Coord> getAllCoords()
    {
        return allCoords;
    }
    static  boolean InRange(Coord coord)
    {
        return coord.x>=0 && coord.x < size.x &&
                coord.y>=0 && coord.y<size.y;
    }
    static Coord GetRandomCoord()
    {
        return new Coord(random.nextInt(size.x),random.nextInt(size.y));
    }
    static ArrayList<Coord> GetCoordsAround(Coord coord)
    {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for(int x=coord.x-1;x<=coord.x+1;x++)
            for(int y=coord.y-1;y<=coord.y+1;y++)
                if(InRange(around=new Coord(x,y)))
                    if(!around.equals(coord))
                        list.add(around);
        return list;
    }
    static int getSquare()
    {
        return  size.x*size.y;
    }
}
