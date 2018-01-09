package sweeper;

 class Bomb
 {
     private Matrix bombMap
             ;
     private int totalBombs;

      Bomb(int totalBombs) {
         this.totalBombs = totalBombs;
         FixBombCount();
     }
      void Start()
     {
         bombMap=new Matrix(Box.ZERO);
         for (int j=0;j<totalBombs;j++)
        PlaceBomb();

     }
      Box Get (Coord coord)
 {
     return bombMap.Get(coord);
 }
 private void PlaceBomb()
 {
     int loop=100;
     while (loop -->0)
     {
         Coord coord = Ranges.GetRandomCoord();

         if(Box.BOMB==bombMap.Get(coord))
             continue;

         bombMap.Set(coord, Box.BOMB);
         IncNumberAroundBomb(coord);
         break;
     }
 }
private  void FixBombCount()
{
    int maxBombs=Ranges.GetSize().x*Ranges.GetSize().y/2;
    if(totalBombs>maxBombs)
        totalBombs=maxBombs;
}

int GetTotalBombs()
{
    return totalBombs;
}

     private void IncNumberAroundBomb(Coord coord)
     {
         for(Coord around: Ranges.GetCoordsAround(coord))
             if(Box.BOMB!=bombMap.Get(around))
             bombMap.Set(around,bombMap.Get(around).NextNumberBox());
     }
 }
