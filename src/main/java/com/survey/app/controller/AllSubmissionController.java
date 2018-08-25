package com.survey.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suervey.app.util.AppConstants;
import com.survey.app.payload.PagedResponse;
import com.survey.app.payload.PollResponse;
import com.survey.app.repository.UserRepository;
import com.survey.app.security.CurrentUser;
import com.survey.app.security.UserPrincipal;
import com.survey.app.service.PollService;


@RestController
@RequestMapping("/api/allSubmission")
public class AllSubmissionController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollService pollService;
    
    @Autowired
    JobLauncher simpleJobLauncher;
 
    @Autowired
    Job processJob;

    private static final Logger logger = LoggerFactory.getLogger(AllSubmissionController.class);

    @GetMapping
    public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getAllPolls(currentUser, page, size);
    }

 
    @PostMapping("invokejob")
    public ResponseEntity<Void> handle() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            simpleJobLauncher.run(processJob, jobParameters);
 
            return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    
    
    

}
