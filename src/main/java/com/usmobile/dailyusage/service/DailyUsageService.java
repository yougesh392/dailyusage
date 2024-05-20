package com.usmobile.dailyusage.service;

import com.usmobile.dailyusage.entity.DailyUsage;
import com.usmobile.dailyusage.model.DailyUsageRequest;
import com.usmobile.dailyusage.model.DailyUsageResponse;
import com.usmobile.dailyusage.repository.DailyUsageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DailyUsageService {
    private final DailyUsageRepository dailyUsageRepository;

    public DailyUsageService(DailyUsageRepository dailyUsageRepository) {
        this.dailyUsageRepository = dailyUsageRepository;
    }

    public void createUsageHistory(DailyUsageRequest dailyUsageRequest) {
        DailyUsage dailyUsage = new DailyUsage();
        dailyUsage.setUserId(dailyUsageRequest.getUserId());
        dailyUsage.setMdn(dailyUsageRequest.getPhoneNumber());
        dailyUsage.setCycleId(dailyUsageRequest.getCycleId());

        DailyUsage.UsageHistory usageHistory = dailyUsage.new UsageHistory();
        usageHistory.setUsageDate(new Date()); // set this based on your requirements
        usageHistory.setUsedInMb(0); // set this based on your requirements

        dailyUsage.setUsageHistory(Collections.singletonList(usageHistory));
        dailyUsageRepository.save(dailyUsage);
    }

    public DailyUsageResponse getUsageHistoryForCurrentCycle(DailyUsageRequest dailyUsageRequest) {

        // get the DailyUsageDAO object from the repository based on userId and cycleId
        DailyUsage dailyUsage = dailyUsageRepository.findByUserIdAndCycleId(dailyUsageRequest.getUserId(),
                dailyUsageRequest.getCycleId());
        // create and return a DailyUsageResponse based on your requirements
        return new DailyUsageResponse(dailyUsage.getUsageHistory());
    }

    /*
     * usage collection is updated every 15 minutes for the usedInMb field and create a new Date next day
     *
     * */
    @Scheduled(fixedRate = 15 * 60 * 1000) // 15 minutes in milliseconds
    public void updateUsageAndDate() {
        // get all DailyUsageDAO objects from the repository
        List<DailyUsage> dailyUsages = dailyUsageRepository.findAll();

        for (DailyUsage dailyUsage : dailyUsages) {
            // get today's date
            Date today = new Date();

            // find the usage history for today
            DailyUsage.UsageHistory usageHistoryToday = dailyUsage.getUsageHistory().stream()
                    .filter(usageHistory -> isSameDay(usageHistory.getUsageDate(), today))
                    .findFirst()
                    .orElse(null);

            if (usageHistoryToday != null) {
                // if usage history for today exists, add 15 to usedInMb
                usageHistoryToday.setUsedInMb(usageHistoryToday.getUsedInMb().intValue() + 15);
            } else {
                // if usage history for today doesn't exist, create a new one with usedInMb set to 0
                usageHistoryToday = dailyUsage.new UsageHistory();
                usageHistoryToday.setUsageDate(today);
                usageHistoryToday.setUsedInMb(0);

                dailyUsage.getUsageHistory().add(usageHistoryToday);
            }

            dailyUsageRepository.save(dailyUsage);
        }
    }

    private boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }
}
