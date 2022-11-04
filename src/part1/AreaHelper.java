package part1;

import java.util.List;

public class AreaHelper {
    public static <E> void printArea(List<E> l, Area<E> area) {
        l.forEach(e -> System.out.println(area.compute(e)));
    }
}