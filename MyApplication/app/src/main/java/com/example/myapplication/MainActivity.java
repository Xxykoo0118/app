package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    String[] mountainArray;
    ProgressBar progressBar;
    EditText  usernameView;
    TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativity_layout1);
        mountainArray= getResources().getStringArray(R.array.visit_place_array);
        initView();

    }
    private void initView(){
        RadioGroup rg =findViewById(R.id.place_label_rg);
        usernameView = findViewById(R.id.username);
        resultView =findViewById(R.id.result_view);
        progressBar = findViewById(R.id.progressBar);
        Button plusView = (Button)findViewById(R.id.puls_view);
        Button substractView = (Button) findViewById(R.id.substract_view);
        Button submitView = (Button) findViewById(R.id.submit_view);
        TextView placeLabelView =findViewById(R.id.place_label);
        EditText username = findViewById(R.id.username);
        CheckBox CB1 =(CheckBox)findViewById(R.id.like_shuttlecock);
        CheckBox CB2 =(CheckBox)findViewById(R.id.like_basketball);
        CheckBox CB3 =(CheckBox)findViewById(R.id.like_ping_pong);
        CheckBox CB4 =(CheckBox)findViewById(R.id.like_volleyball);
        CB1.setOnCheckedChangeListener(this);
        CB2.setOnCheckedChangeListener(this);
        CB3.setOnCheckedChangeListener(this);
        CB4.setOnCheckedChangeListener(this);
        placeLabelView.setText("请投票：");
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                int index = -1;
                if (i == R.id.place1) {
                    index = 0;
                } else if (i == R.id.place2) {
                    index = 1;
                } else if (i == R.id.place3) {
                    index = 2;
                } else if (i == R.id.place4) {
                    index = 3;
                }
                if (index >=0 && index < mountainArray.length){
                    showUIToast(mountainArray[index]);
                }

            }
        });
        //        plusView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                int id = view.getId();
//                switch (id){
//                    case R.id.puls_view:
//                    updateProgressView(true);
//                    break;
//                  /*  case R.id.submit_view:
//                    onSubmitConfirm();
//                    *//*onSubmit();*//*
//                    break;*/
//                    case R.id.substract_view:
//                    updateProgressView(false);
//                    break;
//                    default:
//                    break;
//                }
//            }
//        });
        //注释的地方和下面这一行冲突了，上面这样是不用进行依赖OnclickListener，下面这种是依赖了OnClickListener之后使用的
        //使用.setOnClickListerer
        plusView.setOnClickListener(this);//这里调用的Onclick方法
        substractView.setOnClickListener(this);
        submitView.setOnClickListener(this);
        Button button =findViewById(R.id.submit_view);
        button.setOnClickListener(this);


    }
    private void onSubmitConfirm(){
        AlertDialog dlg;
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setTitle("普通对话框")
                .setMessage("是否确认提交？")
                //添加“确定按钮”
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        onSubmit();
                        dialog.dismiss();

                    }
                })
                //添加“取消按钮”
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlg = builder.create();
        dlg.show();

    }

    private void updateProgressView(boolean plus) {
        int progress = progressBar.getProgress();
        if(plus){
            progress += 10;
            if(progress > 100){
                progress = 100;
            }
        }else {
            progress -= 10;
            if(progress < 0){
                progress = 0;
            }
        }
        progressBar.setProgress(progress);
    }
    @Override
    public void onClick(View view) {
        //这里有判断
        int id = view.getId();
        switch (id){
            case R.id.puls_view:
                updateProgressView(true);
                break;
            case R.id.submit_view:
               onSubmitConfirm();
               onSubmit();
                break;
                  /*  case R.id.submit_view:
                    onSubmitConfirm();
                    *//*onSubmit();*//*
                    break;*/
            case R.id.substract_view:
                updateProgressView(false);
                break;
            default:
                break;
        }
    }

    private void onSubmit() {
        StringBuffer sb=new StringBuffer();
        sb.append("666666666666666666");
        String username =usernameView.getText().toString();
        if (username !=null && username.isEmpty()){
            sb.append("\n by"+usernameView.getText().toString());
        }
        resultView.setText((sb.toString()));
    }

    private  void showUIToast(String info){
        Toast.makeText(MainActivity.this,"您投票的景点是："+info,
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
    }
    }
