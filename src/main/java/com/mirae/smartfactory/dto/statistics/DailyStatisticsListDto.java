package com.mirae.smartfactory.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DailyStatisticsListDto {
    AllDomainStatisticsListDto day;
}
