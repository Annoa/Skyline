package com.skyline.skyline.model;

import java.util.List;

/**
 * Interface for a database access object
 *
 * @author anno
 */
public interface IDAO<T, K> {
    public void add(T t);

    public void remove(K id);

    public T update(T t);

    public T find(K id);

    public List<T> getRange(int nItems);

    public int getCount();
}
