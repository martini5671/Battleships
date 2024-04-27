package testsandstuff;

import battleships2.Battlefield;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DescriptivesAndPlotsTets {
    public static void main(String[] args) {

        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<Long> creationTimes = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            long startTime = System.nanoTime();
            Battlefield enemybattlefield = new Battlefield(false);
            long endTime = System.nanoTime();
            long creationTime = (endTime - startTime)/ (long)1000000000;

            data.add(enemybattlefield.counter_loops);
            creationTimes.add(creationTime);
        }
        // zrobić hash mape z wszystkimi kombinacjami orientacji + każdy punkt i usuwać te pozycje
        // po kążdej nieudanej iteracji

        double[] doubleArray = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            doubleArray[i] = data.get(i);
        }

        //descriptive stats for loops
        DescriptiveStatistics stats = new DescriptiveStatistics(doubleArray);
        System.out.println("Mean is: "+ stats.getMean());
        System.out.println("Median is: "+stats.getPercentile(50));
        System.out.println("Min is: "+stats.getMin());
        System.out.println("Max is: "+stats.getMax());

        double[] doubleArrayTime = new double[creationTimes.size()];
        for (int i = 0; i < creationTimes.size(); i++) {
            doubleArrayTime[i] = creationTimes.get(i);
        }
        //descriptive stats for times
        DescriptiveStatistics stats2 = new DescriptiveStatistics(doubleArrayTime);
        System.out.println("Mean is: "+ stats2.getMean());
        System.out.println("Median is: "+stats2.getPercentile(50));
        System.out.println("Min is: "+stats2.getMin());
        System.out.println("Max is: "+stats2.getMax());

        // plots
        List<Double> dataAsDoubles = data.stream().map(Integer::doubleValue).collect(Collectors.toList());

        Plot plt = Plot.create();
        plt.hist()
                .add(dataAsDoubles)
                .bins(20)
                .color("#66DD66");
        plt.title("Histogram of Counter Loops");
        try {
            plt.show();
        } catch (IOException | PythonExecutionException e) {
            throw new RuntimeException(e);
        }




    }
}
