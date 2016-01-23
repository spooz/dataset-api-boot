package dataset.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import java.io.IOException;

/**
 * Created by programowanie on 24.10.2015.
 */
public interface DataSetService {

    String buildKey(long id);

    DataSet getDataSet(long id) throws NotFoundException, NotAuthorizedException;

    Long saveDataSet(MultipartFile file) throws IOException;

    void deleteDataSet(long id) throws NotFoundException, NotAuthorizedException;

    void updateDataSet(long id, DataSet dataSet) throws NotFoundException, NotAuthorizedException;

    void grantAuthorities(long dataSetId, long userId) throws NotFoundException, NotAuthorizedException;

}
