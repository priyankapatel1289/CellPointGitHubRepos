package patel.priyanka.cellpointgithubrepos;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import patel.priyanka.cellpointgithubrepos.models.ReposModel;

public class ReposUtils extends AsyncTask<String, Void, ArrayList<ReposModel>> {
    private ArrayList<ReposModel> reposList = new ArrayList<>();
    private AsyncTaskCompleteListener listener;
    private Context context;

    public interface AsyncTaskCompleteListener{
        void onTaskComplete(ArrayList<ReposModel> result);
    }

    public ReposUtils(Context context, AsyncTaskCompleteListener listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;
    }

    @Override
    protected ArrayList<ReposModel> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String json = stringBuilder.toString();
            JSONArray parentArray = new JSONArray(json);

            for (int i = 0; i<parentArray.length(); i++) {
                JSONObject parentObject = parentArray.getJSONObject(i);
                ReposModel reposModel = new ReposModel();
                reposModel.setName(parentObject.getString("name"));
                reposModel.setLanguage(parentObject.getString("language"));
                reposModel.setStargazers_count(parentObject.getInt("stargazers_count"));

                reposList.add(reposModel);
            }

            return reposList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            } try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ReposModel> result) {
        super.onPostExecute(result);
        if (reposList != null) {
            listener.onTaskComplete(result);
        }
    }
}
