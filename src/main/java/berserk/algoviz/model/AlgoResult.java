package berserk.algoviz.model;

import berserk.algoviz.enums.SortType;

import java.util.List;


public record AlgoResult(SortType sortType, List<Step> steps, List<LanguagePerformance> languagePerformances, int[] nonSortedArray) {
}
