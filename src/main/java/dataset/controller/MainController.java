package dataset.controller;

import dataset.domain.DataSet;
import dataset.domain.DataSetService;
import dataset.helpers.FileService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/data")
public class MainController {

    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/{ID}", method = RequestMethod.OPTIONS, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    DataSet getDataSetDetail(@PathVariable(value = "ID") final long id) {

        return dataSetService.getDataSet(id);
    }


    @RequestMapping(value = "/{ID}", method = RequestMethod.PUT, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void editDataSet(@PathVariable(value = "ID") final long id, @RequestBody DataSet dataSet) {
        dataSetService.updateDataSet(id, dataSet);

    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void downloadDataSet(HttpServletResponse response, @PathVariable(value = "ID") final long id) throws IOException {
        DataSet dataSet = dataSetService.getDataSet(id);
       fileService.prepareResponse(dataSet, response);
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDataSet(@PathVariable(value = "ID") final long id) {

        dataSetService.deleteDataSet(id);
    }

    //mutlipart file upload
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"multipart/form-data"}, produces = {"application/json"})
    public
    @ResponseBody
    String saveDataSet(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        Long dataSetId = dataSetService.saveDataSet(file);
        JSONObject response = new JSONObject().append("id", dataSetId);
        return response.toString();
    }


}
