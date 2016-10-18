package nizami_13512501.UTS.LetterDetect;

import android.support.v4.util.Pair;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class IdealLetterMapTest {
    @Test
    public void testNumberMapTemplatesShouldBeRectangle(){
        for (Pair<IdealLetter,Character> p: IdealLetterMap.numberMap.listIdealLetter){
            System.out.println("testing " + p.second);
            boolean [][] template = p.first.template;
            int width = template.length;
            int height = template[0].length;
            for (int i=0;i<template.length;i++){
                Assert.assertEquals(height,template[i].length);
            }
        }
    }
}
