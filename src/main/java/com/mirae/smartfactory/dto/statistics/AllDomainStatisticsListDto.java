package com.mirae.smartfactory.dto.statistics;

import com.mirae.smartfactory.domain.statistics.Statistics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllDomainStatisticsListDto {
    private Statistics ashes;
    private Statistics billet;
    private Statistics defective;
    private Statistics material;

}
