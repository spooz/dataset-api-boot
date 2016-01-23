package dataset.domain;

import dataset.helpers.DataSetNotFoundException;
import dataset.helpers.DataSetUnauthorizedAccessException;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

/**
 * Created by programowanie on 24.10.2015.
 */
public interface DataSetService {

    String buildKey(long id);

    DataSet getDataSet(long id) throws DataSetNotFoundException, DataSetUnauthorizedAccessException;

    Long saveDataSet(MultipartFile file) throws IOException;

    void deleteDataSet(long id) throws DataSetNotFoundException, DataSetUnauthorizedAccessException;

    void updateDataSet(long id, DataSet dataSet) throws DataSetNotFoundException, DataSetUnauthorizedAccessException;

    void grantAuthorities(long dataSetId, long userId) throws DataSetNotFoundException, DataSetUnauthorizedAccessException;

}
