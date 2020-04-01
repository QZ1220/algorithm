package com.audi.other.p2;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 2. Problem Two
 * <p>
 * Implement a cache class (CachedData) that caches the target data, and implement a method (processCachedData),
 * which process the data if the cache is valid, if not, first update the cache object according to its data source,
 * then process it.
 * <p>
 * Requirments:
 * <p>
 * * Use ReentrantReadWriteLock to implement above requirements.
 * <p>
 * * Use lock downgrading
 *
 * @author: WangQuanzhou
 * @date: 2020/2/21 19:37
 */
public class CachedData<K, V> {
    private Map<K, V> cache = new HashMap<K, V>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private V processCachedData(K key) {
        V value = null;
        try {
            readWriteLock.readLock().lock();
            value = cache.get(key);
            if (value == null) {
                try {
                    readWriteLock.readLock().unlock();
                    readWriteLock.writeLock().lock();
                    value = cache.get(key);
                    if (value == null) {
                        // update the cache object according to its data source
                        // value = dbQuery(key);

                        // after db query, key not exist
                        if (value == null) {
                            return null;
                        }
                        cache.put(key, value);
                    }
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
            return value;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
