package tradeassist.corey.tradeassist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final DecimalFormat formatter = (DecimalFormat)DecimalFormat.getInstance(Locale.getDefault());

    private int         shareSize;
    private float       profitTarget;
    private float       stopLoss;

    private EditText    edtTotalCapital;
    private EditText    edtStockPrice;
    private EditText    edtMaxLoss;
    private EditText    edtMaxShareSizeValue;
    private EditText    edtProfitTarget;
    private EditText    edtStopLoss;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGui();
    }

    /**
     * This function provides the initialization of the graphical elements of this activity.
     */
    private void initGui() {

        // EditText
        edtTotalCapital         = findViewById(R.id.edtTotalCapital);
        edtStockPrice           = findViewById(R.id.edtStockPrice);
        edtMaxLoss              = findViewById(R.id.edtMaxLoss);
        edtMaxShareSizeValue    = findViewById(R.id.edtMaxShareSize);
        edtProfitTarget         = findViewById(R.id.edtProfitTarget);
        edtStopLoss             = findViewById(R.id.edtStopLoss);
    }

    public void onCrunch(View view) {

        float totalCapital  = safeParseFloat(edtTotalCapital.getText().toString(), 0.0f);
        float stockPrice    = safeParseFloat(edtStockPrice.getText().toString(), 0.0f);
        float maxLoss       = safeParseFloat(edtMaxLoss.getText().toString(), 1.0f)/100.0f;
//        float profitTarget  = safeParseFloat(edtProfitTarget.getText().toString(), 0.0f);
//        float stopLoss      = safeParseFloat(edtStopLoss.getText().toString(), 0.0f);

        int maxShareSize    = (int) (totalCapital/stockPrice);

        float profitTarget = ((maxLoss*2.0f)*totalCapital)/maxShareSize;
        float stopLoss = (maxLoss*totalCapital)/maxShareSize;

        this.shareSize      = maxShareSize;
        this.profitTarget   = profitTarget;
        this.stopLoss       = stopLoss;

        edtMaxShareSizeValue.setText(String.valueOf(maxShareSize));
        edtProfitTarget.setText(String.format(Locale.getDefault(),"%.2f", profitTarget));
        edtStopLoss.setText(String.format(Locale.getDefault(), "%.2f", stopLoss));

    }

    public void onUpdate(View view) {
        float totalCapital  = safeParseFloat(edtTotalCapital.getText().toString(), 0.0f);
        float stockPrice    = safeParseFloat(edtStockPrice.getText().toString(), 0.0f);
        float maxLoss       = safeParseFloat(edtMaxLoss.getText().toString(), 1.0f)/100.0f;
        float profitTarget  = safeParseFloat(edtProfitTarget.getText().toString(), 0.0f);
        float stopLoss      = safeParseFloat(edtStopLoss.getText().toString(), 0.0f);
        int maxShareSize    = safeParseInt(edtMaxShareSizeValue.getText().toString(), 0);





        if(this.shareSize != maxShareSize) {
            shareSize = maxShareSize;

            profitTarget = ((maxLoss*2.0f)*totalCapital)/maxShareSize;
            stopLoss = (maxLoss*totalCapital)/maxShareSize;
        }


        edtMaxShareSizeValue.setText(String.valueOf(maxShareSize));
        edtProfitTarget.setText(String.format(Locale.getDefault(),"%.2f", profitTarget));
        edtStopLoss.setText(String.format(Locale.getDefault(), "%.2f", stopLoss));


    }

    private float safeParseFloat(String source, float defaultValue) {
        float   result = defaultValue;
        Number  number;

        try {
            number = formatter.parse(source);
            result = number.floatValue();
        } catch (Exception c) { System.err.println(); }

        return result;
    }

    private int safeParseInt(String source, int defaultValue) {
        int   result = defaultValue;
        Number  number;

        try {
            number = formatter.parse(source);
            result = number.intValue();
        } catch (Exception c) { System.err.println(); }

        return result;
    }

}

