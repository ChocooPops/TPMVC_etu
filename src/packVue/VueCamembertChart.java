package packVue;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import packModele.Promotion;

public class VueCamembertChart extends AbstractVue implements Observer {

    private Camembert camemb;

    public VueCamembertChart() {
        camemb = new Camembert();
        this.setContentPane(camemb);
        this.pack();
    }

    @Override
    public void update(Observable o, Object o1) {
         camemb = new Camembert();
         repaint(); 
         revalidate(); 
    }
    
// internal class
    public class Camembert extends ChartPanel {

        public Camembert() {
            super(null);
            this.setPreferredSize(new Dimension(450, 350));
            final PieDataset dataset = createSampleDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);
            this.setChart(chart);
        }

        private PieDataset createSampleDataset() {
            final DefaultPieDataset result = new DefaultPieDataset();
            Promotion.setResult(result);
            return result;
        }

        private JFreeChart createChart(final PieDataset dataset) {
            final JFreeChart chart = ChartFactory.createPieChart3D(
                    "Répartition Géographique", // chart title
                    dataset, // data
                    true, // include legend
                    true,
                    false
            );
            final PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
            plot.setNoDataMessage("No data to display");
            return chart;
        }
    }
}
