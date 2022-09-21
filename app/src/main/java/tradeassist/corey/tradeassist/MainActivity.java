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

    private TextView    txtMaxShareSizeValue;
    private TextView    txtProfitTarget;
    private TextView    txtStopLoss;

    private EditText    edtTotalCapital;
    private EditText    edtStockPrice;
    private EditText    edtMaxLoss;


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
        // TextViews
        txtMaxShareSizeValue    = findViewById(R.id.txtSharesValue);
        txtProfitTarget         = findViewById(R.id.txtProfitTargetValue);
        txtStopLoss             = findViewById(R.id.txtStopLossValue);

        // EditText
        edtTotalCapital         = findViewById(R.id.edtTotalCapital);
        edtStockPrice           = findViewById(R.id.edtStockPrice);
        edtMaxLoss              = findViewById(R.id.edtMaxLoss);
    }

    public void onCrunch(View view) {
        float totalCapital  = safeParseFloat(edtTotalCapital.getText().toString(), 0.0f);
        float stockPrice    = safeParseFloat(edtStockPrice.getText().toString(), 0.0f);
        float maxLoss       = safeParseFloat(edtMaxLoss.getText().toString(), 1.0f)/100.0f;
//        float profitTarget  = safeParseFloat(txtProfitTarget.getText().toString(), 0.0f);
//        float stopLoss      = safeParseFloat(txtStopLoss.getText().toString(), 0.0f);

        int maxShareSize    = (int) (totalCapital/stockPrice);

        float profitTarget = ((maxLoss*2.0f)*totalCapital)/maxShareSize;
        float stopLoss = (maxLoss*totalCapital)/maxShareSize;

        txtMaxShareSizeValue.setText(String.valueOf(maxShareSize));
        txtProfitTarget.setText(String.format(Locale.getDefault(),"%.2f", profitTarget));
        txtStopLoss.setText(String.format(Locale.getDefault(), "%.2f", stopLoss));


        if(stopLoss < 0.1f) {

        }


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

