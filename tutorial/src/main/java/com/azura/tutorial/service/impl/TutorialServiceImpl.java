package com.azura.tutorial.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.CommonUtils;
import com.azura.common.utils.TutorialUtils;
import com.azura.tutorial.Request.TutorialRequest;
import com.azura.tutorial.dto.TutorialDTO;
import com.azura.tutorial.model.Material;
import com.azura.tutorial.model.Tutorial;
import com.azura.tutorial.repository.MaterialRepository;
import com.azura.tutorial.repository.TutorialRepository;
import com.azura.tutorial.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Override
    public TutorialRequest saveTutorial(TutorialRequest dataForm) throws BusinessException {
        Tutorial tutorialModel = dataForm.getTutorial();
        Material materialModel = dataForm.getMaterial();
        tutorialModel.setShortName(CommonUtils.covertStringToURL(tutorialModel.getName()));
        //tutorialModel.setTutorialCode(TutorialUtils.generalCode(tutorialModel.getEduId()));
        tutorialModel.setViewTotal(0l);
        tutorialModel.setLikeTotal(0l);
        Tutorial tutorialNew = tutorialRepository.save(tutorialModel);
        if (tutorialNew == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "Created fail !");
        TutorialRequest tutorialData = new  TutorialRequest();
        tutorialData.setTutorial(tutorialNew);
        if (materialModel != null) {
            materialModel.setTutorialId(tutorialNew.getId());
            Material materialNew = materialRepository.save(materialModel);
            tutorialData.setMaterial(materialNew);
        }

        return tutorialData;
    }

    @Override
    public Tutorial  updateTutorial(Tutorial tutorialForm) throws BusinessException {
        Tutorial tutorialModel = tutorialRepository.getOne(tutorialForm.getId());
        if (tutorialModel == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "edu not found !");
        tutorialModel.setName(tutorialForm.getName());
        tutorialModel.setShortName(CommonUtils.covertStringToURL(tutorialForm.getName()));
        tutorialModel.setKeyword(tutorialForm.getKeyword());
        return tutorialRepository.save(tutorialModel);
    }

    @Override
    public Page<TutorialDTO> filterTutorial(){
        return tutorialRepository.filterTutorial(new PageRequest(0,10));
    }

    @Override
    public TutorialDTO getTutorialById(Long id){
        return tutorialRepository.getTutorialById(id);
    }

    @Override
    public Page<TutorialDTO>  filterTutorialByEduId(Long eduId){
        return tutorialRepository.filterTutorialByEduId(eduId, new PageRequest(0,10));
    }

    @Override
    public Page<TutorialDTO>  filterTutorialByEduShortName(String eduShortName){
        return tutorialRepository.filterTutorialByEduShortName(eduShortName, new PageRequest(0,10));
    }

    @Override
    public Page<TutorialDTO> searchTutorial(String q, Pageable pageRequest){
        return tutorialRepository.searchTutorial(q, pageRequest);
    }

    @Override
    public Tutorial findById(Long id) {
        return tutorialRepository.findById(id);
    }

    @Override
    public void updateView(Long id) {
        Tutorial tutorial = tutorialRepository.findById(id);
        tutorial.setViewTotal(tutorial.getViewTotal() + 1);
        tutorialRepository.save(tutorial);
    }
}
