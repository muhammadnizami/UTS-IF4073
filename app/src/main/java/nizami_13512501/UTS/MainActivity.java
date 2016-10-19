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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import nizami_13512501.UTS.LetterDetect.DetectSpace;
import nizami_13512501.UTS.LetterDetect.IdealLetterMap;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    Button buttonAmbilGambar;
    ImageView imageView1;
    ImageView imageView2;
    Bitmap gambar;
    Bitmap gambarPreview;
    TextView textViewProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

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

        textViewProgress = (TextView) findViewById(R.id.textViewProcessing);
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
        imageView1.setImageBitmap(gambar);
    }

    private void previewGambarFilter(Bitmap gambarFilter){
        this.gambarPreview = gambarFilter;
        imageView1.setImageBitmap(gambarFilter);
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

    private class OperasiDetectGambarProgress{
        private Bitmap gambar1;
        private Bitmap gambar2;
        private String progressString;
        public OperasiDetectGambarProgress(){
            setGambar1(OrphanFunctions.createBitmapFromGrayLevel(new int[][]{{255}}));
            setGambar2(OrphanFunctions.createBitmapFromGrayLevel(new int[][]{{255}}));
            setProgressString("processing...");
        }

        boolean gambar1Updated;
        boolean gambar2Updated;
        boolean progressStringUpdated;
        public void displayUpdate(){
            if (gambar1Updated) {
                imageView1.setImageBitmap(gambar1);
                gambar1Updated = false;
            }
            if (gambar2Updated) {
                imageView2.setImageBitmap(gambar2);
                gambar2Updated = false;
            }
            if (progressStringUpdated) {
                textViewProgress.setText(progressString);
                progressStringUpdated = false;
            }

        }

        public void setGambar1(Bitmap gambar1) {
            this.gambar1 = gambar1;
            gambar1Updated = true;
        }

        public void setGambar2(Bitmap gambar2) {
            this.gambar2 = gambar2;
            gambar2Updated = true;
        }

        public void setProgressString(String progressString) {
            this.progressString = progressString;
            progressStringUpdated = true;
        }

        public void setGambar1(boolean [][] booleen){
            setGambar1(OrphanFunctions.createBitmapFromBooleanMatrix(booleen));
        }

        public void setGambar1(int [][] grayLevel){
            setGambar1(OrphanFunctions.createBitmapFromGrayLevel(grayLevel));
        }

        public void setGambar2(boolean [][] booleen){
            setGambar2(OrphanFunctions.createBitmapFromBooleanMatrix(booleen));
        }

        public void setGambar2(int [][] grayLevel){
            setGambar2(OrphanFunctions.createBitmapFromGrayLevel(grayLevel));
        }
    }

    private class OperasiDetectGambar extends AsyncTask<String, Void, String>{

        public OperasiDetectGambar(Bitmap input){
            this.input = input;
            TextView textViewProgress = (TextView) findViewById(R.id.textViewProcessing);
            textViewProgress.setVisibility(View.VISIBLE);
            progress = new OperasiDetectGambarProgress();
        }

        Bitmap input;
        MatriksKonvolusi matriksKonvolusiBoxBlur = new MatriksKonvolusi(getFloatArrayConstants(R.array.boxblurkernel));
        int [][] oriGrayLevel;
        int [][] contentGrayLevel;
        boolean [][] oriBooleanMatrix;
        boolean [][] contentBooleanMatrix;
        CropFrame contentCropFrame;

        String nama;
        String NIM;
        String alamat;

        OperasiDetectGambarProgress progress;

        final NoiseReduction noiseReduction = new NoiseReduction(6);

        @Override
        protected String doInBackground(String... params) {
            //find the content image, crop the image
            oriGrayLevel = OrphanFunctions.grayLevel(input);
            progress.setGambar1(oriGrayLevel);publishProgress();
            oriBooleanMatrix = OrphanFunctions.createBooleanMatrixFromGrayLevelReverse(oriGrayLevel,Otsu.otsu(oriGrayLevel));
            progress.setGambar1(oriBooleanMatrix);publishProgress();
            noiseReduction.attemptNoiseRemove(oriBooleanMatrix);
            progress.setGambar1(oriBooleanMatrix);publishProgress();
            contentCropFrame = CropFrame.ImageToNotBlank(oriBooleanMatrix);
            contentBooleanMatrix = contentCropFrame.crop(oriBooleanMatrix);
            progress.setGambar1(contentBooleanMatrix);publishProgress();
            contentGrayLevel = contentCropFrame.crop(oriGrayLevel);
            progress.setGambar1(contentGrayLevel);publishProgress();

            readNama();
            readNIM();
            readAlamat();

            return "Executed";
        }



        final double squareWidthToContentWidthRatio = 67.0/640;
        final double squareHeightToContentWidthRatio = 62.0/640;
        final double namaHeightToContentWidthRatio = 412.5/640;
        final double NIMHeightToContentWidthRatio = 274.0/640;
        final double alamatHeightToContentWidthRatio = 124.0/640;
        final double textStartXToContentWidthRatio = 100.5/640;
        final int squareMargin = 4;
        public CropFrame createCharCropFrame(int x, int y, int width, int height){
            CropFrame cropFrame = new CropFrame(x+squareMargin,
                    y+squareMargin,width-squareMargin*2,height-squareMargin*2);
            return cropFrame;
        }
        public void readNama(){
            int startX = (int) (textStartXToContentWidthRatio*contentCropFrame.getWidth());
            int startY = contentCropFrame.getHeight()-(int) (namaHeightToContentWidthRatio*contentCropFrame.getWidth());
            int width = (int) (contentCropFrame.getWidth()*squareWidthToContentWidthRatio);
            int height = (int) (contentCropFrame.getWidth()*squareHeightToContentWidthRatio);
            CropFrame cropFrame = createCharCropFrame(startX,startY,width,height);
            nama = "";

            for (int i=0;i<2;i++){
                for (int j=0;j<8;j++){
                    char letter = readLetter(contentGrayLevel,cropFrame);
                    nama+=letter;
                    progress.setProgressString("nama: " + nama);publishProgress();
                    cropFrame.shiftX(width);
                }
                cropFrame.shiftX(-8*width);
                cropFrame.shiftY(height);
            }
        }

        public void readNIM(){
            int startX = (int) (textStartXToContentWidthRatio*contentCropFrame.getWidth());
            int startY = contentCropFrame.getHeight()-(int) (NIMHeightToContentWidthRatio*contentCropFrame.getWidth());
            int width = (int) (contentCropFrame.getWidth()*squareWidthToContentWidthRatio);
            int height = (int) (contentCropFrame.getWidth()*squareHeightToContentWidthRatio);
            CropFrame cropFrame = createCharCropFrame(startX,startY,width,height);
            NIM = "";

            for (int i=0;i<2;i++){
                for (int j=0;j<8;j++){
                    char letter = readNumber(contentGrayLevel,cropFrame);
                    NIM +=letter;
                    progress.setProgressString("NIM: " + NIM);publishProgress();
                    cropFrame.shiftX(width);
                }
                cropFrame.shiftX(-8*width);
                cropFrame.shiftY(height);
            }
        }

        public void readAlamat(){
            int startX = (int) (textStartXToContentWidthRatio*contentCropFrame.getWidth());
            int startY = contentCropFrame.getHeight()-(int) (alamatHeightToContentWidthRatio*contentCropFrame.getWidth());
            int width = (int) (contentCropFrame.getWidth()*squareWidthToContentWidthRatio);
            int height = (int) (contentCropFrame.getWidth()*squareHeightToContentWidthRatio);
            CropFrame cropFrame = createCharCropFrame(startX,startY,width,height);
            alamat = "";

            for (int i=0;i<2;i++){
                for (int j=0;j<8;j++){
                    char letter = readLetterOrNumber(contentGrayLevel,cropFrame);
                    alamat+=letter;
                    progress.setProgressString("Alamat: " + alamat);publishProgress();
                    cropFrame.shiftX(width);
                }
                cropFrame.shiftX(-8*width);
                cropFrame.shiftY(height);
            }

        }

        public char readLetterOrNumber(int [][] grayLevel, CropFrame location){
            return read(grayLevel,location,IdealLetterMap.numberAndCapitalMap);
        }

        public char readLetter(int [][] grayLevel, CropFrame location){
            return read(grayLevel,location,IdealLetterMap.capitalMap);
        }

        public char readNumber(int [][] grayLevel, CropFrame location){
            return read(grayLevel,location,IdealLetterMap.numberMap);
        }

        public char read(int [][] grayLevel, CropFrame location, IdealLetterMap idealLetterMap){
            int [][] characterGrayLevel = location.crop(grayLevel);
            progress.setGambar2(characterGrayLevel);publishProgress();
            characterGrayLevel = matriksKonvolusiBoxBlur.eksekusi(characterGrayLevel);
            progress.setGambar2(characterGrayLevel);publishProgress();
            boolean [][] characterBoolean = OrphanFunctions.createBooleanMatrixFromGrayLevelReverse(characterGrayLevel,
                    Otsu.otsu(characterGrayLevel));
            progress.setGambar2(characterBoolean);publishProgress();
            HilditchSkeletonization.skeletonize(characterBoolean);
            progress.setGambar2(characterBoolean);publishProgress();
            characterBoolean = CropFrame.cropImageToNotBlank(characterBoolean);
            progress.setGambar2(characterBoolean);publishProgress();
            if (DetectSpace.isSpace(characterBoolean, 16))
                return ' ';
            else return idealLetterMap.detect(characterBoolean);
        }

        protected char charDetect(boolean [][] image, IdealLetterMap idealLetterMap){
            HilditchSkeletonization.skeletonize(image);
            return idealLetterMap.detect(image);
        }

        protected void calculateTextDimensions(){

        }

        @Override
        protected void onProgressUpdate(Void... progress){
            this.progress.displayUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            textViewProgress.setText("nama: " + nama + "\nNIM:" + NIM + "\nAlamat: " + alamat);
            imageView2.setImageBitmap(OrphanFunctions.createBitmapFromBooleanMatrix(new boolean[][]{{true}}));
        }
    }

    public void onButtonDeteksiGambarClicked(View view){
        if (gambar != null){
            OperasiDetectGambar operasiDetectGambar = (OperasiDetectGambar) new OperasiDetectGambar(gambar).execute();
        }
    }

}
