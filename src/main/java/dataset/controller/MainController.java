package dataset.controller;

import dataset.auth.CurrentUser;
import dataset.domain.DataSet;
import dataset.domain.DataSetService;
import dataset.auth.RemoteAuthService;
import dataset.helpers.FileService;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/data")
@PreAuthorize("@defaultRemoteAuthService.isAuthorized(#token)")
public class MainController {

    // TODO : accepted media types

    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/{ID}", method = RequestMethod.OPTIONS, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public DataSet getDataSetDetail(@PathVariable(value = "ID") final long id, @NotEmpty @RequestHeader(value ="token") String token) throws Exception{
        return dataSetService.getDataSet(id);
    }


    @RequestMapping(value = "/{ID}", method = RequestMethod.PUT, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void editDataSet(@PathVariable(value = "ID") final long id, @Valid @RequestBody DataSet dataSet,@NotEmpty @RequestHeader(value ="token") String token) throws Exception {
        dataSetService.updateDataSet(id, dataSet);

    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void downloadDataSet(HttpServletResponse response, @PathVariable(value = "ID") final long id, @NotEmpty @RequestHeader(value ="token") String token) throws Exception {
        DataSet dataSet = dataSetService.getDataSet(id);
       fileService.prepareResponse(dataSet, response);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDataSet(@PathVariable(value = "ID") final long id, @NotEmpty @RequestHeader(value ="token") String token) throws Exception{
        dataSetService.deleteDataSet(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"multipart/form-data"}, produces = {"application/json"})
    public String saveDataSet(@RequestParam(value = "file", required = true) MultipartFile file, @NotEmpty @RequestHeader(value ="token") String token) throws Exception {
        Long dataSetId = dataSetService.saveDataSet(file);
        JSONObject response = new JSONObject().append("id", dataSetId);
        return response.toString();
    }

    @RequestMapping(value = "/{ID}/share/{user}", method = RequestMethod.POST, consumes = {"application/json"})
    public void grantAuthorities(@PathVariable(value = "ID") final long dataSetId, @PathVariable(value = "user") final long user, @NotEmpty @RequestHeader(value = "token") String token) throws Exception {
        dataSetService.grantAuthorities(dataSetId, user);
    }


}
