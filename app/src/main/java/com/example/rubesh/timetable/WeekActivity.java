package com.example.rubesh.timetable;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.rubesh.timetable.Utils.LetterImageView;

public class WeekActivity extends AppCompatActivity {



    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String Sel_day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        setupUIViews();
        initToolbar();
        setUpListview();
    }

    private void setupUIViews()
    {
         toolbar = (Toolbar)findViewById(R.id.ToolBarWeek);

         listView = (ListView)findViewById(R.id.LvWeek);
        /**
         * Get the sharedpreferences declared above
         * Mode_Private is used to Access Only From Myapp Not all apps or other apps
         */
        sharedPreferences = getSharedPreferences("My_day",MODE_PRIVATE);

    }

    private void initToolbar()

    {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");

        /**
         * this line is used to comeback homescreen
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setUpListview()

    {

        String [] week = getResources().getStringArray(R.array.Week);
        weekAdapter adapter = new weekAdapter(this,R.layout.activity_week_single_item,week);

        /**
         * Setting the adapter in listview
         */
        listView.setAdapter(adapter);
           /**
            * OnItemclick listner is
            */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    /**
                     * The following line used , whenever the Monday or Any day is clicked
                     * Its gonna edit the string and Put it .
                     * the apply method is used to put
                     */
                    case 0:
                        sharedPreferences.edit().putString(Sel_day,"Monday").apply();
                    break;

                    case 1 :
                        sharedPreferences.edit().putString(Sel_day,"Tuesday").apply();
                        break;
                    case 2 :
                        sharedPreferences.edit().putString(Sel_day,"Wednesday").apply();
                        break;
                    case 3 :
                        sharedPreferences.edit().putString(Sel_day,"Thursday").apply();
                        break;
                    case 4 : sharedPreferences.edit().putString(Sel_day,"Friday").apply();
                        break;
                    case 5:
                        sharedPreferences.edit().putString(Sel_day,"Saturday").apply();
                        break;
                    default: break;

                }
            }
        });
    }

    public class weekAdapter extends ArrayAdapter
    {

        private int resource;

        private LayoutInflater layoutInflater;
        String[] week = new String[] {};

        public weekAdapter( Context context, int resource, String[] objects) {
            super(context, resource,objects);

            this.resource = resource;
            this.week = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            /**
             * After using ViewHolder here ,
             * Using If else to Convertview
             */
          ViewHolder holder;

          if (convertView == null)
          {
              holder = new ViewHolder();
              convertView = layoutInflater.inflate(resource,null);

              holder.ivLetter = (LetterImageView)convertView.findViewById(R.id.ivLetter);
              holder.tvWeek = (TextView)convertView.findViewById(R.id.tvWeek);
              convertView.setTag(holder);
          }
          else
          {
            holder=  (ViewHolder)convertView.getTag();
          }

          /***
           * Using setOval to Enable the Oval imageView in App
           * Using setLetter at Position and CharAt(0) to take the First letter
           * Using setText to show the text at the position
            */
          holder.ivLetter.setOval(true);
          holder.ivLetter.setLetter(week[position].charAt(0));
          holder.tvWeek.setText(week[position]);
             return convertView;
        }

        /**
         * View holder class Created , and it used above.
         * assign the local variables used in ViewHolder
         */
        class ViewHolder
        {
            private LetterImageView ivLetter;
            private TextView tvWeek;
        }



    }

    /**
     * This Option used to come Back from the screen
     * to comeback the homescreen
     * The arrow is declared above
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case android.R.id.home:
            {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);

    }
}
