package berserk.algoviz.model;

public record Cell(int row, int col, int dist, Cell prev) {
}
