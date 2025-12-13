// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};
    
    static int[][][] profitdata=new int[MONTHS][DAYS][COMMS];
    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        for(int i=0;i<MONTHS;i++){
            File file=new File("Data_Files/"+months[i]+".txt");
            try{
                Scanner sc=new Scanner(file);
                sc.nextLine(); //Day,commodity,profit SKİP
                while (sc.hasNextLine()){
                    String line=sc.nextLine();
                    String[] parts=line.split(",");
                    int day=Integer.parseInt(parts[0])-1;
                    String commodity=parts[1];
                    int profit=Integer.parseInt(parts[2]);

                    int commind=-1;
                    for(int a=0;a<COMMS;a++){
                        if(commodities[a].equals(commodity)){
                            commind=a;
                            break;
                        }
                    }
                    if(commind!=-1){  //Control. because commind can remain -1
                        profitdata[i][day][commind]=profit;
                    }
                }
                sc.close();
            }
            catch (FileNotFoundException e){
                System.out.println("file not found: "+months[i]+".txt");
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if(month<0 || month>11){
            return "INVALID_MONTH";
        }
        else {
            int[] commodity={0,0,0,0,0}; //gold,oil,silver,wheat,copper
            for(int i=0;i<COMMS;i++){
                for(int j=0;j<DAYS;j++){
                    commodity[i]+=profitdata[month][j][i];
                }
            }
            int max=commodity[0]; //profit can be negative
            int index=0;
            for(int a=1;a<commodity.length;a++){
                if(commodity[a]>max){
                    max=commodity[a];
                    index=a;
                }
            }
            return commodities[index]+" "+max;
        }
    }

    public static int totalProfitOnDay(int month, int day) {
        if(month<0 || month>11){
            return -99999;
        } else if (day<1 || day>DAYS) {
            return -99999;
        }
        else {
            int sum=0;
            for (int i=0;i<COMMS;i++){
                sum+=profitdata[month][day-1][i];
            }
            return sum;
        }
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        boolean right=true;
        int index_com=0;
        for (int i=0;i<commodities.length;i++){
            if(commodity.toLowerCase().equals(commodities[i].toLowerCase())){
                index_com=i;
                right=false;
                break;
            }
        }
        if(right){
            return -99999;
        } else if (from<1 || from>28) {
            return -99999;
        }
        else if (to<1 || to>28) {
            return -99999;
        } else if (from>to) {
            return -99999;
        }
        else {
            int sum=0;
            for(int i=0;i<MONTHS;i++){
                for(int a=from-1;a<=to-1;a++){
                    sum+=profitdata[i][a][index_com];
                }
            }
            return sum;
        }

    }

    public static int bestDayOfMonth(int month) { 
        if(month<0 || month>=MONTHS){
            return -1;
        }
        int max=0;
        for(int a=0;a<commodities.length;a++){
            max+=profitdata[month][0][a];
        }
        int day=1;
        for(int i=1;i<DAYS;i++){
            int sum=0;
            for(int a=0;a<commodities.length;a++){
                sum+=profitdata[month][i][a];
            }
            if(sum>max){
                max=sum;
                day=i+1;
            }
        }
        return day;
    }
    
    public static String bestMonthForCommodity(String comm) { 
        boolean right=true;
        int index=0;
        for (int i=0;i<commodities.length;i++){
            if (commodities[i].toLowerCase().equals(comm.toLowerCase())){
                right=false;
                index=i;
                break;
            }
        }
        if(right){
            return "INVALID_COMMODITY";
        }
        else {
            int max=0;
            int index_month=0;
            for (int i=0;i<DAYS;i++){
                max+=profitdata[0][i][index];
            }
            for(int i=1;i<MONTHS;i++){
                int sum=0;
                for (int a=0;a<DAYS;a++){
                    sum+=profitdata[i][a][index];
                }
                if(sum>max){
                    max=sum;
                    index_month=i;
                }
            }
            return months[index_month];
        }
    }

    public static int consecutiveLossDays(String comm) { 
        return 1234; 
    }
    
    public static int daysAboveThreshold(String comm, int threshold) { 
        return 1234; 
    }

    public static int biggestDailySwing(int month) { 
        return 1234; 
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
        return "DUMMY is better by 1234"; 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}