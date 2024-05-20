package com.usmobile.dailyusage.model;

import com.usmobile.dailyusage.entity.DailyUsage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DailyUsageResponse {
    List<DailyUsage.UsageHistory> usageHistory;
}
