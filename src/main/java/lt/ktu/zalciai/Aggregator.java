package lt.ktu.zalciai;

import java.util.Iterator;

public interface Aggregator<T> {
    Iterator<T> getIterator();
}
