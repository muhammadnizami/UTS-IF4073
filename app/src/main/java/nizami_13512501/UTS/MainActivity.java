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

import nizami_13512501.UTS.LetterDetect.IdealLetter;
import nizami_13512501.UTS.LetterDetect.IdealLetterMap;
import nizami_13512501.UTS.LetterDetect.LetterDetectorMap;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    Button buttonAmbilGambar;
    ImageView imageView;
    Bitmap gambar;
    Bitmap gambarPreview;
    CheckBox checkBoxNoiseRemoval;
    CheckBox checkBoxSkeletonization;
    CheckBox checkBoxSmooth;

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
        checkBoxSmooth = (CheckBox) findViewById(R.id.checkBoxSmooth);
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

    private class OperasiDetectGambar extends AsyncTask<String, Void, String>{

        public OperasiDetectGambar(Bitmap input){
            this.input = input;
            TextView textViewProgress = (TextView) findViewById(R.id.textViewProcessing);
            textViewProgress.setVisibility(View.VISIBLE);
        }

        Bitmap input;
        Bitmap hasil;
        char letter;
        char number;

        @Override
        protected String doInBackground(String... params) {
            int [][] grayLevel = OrphanFunctions.grayLevel(input);
            MatriksKonvolusi matriksKonvolusi = new MatriksKonvolusi(getFloatArrayConstants(R.array.boxblurkernel));
            grayLevel = matriksKonvolusi.eksekusi(grayLevel);
            boolean [][] booleanImage = OrphanFunctions.createBooleanMatrixFromGrayLevelReverse(grayLevel,Otsu.otsu(grayLevel));
            (new NoiseReduction(10)).attemptNoiseRemove(booleanImage);
            HilditchSkeletonization.skeletonize(booleanImage);
            hasil = OrphanFunctions.createBitmapFromBooleanMatrix(booleanImage);
            letter = IdealLetterMap.capitalMap.detect(booleanImage);
            number = IdealLetterMap.numberMap.detect(booleanImage);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            previewGambarFilter(hasil);
            Toast.makeText(getApplicationContext(),"Letter: "+letter+"\nNumber: "+number,Toast.LENGTH_SHORT).show();
            TextView textViewProgress = (TextView) findViewById(R.id.textViewProcessing);
            textViewProgress.setVisibility(View.INVISIBLE);
        }
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

    public void onButtonDeteksiGambarClicked(View view){
        if (gambar != null){
            OperasiDetectGambar operasiDetectGambar = (OperasiDetectGambar) new OperasiDetectGambar(gambar).execute();
            System.out.println(operasiDetectGambar.getStatus());
        }
    }

}
