package org.harender.utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelFileHandling {

    static List<String[]> matchingRecords = new ArrayList<>();
    static List<String[]> unMatchingRecords = new ArrayList<>();
    static List<Integer> matchingRowsIndex = new ArrayList<Integer>();

    public static void main(String[] args) {
        try {
            String file1Path = "H:\\FileWhichIsToCheck.xlsx";
            String file2Path = "H:\\File2WithWhichIsToCheck.xlsx";
            String file3Path = "H:\\File1WithWhichIsToCheck.xlsx";

            List<String> filesPathwithWhomToCompare=new ArrayList<String>();
            filesPathwithWhomToCompare.add(file2Path);
            filesPathwithWhomToCompare.add(file3Path);

            CompareExcelSheets(file1Path,filesPathwithWhomToCompare);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void CompareExcelSheets(String filePathWhoseDataToCompare,List<String> filesPathwithWhomToCompare) {

        try {

            String file1Path=filePathWhoseDataToCompare;
            int fileCounter=1;

            for(String fileWhereToComparePath:filesPathwithWhomToCompare) {

                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println(fileCounter+" - Comparing file ||"+getFileNameAndSheetName(file1Path)+"|| with file \n||"+getFileNameAndSheetName(fileWhereToComparePath)+ "||");

                List<String[]> fileOfWhichToCompareDataIncludingHeaders = readExcelFile(file1Path);
                List<String[]> fileInWhichToCompareDataIncludingHeaders = readExcelFile(fileWhereToComparePath);

                String [] fromWhichHeaders=fileOfWhichToCompareDataIncludingHeaders.get(0);
                String [] toWhichHeaders=fileInWhichToCompareDataIncludingHeaders.get(0);

                fileOfWhichToCompareDataIncludingHeaders.remove(0);
                fileInWhichToCompareDataIncludingHeaders.remove(0);
                List<String[]> file1WithOutHeaders =fileOfWhichToCompareDataIncludingHeaders;
                List<String[]> file2WithOutHeaders =fileInWhichToCompareDataIncludingHeaders;

                fileCounter++;

                if (headersMatching(fromWhichHeaders, toWhichHeaders)) {
                    System.out.println("Headers of " + getFileNameAndSheetName(file1Path) + " and \n" + getFileNameAndSheetName(fileWhereToComparePath) + " are Matching Successfully !");
                    matchingRecords.add(fromWhichHeaders);
                }
                else {
                    System.out.println("Headers are Mismatched - Please Verify !");
                    stringArrayPrinter(getFileNameAndSheetName(file1Path)+" Headers ",fromWhichHeaders);
                    stringArrayPrinter(getFileNameAndSheetName(fileWhereToComparePath)+" Headers - ",toWhichHeaders);
                    continue;
                }

                System.out.println("\nNo. of records in "+getFileNameAndSheetName(file1Path)+" (input data) - " + file1WithOutHeaders.size()
                        + "\nNo. of records in "+getFileNameAndSheetName(fileWhereToComparePath)+" (input data) - " + file2WithOutHeaders.size()+"\n");

                //matching check of current two files data
                matchingRowsIndex= findMatchingAndUnmatchingRecords(file1WithOutHeaders, file2WithOutHeaders);

                System.out.println("Matching Records - "+(matchingRecords.size()-1) +
                                 "\nUnmatching Records - "+(unMatchingRecords.size()) +"\n");

                matchingRecords.clear();
                unMatchingRecords.clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // This Writing Inside a file is pending , If required we can do it
    private static void writeArrayListToFile(List<String> dataList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String data : dataList) {
                writer.write(data);
                writer.newLine(); // Add a new line for each entry
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<Integer> findMatchingAndUnmatchingRecords(List<String[]> file1Data, List<String[]> file2Data) {
        int checkingRowNumber=0;
        List<Integer> matchingRowsIndexes=new ArrayList<>(0);

        for (String[] rowOfSheet1 : file1Data) {
            checkingRowNumber++;
            boolean flag=false;
            for ( String[] rowOfSheet2 : file2Data ) {
                if (isEqual(rowOfSheet1, rowOfSheet2)) {
                    matchingRecords.add(rowOfSheet1);
                    matchingRowsIndexes.add(checkingRowNumber);
                    flag=true;
                    break;
                }
            }
            if(flag==false){
                unMatchingRecords.add(rowOfSheet1);
            }
        }
        return matchingRowsIndexes;
    }
    private static String getFileNameAndSheetName(String filePath) throws Exception {
        try {
            String sheetName = WorkbookFactory.create(new FileInputStream(filePath)).getSheetAt(0).getSheetName();
            String filename = Paths.get(filePath).getFileName().toString();
            return "Excel File -> " + filename + " Sheet Name -> " + sheetName;
        } catch (IOException e) {
            e.printStackTrace();
            return " Exception ";
        }
    }
    private static List<String[]> readExcelFile(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            List<String> rowData = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowData.add(cell.toString());
            }

            data.add(rowData.toArray(new String[0]));
        }

        workbook.close();
        file.close();

        return data;
    }
    private static boolean isEqual(String[] row1, String[] row2) {
        // Implement logic to compare rows
        // System.out.println(stringArrayPrinter(row1));
        // System.out.println(stringArrayPrinter(row2));

        return java.util.Arrays.equals(row1, row2);
    }
    private static void stringArrayPrinter(String whatIsPrinting, String[] arrayToPrint) {
        System.out.println(whatIsPrinting);
        String completeRecordInOneline="";
        for (String s : arrayToPrint) {
            completeRecordInOneline=completeRecordInOneline+""+s+" | ";
            //OutPutList.add(s + " | ");
        }
        System.out.println(completeRecordInOneline);
    }
    private static boolean headersMatching(String[] sheet1Headers, String[] sheet2Headers) {
        try {
            return java.util.Arrays.equals(sheet1Headers, sheet2Headers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}