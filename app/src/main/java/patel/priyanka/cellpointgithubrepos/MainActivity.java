package patel.priyanka.cellpointgithubrepos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import patel.priyanka.cellpointgithubrepos.models.ReposModel;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRepos;
    private Button buttonSearchUser;
    private Button buttonSearchOrgs;

    private ReposAdapter listAdapter;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextRepos = findViewById(R.id.edit_text_enter_user);
        buttonSearchUser = findViewById(R.id.button_search_user);
        buttonSearchOrgs = findViewById(R.id.button_search_orgs);
        expandableListView = findViewById(R.id.list_view_expandable);

        onUserButtonClick();
    }

    private void onUserButtonClick() {
        buttonSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextRepos.getText().toString();
                new ReposUtils(v.getContext(), new Data()).execute("https://api.github.com/users/"+userName+"/repos");
                setTitle(userName);
                editTextRepos.setText("");
            }
        });
    }

    public class Data implements ReposUtils.AsyncTaskCompleteListener {
        HashMap<ReposModel, List<ReposModel>> childList = new HashMap<>();

        @Override
        public void onTaskComplete(ArrayList<ReposModel> result) {

            listAdapter = new ReposAdapter(getApplicationContext(), result, childList);
            expandableListView.setAdapter(listAdapter);
        }
    }
}
