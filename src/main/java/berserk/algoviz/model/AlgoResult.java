package berserk.algoviz.model;

import berserk.algoviz.enums.SortType;

import java.util.List;


public record AlgoResult(SortType sortType, List<Move> moves, int[] nonSortedArray, double timing) {
}
