package linearsplineinterpolation;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LinearSplineInterpolation extends Application{
    
    @Override
    public void start(Stage stage) {
        int size = 37;
        double[] x = new double[size];
        double[] y = new double[size];
        double[] S = new double[size];
        
        x[0] = -2;
        double dx = 0.1;
        for(int i = 1; i < size; i++)
            x[i] = x[i-1] + dx;
        
        
        stage.setTitle("Linear Spline Interpolation");
        
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("X");
        yAxis.setLabel("Y");
        
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Linear spline interpolation of function exp(sinx)");
        
        XYChart.Series series1 = new XYChart.Series();                          
        XYChart.Series series2 = new XYChart.Series();                          
        series1.setName("F(x)");
        series2.setName("Linear spline interpolation's curve");
        
        ObservableList datas1 = FXCollections.observableArrayList();
        ObservableList datas2 = FXCollections.observableArrayList();
        
        for(int i = 0; i < size; i++){
            y[i] = exp(sin(x[i]));                                      
            datas1.add(new XYChart.Data(x[i], y[i]));
        }
        
        
        final double h = x[6] - x[0];
        
        for(int i = 0; i < 6; i++){
            S[i] = y[6] + (x[6]-x[i]) * ((y[0] - y[6]) / h);
            datas2.add(new XYChart.Data(x[i], S[i]));
        }
        
        for(int i = 6; i < 12; i++){
            S[i] = y[12] + (x[12]-x[i]) * ((y[6] - y[12]) / h);
            datas2.add(new XYChart.Data(x[i], S[i]));
        }
        
        for(int i = 12; i < 18; i++){
            S[i] = y[18] + (x[18]-x[i]) * ((y[12] - y[18]) / h);
            datas2.add(new XYChart.Data(x[i], S[i]));
        }
        
        for(int i = 18; i < 24; i++){
            S[i] = y[24] + (x[24]-x[i]) * ((y[18] - y[24]) / h);
            datas2.add(new XYChart.Data(x[i], S[i]));
        }
        
        for(int i = 24; i < 30; i++){
            S[i] = y[30] + (x[30]-x[i]) * ((y[24] - y[30]) / h);
            datas2.add(new XYChart.Data(x[i], S[i]));
        }
        
        for(int i = 30; i < size; i++){
            double h1 = x[size-1] - x[30];
            S[i] = y[36] + (x[36]-x[i]) * ((y[30] - y[36]) / h1);
            datas2.add(new XYChart.Data(x[i], S[i]));           
        }
        
        double[] Rp = new double[size];
        
        for(int i = 0; i < size; i++)
            Rp[i] = y[i] - S[i];
        
        for(int i = 0; i < size; i++)
            System.out.println(Rp[i]);
        
        series1.setData(datas1);
        series2.setData(datas2);
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        
        Scene scene  = new Scene(lineChart,800,600);
        
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
       launch(args);
    }
    
}
