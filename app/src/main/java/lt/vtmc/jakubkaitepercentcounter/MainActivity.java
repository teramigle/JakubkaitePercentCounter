package lt.vtmc.jakubkaitepercentcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView tipOutput;
    private TextView totalOutput;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        tipOutput = findViewById(R.id.tipOutput);
        totalOutput = findViewById(R.id.totalOutput);
        result = findViewById(R.id.result);

        inputText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(20,2)});

        SeekBar sk = (SeekBar) findViewById(R.id.seekBar);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView t=(TextView)findViewById(R.id.textView1);
                t.setText(String.valueOf(i)+'%');

                try {

                    double inputAmount = Double.parseDouble(inputText.getText().toString());
                    double tip = countTip(inputAmount, i);
                    tipOutput.setText(String.valueOf(tip));
                    double total = countTotal(inputAmount, tip);
                    totalOutput.setText(String.valueOf(total));
                    result.setText("Amount: " + String.valueOf(inputAmount) + "\nTip: " + String.valueOf(tip) + "\nTotal: " + String.valueOf(total));

                }catch (NumberFormatException ignored){}

            }

            public double countTip(double input, int percent){
                return Math.round(input * percent) / 100.0;
            }

            public double countTotal(double input, double tip){
                return Math.round((input - tip)* 100.0) / 100.0;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}