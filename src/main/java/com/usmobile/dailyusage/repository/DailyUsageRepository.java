package com.usmobile.dailyusage.repository;

import com.usmobile.dailyusage.dao.DailyUsageDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyUsageRepository extends MongoRepository<DailyUsageDAO, String> {
    DailyUsageDAO findByUserIdAndCycleId(String userId, String cycleId);
}
