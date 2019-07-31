package com.azura.tutorial.service.impl;

import com.azura.common.utils.CommonUtils;
import com.azura.tutorial.model.TuList;
import com.azura.tutorial.repository.TuListRepository;
import com.azura.tutorial.service.TuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TuListServiceImpl implements TuListService {

    @Autowired
    TuListRepository tuListRepository;

    public TuList saveTuList(TuList tuListForm){

        if (tuListForm.getId() != null){
            TuList tuListModel = tuListRepository.findById(tuListForm.getId());
            tuListModel.setName(tuListForm.getName());

            return tuListRepository.save(tuListModel);
        }

        String code = "";
        while (true){
            code = CommonUtils.getTuListCode();
            if (!tuListRepository.existsByCode(code)){
                break;
            }
        }

        tuListForm.setCode(code);
        return tuListRepository.save(tuListForm);
    }



}
