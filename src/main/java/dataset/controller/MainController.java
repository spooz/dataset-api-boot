package dataset.controller;

import dataset.domain.DataSet;
import dataset.domain.DataSetService;
import dataset.helpers.AuthService;
import dataset.helpers.FileService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/data")
@PreAuthorize("@defaultAuthService.isAuthorized(#token)")
public class MainController {

    // TODO : accepted media types

    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AuthService authService;

    @ModelAttribute
    public Long getUserId(@RequestHeader(value="token") String token) {
        return authService.getUserId(token);
    }


    @RequestMapping(value = "/{ID}", method = RequestMethod.OPTIONS, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public DataSet getDataSetDetail(@PathVariable(value = "ID") final long id, @RequestHeader(value ="token") String token, @ModelAttribute Long user) {
        System.out.println("?????? " + user + " ??????" );
        return dataSetService.getDataSet(id);
    }


    @RequestMapping(value = "/{ID}", method = RequestMethod.PUT, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void editDataSet(@PathVariable(value = "ID") final long id, @RequestBody DataSet dataSet, @RequestHeader(value ="token") String token, @ModelAttribute Long user) {
        dataSetService.updateDataSet(id, dataSet);

    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void downloadDataSet(HttpServletResponse response, @PathVariable(value = "ID") final long id, @RequestHeader(value ="token") String token, @ModelAttribute Long user) throws IOException {
        DataSet dataSet = dataSetService.getDataSet(id);
       fileService.prepareResponse(dataSet, response);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDataSet(@PathVariable(value = "ID") final long id, @RequestHeader(value ="token") String token, @ModelAttribute Long user) {
        dataSetService.deleteDataSet(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"multipart/form-data"}, produces = {"application/json"})
    public String saveDataSet(@RequestParam(value = "file", required = true) MultipartFile file, @RequestHeader(value ="token") String token, @ModelAttribute Long user) throws IOException {
        Long dataSetId = dataSetService.saveDataSet(file);
        JSONObject response = new JSONObject().append("id", dataSetId);
        return response.toString();
    }


}
