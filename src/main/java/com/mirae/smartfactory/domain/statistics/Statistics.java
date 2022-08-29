package com.mirae.smartfactory.domain.statistics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * 달, 년, 분기마다 SUM으로 총량을 받는 SQL문 만들기
 */
public class Statistics {
    private List<Map<String, Object>> avgData = new ArrayList<>();
    private List<Map<String, Object>> deviationData = new ArrayList<>();
    private String key;
    private String indexBy;
    private String color;
    private String bottomLegend;
    private String leftLegend;

    private Statistics(String key, String indexBy, String color, String bottomLegend, String leftLegend) {
        this.key = key;
        this.indexBy = indexBy;
        this.color = color;
        this.bottomLegend = bottomLegend;
        this.leftLegend = leftLegend;
    }



    public void addDailyDatum(LocalDate date, Integer value, Integer totalValueForPeriod){
        Map<String, Object> map = new HashMap<>();

        int monthValue = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        String targetDate = monthValue + "/" + dayOfMonth;

        addAvgDatum(targetDate, value);
        addDeviationDatum(targetDate, value, totalValueForPeriod, 7);
    }

    private void addAvgDatum(String targetDate, Integer value) {
        Map<String, Object> map = new HashMap<>();
        map.put("x", targetDate);
        map.put("y", value);

        avgData.add(map);
    }

    private void addDeviationDatum(String targetDate, Integer value, Integer totalValueForPeriod, int periodCnt){
        Map<String, Object> map = new HashMap<>();
        Integer deviationValue = 0;
        map.put("x", targetDate);

        deviationValue = value - (totalValueForPeriod / periodCnt);
        map.put("y", deviationValue);

        deviationData.add(map);
    }

//    public Integer getTotalAvgValue() {
//        Integer totalAmount = 0;
//        for (Map<String, Object> avgDatum : avgData) {
//            totalAmount += (int)avgDatum.get("y");
//        }
//
//        return totalAmount;
//    }

//    public void addDailyAvgData(LocalDate date, Float value);
//    public void addMonthlyAvgData(String month, Float value);
//    public void addQuarterAvgData(String quarter, Float value);
//    public void addYearlyAvgData(String year,Float value);
//
//    public void addDailyDeviationData(LocalDate date, Float value);
//    public void addMonthlyDeviationData(String month, Float value);
//    public void addQuarterDeviationData(String quarter, Float value);
//    public void addYearlyDeviationData(String year,Float value);

    public static Statistics createDailyAshesStatistics(){
        return new Statistics("ashes", "일", "#F39B0B", "day", "재발생량");
    }

    public static Statistics createMonthlyAshesStatistics(){
        return new Statistics("ashes", "월", "#F39B0B", "month", "재발생량");
    }

    public static Statistics createQuarterAshesStatistics(){
        return new Statistics("ashes", "분기", "#F39B0B", "quarter", "재발생량");
    }

    public static Statistics createYearlyAshesStatistics(){
        return new Statistics("ashes", "년", "#F39B0B", "year", "재발생량");
    }


}
