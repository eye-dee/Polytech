package introducion.task;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Polytech
 * Created by igor on 18.04.17.
 */
public class SumTask implements Callable<Integer>, Serializable, HazelcastInstanceAware {
    private transient HazelcastInstance hazelcastInstance;

    @Override
    public void setHazelcastInstance(final HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public Integer call() throws Exception {
        IMap<String, Integer> map = hazelcastInstance.getMap("map");

        int result = 0;
        for (final String key : map.localKeySet()) {
            System.out.println("Calculating for key: " + key);
            result += map.get(key);
        }

        System.out.println("Local result = " + result);
        return result;
    }
}
