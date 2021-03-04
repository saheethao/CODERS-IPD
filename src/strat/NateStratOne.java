package strat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NateStratOne extends Strategy{

    private String urlString = "https://icanhazdadjoke.com/";

    @Override
    protected int generateMove() {
        try {
            URL dadJokeURL = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) dadJokeURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestProperty("Accept", "text/plain");


            int responseCode = connection.getResponseCode();
            if(responseCode==200){
                BufferedReader bin  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String joke = bin.readLine();
                int hashValue = joke.hashCode();
                return hashValue%2;
            }else{
                System.out.println(responseCode);
                return 1;
            }

        } catch (Exception e) {
            //e.printStackTrace();
            return 1;
        }
    }

    @Override
    protected String getName() {
        return "DadJokeStrat";
    }

    @Override
    protected String getAuthor() {
        return "Nathan Diedrick";
    }

    @Override
    public void reinit() {

    }
}
