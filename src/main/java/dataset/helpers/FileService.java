package dataset.helpers;

import com.sun.deploy.net.HttpResponse;
import dataset.domain.DataSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by programowanie on 28.11.2015.
 */
public interface FileService {

    String saveToDisk(MultipartFile file) throws IOException;
    void prepareResponse(DataSet dataSet, HttpServletResponse response) throws IOException;

}
