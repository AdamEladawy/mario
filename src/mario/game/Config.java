package mario.game;

import mario.game.drawings.GamePanel;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            // FULL SCREEN SETTING
            if (gp.isFullScreenOn()) {
                bw.write("On");
            } else {
                bw.write("Off");
            }
            bw.newLine();

            //MUSIC VOLUME
            bw.write(String.valueOf(gp.getMusic().getVolumeScale()));
            bw.newLine();

            // SE VOLUME
            bw.write(String.valueOf(gp.getSe().getVolumeScale()));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            //TODO Auto-generated catch book
            e.printStackTrace();
        }

    }

    public void loadConfig() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            // FULL SCREEN
            gp.setFullScreenOn(s.equals("On"));


            // MUSIC VOLUME
            s = br.readLine();
            gp.getMusic().setVolumeScale(Integer.parseInt(s));

            // SE VOLUME
            s = br.readLine();
            gp.getSe().setVolumeScale(Integer.parseInt(s));

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}