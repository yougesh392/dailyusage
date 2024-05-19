package com.usmobile.dailyusage.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class DailyUsageRequest {
    private String userId;
    private String phoneNumber;
    private String cycleId;
}
