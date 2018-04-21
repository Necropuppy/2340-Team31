package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Class for the AccountActivity (the main screen logic after login)
 */
public class AccountActivity extends AppCompatActivity {
    static String TAG = "MY_APP";
    //private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;


    private String[] shelterNames = new String[13];
    //private Object Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(AccountActivity.TAG, "Successful on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;

        //Button logout;
        //Button search;
        //Button map;

        //logout = (Button) findViewById(R.id.logout);
        //search = (Button) findViewById(R.id.search);
        //map = (Button) findViewById(R.id.map);

        //logout = (Button) findViewById(R.id.logout);
        /*logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent go2Login = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(go2Login);
            }
        });*/
        /*search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent go2Search = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(go2Search);
            }
        });*/
       /*map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent go2Map = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(go2Map);
            }
        });*/

        //String name = getIntent().getStringExtra("Name");
        //TextView userName = (TextView) findViewById(R.id.insertUserName);
        //userName.setText(name);
        Log.d(AccountActivity.TAG, "Before file read");
        InputStream is = getResources().openRawResource(R.raw.homeless);
        readSDFile(is);
        //int x = Shelter.shelters.size();
        //shelterNames = new String[x];
        //Shelter.updateBedCounts();
        Log.d(AccountActivity.TAG, "After file read");
        mRecyclerView = (RecyclerView) findViewById(R.id.shelter_list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(shelterNames);
        mRecyclerView.setAdapter(mAdapter);
    }
    // create an action bar button
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            AccountActivity.super.onBackPressed();
        }
        if (id == R.id.search) {
            Intent go2Search = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(go2Search);
        }
        if (id == R.id.map2) {
            Intent go2Map = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(go2Map);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Reads homeless.csv document and populates the shelter arraylist
     *
     * @param is InputStream containing the homeless.csv file
     */
    public void readSDFile(InputStream is) {
        //SimpleModel model = SimpleModel.INSTANCE;

        try {
            //Open a stream on the raw file
                //InputStream is = getResources().openRawResource(R.raw.homeless);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                    StandardCharsets.UTF_8));

            String line;
            int cnt = 0;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                Log.d(AccountActivity.TAG, line);
                String[] tokens = line.split(",");
                //int id = Integer.parseInt(tokens[1]);
                shelterNames[cnt] = tokens[1];
                int key = Integer.parseInt(tokens[0]);
                int capacity;
                if (tokens[2].compareTo("")== 0) {
                    capacity = 0;
                } else {
                    capacity = Integer.parseInt(tokens[2]);
                }
                double longitude = Double.parseDouble(tokens[4]);
                double latitude = Double.parseDouble(tokens[5]);
                Shelter.shelters.add(new Shelter(tokens[1], key, capacity,
                        tokens[3],longitude, latitude, tokens[6]));
                Log.d(AccountActivity.TAG, "The shelter is " + Shelter.shelters.get(cnt));
                cnt++;
                //model.addItem(new DataItem(tokens[NAME_POSITION], tokens[2], id, tokens[3]));
            }
            br.close();
        } catch (IOException e) {
            //Log.e(MainActivity.TAG, "error reading assets", e);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView mTextView;

            public ViewHolder(View v) {
                super(v);
                Log.d(TAG, "Inside adapter, view holder.");
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                        Intent goToDetailed = new Intent(getApplicationContext(),
                                DetailedViewActivity.class);
                        goToDetailed.putExtra("Position", getAdapterPosition());
                        startActivity(goToDetailed);
                    }
                });
                mTextView = (TextView) v.findViewById(R.id.textView);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_shelters, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}