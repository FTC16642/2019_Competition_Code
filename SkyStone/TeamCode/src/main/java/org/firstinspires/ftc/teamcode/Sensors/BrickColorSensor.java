package org.firstinspires.ftc.teamcode.Sensors;
/*
* BrickColorSensor handles a sensor under the robot which identifies color in specific skystones.
* This class is used in Autonomous and Driver control periods.
* */
import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

public class BrickColorSensor {


    private ColorSensor sensorColor;
    private DistanceSensor sensorDistance;
    private HardwareMap hardwareMap = null;
    private Telemetry telemetry = null;
    // hsv Values is an array that will hold the hue, saturation, and value information.
    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    final double SCALE_FACTOR = 255;

    //View relativeLayout = null;


    public BrickColorSensor(HardwareMap hrdMap,Telemetry tele)
    {
        hardwareMap = hrdMap;
        telemetry = tele;
    }

    //color RGB to int
    public int getIntFromColor(float Red, float Green, float Blue)
    {
        int R = Math.round(255 * Red);
        int G = Math.round(255 * Green);
        int B = Math.round(255 * Blue);

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B ;
    }


    public void init()
    {
        // get a reference to the color sensor.
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        //int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        //relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


    }


    public double getDistanceValue()
    {
        telemetry.addData("Distance (cm)",
                String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
        return  sensorDistance.getDistance(DistanceUnit.CM);
    }

    public int getRGBValue()
    {
        // convert the RGB values to HSV values.
        // multiply by the SCALE_FACTOR.
        // then cast it back to int (SCALE_FACTOR is a double)
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);

        // send the info back to driver station using telemetry function.
        telemetry.addData("Alpha", sensorColor.alpha());
        telemetry.addData("Red  ", sensorColor.red());
        telemetry.addData("Green", sensorColor.green());
        telemetry.addData("Blue ", sensorColor.blue());
        telemetry.addData("Hue", hsvValues[0]);

        getDistanceValue();

        // change the background color to match the color detected by the RGB sensor.
        // pass a reference to the hue, saturation, and value array as an argument
        // to the HSVToColor method.
        /*relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
            }
        });*/

        telemetry.update();

        return getIntFromColor(sensorColor.red(), sensorColor.green(), sensorColor.blue());
    }

    /*public void uninitialize()
    {
        // Set the panel back to the default color
        /*relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });* /
    }*/

    public boolean isSkystone()
    {
        if (sensorColor.red() < 30)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
