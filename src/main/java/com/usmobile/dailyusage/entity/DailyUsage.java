package com.usmobile.dailyusage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Daily_Usage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyUsage {
    @Id
    private String id;
    private String mdn;
    private String userId;

    @Indexed(unique = true)
    private String cycleId;

    List<UsageHistory> usageHistory;
    @Getter
    @Setter
    public class UsageHistory {
        private Date usageDate;
        private Number usedInMb;
    }
}

