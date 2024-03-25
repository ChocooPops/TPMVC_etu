package packVue;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import packModele.Promotion;

public class VueHistogrammeChart extends AbstractVue implements Observer{

    private Histogramme histo;

    public VueHistogrammeChart() {
        histo = new Histogramme();
        this.setContentPane(histo);
        this.pack();
    }

    @Override
    public void update(Observable o, Object o1) {
        histo = new Histogramme();
        this.setContentPane(histo);
        repaint(); 
        revalidate(); 
    }


    // internal class
    public class Histogramme extends ChartPanel {

        public Histogramme() {
            super(null);
            this.setPreferredSize(new Dimension(285, 350));
            CategoryDataset dataset = createDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            this.setChart(chart);
        }

        private CategoryDataset createDataset() {
            double[][] data = Promotion.setDataHistogramme();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(data[0][0], "Bac G", "");
            dataset.addValue(data[0][1], "Bac T", "");
            dataset.addValue(data[0][2], "Bac Autre", "");
            dataset.addValue(data[0][3], "Bac Pro", "");

            return dataset;
        }

        private JFreeChart createChart(final CategoryDataset dataset) {
           
            
            final JFreeChart chart = ChartFactory.createBarChart3D(
                    "Série de bac", // chart title
                    "Bacs", // domain axis label
                    "Nombre", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips
                    false // urls
            );

            final CategoryPlot plot = chart.getCategoryPlot();
            final CategoryAxis axis = plot.getDomainAxis();
            axis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
            );
            final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
            renderer.setDrawBarOutline(false);
            return chart;
        }
    }
}
