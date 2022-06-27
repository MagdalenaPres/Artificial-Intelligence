
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Data
{
    public JSONArray readFile(String path) {

        JSONParser parser = new JSONParser();
        JSONArray jArray = new JSONArray();

        try(FileReader reader = new FileReader(path))
        {
            Object obj = parser.parse(reader);
            jArray = (JSONArray) obj;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return jArray;
    }

    public ArrayList<Connection> createConnectionsList(String fileCost, String fileAmount){
        ArrayList<Connection> connections = new ArrayList<>();
        JSONArray jArrayCost = readFile(fileCost);
        JSONArray jArrayFlow = readFile(fileAmount);

        for (Object o : jArrayCost)
        {
            JSONObject jo = (JSONObject) o;
            Connection con = new Connection((long) jo.get("dest"),(long) jo.get("source"),(long) jo.get("cost"), 0);
            connections.add(con);
        }

        int i = 0;
        for(Object o : jArrayFlow)
        {
            JSONObject jo = (JSONObject) o;
            connections.get(i).setAmount((long) jo.get("amount"));
            i++;
        }
        return connections;
    }
}
