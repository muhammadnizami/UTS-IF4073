package nizami_13512501.UTS;

import android.support.v4.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by nim_13512501 on 17/10/16.
 */
public class NoiseReduction {
    int thresholdSize;
    public NoiseReduction(int thresholdSize){
        this.thresholdSize = thresholdSize;
    }

    public class Island{
        int start_x;
        int start_y;
        int size;
        boolean value;
        boolean [][] fromImage;
        public Island(int start_x, int start_y, int size, boolean value, boolean [][] fromImage){
            this.start_x = start_x;
            this.start_y = start_y;
            this.size = size;
            this.value = value;
            this.fromImage = fromImage;
        }

        public void negateValue(){
            Queue<Pair<Integer, Integer>> searchQueue = new LinkedBlockingQueue<>();
            Pair<Integer, Integer> startPoint = new Pair<>(start_x, start_y);
            searchQueue.add(startPoint);
            while (!searchQueue.isEmpty()){
                Pair<Integer, Integer> currentPoint = searchQueue.remove();
                fromImage[currentPoint.first][currentPoint.second]=!value;
                for (int [] neighbordelta : neighborsdelta){
                    int new_x = currentPoint.first+neighbordelta[0];
                    int new_y = currentPoint.second+neighbordelta[1];
                    if (new_x>=0 && new_y >= 0 && new_x < fromImage.length && new_y < fromImage[new_x].length)
                    if (fromImage[new_x][new_y]==value) {
                        searchQueue.add(new Pair<>(new_x, new_y));
                    }
                }
            }
            value = !value;
        }
    }

    public void attemptNoiseRemove(boolean[][] image){
        List<Island> islands = islands(image);
        for (Island island : islands){
            if (island.size<thresholdSize){
                island.negateValue();
            }
        }
    }

    public List<Island> islands(boolean[][] image){
        List<Island> returnValue = new LinkedList<>();
        boolean [][] visited = new boolean[image.length][];
        for (int x=0;x<image.length;x++){
            visited[x] = new boolean[image[x].length];
            for (int y=0;y<image[x].length;y++){
                visited[x][y]=false;
            }
        }
        for (int x=0;x<image.length;x++){
            for (int y=0;y<image[x].length;y++){
                if (!visited[x][y]){
                    returnValue.add(createIsland(image,visited,x,y));
                }
            }
        }
        return returnValue;
    }

    private final int[][] neighborsdelta = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    private Island createIsland(boolean[][] image, boolean[][] visited, int start_x, int start_y){
        Queue<Pair<Integer, Integer>> searchQueue = new LinkedBlockingQueue<>();
        boolean value = image[start_x][start_y];
        visited[start_x][start_y]=true;
        Pair<Integer, Integer> startPoint = new Pair<>(new Integer(start_x),new Integer(start_y));
        searchQueue.add(startPoint);
        int size = 0;
        while (!searchQueue.isEmpty()){
            Pair<Integer, Integer> currentPoint = searchQueue.remove();
            size++;
            for (int [] neighbordelta : neighborsdelta){
                int new_x = currentPoint.first+neighbordelta[0];
                int new_y = currentPoint.second+neighbordelta[1];
                if (new_x>=0 && new_y >= 0 && new_x < image.length && new_y < image[new_x].length)
                if (image[new_x][new_y]==value)
                if (!visited[new_x][new_y]){
                    visited[new_x][new_y]=true;
                    searchQueue.add(new Pair<>(new_x, new_y));
                }
            }
        }
        return new Island(start_x,start_y,size,value,image);
    }
}

