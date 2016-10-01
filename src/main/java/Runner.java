/**
 * Created by Bob on 2016-04-03.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import sun.awt.image.ImageWatched;




public class Runner {


    public static void main(String[] args) throws IOException {

        LinkedList<Question> questions = new LinkedList<Question>();

        String excelFilePath = "/Users/Bob/Desktop/app.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("Sheet1");

        Row listOfQuestions = sheet.getRow(0);


        // creates the list of questions

        for (Cell cell : listOfQuestions) {
            Question q = new Question();
            q.setQuestion(cell.getStringCellValue());
            questions.add(q);
        }

        System.out.println(questions.size());

        int numApps = 0;
        while (true) {
            Row row = sheet.getRow(numApps);

            String temp = "";

            if (row == null) {
                break;
            }

            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    temp = temp.concat(cell.getStringCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    temp = temp.concat(Double.toString(cell.getNumericCellValue()));
                }
            }

            if (temp != "") {
                numApps++;
            } else {
                break;
            }
        }



        for (int i = 1; i < numApps; i++) {

            Row row = sheet.getRow(i);

            if (row != null) {

                Applicant applicant = new Applicant();

                applicant.setQuestions(questions);

                for (int ii = 1; ii < questions.size(); ii++) {

                    Cell cell = row.getCell(ii, Row.RETURN_BLANK_AS_NULL);

                    if (cell == null) {
                        List<Question> temp = questions;
                        Question tempQ = new Question();
                        tempQ.setQuestion(temp.get(ii).getQuestion());
                        tempQ.setResponse("no response");
                        temp.set(ii, tempQ);
                        applicant.setQuestions(temp);
                    }

                    else if (cell.getColumnIndex() != 0) {

                        int index = cell.getColumnIndex();

                        if (index <= questions.size() - 1) {

                            List<Question> temp = questions;
                            Question tempQ = new Question();
                            tempQ.setQuestion(temp.get(index).getQuestion());



                            if(cell.getCellType() == cell.CELL_TYPE_STRING) {
                                tempQ.setResponse(cell.getStringCellValue());
                            } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                                tempQ.setResponse(Double.toString(cell.getNumericCellValue()));
                            }

                            temp.set(index, tempQ);
                            applicant.setQuestions(temp);
                        }

                    }
                }


                try {
                    createDoc(applicant, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }



        }



    System.out.println("Total number of applicants: " + (numApps - 2)); // minus one for row with question titles.




    }



    public static void createDoc (Applicant a, int count) throws Exception {
/*
        System.out.println("At this point");
        for (Applicant a : applicants) {
            List<Question> print = a.getQuestions();
            for (Question q : print) {
                System.out.println(q.getQuestion());
                System.out.println(q.getResponse());
            }
        }
*/


            final int nameColumn = 1;

            String name = a.getQuestions().get(nameColumn).getResponse() + " " + a.getQuestions().get(2).getResponse();

            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(new File ("/Users/Bob/Desktop/Results/" + name + ".docx"));

            int length = a.getQuestions().size();

            for (int i = 0; i < length; i++) {
                XWPFParagraph temp = document.createParagraph();
                XWPFRun tempQuestion = temp.createRun();
                tempQuestion.setBold(true);
                tempQuestion.setText(a.getQuestions().get(i).getQuestion() + " : ");
                tempQuestion.addBreak();
                XWPFRun tempText = temp.createRun();
                tempText.setText(a.getQuestions().get(i).getResponse());
                tempText.addBreak();

            }





            document.write(out);
            out.close();
            System.out.println(name + " written successfully");

        }




}
