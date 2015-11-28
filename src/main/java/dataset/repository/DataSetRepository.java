package dataset.repository;

import dataset.domain.DataSet;

/**
 * Created by programowanie on 28.11.2015.
 */
public interface DataSetRepository {

    DataSet getDataSet(String key);

    void saveDataSet(String key, DataSet dataSet);

    void deleteDataSet(String key);
}