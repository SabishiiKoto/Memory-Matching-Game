package sabishiikoto.memorymatchinggame;

import java.io.*;

public class Data {
    private static int[] highScore = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    private static String[] preferredColor = {null, null, null, null};
    private static boolean time = true;
    public static String updateHighScore(int level, int time){
        if (level == 4 && time < highScore[0]){
            highScore[0] = time;
            int minute = time/60;
            int second = time%60;
            return String.format("%02d:%02d", minute,second);
        }
        else if (level == 6 && time < highScore[1]){
            highScore[1] = time;
            int minute = time/60;
            int second = time%60;
            return String.format("%02d:%02d", minute,second);
        }
        else if (level == 8 && time < highScore[2]){
            highScore[2] = time;
            int minute = time/60;
            int second = time%60;
            return String.format("%02d:%02d", minute,second);
        }
        else if (level == 10 && time < highScore[3]){
            highScore[3] = time;
            int minute = time/60;
            int second = time%60;
            return String.format("%02d:%02d", minute,second);
        }
        return null;
    }
    public static String getHighScore(int level){
        int time = 0;
        switch(level){
            case 4:
                time = highScore[0];
                break;
            case 6:
                time = highScore[1];
                break;
            case 8:
                time = highScore[2];
                break;
            case 10:
                time = highScore[3];
                break;
        }
        if (time != Integer.MAX_VALUE) {
            int minute = time / 60;
            int second = time % 60;
            return String.format("%02d:%02d", minute, second);
        }
        return null;
    }

    public static boolean saveFile(){
        try{
            String filename = "memoryMatchingData.txt";
            File file = new File(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (int time : highScore){
                bufferedWriter.write(time + "\n");
                bufferedWriter.flush();
            }
            for (String colorCode : preferredColor){
                bufferedWriter.write(colorCode + "\n");
                bufferedWriter.flush();
            }
            bufferedWriter.write(time + "\n");
            bufferedWriter.flush();
            return true;
        }
        catch(IOException e){
            return false;
        }
    }
    public static boolean loadFile(){
        try{
            String filename = "memoryMatchingData.txt";
            File file = new File(filename);
            BufferedReader bufferedWriter = new BufferedReader(new FileReader(file));
            String line = bufferedWriter.readLine();
            int index = 0;
            int hexIndex = 0;
            while (line != null){
                if (line.charAt(0) == '-'){
                    preferredColor[hexIndex] = line;
                    hexIndex++;
                }
                else if (!line.equals("null")) {
                    if (line.equals("true")){
                        time = true;
                    }
                    else if (line.equals("false")){
                        time = false;
                    }
                    else {
                        highScore[index] = Integer.parseInt(line);
                        index++;
                    }
                }
                line = bufferedWriter.readLine();
            }
            return true;
        }
        catch(IOException e){
            return false;
        }
    }
    public static boolean setPreferredColor (String menuColor, String titleColor, String paneColor, String errorColor){
        preferredColor[0] = menuColor;
        preferredColor[1] = titleColor;
        preferredColor[2] = paneColor;
        preferredColor[3] = errorColor;
        return true;
    }
    public static String getPreferredColor(int index){
        return preferredColor[index];
    }

    public static boolean setTime(boolean status){
        time = status;
        return true;
    }
    public static boolean getTime(){
        return time;
    }
}
