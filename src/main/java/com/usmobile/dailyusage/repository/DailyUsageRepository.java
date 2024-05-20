package com.usmobile.dailyusage.repository;

import com.usmobile.dailyusage.entity.DailyUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyUsageRepository extends MongoRepository<DailyUsage, String> {
    DailyUsage findByUserIdAndCycleId(String userId, String cycleId);
}
