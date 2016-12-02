package Repository;

import java.util.ArrayList;

/**
 * Created by Nico on 01/12/2016.
 */
public interface Repository<T> {

    public T save(T t);

    public void delete(int id);

    public Object findOneById(int id);

    public ArrayList<T> findAll();

}
