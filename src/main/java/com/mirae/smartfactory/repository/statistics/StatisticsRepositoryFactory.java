package com.mirae.smartfactory.repository.statistics;

import com.mirae.smartfactory.domain.statistics.StatisticsType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatisticsRepositoryFactory {

    private final Map<StatisticsType, StatisticsRepository> statisticsRepositories = new HashMap<>();

    public StatisticsRepositoryFactory(List<StatisticsRepository> statisticsRepositoryList) {
        if(CollectionUtils.isEmpty(statisticsRepositoryList)) {
            throw new IllegalArgumentException("통계를 처리할 Repository가 없습니다.");
        }

        for (StatisticsRepository statisticsRepository : statisticsRepositoryList) {
            this.statisticsRepositories.put(statisticsRepository.getType(), statisticsRepository);
        }
    }

    public StatisticsRepository getRepository(StatisticsType statisticsType) {
        return statisticsRepositories.get(statisticsType);
    }
}
