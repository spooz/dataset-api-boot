package dataset.helpers;

import dataset.domain.DataSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by programowanie on 28.11.2015.
 */
@Service
public class DefaultFileService implements FileService {


   @Value("{dataset.filepath}")
    private String filepath;

    @Override
    public String saveToDisk(MultipartFile file)  throws IOException{
        byte[] bytes = file.getBytes();
        String path = filepath + file.getOriginalFilename();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
        stream.write(bytes);
        stream.close();
        return path;
    }

    @Override
    public void prepareResponse(DataSet dataSet, HttpServletResponse response) throws IOException {
        File file = new File(dataSet.getPath());
        response.setContentType(dataSet.getContentType());
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
