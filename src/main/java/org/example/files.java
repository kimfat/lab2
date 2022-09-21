package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class files {
    public final static String textFileName="Input.txt";
    public final static String textSave="Results.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


/******************* Reading from a text file *************************/
// using FileReader
        System.out.println(String.format("Reading file [%s] using FileReader",textFileName));
        StringBuilder sb= new StringBuilder();
        List<String> myList = new ArrayList<String>();
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> index = new ArrayList<>();

        try {
            FileReader myReader = new FileReader(textFileName);


            try(Scanner sc = new Scanner(new FileInputStream(textFileName))) {
                int count = 0;
                while (sc.hasNext()) {
                    sc.next();
                    count++;
                }
                System.out.println("Number of words: " + count);
            }
            FileReader fr = new FileReader(textFileName);
            BufferedReader br = new BufferedReader(fr);
            br.lines()
                    .map(Letters::stringToLettersCount)
                    .reduce(Letters::sum)
                    .ifPresent(l -> System.out.println(l.toString()));
            br.close();


            int character=myReader.read();
            while(character!=-1)
            {
                sb.append((char) character);
                character=myReader.read();


            }
            list1.add(sb.toString());


            Pattern pattern = Pattern.compile("[+-]?([0-9]*[.])?[0-9]+");
            Matcher matcher = pattern.matcher(sb);

            while (matcher.find()) {
                myList.add(matcher.group());

            }
            String[] words_sp = sb.toString().split(" ");


            String resultOfConvert;
            for (int i = 0; i<words_sp.length;i++) {
                if (myList.contains(words_sp[i])){

                    int b = Integer.parseInt(words_sp[i]);
                    resultOfConvert = Integer.toHexString(b);
                    words_sp[i] = resultOfConvert;

                }

            }
            String listString = String.join(", ", words_sp);
            String words = listString.replaceAll("\\p{Punct}", "");
            System.out.println("Your string: " + words);

            try {
                FileWriter myWriter = new FileWriter(textSave);
                myWriter.write(words);
                myWriter.close();

            } catch (Exception e) {
                System.out.println("Text saving failed.");
                e.printStackTrace();
            }

            int fileSize = br.toString().length();
            BufferedReader consoleBR = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Write word to find it in Input.txt file, exit to end:");
            String find = consoleBR.readLine();
            while (!find.equals("exit")){
                int findSize = find.length();
                int findIndex = 0;
                int wordsFound = 0;
                while (findIndex != -1){
                    int foundIndex = sb.indexOf(find, findIndex);
                    if (foundIndex != -1){
                        wordsFound++;
                        System.out.println("Word found: begin " + foundIndex + ", end " + (foundIndex + findSize));
                        findIndex = foundIndex + findSize;
                    } else {
                        findIndex = -1;
                    }
                }
                System.out.println(wordsFound + " found");
                System.out.println("Write word to find it in Input.txt file, exit to end:");
                find = consoleBR.readLine();
            }

            myReader.close();

        } catch (Exception e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }


    }
}
