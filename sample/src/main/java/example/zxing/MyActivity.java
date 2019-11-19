package example.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.TooManyListenersException;

public class MyActivity extends AppCompatActivity {

    public final int CUSTOMIZED_REQUEST_CODE = 0x0000ffff;
    private final String TAG = "MyActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_layout);
    }

    public void scanBarcode(View view) {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        switch (requestCode) {
            case CUSTOMIZED_REQUEST_CODE: {
                Toast.makeText(this, "REQUEST_CODE = " + requestCode,
                        Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;
        }

        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if (result.getContents() == null) {
            Log.d(TAG, "Cancelled scan");
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        } else {
            Log.d(TAG, "start Scanned");
            Toast.makeText(this, "Scaned: " + result.getContents(), Toast.LENGTH_LONG).show();
        }
    }
}
