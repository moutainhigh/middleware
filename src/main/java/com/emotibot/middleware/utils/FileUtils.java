package com.emotibot.middleware.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtils
{
    public static List<String> readFile(String fileName)
    {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> ret = new ArrayList<String>();
        try 
        {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) 
            {
                ret.add(line);
            }
            return ret;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        } 
        finally 
        {
            if (reader != null) 
            {
                try 
                {
                    reader.close();
                } 
                catch (IOException e1) 
                {
                    
                }
            }
        }
    }
    
    public static String readFileToString(String fileName)
    {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try 
        {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) 
            {
                sb.append(line);
            }
            return sb.toString();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        } 
        finally 
        {
            if (reader != null) 
            {
                try 
                {
                    reader.close();
                } 
                catch (IOException e1) 
                {
                    
                }
            }
        }
    }
    
    public static void storeFile(List<String> lines, String fileName)
    {
        FileWriter fw = null;
        try
        {
            fw = new FileWriter(fileName);
            for (int i = 0; i < lines.size(); i ++)
            {
                fw.write(lines.get(i) + "\r\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }
        finally
        {
            try
            {
                fw.close();
            } 
            catch (IOException e)
            {
                
            }
        }
    }
    
    public static void writeLogForXls(String xlsFile, List<List<String>> contents)
    {
        File file = new File(xlsFile);
        if (file.exists())
        {
            file.delete();
        }
        OutputStream os = null;
        XSSFWorkbook xssfWorkbook = null;
        try
        {
            os = new FileOutputStream(xlsFile);
            xssfWorkbook = new XSSFWorkbook();
            Sheet sheet = xssfWorkbook.createSheet();
            int rowCount = 0;
            for (List<String> content : contents)
            {
                Row row = sheet.createRow(rowCount);
                for (int i = 0; i < content.size(); i ++)
                {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(content.get(i));
                }
                rowCount ++;
            }
            xssfWorkbook.write(os);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                os.close();
            }
            catch (Exception e)
            {
                
            }
        }
    }
}
