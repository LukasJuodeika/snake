package lt.ktu.zalciai.snakemap;

public class SnakeMapFactory {

    public static SnakeMap createDefaultMap(int mapWidth, int mapHeight) {
        return new SnakeMapDefault.Builder()
                .setWidth(mapWidth)
                .setHeight(mapHeight)
                .build();
    }

    public static SnakeMap createCenterWallMap(int mapWidth, int mapHeight) {
        return new SnakeMapCenterWall(mapWidth, mapHeight);
    }

    public static SnakeMap createCustomSidesMap(int mapWidth, int mapHeight) {
        return new SnakeMapCustomSides(mapWidth, mapHeight);
    }
}
