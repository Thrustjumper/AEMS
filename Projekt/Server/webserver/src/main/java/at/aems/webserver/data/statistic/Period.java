/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.aems.webserver.data.statistic;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Niklas
 */
public enum Period {
    @SerializedName("0")
    DAILY(0, "Tag", new String[]{"0 - 6 Uhr", "6 - 12 Uhr", "12 - 18 Uhr", "18 - 24 Uhr"}), 
    //
    
    @SerializedName("1")
    WEEKLY(1, "Woche", new String[]{"Montag", "Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"}),  //
    
    @SerializedName("2")
    MONTHLY(2, "Monat", new String[]{"Woche 1", "Woche 2", "Woche 3", "Woche 4", "Woche 5"}), //
    
    @SerializedName("3")
    YEARLY(3, "Jahr", new String[]{"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", 
                     "August", "September", "Oktober", "November", "Dezember"}); //
   
    
    private int periodId;
    private String label;
    private String[] labels;
    
    private Period(int id, String label, String[]labels) {
        this.periodId = id;
        this.label = label;
        this.labels = labels;
    }
    
    public static Period byId(int id) {
        for(Period p : Period.values()) {
            if(p.getPeriodId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public int getPeriodId(){
        return this.periodId;
    }
    
    public String getLabel() {
        return label;
    }
    
    public String[] getLabels() {
        return labels;
    }
}
