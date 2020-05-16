package TP3;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Created by efalcon
 * This is an Iterator of Arcs Iterators
 */
public class ArcIterator<T> implements Iterator<Arc<T>> {

    private List<Iterator<Arc<T>>> iteratorList;

    public ArcIterator(List<Iterator<Arc<T>>> iteratorList) {
        this.iteratorList = iteratorList;
    }

    @Override
    public boolean hasNext() {
        return iteratorList.stream().filter(x -> x.hasNext())
                .findFirst()
                .isPresent();
    }

    @Override
    public Arc<T> next() {
        Optional<Iterator<Arc<T>>> first = iteratorList.stream()
                .filter(x -> x.hasNext())
                .findFirst();
        return first.isPresent() ? first.get().next() : null;
    }
}
