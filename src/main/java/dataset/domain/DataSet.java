package dataset.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by programowanie on 28.11.2015.
 */
public class DataSet implements Serializable {

    public static String OBJECT_KEY = "DataSet";

    private long id;

    // TODO: time of creation

    @NotEmpty
    @Size(min = 1, max = 30)
    private String name;
    private String path;
    private Long authorId;
    private Long size;
    private String contentType;
    private List<Long> allowedUsers;
    private String created;

    public DataSet() {
        allowedUsers = new LinkedList<>();
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

    public void setAllowedUsers(List<Long> users) {
        this.allowedUsers = users;
    }
    public List<Long> getAllowedUsers() {
        return allowedUsers;
    }

    public void addAllowedUser(Long id) {
        this.allowedUsers.add(id);
    }

    public void setAuthorId(Long id) {
        this.authorId = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated(){
        return this.created;
    }


    @Override
    public String toString() {
        return "{ " + id + " " + name + " " + path + " }";
    }


}