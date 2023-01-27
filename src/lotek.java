import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class lotek {
    public static File file = new File("C:\\Users\\szyns\\IdeaProjects\\Lotki\\src\\lotto.txt");

    public static void wczytanie(File name) throws IOException {
        FileReader fileReader= new FileReader(name);
        BufferedReader bufferedReader= new BufferedReader(fileReader);
        String []tab = null;
        String [] numberList;
        String line;

        ArrayList<Integer> numbers= new ArrayList<>();
        while((line= bufferedReader.readLine())!=null)
        {
            tab=line.split(" ");
            for(int i=0;i< tab.length;i++)
            {
                numberList=tab[2].split(",");
                for(int j=0;j<numberList.length;j++)
                {
                    numbers.add(Integer.valueOf((numberList[j])));
                }

            }

        }
        Collections.sort(numbers);
        Hashtable<Integer,Integer>wystapienia=new Hashtable<>();

        for(int i=0;i<50;i++)
        {

            wystapienia.put(i,0);
        }
        for(int i=0;i<numbers.size();i++)
        {
            if(wystapienia.containsKey(numbers.get(i)))
            {
                wystapienia.computeIfPresent(numbers.get(i),(key,value)-> ++value);
            }
        }

        List<Map.Entry<Integer, Integer>> najczestsze= new ArrayList<>();
        List<Map.Entry<Integer, Integer>> najrzadsze= new ArrayList<>();
        najrzadsze=wystapienia.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList()).subList(0,6);
        najczestsze=wystapienia.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList()).subList(wystapienia.size()-6,wystapienia.size());
        System.out.println(Arrays.toString(tab));
        System.out.println(numbers);
        System.out.println(wystapienia);
        System.out.println(najczestsze);
        System.out.println(najrzadsze);
        bufferedReader.close();
        fileReader.close();


        File wynik = new File("wynik.txt");
        FileWriter fileWriter=new FileWriter(wynik);
        BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
        bufferedWriter.append("Najrzadsze wystapienia ").append(String.valueOf(najrzadsze));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.append("Najczestsze wystapienia ").append(String.valueOf(najczestsze));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();


    }

    public static void main(String[] args) throws IOException {
        wczytanie(file);
    }
}