package sweeper;

class Matrix
{
    Box [] [] matrix;
    Matrix(Box box)
    {
        matrix = new Box[Ranges.GetSize().x] [Ranges.GetSize().y];
        for (Coord coord : Ranges.getAllCoords())
            matrix[coord.x][coord.y]=box;
    }

    Box Get(Coord coord)
    {
        if(Ranges.InRange((coord)))
        return matrix[coord.x][coord.y];
        return null;
    }
    void Set(Coord coord,Box box)
    {
        if(Ranges.InRange((coord)))
            matrix [coord.x][coord.y]=box;
    }
}
