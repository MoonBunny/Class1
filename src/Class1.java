import java.io.*;

public class Class1 {

    public static void main(String[] args) {
        DoStuff();
    }

    public static void DoStuff(){
        try
        {
            FileReader fileReader = new FileReader(new File("C:\\Users\\Chris\\Desktop\\testdata.txt"));
            BufferedReader br = new BufferedReader(fileReader);
            PrintWriter writer = new PrintWriter("C:\\Users\\Chris\\Desktop\\newtestdata.txt", "UTF-8");
            int count = 1;
            String output = "";
            String line = null;
            // if no more lines the readLine() returns null
            while ((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++)
                {
                    if(count == 24)
                    {
                        count = 1;
                        i += 8;
                        output += line.charAt(i);
                        continue;
                    }
                    output += line.charAt(i);
                    count++;
                }
            }
            writer.println(output);
            writer.close();
        }
        catch (Exception e)
        {

        }
    }
}