package com.mirae.smartfactory.service.statistics;

import com.mirae.smartfactory.domain.statistics.Statistics;
import com.mirae.smartfactory.domain.statistics.StatisticsType;
import com.mirae.smartfactory.dto.statistics.DailyStatisticsListDto;
import com.mirae.smartfactory.dto.statistics.StatisticsListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TotalStatisticsService {

    private final StatisticsService statisticsService;

    public DailyStatisticsListDto findTotalDomainDailyStatistics() {
        Statistics ashes = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(StatisticsType.ASHES);
        Statistics billet = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(StatisticsType.BILLET);
        Statistics defective = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(StatisticsType.DEFECTIVE);
        Statistics material = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(StatisticsType.MATERIAL);


        StatisticsListDto statisticsListDto = new StatisticsListDto(ashes, billet, defective, material);

        return new DailyStatisticsListDto(statisticsListDto);
    }
}
