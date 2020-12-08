/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals(Object obj)
    {
        if (this == obj) // Является ли объектом
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass()) // Если класс объекта не равен текущему классу
            return false;
        Location other = (Location) obj;
        if (xCoord != other.xCoord)
            return false;
        if (yCoord != other.yCoord)
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = 17; // Любое ненулевое простое число

        // Выставить значения следующим образом: 37 * result + value
        result = 37 * result + (xCoord * 11);
        result = 37 * result + (yCoord * 13);
        return result;
    }
}