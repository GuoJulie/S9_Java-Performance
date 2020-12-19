package fr.florentclarret.polytechtours.javaperformance.sortcollection.impl;

import fr.florentclarret.polytechtours.javaperformance.sortcollection.Sort;

import java.util.List;

public final class BubbleSort implements Sort {

    @Override
    public <T extends Comparable<T>> List<T> sort(final List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size() - i; j++) {
                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    list.set(j - 1, list.set(j, list.get(j - 1)));
                }
            }
        }
        return list;
    }

}
