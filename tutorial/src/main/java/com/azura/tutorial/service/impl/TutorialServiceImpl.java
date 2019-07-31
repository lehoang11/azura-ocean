package com.azura.tutorial.service.impl;

import com.azura.common.exception.BusinessException;
import com.azura.common.exception.ExceptionCode;
import com.azura.common.utils.CommonUtils;
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

        if(tutorialModel.getId() != null){
            TutorialRequest tutorialData = new  TutorialRequest();

            Tutorial tutorial = tutorialRepository.findById(tutorialModel.getId());
            tutorial.setShortName(CommonUtils.covertStringToURL(tutorialModel.getName()));
            String mediaType = CommonUtils.getExtensionMedia(tutorialModel.getMedialUrl());
            tutorialModel.setVcrType(CommonUtils.getTypeMedia(mediaType));
            Tutorial tutorialUp = tutorialRepository.save(tutorial);

            if (tutorialUp == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "Update fail !");
            tutorialData.setTutorial(tutorialUp);

            Material material = materialRepository.findByTutorialId(tutorialModel.getId());

            if(material !=null){
                material.setContent(materialModel.getContent());
                Material MaterialUp = materialRepository.save(material);
                tutorialData.setMaterial(MaterialUp);
            }else {
                materialModel.setTutorialId(tutorial.getId());
                Material MaterialUp = materialRepository.save(materialModel);
                tutorialData.setMaterial(MaterialUp);
            }

            return tutorialData;
        }else {

            TutorialRequest tutorialData = new  TutorialRequest();
            tutorialModel.setShortName(CommonUtils.covertStringToURL(tutorialModel.getName()));
            tutorialModel.setViewTotal(0l);
            tutorialModel.setLikeTotal(0l);

            String mediaType = CommonUtils.getExtensionMedia(tutorialModel.getMedialUrl());
            tutorialModel.setVcrType(CommonUtils.getTypeMedia(mediaType));
            Tutorial tutorialNew = tutorialRepository.save(tutorialModel);
            if (tutorialNew == null) throw new BusinessException(ExceptionCode.Edu.EDU_NOT_FOUND, "Created fail !");

            materialModel.setTutorialId(tutorialNew.getId());
            Material materialNew = materialRepository.save(materialModel);

            tutorialData.setTutorial(tutorialNew);
            tutorialData.setMaterial(materialNew);
            return tutorialData;
        }

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
    public TutorialRequest getTutorialUpdateById(Long id){
        Tutorial tutorial = tutorialRepository.findById(id);
        Material material = materialRepository.findByTutorialId(tutorial.getId());
        TutorialRequest tutorialRequest = new TutorialRequest();
        tutorialRequest.setTutorial(tutorial);
        tutorialRequest.setMaterial(material);
        return tutorialRequest;
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
