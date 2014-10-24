package org.dongyf.listview01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.dongyf.listview01.biz.XMLHandler;
import org.dongyf.listview01.entity.BusStatus;

import java.util.List;

public class MyActivity extends Activity implements View.OnClickListener
{
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(this);

        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new MyDataAdapter());
    }

    @Override
    public void onClick(View v)
    {

    }

    public class MyDataAdapter extends BaseAdapter{

        List<BusStatus> busStatusList = XMLHandler.parserXml();
        @Override
        public int getCount()
        {
            return busStatusList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            BusStatus busStatus = busStatusList.get(position);
            View view = View.inflate(MyActivity.this,R.layout.list_item,null);
            TextView tv_stationName = (TextView) view.findViewById(R.id.x_stationName);
            TextView tv_carCode = (TextView)view.findViewById(R.id.x_carCode);
            TextView tv_arrivalTime = (TextView) view.findViewById(R.id.x_arrivalTime);
            tv_stationName.setText("站台："+busStatus.getStationName());
            tv_stationName.setTextSize(16);
            tv_carCode.setText("车牌："+busStatus.getCarCode());
            tv_arrivalTime.setText("到达时间："+busStatus.getArrivalTime());
            return view;
        }
    }
}
