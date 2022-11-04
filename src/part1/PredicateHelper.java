package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateHelper {
    public <T> Iterable<T> filter(Iterable<T> list, Predicate<T> cond){
        List<T> returnValue = new ArrayList<>();
        list.forEach(e -> {
            if(cond.test(e)){
                returnValue.add(e);
            }
        });
        return list;
    }
}
