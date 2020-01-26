package com.clillo.ilda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class IldaColorTable {

    private Map<Integer, ColorData> colors = new HashMap<Integer, ColorData>();

    public IldaColorTable() {
   /*     String line;
        BufferedReader reader = null;
        try {
        	InputStream inputStream = new FileInputStream(new File("ildaColorTable.csv"));
            reader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = reader.readLine()) != null) {
                createColor(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	try {
				reader.close();
			} catch (IOException e) {
			}
        }*/
    }

    private void createColor(String line) {
        String[] split = line.split(" ");
        ColorData colorData = new ColorData();
        int colorNr = Integer.valueOf(split[0].trim());
        int red = Integer.valueOf(split[1].trim());
        int green = Integer.valueOf(split[2].trim());
        int blue = Integer.valueOf(split[3].trim());
        colorData.setRed1(red);
        colorData.setGreen1(green);
        colorData.setBlue1(blue);
        colorData.setCode(colorNr);
        colors.put(colorNr, colorData);
    }

    public ColorData getColor(int colorNr) {
    	ColorData colorData = new ColorData();
        colorData.setRed1(255);
        colorData.setGreen1(255);
        colorData.setBlue1(255);
        colorData.setCode(colorNr);
        //return colors.get(colorNr);
        return colorData;
    }

}
