package com.azura.lisa.controller;

import com.azura.lisa.dto.DemoExDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/api/")
@Api(value = "ExcelReport", description = "Excel Report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExcelReportController {


    @RequestMapping(value = "/lehoang/excel", method = RequestMethod.GET)
    public ResponseEntity<Void> exportvideoClick(HttpServletResponse response, @RequestParam("startDate") Long startDate, @RequestParam("endDate") Long endDate,@RequestParam(name = "hourStart", required = false) Long hourStart)  {
        try {
            generateExcelFile( response, startDate, endDate, hourStart);
        } catch (ParsePropertyException | InvalidFormatException | IOException e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    public void generateExcelFile(HttpServletResponse response, Long startDate,
                                  Long endDate, Long hourStart) throws IOException, ParsePropertyException, InvalidFormatException {
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("application//vnd.ms-excel");
        StringBuilder fileName = new StringBuilder("Course_manage_report_");
        String template = "course_manage_report_template.xlsx";
        Date today = Calendar.getInstance().getTime();
        String reportDate = "2018/15/10";
        fileName.append(reportDate);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");

        String startDateStr = "2018/15/10";
        String endDateStr = "2018/15/10";

        String hourStartStr;
        if (hourStart == null) {
            hourStartStr = "All";
        } else {
            hourStartStr = hourStart + "h";
        }

        Collection<Object> data = new LinkedList<>();
        DemoExDTO demoExDTO ;


        for(int i=1;i<=10;i++){
            demoExDTO = new DemoExDTO();
            demoExDTO.setClassId("15" + i);
            demoExDTO.setDateTime("20/18/2018");
            demoExDTO.setTimeRange("10");
            demoExDTO.setTeacherName("anh la ai");
            demoExDTO.setSubject("bai hoc vi du");
            demoExDTO.setObjective("thu xem sao");
            demoExDTO.setTypeClass("LS");
            demoExDTO.setThumbnail("gjikj.jpg");
            demoExDTO.setMaterial("dfkgjkf.pdf");
            demoExDTO.setMaterialMp4("jkgfdjg.mp4");
            data.add(demoExDTO);
        }

        Map<String, Object> beans = new HashMap<>();
        beans.put("startDateStr", startDateStr);
        beans.put("endDateStr", endDateStr);
        beans.put("hourStartStr", hourStartStr);
        beans.put("data", data);
        XLSTransformer transformer = new XLSTransformer();
        ClassPathResource resource = new ClassPathResource("report/" + template);
        try {
            Workbook workbook = transformer.transformXLS(resource.getInputStream(), beans);
            workbook.write(os);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
        os.flush();
    }

}
