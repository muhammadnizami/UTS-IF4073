package nizami_13512501.UTS.LetterDetect;

import nizami_13512501.UTS.CropFrame;
import nizami_13512501.UTS.ExtendedBooleanMatrix;
import nizami_13512501.UTS.OrphanFunctions;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class IdealLetter {
    boolean [][] template;
    public IdealLetter(boolean [][] template){
        this.template = template;
    }

    public double cropAndCalculateError(boolean [][] letter){
        boolean [][] croppedLetter = cropToLetterOnly(letter);
        return error(croppedLetter);
    }

    public static boolean [][] cropToLetterOnly(boolean [][] letter){
        int min_x = letter.length-1;
        for (int y=0;y<letter[0].length;y++){
            for (int x=0;x<min_x;x++){
                if (letter[x][y])
                    min_x=x;
            }
        }
        int min_y = letter[0].length-1;
        for (int x=0;x<letter.length;x++){
            for (int y=0;y<min_y;y++){
                if (letter[x][y])
                    min_y=y;
            }
        }
        int max_x = 0;
        for (int y=0;y<letter[0].length;y++) {
            for (int x = letter.length - 1; x > max_x; x--) {
                if (letter[x][y])
                    max_x = x;
            }
        }
        int max_y = 0;
        for (int x=0;x<letter.length;x++) {
            for (int y = letter[0].length - 1; y > max_y; y--) {
                if (letter[x][y])
                    max_y = y;
            }
        }
        CropFrame cropFrame = new CropFrame(min_x,min_y,max_x-min_x+1,max_y-min_y+1);
        return cropFrame.crop(letter);
    }

    public double error(boolean [][] letter){
        IdealLetter scaledIdealLetter = this.scaleTo(letter);
        if (letter.length==0)
            return Integer.MAX_VALUE;
        return scaledIdealLetter.errorActualToIdeal(letter)
                + scaledIdealLetter.errorIdealToActual(letter);
    }

    public double errorActualToIdeal(boolean [][] actual){
        int numTrue = 0;
        long sumError = 0;
        for (int x=0;x<actual.length;x++){
            for (int y=0;y<actual[x].length;y++){
                if (actual[x][y]) {
                    sumError += distanceToNearestTrue(x, y);
                    numTrue++;
                }
            }
        }
        return sumError/numTrue;
    }

    public double errorIdealToActual(boolean [][] actual){
        //the logic is reversed
        IdealLetter actualLetter = new IdealLetter(actual);
        return actualLetter.errorActualToIdeal(template);
    }

    public int distanceToNearestTrue(int x, int y){
        ExtendedBooleanMatrix extendedBooleanMatrix = new ExtendedBooleanMatrix(template);
        int err;
        for (err=0;err<extendedBooleanMatrix.getWidth()+extendedBooleanMatrix.getHeight();err++){
            for (int xit=x-err;xit<=x+err;xit++){
                if (extendedBooleanMatrix.getPixel(xit,y+err) || extendedBooleanMatrix.getPixel(xit,y-err))
                    return err;
            }
            for (int yit=y-err;yit<=y+err;yit++){
                if (extendedBooleanMatrix.getPixel(x+err,yit) || extendedBooleanMatrix.getPixel(x-err,yit))
                    return err;
            }
        }
        return err;
    }

    public IdealLetter scaleTo(boolean [][] letter){
        int targetWidth = letter.length;
        int targetHeight = letter[0].length;
        return scaleTo(letter, targetWidth, targetHeight);
    }

    public IdealLetter scaleTo(boolean [][] letter, int targetWidth, int targetHeight){
        int currentWidth = template.length;
        int currentHeight = template[0].length;
        boolean [][] newTemplate = new boolean[targetWidth][targetHeight];
        for (int x=0;x<targetWidth;x++){
            for (int y=0;y<targetHeight;y++){
                newTemplate[x][y]=template[x*currentWidth/targetWidth][y*currentHeight/targetHeight];
            }
        }
        return new IdealLetter(newTemplate);
    }

    public double thisSizeComparedTo(boolean [][] letter){
        int targetWidth = letter.length;
        int targetHeight = letter[0].length;
        int currentWidth = template.length;
        int currentHeight = template[0].length;
        return currentHeight*currentWidth/(targetWidth*targetHeight);
    }

    public String toString(){
        String ret="";
        for (int y=0;y<template[0].length;y++){
            for (int x=0;x<template.length;x++){
                if (template[x][y])
                    ret+='1';
                else
                    ret+=' ';
            }
            ret+='\n';
        }
        return ret;
    }

}
