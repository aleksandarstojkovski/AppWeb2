import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class testUrlConnection {

    public static void main(String[] args) throws IOException  {
        URL supsi;
        supsi = new URL("https://www.supsi.ch/");
        esercizio02(supsi);
    }

    static void esercizio02(URL supsi) throws IOException {
        HttpURLConnection con = (HttpURLConnection) supsi.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setReadTimeout(10000); // 10 secondi
        con.setRequestProperty(
                "User-agent",
                "Mozilla/5.0(iPad; U; CPU iPhone OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B314 Safari/531.21.10");
        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        //get all headers
        Map<String, List<String>> map = con.getRequestProperties();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
        }

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.flush();
        int HttpResult = con.getResponseCode();
        System.out.println("---begin header fields ----");
        //get all headers
        Map<String, List<String>> map2 = con.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map2.entrySet()) {
            System.out.println("Key : " + entry.getKey() +
                    " ,Value : " + entry.getValue());
        }
        System.out.println("---end header fields ----");
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("in :" + line);
            }
            br.close();

        }
    }

    void esercizio01(URL supsi) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(supsi.openStream())); //apre uno stream
        // su un URL
        while (true) {
            String s = null; //legge dallo stream
            try {
                s = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (s == null)
                break;
            else
                System.out.println(s);
        }
        in.close(); //chiude lo stream
    }

}
