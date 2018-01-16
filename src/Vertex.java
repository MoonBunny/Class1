import java.io.*;

public class Vertex {
    public static final String pattOne = "0000803F";
    public static final String pattTwo = "000080BF";
    public static final String pattZero = "00000000";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //GetVertex();
        stripData();
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("finished: " + endTime);
    }

    public static void stripData() {
        try
        {
            FileReader fileReader = new FileReader(new File("C:\\Users\\Chris\\Desktop\\sample.txt"));
            BufferedReader br = new BufferedReader(fileReader);
            PrintWriter vertexFile = new PrintWriter("C:\\Users\\Chris\\Desktop\\vertexFile.txt", "UTF-8");
            PrintWriter normalFile = new PrintWriter("C:\\Users\\Chris\\Desktop\\normalsFile.txt", "UTF-8");
            PrintWriter uvFile = new PrintWriter("C:\\Users\\Chris\\Desktop\\uvFile.txt", "UTF-8");
            PrintWriter faceFile = new PrintWriter("C:\\Users\\Chris\\Desktop\\faceFile.txt", "UTF-8");
            int blockCount = 0;
            String vertexOut = "";
            String normalOut = "";
            String uvOut = "";
            String faceOut = "";
            String tempLines = "";
            String line;
            String last8Char;
            // if no more lines the readLine() returns null
            while ((line = br.readLine()) != null) { // get line 1
                if (line.equals("")) {
                    continue;
                }
                last8Char = line.substring(line.length()-8, line.length());
                if (last8Char.equals(pattOne) || last8Char.equals(pattTwo)) {
                    tempLines += line + "\n";
                    if ((line = br.readLine()) != null) { // finding 2nd line
                        last8Char = line.substring(line.length()-8, line.length());
                        if (last8Char.equals(pattZero)) {
                            tempLines += line + "\n";
                            if ((line = br.readLine()) != null) { // line 3
                                last8Char = line.substring(line.length()-8, line.length());
                                if (last8Char.equals(pattOne) || last8Char.equals(pattTwo)) {
                                    tempLines += line + "\n";
                                    blockCount++;
                                    vertexOut += tempLines;
                                    tempLines = "";
                                } else {
                                    tempLines = "";
                                }
                            } else {
                                tempLines = "";
                            }
                        } else {
                            tempLines = "";
                        }
                    } else {
                        tempLines = "";
                    }
                } else {
                    for(int i = 0; i < blockCount; i++)
                    {
                        tempLines += line + "\n";
                        line = br.readLine();
                        tempLines += line + "\n";
                        line = br.readLine();
                    }
                    normalOut += tempLines;
                    tempLines = "";
                    for(int i = 0; i < blockCount; i++)
                    {
                        tempLines += line + "\n";
                        line = br.readLine();
                    }
                    uvOut += tempLines;
                    tempLines = "";
                    last8Char = line.substring(line.length()-8, line.length());
                    while((line = br.readLine()) != null) {
                        if(line.equals("")) {
                            continue;
                        }
                        if(last8Char.equals(pattOne) || last8Char.equals(pattTwo))
                        {
                            break;
                        }
                        tempLines += line + "\n";
                        last8Char = line.substring(line.length()-8, line.length());
                    }
                    if (line == null) {
                        break;
                    }
                    faceOut += tempLines;
                    tempLines = "";
                    blockCount = 0;
                    last8Char = line.substring(line.length()-8, line.length());
                    if (last8Char.equals(pattOne) || last8Char.equals(pattTwo)) {
                        tempLines += line + "\n";
                        if ((line = br.readLine()) != null) { // finding 2nd line
                            last8Char = line.substring(line.length()-8, line.length());
                            if (last8Char.equals(pattZero)) {
                                tempLines += line + "\n";
                                if ((line = br.readLine()) != null) { // line 3
                                    last8Char = line.substring(line.length()-8, line.length());
                                    if (last8Char.equals(pattOne) || last8Char.equals(pattTwo)) {
                                        tempLines += line + "\n";
                                        blockCount++;
                                        vertexOut += tempLines;
                                        tempLines = "";
                                    } else {
                                        tempLines = "";
                                    }
                                } else {
                                    tempLines = "";
                                }
                            } else {
                                tempLines = "";
                            }
                        } else {
                            tempLines = "";
                        }
                    } else {
                        tempLines = "";
                    }
                }
            }
            vertexFile.println(vertexOut);
            vertexFile.close();
            normalFile.println(normalOut);
            normalFile.close();
            uvFile.println(uvOut);
            uvFile.close();
            faceFile.println(faceOut);
            faceFile.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void GetVertex(){
        try
        {
            FileReader fileReader = new FileReader(new File("C:\\Users\\Chris\\Desktop\\sample.txt"));
            BufferedReader br = new BufferedReader(fileReader);
            PrintWriter writer = new PrintWriter("C:\\Users\\Chris\\Desktop\\newsample.txt", "UTF-8");
            String output = "";
            String line;
            // if no more lines the readLine() returns null
            while ((line = br.readLine()) != null) {
                int index = 0;
                while (index < line.length()) {
                    output += (line.substring(index, Math.min(index + 32,line.length()))) + "\n";
                    index += 32;
                }
            }
            writer.println(output);
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}