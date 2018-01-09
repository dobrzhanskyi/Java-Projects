package sweeper;

public class Game
{
    private Bomb bomb;
    private Flag flag;



    private GameState state;

    public void Start()
    {
       bomb.Start();
       flag.Start();
       state=GameState.PLAYED;
    }
    public Game(int cols,int rows, int bombs)
    {
        Ranges.SetSize(new Coord(cols,rows));
        bomb=new Bomb(bombs);
        flag = new Flag();
    }
    public Box GetBox(Coord coord)
    {
        if(Box.OPENED==flag.Get(coord))
            return bomb.Get(coord);
        else {
            return flag.Get(coord);
        }
    }

    public GameState GetState()
    {
        return state;
    }

    public void PressLeftButton(Coord coord)
    {
        if(gameOver()) return;
            openBox(coord);
            checkWinner();

    }

    private void checkWinner()
    {
        if(state == GameState.PLAYED)
            if(flag.getTotalClosed()== bomb.GetTotalBombs())
            {
                state=GameState.WINNER;
                flag.setFlagedToLastClosedBox();
            }
    }

    private void openBox(Coord coord)
    {
        switch (flag.Get(coord))
        {
            case OPENED: setOpenedToClosedBoxesAroundNumber(coord);break;
            case FLAGED:break;
            case CLOSED:
                switch(bomb.Get(coord))
                {
                    case ZERO: openBoxesAroundZero(coord);break;
                    case BOMB:openBoms(coord); break;
                    default:flag.SetOpenedToBox(coord);break;
                }
        }
    }

    private void setOpenedToClosedBoxesAroundNumber(Coord coord)
    {
        if(Box.BOMB!=bomb.Get(coord))
            if(bomb.Get(coord).getNumber()==flag.getCountOfFlagedBoxesAround(coord))
                for(Coord around:Ranges.GetCoordsAround(coord))
                    if(flag.Get(around)==Box.CLOSED)
                        openBox(around);
    }

    private void openBoms(Coord bombedCoord)
    {
        flag.setBombedToBox(bombedCoord);
        for(Coord coord:Ranges.getAllCoords())
            if(bomb.Get(coord)==Box.BOMB)
             flag.SetOpenedToCloseBox(coord);
             else
                 flag.setNoBombToFlagedBox(coord);
        state=GameState.BOMBED;
    }

    private void openBoxesAroundZero(Coord coord)
    {
        flag.SetOpenedToBox(coord);
        for (Coord around : Ranges.GetCoordsAround(coord))
            openBox(around);
    }
    private boolean gameOver()
    {
        if(GameState.PLAYED!=state){
            Start();
            return  true;
        }
        return false;
    }
    public void PressRightButton(Coord coord)
    {
        if(gameOver()) return;

            flag.ToogleFlagedToBox(coord);


    }
    public int getTotalBombs()
    {
        return  bomb.GetTotalBombs();
    }
    public int getTotalFlaged()
    {
        return  flag.getTotalFlaged();
    }
}
