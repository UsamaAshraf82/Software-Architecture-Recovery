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
import java.util.ArrayList;

public class ObjectInfo
{

    String name;
    ArrayList<String> objectList;//for public
    ArrayList<String> object_List;//for + as public
    ArrayList<String> functionList;
    ArrayList<String> function_List;

    public ObjectInfo()
    {
        objectList = new ArrayList<>();
        functionList = new ArrayList<>();
        object_List = new ArrayList<>();
        function_List = new ArrayList<>();

    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void addObject(String line)
    {

        objectList.add(line);
    }

    public void add_Object(String line)
    {

        object_List.add(line);
    }

    public ArrayList<String> getObjects()
    {
        return objectList;
    }

    public ArrayList<String> get_Objects()
    {
        return object_List;
    }

    public void addfunction(String line)
    {

        functionList.add(line);
    }

    public void add_function(String line)
    {

        function_List.add(line);
    }

    public ArrayList<String> getfunctions()
    {
        return functionList;
    }

    public ArrayList<String> get_functions()
    {
        return function_List;
    }

}
