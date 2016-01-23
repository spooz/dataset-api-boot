package dataset.repository;

import dataset.domain.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;

/**
 * Created by programowanie on 28.11.2015.
 */
@Repository
public class DataSetRepositoryImpl implements DataSetRepository{

        @Autowired
        private RedisTemplate<String, DataSet> template;

        @Override
        public DataSet getDataSet(String key) {

            return (DataSet) template.opsForHash().get(DataSet.OBJECT_KEY, key);
        }

        @Override
        public void saveDataSet(String key, DataSet dataSet) {
            template.opsForHash().put(DataSet.OBJECT_KEY, key, dataSet);
        }

        public void deleteDataSet(String key) {

            template.opsForHash().delete(DataSet.OBJECT_KEY, key);
        }


}
