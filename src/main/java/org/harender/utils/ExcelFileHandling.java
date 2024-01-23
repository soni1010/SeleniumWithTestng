package org.harender.utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import org.apache.poi.ss.usermodel.*;


public class ExcelFileHandling {
    static List<String> OutPutList = new ArrayList<String>();

    static List<String[]> matchingRecords = new ArrayList<>();

    static List<String[]> unMatchingRecords = new ArrayList<>();

    static List<Integer> mismatchIndex = new ArrayList<Integer>();

    static int sheetNumberRightNowInComparison=0;

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
            int fileCounter=0;

            for(String fileWhereToComparePath:filesPathwithWhomToCompare) {

                System.out.println("Comparing file ||"+getFileNameAndSheetName(file1Path)+"|| with file \n||"+getFileNameAndSheetName(fileWhereToComparePath)+ "||");

                sheetNumberRightNowInComparison++;
                List<String[]> fileOfWhichToCompareDataIncludingHeaders = readExcelFile(file1Path);
                List<String[]> fileInWhichToCompareDataIncludingHeaders = readExcelFile(fileWhereToComparePath);

                String [] fromWhichHeaders=fileOfWhichToCompareDataIncludingHeaders.get(0);
                String [] toWhichHeaders=fileInWhichToCompareDataIncludingHeaders.get(0);

                fileOfWhichToCompareDataIncludingHeaders.remove(0);
                fileInWhichToCompareDataIncludingHeaders.remove(0);
                List<String[]> file1WithOutHeaders =fileOfWhichToCompareDataIncludingHeaders;
                List<String[]> file2WithOutHeaders =fileInWhichToCompareDataIncludingHeaders;

                if (headersMatching(fromWhichHeaders, toWhichHeaders))
                    System.out.println("Headers of "+getFileNameAndSheetName(file1Path)+" and \n"+getFileNameAndSheetName(fileWhereToComparePath)+" are Matching Successfully !");
                else {
                    System.out.println("Headers of "+getFileNameAndSheetName(file1Path)+" and \n"+getFileNameAndSheetName(fileWhereToComparePath)+" are Mismatched - Please Verify !");
                    break;
                }

                //printing the sheet name and number of records without Headers now
                OutPutList.add("No. of records in file "+getFileNameAndSheetName(file1Path)+" (input data) - " + file1WithOutHeaders.size()
                        + "\nNo. of records in file 2 "+getFileNameAndSheetName(fileWhereToComparePath)+" (input data) - " + file2WithOutHeaders.size());

                System.out.println("\nNo. of records in file "+getFileNameAndSheetName(file1Path)+" (input data) - " + file1WithOutHeaders.size()
                        + "\nNo. of records in file 2 "+getFileNameAndSheetName(fileWhereToComparePath)+" (input data) - " + file2WithOutHeaders.size()+"\n");

                //matching check of current two files data
                List<Integer> matchingRowsIndex= findMatchingRecords(file1WithOutHeaders, file2WithOutHeaders);
                fileCounter++;
                System.out.println(matchingRowsIndex.size()+" - Matching in "+fileCounter+" compare");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getHeadersOnly(){


    }

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

    private static List<Integer> findMatchingRecords(List<String[]> file1Data, List<String[]> file2Data) {

        int checkingRowNumber=0;
        List<Integer> matchingRowsIndexes=new ArrayList<>(0);

        for (String[] rowOfSheet1 : file1Data) {
            checkingRowNumber++;
            for ( String[] rowOfSheet2 : file2Data ) {
                if (isEqual(rowOfSheet1, rowOfSheet2)) {
                    matchingRecords.add(rowOfSheet1);
                    matchingRowsIndexes.add(checkingRowNumber);
                    break;
                }
            }
        }

        return matchingRowsIndexes;
    }
//    if(file2Data.size()==RowNumSheet2) {
//                        unmatchCountVerification++;
//                        String[] infoAboutUnMatching= {"Row Number Sheet 1: "+RowNumSheet1+"\nRow Number Sheet 2: "+RowNumSheet2+" "};
//                        System.out.println("Row Number Sheet 1: "+RowNumSheet1+"\nRow Number Sheet 2: "+RowNumSheet2+" ");
//                        unMatchingRecords.add(infoAboutUnMatching);
//                        unMatchingRecords.add(rowOfSheet1);
//                    }

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

        OutPutList.add(whatIsPrinting);
        String completeRecordInOneline="";
        for (String s : arrayToPrint) {
            completeRecordInOneline=completeRecordInOneline+""+s+" | ";
            //OutPutList.add(s + " | ");
        }

        OutPutList.add(completeRecordInOneline);
    }

    private static boolean headersMatching(String[] sheet1Headers, String[] sheet2Headers) {
        try {
            stringArrayPrinter("Headers : ", sheet1Headers);
            stringArrayPrinter("Headers : ", sheet2Headers);

            return java.util.Arrays.equals(sheet1Headers, sheet2Headers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}