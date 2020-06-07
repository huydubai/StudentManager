package com.example.studentmanager;

import java.util.ArrayList;

import student.student;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

    ArrayList<student> listsv=new ArrayList<student>();

    EditText et1,et2;
    Button btn_them,btn_xoa,btn_sua;
    TextView tv,tv_sl;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText) findViewById(R.id.editText1);
        et2=(EditText) findViewById(R.id.editText2);

        tv = (TextView) findViewById(R.id.textView3);
        tv_sl = (TextView) findViewById(R.id.textView_soluong);
        tv_sl.setTextSize(25);

        btn_them=(Button) findViewById(R.id.button1);
        btn_xoa=(Button) findViewById(R.id.button2);
        btn_sua=(Button) findViewById(R.id.button3);

        lv=(ListView) findViewById(R.id.listView1);
        student.soluong=0;


        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub

                student sv_click= (student) arg0.getItemAtPosition(arg2);
                et1.setText(sv_click.getStudent_id()+"");
                et2.setText(sv_click.getStudent_name());
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try {
                    tv.setText("");

                    int id_sinhvien		=	Integer.parseInt(et1.getText().toString());
                    String ten_sinhvien	=	et2.getText().toString();

                    if(findStudentById(id_sinhvien, listsv) == null)
                    {
                        student sv=new student();
                        student.soluong++;
                        sv.setStudent_id(id_sinhvien);
                        sv.setStudent_name(ten_sinhvien);

                        listsv.add(sv);
                    }
                    else
                        tv.setText("Mã số sinh viên đã tồn tại!!!");

                    showListView(listsv);
                } catch (NumberFormatException e) {
                    // TODO: handle exception
                    tv.setText(e.toString());
                }
            }

        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try {
                    tv.setText("");
                    int id_sinhvien		=	Integer.parseInt(et1.getText().toString());

                    student sv 	= findStudentById(id_sinhvien, listsv);

                    if(sv != null)
                    {
                        listsv.remove(sv);
                        student.soluong--;
                    }


                    showListView(listsv);
                } catch (NumberFormatException e) {
                    // TODO: handle exception
                    tv.setText(e.toString());
                }
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try {
                    tv.setText("");

                    int id_sinhvien		=	Integer.parseInt(et1.getText().toString());
                    String ten_sinhvien	=	et2.getText().toString();

                    student sv 	= findStudentById(id_sinhvien, listsv);

                    if(sv != null)
                    {
                        int index = listsv.indexOf(sv);
                        sv.setStudent_name(ten_sinhvien);

                        listsv.set(index, sv);

                        showListView(listsv);
                    }
                } catch (NumberFormatException e) {
                    // TODO: handle exception
                    tv.setText(e.toString());
                }
            }
        });


    }
    public void showListView(ArrayList<student> listsv) {
        tv_sl.setText("Số lượng : "+student.soluong);

        //Gán dữ liệu từ ArrayList vào ListView
        ArrayAdapter<student> array_adt=new ArrayAdapter<student>(getApplicationContext(), android.R.layout.simple_list_item_1 , listsv);

        lv.setAdapter(array_adt);
    }
    public student findStudentById(int id, @org.jetbrains.annotations.NotNull ArrayList<student> list_student)
    {
        for (student std : list_student) {
            if(std.getStudent_id()==id)
                return std;
        }
        return null;
    }
}
