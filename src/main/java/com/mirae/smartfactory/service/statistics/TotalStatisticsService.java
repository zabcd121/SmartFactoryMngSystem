package com.mirae.smartfactory.service.statistics;

import com.mirae.smartfactory.domain.statistics.Statistics;
import com.mirae.smartfactory.dto.statistics.DailyStatisticsListDto;
import com.mirae.smartfactory.dto.statistics.StatisticsListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatisticsService {

    private final AshesStatisticsService ashesStatisticsService;

    public DailyStatisticsListDto findTotalDomainDailyStatistics() {
        Statistics ashes = ashesStatisticsService.findAshesAmountForCurrent7Days();

        StatisticsListDto statisticsListDto = new StatisticsListDto(ashes, null, null, null);

        return new DailyStatisticsListDto(statisticsListDto);
    }
}
