package Stats;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HistogramPlotter {

    public static void plotOverlappingHistograms(List<Integer> data1, List<Integer> data2, String label_1, String label_2) throws PythonExecutionException, IOException {
        List<Double> data1AsDoubles = data1.stream().map(Integer::doubleValue).collect(Collectors.toList());
        List<Double> data2AsDoubles = data2.stream().map(Integer::doubleValue).collect(Collectors.toList());

        Plot plt = Plot.create();
        plt.hist()
                .add(data1AsDoubles)
                .add(data2AsDoubles)
                .bins(30)
                .stacked(true)
                .color("#66DD66", "#6688FF");
        plt.title("Histogram ilości oddanych strzałów przed zakończeniem gry");
        plt.xlabel("Na zielono: " + label_1 + " || Na niebiesko: "+ label_2);
        // Don't miss this line to output the file!
        plt.savefig("C:/Users/koczo/IdeaProjects/Battleships/src/main/resources/histogram.png").dpi(200);
        try {
            plt.show();
        } catch (IOException | PythonExecutionException e) {
            System.out.println(" ");
        }
    }
}
