package nizami_13512501.UTS;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    Button buttonAmbilGambar;
    ImageView imageView;
    Bitmap gambar;
    Bitmap gambarPreview;
    CheckBox checkBoxNoiseRemoval;
    CheckBox checkBoxSkeletonization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonAmbilGambar =(Button) findViewById(R.id.buttonAmbilGambar);
        buttonAmbilGambar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

        checkBoxNoiseRemoval = (CheckBox) findViewById(R.id.checkBoxNoiseRemoval);
        checkBoxSkeletonization = (CheckBox) findViewById(R.id.checkBoxSkeletonize);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if( requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null)
        {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), pickedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Do something with the bitmap

            updateGambar(bitmap);
        }
        else
        {
            Toast.makeText(MainActivity.this, "Picture Not taken", Toast.LENGTH_LONG);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateGambar(Bitmap gambar){
        this.gambar = gambar;
        imageView.setImageBitmap(gambar);
    }

    private void previewGambarFilter(Bitmap gambarFilter){
        this.gambarPreview = gambarFilter;
        imageView.setImageBitmap(gambarFilter);
    }

    private void applyGambarFilter(){
        this.gambar = this.gambarPreview;
    }

    private float[] getFloatArrayConstants(int resId){
        float [] result = new float[9];
        TypedArray ta = getResources().obtainTypedArray(resId);
        for (int i =0; i< 9; i++){
            result[i]=ta.getFloat(i,0);
        }
        return result;
    }

    private class OperasiMatriksKonvolusi extends AsyncTask<String, Void, String> {

        public OperasiMatriksKonvolusi(MatriksKonvolusi matriksKonvolusi, Bitmap input, boolean noiseRemoval, boolean skeletonization){
            super();
            this.matriksKonvolusi = matriksKonvolusi;
            this.input = input;
            this.noiseRemoval = noiseRemoval;
            this.skeletonization = skeletonization;
            TextView textViewProgress = (TextView) findViewById(R.id.textViewProcessing);
            textViewProgress.setVisibility(View.VISIBLE);
        }

        Bitmap input;
        MatriksKonvolusi matriksKonvolusi;
        boolean noiseRemoval;
        boolean skeletonization;

        Bitmap hasil = null;

        @Override
        protected String doInBackground(String... params) {
            System.out.println("mulai");
            int [][] grayLevel = OrphanFunctions.grayLevel(input);
            int [][] grayLevelBorder = matriksKonvolusi.eksekusi(grayLevel);
            boolean [][] edges = OrphanFunctions.createBooleanMatrixFromGrayLevel(grayLevelBorder,Otsu.otsu(grayLevelBorder));
            if (noiseRemoval) {
                (new NoiseReduction(5)).attemptNoiseRemove(edges);
            }
            if (skeletonization){
                HilditchSkeletonization.skeletonize(edges);
            }
            hasil = OrphanFunctions.createBitmapFromBooleanMatrix(edges);
            System.out.println("eksekusi done");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            previewGambarFilter(hasil);
            TextView textViewProgress = (TextView) findViewById(R.id.textViewProcessing);
            textViewProgress.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    OperasiMatriksKonvolusi operasiMatriksKonvolusi;

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        float [] floatConstants = null;
        MatriksKonvolusi matriksKonvolusi = null;

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.radioButtonDifference:
                if (checked)
                    matriksKonvolusi = new OperatorDifference();
                break;

            case R.id.radioButtonHomogen:
                if (checked)
                    matriksKonvolusi = new OperatorHomogen();
                break;

            case R.id.radioButtonPrewitt8:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.prewitt8);
                    matriksKonvolusi = new EdgeDerajat2(floatConstants);
                break;

            case R.id.radioButtonRobert:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.robert);
                matriksKonvolusi = new EdgeDerajat1(floatConstants);
                break;

            case R.id.radioButtonPrewitt:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.prewitt);
                matriksKonvolusi = new EdgeDerajat1(floatConstants);
                break;

            case R.id.radioButtonSobel:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.sobel);
                matriksKonvolusi = new EdgeDerajat1(floatConstants);
                break;

            case R.id.radioButtonFreiChi:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.freichi);
                matriksKonvolusi = new EdgeDerajat1(floatConstants);
                break;

            case R.id.radioButtonKirch:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.kirch);
                matriksKonvolusi = new EdgeDerajat2(floatConstants);
                break;

            case R.id.radioButtonRobinson3Level:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.robinson3level);
                matriksKonvolusi = new EdgeDerajat2(floatConstants);
                break;

            case R.id.radioButtonRobinson5Level:
                if (checked)
                    floatConstants = getFloatArrayConstants(R.array.robinson5level);
                matriksKonvolusi = new EdgeDerajat2(floatConstants);
                break;
        }

        if (gambar != null && matriksKonvolusi != null) {
            operasiMatriksKonvolusi = (OperasiMatriksKonvolusi) new OperasiMatriksKonvolusi(matriksKonvolusi, gambar, checkBoxNoiseRemoval.isChecked(), checkBoxSkeletonization.isChecked()).execute("");
            System.out.println(operasiMatriksKonvolusi.getStatus());
        }

    }

    public void onButtonDeteksiGambarClicked(View view){
        if (gambar != null){
            int [][] grayLevel = OrphanFunctions.grayLevel(gambar);
            boolean [][] booleen = OrphanFunctions.createBooleanMatrixFromGrayLevel(grayLevel,128);
            System.out.println("scanning...");
            Integer[] chainCode = EdgeExplorer.getChainCode(booleen,booleen.length/2,0,EdgeExplorer.DIRECTION_SOUTH);
            String message=" chain code: ";
            for (int i=0;i<chainCode.length;i++){
                message+=chainCode[i];
            }
            message+="\nbentuk: " + ShapeDetect.getNamaBentuk(chainCode);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            System.out.print("this is the chain code!");
            for (int i=0;i<chainCode.length;i++){
                System.out.print(""+chainCode[i]+",");
            }
            System.out.println();

        }
    }

}
