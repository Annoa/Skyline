package com.skyline.model.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Abstract implementation of a database access object
 * Currently only supports hardcoded data
 *
 * @author anno
 */
public abstract class AbstractDAO<T extends IEntity<K>, K> implements IDAO<T, K> {
      
//    private final Class<T> clazz;
    private List<T> elems;

    public AbstractDAO() {
//        this.clazz = clazz;
        this.elems = new ArrayList<T>();
    }
    
    @Override
    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Nulls not allowed");
        }
        elems.add(t);
    }

    @Override
    public void remove(K id) {
        T t = find(id);
        if (t != null) {
            elems.remove(t);
        }
    }

    @Override
    public T update(T t) {
        T old = find(t.getId());
        if (old != null) {
            elems.remove(old);
        }
        elems.add(t);
        
        return t;
    }

    @Override
    public T find(K id) {
        for (T t : elems) {
            if (t.getId().equals(id)) { // NOTE: equals, not ==
                return t;
            }
        }
        return null;
    }

    @Override
    public List<T> getRange(int nItems) {
        // From inclusive, to exclusive
        return elems.subList(0, nItems);
    }

    @Override
    public int getCount() {
        return elems.size();
    }

    public void sort(Comparator<T> comp) {
        Collections.sort(elems, comp);
    }
    
}
