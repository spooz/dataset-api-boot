package dataset.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by programowanie on 24.10.2015.
 */
public interface DataSetService {

    String buildKey(long id);

    DataSet getDataSet(long id) throws IllegalArgumentException;

    Long saveDataSet(MultipartFile file) throws IOException;

    void deleteDataSet(long id);

}
