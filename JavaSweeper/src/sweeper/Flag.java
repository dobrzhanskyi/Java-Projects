package sweeper;

public class Flag
{
    private Matrix flagMap;

    private int totalFlaged;
    private int totalClosed;

    void Start()
    {
        flagMap = new Matrix((Box.CLOSED));
        totalFlaged=0;
        totalClosed=Ranges.getSquare();
    }



    Box Get(Coord coord)
    {
        return flagMap.Get(coord);
    }

     void SetOpenedToBox(Coord coord)
    {
        flagMap.Set(coord,Box.OPENED);
        totalClosed--;
    }

    private void SetFlagedToBox(Coord coord)
    {
        flagMap.Set(coord,Box.FLAGED);
        totalFlaged++;
    }
    private void SetClosedToBox(Coord coord)
    {
        flagMap.Set(coord,Box.CLOSED);
        totalFlaged--;
    }

     void ToogleFlagedToBox(Coord coord)
     {
         switch(flagMap.Get(coord))
         {
             case FLAGED: SetClosedToBox(coord);break;
             case CLOSED: SetFlagedToBox(coord);break;
         }
    }

     int getTotalFlaged()
     {
        return totalFlaged;
    }

     int getTotalClosed()
     {
        return totalClosed;
    }

 void setFlagedToLastClosedBox()
 {
     for (Coord coord: Ranges.getAllCoords())
         if(Box.CLOSED==flagMap.Get(coord))
             SetFlagedToBox(coord);
    }

    public void setBombedToBox(Coord bombedCoord)
    {
        flagMap.Set(bombedCoord,Box.BOMBED);
    }

    void SetOpenedToCloseBox(Coord coord)
    {
        if(Box.CLOSED==flagMap.Get(coord))
            flagMap.Set(coord,Box.OPENED);
    }

     void setNoBombToFlagedBox(Coord coord)
     {
         if(Box.FLAGED==flagMap.Get(coord))
             flagMap.Set(coord,Box.NOBOMB);
    }

     int getCountOfFlagedBoxesAround(Coord coord)
    {
        int count=0;
        for(Coord around :Ranges.GetCoordsAround(coord))
            if(flagMap.Get(around)==Box.FLAGED)
                count++;
        return count;
    }
}
