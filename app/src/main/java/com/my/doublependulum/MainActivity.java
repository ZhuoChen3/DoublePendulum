package com.my.doublependulum;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.androidplot.xy.*;

public class MainActivity extends AppCompatActivity {

    private EditText mass1;
    private EditText mass2;
    private EditText length1;
    private EditText length2;
    private EditText angle1;
    private EditText angle2;
    private EditText angularVelocity1;
    private EditText angularVelocity2;
    private final int ITERATION = 100000;
    private final double STEP_SIZE = 0.0002;
    private LineGraphSeries<DataPoint> series1;
    private LineGraphSeries<DataPoint> series2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GraphView graphX = (GraphView) findViewById(R.id.xGraph);
        GraphView graphY = (GraphView) findViewById(R.id.yGragh);
        graphX.setTitle("Horizontal Position (meter) vs Time (second)");
        graphY.setTitle("Vertical Position (meter) vs Time (second");
    }

    public void compute(final View c) {
        Button comp = (Button) c;
        (comp).setText("COMPUTING");
        mass1 = findViewById(R.id.m1);
        mass2 = findViewById(R.id.m2);
        length1 = findViewById(R.id.l1);
        length2 = findViewById(R.id.l2);
        angle1 = findViewById(R.id.ang1);
        angle2 = findViewById(R.id.ang2);
        angularVelocity1 = findViewById(R.id.angVel1);
        angularVelocity2 = findViewById(R.id.angVel2);
        series1 = new LineGraphSeries<DataPoint>();
        series2 = new LineGraphSeries<DataPoint>();
        double m1;
        double m2;
        double l1;
        double l2;
        double ang1;
        double ang2;
        double angVel1;
        double angVel2;
        GraphView graphX = (GraphView) findViewById(R.id.xGraph);
        GraphView graphY = (GraphView) findViewById(R.id.yGragh);
        if (mass1.getText().toString().equals("")) {
            m1 = 1;
        } else {
            m1 = Double.parseDouble(mass1.getText().toString());
        }
        if (mass2.getText().toString().equals("")) {
            m2 = 1;
        } else {
            m2 = Double.parseDouble(mass2.getText().toString());
        }
        if (length1.getText().toString().equals("")) {
            l1 = 1;
        } else {
            l1 = Double.parseDouble(length1.getText().toString());
        }
        if (length2.getText().toString().equals("")) {
            l2 = 1;
        } else {
            l2 = Double.parseDouble(length2.getText().toString());
        }
        if (angle1.getText().toString().equals("")) {
            ang1 = Math.PI / 2;
        } else {
            ang1 = Double.parseDouble(angle1.getText().toString()) / 180 * Math.PI;
        }
        if (angle2.getText().toString().equals("")) {
            ang2 = Math.PI / 2;
        } else {
            ang2 = Double.parseDouble(angle2.getText().toString()) / 180 * Math.PI;
        }
        if (angularVelocity1.getText().toString().equals("")) {
            angVel1 = 0;
        } else {
            angVel1 = Double.parseDouble(angularVelocity1.getText().toString()) / l1;
        }
        if (angularVelocity2.getText().toString().equals("")) {
            angVel2 = 0;
        } else {
            angVel2 = (Double.parseDouble(angularVelocity2.getText().toString()) - angVel1 * l1) / l2;
        }
        Toast toast = new Toast(getApplicationContext());
        if (m1 <= 0 || m2 <= 0 || l1 <= 0 || l2 <= 0) {
            toast.makeText(MainActivity.this, "Invalid Parameters", toast.LENGTH_SHORT).show();
        } else {
            DoublePendulum.l1 = l1;
            DoublePendulum.l2 = l2;
            DoublePendulum.m1 = m1;
            DoublePendulum.m2 = m2;
            DoublePendulum pendulum = new DoublePendulum(ang1, ang2, angVel1, angVel2, 0);
            double x;
            double y;
            double t;
            double[] values = pendulum.move(STEP_SIZE);
            for (int i = 0; i < ITERATION; i++) {
                pendulum = new DoublePendulum(values[0], values[1], values[2], values[3], values[4]);
                x = l1 * Math.sin(values[0]) + l2 * Math.sin(values[1]);
                y = -(l1 * Math.cos(values[0]) + l2 * Math.cos(values[1]));
                t = values[4];
                values = pendulum.move(STEP_SIZE);
                series1.appendData(new DataPoint(t, x), true, ITERATION);
                series2.appendData(new DataPoint(t, y), true, ITERATION);
            }
            graphX.addSeries(series1);
            graphY.addSeries(series2);
            (comp).setText("COMPUTE ANOTHER ONE");
        }
    }
    public void clear(final View c) {
        Button cl = (Button) c;
        GraphView graphX = (GraphView) findViewById(R.id.xGraph);
        GraphView graphY = (GraphView) findViewById(R.id.yGragh);
        graphX.removeAllSeries();
        graphY.removeAllSeries();
    }
    public void reEnter(final View r) {
        mass1 = findViewById(R.id.m1);
        mass2 = findViewById(R.id.m2);
        length1 = findViewById(R.id.l1);
        length2 = findViewById(R.id.l2);
        angle1 = findViewById(R.id.ang1);
        angle2 = findViewById(R.id.ang2);
        angularVelocity1 = findViewById(R.id.angVel1);
        angularVelocity2 = findViewById(R.id.angVel2);
        mass1.setText("");
        mass2.setText("");
        length1.setText("");
        length2.setText("");
        angle1.setText("");
        angle2.setText("");
        angularVelocity1.setText("");
        angularVelocity2.setText("");
    }
}