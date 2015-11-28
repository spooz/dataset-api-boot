package dataset.domain;


import dataset.repository.DataSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by programowanie on 24.10.2015.
 */
@Service
public class IDataSetService implements DataSetService {

    @Autowired
    private DataSetRepository repository;
    @Autowired
    private RedisAtomicLong redisAtomicLong;

    @Value("{dataset.filepath}")
    private String filepath;

    @Override
    public String buildKey(long id) {
        return "dataset:" + id;
    }

    @Override
    public DataSet getDataSet(long id) throws IllegalArgumentException {
        DataSet dataSet = repository.getDataSet(buildKey(id));
        if (dataSet == null) {
            throw new IllegalArgumentException();
        }
        return dataSet;
    }

    @Override
    public Long saveDataSet(MultipartFile file) throws IOException {
        String path = saveFileToDisk(file);
        DataSet dataSet = new DataSet();
        long id = redisAtomicLong.incrementAndGet();
        dataSet.setId(id);
        dataSet.setName(file.getOriginalFilename());
        dataSet.setPath(path);
        dataSet.setSize(file.getSize());
        dataSet.setContentType(file.getContentType());
        repository.saveDataSet(buildKey(id),dataSet);
        return dataSet.getId();
    }

    @Override
    public void deleteDataSet(long id) {
        repository.deleteDataSet(buildKey(id));
    }

    private String saveFileToDisk(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String path = filepath + file.getOriginalFilename();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
        stream.write(bytes);
        stream.close();
        return path;
    }
}