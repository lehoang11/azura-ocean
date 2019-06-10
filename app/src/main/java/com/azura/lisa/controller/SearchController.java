package com.azura.lisa.controller;

import com.azura.common.common.ApiDataResponse;
import com.azura.common.utils.Messages;
import com.azura.lisa.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
@Api(value = "search", description = "search Information", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    @Autowired
    Messages messages;

    @Autowired
    SearchService searchService;

    /* API show Tutorial */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = " API search ", response = ApiDataResponse.class)
    public  ApiDataResponse search(@RequestParam("q") String q ,@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize)  {
        log.info("Begin get info search: ");
        log.info("q: " + String.valueOf(q));
        log.info("currentPage: " + String.valueOf(currentPage));
        log.info("pageSize: " + String.valueOf(pageSize));
        if (currentPage == null){
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Pageable pageRequest = new PageRequest(currentPage -1 , pageSize);
        return new ApiDataResponse(searchService.filter(q, pageRequest), HttpStatus.OK.value(),
                messages.get("response.successful"));
    }
}
