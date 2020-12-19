package fr.florentclarret.polytechtours.javaperformance.sortcollection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractSortTest<T extends Sort> {

    private final Supplier<T> sortSupplier;

    private Sort sort;

    protected AbstractSortTest(final Supplier<T> sortSupplier) {
        this.sortSupplier = sortSupplier;
    }

    private <U extends Comparable<U>> void testSort(final List<U> input) {
        final List<U> result = this.sort.sort(new ArrayList<>(input));
        final List<U> tmp = new ArrayList<>(input);

        Assertions.assertEquals(result.size(), input.size());

        for (int i = 0; i < input.size() - 1; i++) {
            Assertions.assertTrue(result.get(i).compareTo(result.get(i + 1)) <= 0);
            Assertions.assertTrue(tmp.remove(result.get(i)));
        }

        if (!input.isEmpty()) {
            Assertions.assertTrue(tmp.remove(result.get(result.size() - 1)));
        }

        Assertions.assertTrue(tmp.isEmpty());
    }

    @BeforeEach
    public void beforeEach() {
        this.sort = sortSupplier.get();
    }

    @Test
    public void testSortNullArray() {
        Assertions.assertThrows(NullPointerException.class, () -> this.sort.sort(null));
    }

    @Test
    public void testSortEmptyArray() {
        testSort(List.<Integer>of());
    }

    @Test
    public void testSortSmallSortedArray() {
        testSort(List.of(1, 2, 3, 4, 5));
    }

    @Test
    public void testSortSmallReverseSortedArray() {
        testSort(List.of(5, 4, 3, 2, 1));
    }

    @Test
    public void testSortSmallSortedArrayWithOnlyNegativeValues() {
        testSort(List.of(-5, -4, -3, -2, -1));
    }

    @Test
    public void testSortSmallReverseSortedArrayWithOnlyNegativeValues() {
        testSort(List.of(-1, -2, -3, -4, -5));
    }

    @Test
    public void testSortSmallSortedArrayWithTwoValuesSwapped() {
        testSort(List.of(1, 2, 5, 4, 3));
    }

    @Test
    public void testSortLargeSortedArray() {
        testSort(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
    }

    @Test
    public void testSortLargeReverseSortedArray() {
        testSort(List.of(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    }

    @Test
    public void testSortLargeArrayWithTwoValuesSwapped() {
        testSort(List.of(0, 1, 2, 8, 4, 5, 6, 7, 3, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
    }

    @Test
    public void testSortLargeSortedArrayWithOnlyNegativeValues() {
        testSort(List.of(-20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1));
    }

    @Test
    public void testSortLargeReverseSortedArrayWithOnlyNegativeValues() {
        testSort(List.of(-1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, -19, -20));
    }

    @Test
    public void testSortJumbledArrayWithSmallRangeOfValues() {
        testSort(List.of(5, -3, 2, 0, -5, 1, 4, -4, -2, 3, -1));
    }

    @Test
    public void testSortJumbledArrayWithLargeRangeOfValues() {
        testSort(List.of(102, 10, -50, 2938, 108, -4011, -38, 523, 16));
    }

    @Test
    public void testSortArrayWithDuplicateValues() {
        testSort(List.of(-2, -7, 1, 9, -7, 1, -2, 10, -7, -7));
    }
}
