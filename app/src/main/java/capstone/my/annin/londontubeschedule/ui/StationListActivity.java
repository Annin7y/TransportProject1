package capstone.my.annin.londontubeschedule.ui;

import capstone.my.annin.londontubeschedule.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import capstone.my.annin.londontubeschedule.model.Lines;

public class StationListActivity extends AppCompatActivity
{
    //Tag for the log messages
    private static final String TAG = StationListActivity.class.getSimpleName();

    Lines lines;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);


    }
}
