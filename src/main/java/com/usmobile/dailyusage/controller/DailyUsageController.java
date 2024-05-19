package com.usmobile.dailyusage.controller;

import com.usmobile.dailyusage.model.DailyUsageRequest;
import com.usmobile.dailyusage.model.DailyUsageResponse;
import com.usmobile.dailyusage.service.DailyUsageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dailyusage")
public class DailyUsageController {
    DailyUsageService dailyUsageService;

    public DailyUsageController(DailyUsageService dailyUsageService) {
        this.dailyUsageService = dailyUsageService;
    }
    /*
    * Usage history for provided billing history
    * */
    @PostMapping
    public ResponseEntity createDailyUsage(@RequestBody DailyUsageRequest dailyUsageRequest) throws Exception {
        dailyUsageService.createUsageHistory(dailyUsageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/currentbillingcycle")
    public ResponseEntity<DailyUsageResponse> getDailyUsageForCurrentCycle(@RequestBody DailyUsageRequest dailyUsageRequest) throws Exception {
        DailyUsageResponse response = dailyUsageService.getUsageHistoryForCurrentCycle(dailyUsageRequest);
        return new ResponseEntity<DailyUsageResponse>(response, HttpStatus.OK);
    }
}
