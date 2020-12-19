package fr.florentclarret.polytechtours.javaperformance.sortcollection.impl;

import fr.florentclarret.polytechtours.javaperformance.sortcollection.Sort;

import java.util.List;

public final class CombSort implements Sort {

    @Override
    public <T extends Comparable<T>> List<T> sort(final List<T> list) {
        int gap = list.size();
        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = nextGap(gap);

            swapped = false;

            for (int i = 0; i < list.size() - gap; i++) {
                if (list.get(i + gap).compareTo(list.get(i)) < 0) {
                    list.set(i + gap, list.set(i, list.get(i + gap)));
                    swapped = true;
                }
            }
        }

        return list;
    }

    private int nextGap(int gap) {
        gap = (gap * 10) / 13;
        return (gap < 1) ? 1 : gap;
    }
}
