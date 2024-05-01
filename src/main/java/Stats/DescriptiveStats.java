package Stats;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;

public class DescriptiveStats {

    public static void displayDescriptiveStats(ArrayList<Integer> integerArrayList)
    {
        double[] doubleArray = new double[integerArrayList.size()];
        for (int i = 0; i < integerArrayList.size(); i++) {
            doubleArray[i] = integerArrayList.get(i);
        }
        DescriptiveStatistics stats = new DescriptiveStatistics(doubleArray);
        System.out.println("Mean is: "+ stats.getMean());
        System.out.println("Median is: "+stats.getPercentile(50));
        System.out.println("Min is: "+stats.getMin());
        System.out.println("Max is: "+stats.getMax());

    }
}
