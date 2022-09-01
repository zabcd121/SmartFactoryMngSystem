package com.mirae.smartfactory.dto.statistics;

import com.mirae.smartfactory.domain.statistics.Statistics;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DailyStatisticsListDto {
    StatisticsListDto day;
}
