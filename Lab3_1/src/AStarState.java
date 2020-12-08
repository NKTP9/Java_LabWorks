import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    private HashMap<Location, Waypoint> openWaypoints; // Открытые вершины
    private HashMap<Location, Waypoint> closedWaypoints; // Закрытые вершины

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;

        openWaypoints = new HashMap<Location, Waypoint>(); // Ссылки на новую пустую коллекцию
        closedWaypoints = new HashMap<Location, Waypoint>();
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /** Возращает количество точек в наборе открытых вершин **/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }

    /**
     * Эта функция должна проверить все вершины в наборе открытых вершин,
     * и после этого она должна вернуть ссылку на вершину с наименьшей общей
     * стоимостью. Если в "открытом" наборе нет вершин, функция возвращает NULL.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        Waypoint ver = null;
        double min = Double.POSITIVE_INFINITY; //
        float totalCost = 0;

        for(Waypoint p : openWaypoints.values())
        {
            totalCost = p.getTotalCost();
            if(min > totalCost)
            {
                min = totalCost;
                ver = p;
            }
        }
        return ver;
    }

    /**
     * Данный метод должен добавлять указанную вершину только в том
     * случае, если существующая вершина хуже новой.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Waypoint other = openWaypoints.get(newWP.loc);

        if(other == null || newWP.getPreviousCost() < other.getPreviousCost()) // Если в наборе "открытых вершин" нет вершины для местоположения
        {
            openWaypoints.put(newWP.loc, newWP); // Добавляем новую вершину
            return true;
        }
        return false;
    }

    /**
     * Эта функция должна возвращать значение true, если указанное
     * местоположение встречается в наборе закрытых вершин, и false в противном
     * случае.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closedWaypoints.containsKey(loc);
    }

    /**
     * Эта функция перемещает вершину из набора «открытых вершин» в набор
     * «закрытых вершин». Так как вершины обозначены местоположением, метод
     * принимает местоположение вершины.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint point = openWaypoints.remove(loc); // Удаление вершины, соответствующей указанному местоположению из набора «открытых вершин».

        if(point != null)
        {
            closedWaypoints.put(loc, point); // Добавление вершины в набор закрытых вершин
        }
    }
}
