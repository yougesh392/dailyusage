package com.usmobile.dailyusage.model;

import com.usmobile.dailyusage.dao.DailyUsageDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DailyUsageResponse {
    List<DailyUsageDAO.UsageHistory> usageHistory;
}
