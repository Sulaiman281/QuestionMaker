package org.witshells.filehandling;

import org.witshells.App;
import org.witshells.models.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandling {

    private final String file_name = "BinaryQFile.dat";

    public byte[] stringToBinary(String _str){
        return _str.getBytes(StandardCharsets.UTF_8);
    }

    public String binaryToString(byte[] bytes){
        return new String(bytes,StandardCharsets.UTF_8);
    }

    public void fileWriting(){
        File file = new File(file_name);
        try {
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            byte[] bytes = stringToBinary(App.questions.toString());
            fileWriter.write(Arrays.toString(bytes));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void fileReading(){
        File file = new File(file_name);
        try{
            if(file.exists()){
                Scanner scanner = new Scanner(file);
//                byte[] bytes;
//                int size = 0, index = 0;
                String line = "";
                while(scanner.hasNext()){
//                    bytes = new byte[++size];
//                    bytes[index++] = scanner.nextByte();
                    line = line.concat(scanner.nextLine());
                }

                // converting binary to string then getting mcqs from it.
                String test = convertToString(line);
                test = test.replace("[","");
                test = test.replace("]","");
                extractQuestion(test);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String convertToString(String str){
        str = str.replace("[","");
        str = str.replace("]","");
        str = str.replace(" ","");
        String[] num = str.split(",");
        String line = "";
        for(int i = 0; i< num.length; i++){
            int x = Integer.parseInt(num[i]);
            line = line.concat((char)x+"");
        }
        return line;
    }

    public void extractQuestion(String test){
        test = test.replace(",,",",");
        String[] questions = test.split(",");
        System.out.println(test);
        for (String question : questions) {
            String[] q = question.split("-");
            System.out.println(Arrays.toString(q));
            if(q.length < 5) return;
            App.questions.add(new Question(
                    q[0],
                    q[1],
                    q[2],
                    q[3],
                    q[4]
            ));
        }
    }

}
