package dataset.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by programowanie on 28.11.2015.
 */
public class DataSet implements Serializable {

    public static String OBJECT_KEY = "DataSet";

    private long id;

    @NotEmpty
    @Size(min = 1, max = 30)
    private String name;

    private String path;
    private long authorId;
    private long size;
    private String contentType;


    public DataSet() {

    }
    public DataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DataSet(long id, String name, String path, long authorId, long size) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.authorId = authorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "{ " + id + " " + name + " " + path + " }";
    }


}