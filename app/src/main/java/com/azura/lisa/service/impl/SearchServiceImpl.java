package com.azura.lisa.service.impl;

import com.azura.lisa.dto.SearchDTO;
import com.azura.lisa.dto.edu.EduDTO;
import com.azura.lisa.model.edu.Edu;
import com.azura.lisa.service.EduService;
import com.azura.lisa.service.SearchService;
import com.azura.tutorial.dto.TutorialDTO;
import com.azura.tutorial.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    EduService eduService;

    @Autowired
    TutorialService tutorialService;

    @Override
    public SearchDTO filter(String q, Pageable pageRequest) {
        Page<EduDTO> eduDTO = eduService.searchEdu(q);
        List<EduDTO> eduListDTO = eduDTO.getContent();
        Page<TutorialDTO> tutorialDTO = tutorialService.searchTutorial(q, pageRequest);
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setEduDTO(eduListDTO);
        searchDTO.setTutorialDTO(tutorialDTO);
        return searchDTO;
    }
}
