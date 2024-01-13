package fr.uparis.informatique.cpoo5.utils;

public record IntCoordinates(int x, int y) {
    public RealCoordinates toRealCoordinates(double scale) {
        return new RealCoordinates(x * scale, y * scale);
    }
}
