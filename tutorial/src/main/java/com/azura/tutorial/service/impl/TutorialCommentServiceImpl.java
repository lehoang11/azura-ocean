package com.azura.tutorial.service.impl;

import com.azura.tutorial.repository.TutorialCommentRepository;
import com.azura.tutorial.service.TutorialCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorialCommentServiceImpl implements TutorialCommentService {

    @Autowired
    TutorialCommentRepository tutorialCommentRepository;
}
