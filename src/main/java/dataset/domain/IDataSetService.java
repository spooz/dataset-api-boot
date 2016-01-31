package dataset.domain;


import dataset.auth.CurrentUser;
import dataset.helpers.DataSetNotFoundException;
import dataset.helpers.DataSetUnauthorizedAccessException;
import dataset.helpers.FileService;
import dataset.repository.DataSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by programowanie on 24.10.2015.
 */
@Service
public class IDataSetService implements DataSetService {

    @Autowired
    private DataSetRepository repository;
    @Autowired
    private RedisAtomicLong redisAtomicLong;
    @Autowired
    private FileService fileService;
    @Autowired
    private CurrentUser currentUser;

    @Override
    public String buildKey(long id) {
        return "dataset:" + id;
    }

    @Override
    public DataSet getDataSet(long id) throws DataSetNotFoundException, DataSetUnauthorizedAccessException{
        DataSet dataSet = repository.getDataSet(buildKey(id));

        if (dataSet == null)
            throw new DataSetNotFoundException();

        if(dataSet.getAuthorId() != currentUser.getId() && !dataSet.getAllowedUsers().contains(currentUser.getId()))
            throw new DataSetUnauthorizedAccessException();

        return dataSet;
    }

    @Override
    public Long saveDataSet(MultipartFile file) throws IOException {
        String path = fileService.saveToDisk(file);
        DataSet dataSet = new DataSet();
        long id = redisAtomicLong.incrementAndGet();
        dataSet.setId(id);
        dataSet.setName(file.getOriginalFilename());
        dataSet.setPath(path);
        dataSet.setSize(file.getSize());
        dataSet.setContentType(file.getContentType());
        dataSet.setAuthorId(currentUser.getId());
        dataSet.setCreated(LocalDate.now().toString() + " " + LocalTime.now().toString());
        repository.saveDataSet(buildKey(id),dataSet);
        return dataSet.getId();
    }

    @Override
    public void updateDataSet(long id, DataSet uDataSet) throws DataSetNotFoundException, DataSetUnauthorizedAccessException {
        DataSet dataSet = repository.getDataSet(buildKey(id));

        if(dataSet == null)
            throw new DataSetNotFoundException();

        if(dataSet.getAuthorId() != currentUser.getId())
            throw new DataSetUnauthorizedAccessException();

        dataSet.setName(uDataSet.getName());
        repository.saveDataSet(buildKey(id), dataSet);

    }

    @Override
    public void deleteDataSet(long id) throws DataSetNotFoundException, DataSetUnauthorizedAccessException{
        DataSet dataSet = repository.getDataSet(buildKey(id));

        if(dataSet == null) {
            throw new DataSetNotFoundException();
        }

        if(dataSet.getAuthorId() != currentUser.getId())
            throw new DataSetUnauthorizedAccessException();

        repository.deleteDataSet(buildKey(dataSet.getId()));
    }

    @Override
    public void grantAuthorities(long dataSetId, long userId) throws DataSetNotFoundException, DataSetUnauthorizedAccessException {
        DataSet dataSet = repository.getDataSet(buildKey(dataSetId));
        if(dataSet == null)
            throw new DataSetNotFoundException();

        if(dataSet.getAuthorId() != currentUser.getId())
            throw new DataSetUnauthorizedAccessException();

        dataSet.getAllowedUsers().add(userId);

        repository.saveDataSet(buildKey(dataSet.getId()), dataSet);


    }

}