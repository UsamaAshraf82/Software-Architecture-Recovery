/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Software;

/**
 *
 * @author Jahanzaib
 */
import java.awt.HeadlessException;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

public class FileScanner
{

    ArrayList<ObjectInfo> classList;
    JFileChooser f;
    String filePath, separator = "/";
    int flag = 1;
    int clock = 0, chk = 0;
    HashMap<String, String> file = new HashMap<>();

    String showPath, regex;
    ObjectInfo classObject;
    ArrayList<String> printList2 = new ArrayList<>();

    ArrayList<String> Association = new ArrayList<>();
    ArrayList<String> Inheritance = new ArrayList<>();

    public FileScanner()
    {
        classList = new ArrayList<>();
        f = new JFileChooser();

    }

    public void selectFolder()
    {
        try
        {
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);

            String path = f.getSelectedFile().toString();
            String folder = path.replace("\\", "/");
            showPath = folder;
            File[] files = new File(folder).listFiles();
            //If this pathname does not denote a directory, then listFiles() returns null.

            for (File x : files)
            {
                if (x.isFile())
                {

                    if (x.getName().endsWith(".java"))
                    {
                        filePath = folder + separator + x.getName();
                        scanFile(filePath);
                    }
                }
            }
        } catch (HeadlessException iox)
        {

        }
    }

    public void scanFile(String path)
    {

        try
        {
            classObject = new ObjectInfo();
            String fileName = path;
            FileReader fr;
            BufferedReader br;
            try
            {
                fr = new FileReader(fileName);
                br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null)
                {
                    flag = 1;
                    switch (chk)
                    {
                        case 0:
                            scanClassName(line, classObject);
                            scanInheritance(line, classObject);
                            break;
                        case 1:
                            scanAttributes(line, classObject);
                            scanFunction(line, classObject);
                            break;
                        default:
                            scanAssociation(line, classObject);
                            break;
                    }
                    if (line.contains("{"))
                    {
                        chk++;
                    }

                    if (line.contains("}"))
                    {
                        chk--;
                    }

                    line = br.readLine();       //next line

                }

                if (flag == 1)
                {
                    classList.add(classObject);
                }
                br.close(); //close streams
                fr.close();
            } catch (IOException ioex)
            {
            }

        } catch (Exception iox)
        {
        }

    }

    public void scanInheritance(String line, ObjectInfo classObject)
    {
        try
        {

            String pattern = "(private|protected|public)?(\\s+)?(class)(\\s+)?(\\w+)(\\s+)?(extends)(\\s+)?(\\w+)";

            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(line);
            if (m.find())
            {

                line = m.group(5) + " is extended from " + m.group(9) + "\n";

                Inheritance.add(line);

            }

        } catch (Exception iox)
        {
        }

    }

    public void scanAssociation(String line, ObjectInfo classObject)
    {

        try
        {

            String pattern = "(\\w+)(\\s+)(\\w+)(\\s+)?(\\s*)(=)(\\s*)(new)(\\s+)(\\w+)([(])";
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(line);
            if (m.find())
            {

                if (!(m.group(10).equalsIgnoreCase(m.group(1))))
                {
                    line = "Association between " + m.group(1) + " and " + m.group(10) + "\n";
                    Association.add(line);

                }
                else if ((m.group(10).equalsIgnoreCase(m.group(1))))
                {
                    line = "Association between " + classObject.getName() + " and " + m.group(10) + "\n";
                    Association.add(line);
                }

            }

        } catch (Exception iox)
        {
        }

    }

    public void scanClassName(String line, ObjectInfo classObject)
    {
        try
        {

            String pattern = "(private|protected|public)?(\\s+)?(class)(\\s+)?(\\w+)";
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(line);
            if (m.find())
            {
                classObject.setName(m.group(5));
            }

        } catch (Exception iox)
        {
        }

    }

    public void scanAttributes(String line, ObjectInfo classObject)
    {
        String temp1, temp2 = "", temp3 = "", temp4;
        String[] temp, tempo;
        try
        {

            String pattern = "(private|protected|public)?(\\s+)?(final|static)?(\\s+)?(String|int|double|float|long)(\\s+)(.*)[;]";
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(line);
            if (m.find())
            {
                if (m.group(1) == null)
                {
                    temp2 = "+";
                    temp3 = "public";
                }
                else if (m.group(1).equalsIgnoreCase("public"))
                {
                    temp2 = "+";
                    temp3 = "public";
                }
                else if (m.group(1).equalsIgnoreCase("private"))
                {
                    temp2 = "-";

                    temp3 = "private";
                }
                else if (m.group(1).equalsIgnoreCase("protected"))
                {
                    temp2 = "#";
                    temp3 = "protected";

                }

                temp = m.group(7).split(",");
                for (String temp5 : temp)
                {
                    tempo = temp5.split("=");
                    temp1 = temp2 + tempo[0] + " : " + m.group(5);
                    temp4 = temp3 + " " + tempo[0] + " : " + m.group(5);
                    if (m.group(3) != null)
                    {
                        temp4 = temp4 + " ( " + m.group(3) + " )";
                        temp1 = temp1 + " ( " + m.group(3) + " )";
                    }
                    classObject.add_Object(temp1);
                    classObject.addObject(temp4);
                }

                temp1 = temp2 + m.group(7) + m.group(9) + " " + m.group(10) + m.group(11) + " : " + m.group(5);

                classObject.add_function(temp1.trim());
            }

        } catch (Exception iox)
        {
        }

    }

    public void scanFunction(String line, ObjectInfo classObject)
    {
        String temp1, temp2 = "", temp3 = "";
        try
        {

            String pattern = "(private|protected|public)?(\\s+)?(static)?(\\s+)?(String|int|double|float|void|long|boolean)(\\s+)?(\\w+)(\\s+)?([(])(.*)([)])";

            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(line);
            if (m.find())
            {
                temp1 = m.group(0);
                // System.out.println("Found value: " + m.group(0) );
                classObject.addfunction(temp1.trim());
                if (m.group(1) == null)
                {
                    temp2 = "+";
                }
                else if (m.group(1).equalsIgnoreCase("public"))
                {
                    temp2 = "+";
                }
                else if (m.group(1).equalsIgnoreCase("private"))
                {
                    temp2 = "-";
                }
                else if (m.group(1).equalsIgnoreCase("protected"))
                {
                    temp2 = "#";
                }

                temp1 = temp2 + m.group(7) + m.group(9) + " " + m.group(10) + m.group(11) + " : " + m.group(5);
                if (m.group(3) != null)
                {
                    temp1 = temp1 + "( " + m.group(3) + " )";
                }
                classObject.add_function(temp1.trim());

            }

        } catch (Exception iox)
        {
        }

    }

    public void printClasses2()
    {
        try
        {
            String print;
            String line = "\n____________________\n";
            String star = "";
            for (int i = 0; i < classList.size(); i++)
            {
                star = "";

                print = "            " + classList.get(i).getName();
                print = print + line;
                //print = print + "\n" + "Number of attributes: " + classList.get(i).getObjects().size() + line; 

                for (int x = 0; x < classList.get(i).get_Objects().size(); x++)
                {
                    print = print + "\n " + classList.get(i).get_Objects().get(x);

                }
                print = print + line;
                //print = print + "\n" + "Number of Methods: " + classList.get(i).getfunctions().size() + line; 

                for (int m = 0; m < classList.get(i).get_functions().size(); m++)
                {
                    print = print + "\n " + classList.get(i).get_functions().get(m);

                }

                print = print + line;

                printList2.add(print);

            }

        } catch (Exception iox)
        {

        }

    }

    public static String trim(final String s)
    {

        try
        {

            final StringBuilder sb = new StringBuilder(s);
            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0)))
            {
                sb.deleteCharAt(0); // delete from the beginning
            }
            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1)))
            {
                sb.deleteCharAt(sb.length() - 1); // delete from the end
            }
            return sb.toString();
        } catch (Exception iox)
        {
            return null;
        }

    }
}
