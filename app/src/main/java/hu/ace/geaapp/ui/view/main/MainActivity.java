package hu.ace.geaapp.ui.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.utils.UIConstans;

public class MainActivity extends AppCompatActivity {




    @BindView(R.id.cardview_inspection)
    CardView card_inspection;
    @BindView(R.id.cardview_transfer)
    CardView card_cartransfer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.cardview_inspection)
    public void onClickonInspectionCard(View view){
       // System.out.println(" OnClick on SZEMLE -> ["+view+"; "+view.getContext()+"]");
        //Intent intent = new Intent(this, InspectionMainActivity.class);
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("ACTIVITY_FLAG", UIConstans.ACTIVITY_SZEMLE);
        startActivity(intent);

    }

    @OnClick(R.id.cardview_transfer)
    public void onClickonCarTransferCard(View view){
       // System.out.println(" OnClick on ÁTADÁS/ÁTVÉTEL -> ["+view+"; "+view.getContext()+"]");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("ACTIVITY_FLAG", UIConstans.ACTIVITY_LINE);
        startActivity(intent);

    }
}
