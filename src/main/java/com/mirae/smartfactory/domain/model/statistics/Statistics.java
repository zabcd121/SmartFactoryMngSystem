package com.mirae.smartfactory.domain.model.statistics;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Statistics {
    private List<Map<String, Object>> avgData = new ArrayList<>();
    private List<Map<String, Object>> deviationData = new ArrayList<>();

    public void addDailyDatum(LocalDate date, Double value, Double avgFor7Days){

        if(avgFor7Days == null) {
            avgFor7Days = 0.0D;
        }else if(value == null) {
            value = 0.0D;
        }

        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        String targetDate = month + "/" + dayOfMonth;

        addAvgDatum(targetDate, value);
        addDeviationDatum(targetDate, value, avgFor7Days);
    }

    public void addMonthlyDatum(YearMonth yearMonth, Double value, Double avgFor12Months){

        if(avgFor12Months == null) {
            avgFor12Months = 0.0D;
        }else if(value == null) {
            value = 0.0D;
        }

        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        String targetDate = year + "." + month;

        addAvgDatum(targetDate, value);
        addDeviationDatum(targetDate, value, avgFor12Months);
    }

    public void addQuarterlyDatum(LocalDate yearMonth, Double value, Double avgFor4Quarters){

        if(avgFor4Quarters == null) {
            avgFor4Quarters = 0.0D;
        }else if(value == null) {
            value = 0.0D;
        }

        int year = yearMonth.getYear();
        int quarter = (yearMonth.getMonthValue() / 3) + 1;
        String targetDate = year + "." + quarter + "Q";

        addAvgDatum(targetDate, value);
        addDeviationDatum(targetDate, value, avgFor4Quarters);
    }

    public void addYearlyDatum(YearMonth yearMonth, Double value, Double avgFor4Years){

        if(avgFor4Years == null) {
            avgFor4Years = 0.0D;
        }else if(value == null) {
            value = 0.0D;
        }

        int year = yearMonth.getYear();
        String targetDate = year + "";

        addAvgDatum(targetDate, value);
        addDeviationDatum(targetDate, value, avgFor4Years);
    }

    private void addAvgDatum(String targetDate, Double value) {
        Map<String, Object> map = new HashMap<>();
        map.put("x", targetDate);
        map.put("y", value);

        avgData.add(map);
    }

    private void addDeviationDatum(String targetDate, double value, double totalAvgValueForPeriod){
        Map<String, Object> map = new HashMap<>();
        map.put("x", targetDate);

        Double deviationValue = 0D;

        deviationValue = value - totalAvgValueForPeriod;
        map.put("y", deviationValue);


        deviationData.add(map);
    }


    public static Statistics createStatistics(){
        return new Statistics();
    }


}
