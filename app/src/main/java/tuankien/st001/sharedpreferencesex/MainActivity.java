package tuankien.st001.sharedpreferencesex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences msharedPreferences;
    private TextView tv_num;
    private Button btn_black , btn_red, btn_blue , btn_yellow ,btn_count , btn_reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_num = this.findViewById(R.id.tv_num);
        btn_black = this.findViewById(R.id.btn_black);
        btn_red = this.findViewById(R.id.btn_red);
        btn_blue = this.findViewById(R.id.btn_blue);
        btn_yellow = this.findViewById(R.id.btn_yellow);
        btn_count  = this.findViewById(R.id.btn_count);
        btn_reset = this.findViewById(R.id.btn_reset);

        msharedPreferences = getSharedPreferences("Data",MODE_PRIVATE);
        tv_num.setText(msharedPreferences.getString("Num","0"));
        tv_num.setBackgroundColor(Color.parseColor(msharedPreferences.getString("Color","GRAY")));

        btn_black.setOnClickListener(view -> changeColor(tv_num,"BLACK"));
        btn_red.setOnClickListener(view -> changeColor(tv_num,"RED"));
        btn_blue.setOnClickListener(view -> changeColor(tv_num,"BLUE"));
        btn_yellow.setOnClickListener(view -> changeColor(tv_num,"YELLOW"));

        btn_count.setOnClickListener(view -> countClick(tv_num));

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_num.setText("0");
            tv_num.setBackgroundColor(Color.GRAY);
            }
        });

    }

    public void changeColor(TextView tv,String color) {
        tv.setBackgroundColor(Color.parseColor(color));

        SharedPreferences.Editor editor = msharedPreferences.edit();
        editor.putString("Color",color);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void countClick(TextView tv) {
        String presentnum = tv.getText().toString();
        int a = Integer.parseInt(presentnum);
        a++;
        tv.setText(String.valueOf(a));

        SharedPreferences.Editor editor = msharedPreferences.edit();
        editor.putString("Num",tv.getText().toString());
        editor.commit();
    }
}