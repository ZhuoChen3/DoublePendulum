package com.my.doublependulum;

public class DoublePendulum {
//    public static int size = DoublePendulumCreator.ITERATION + 1;
    //    public static DoublePendulum[] pendulums = new DoublePendulum[size];
//    public static double[] angle1s = new double[size];
//    public static double[] angle2s = new double[size];
//    public static double[] x1s = new double[size];
//    public static double[] x2s = new double[size];
//    public static double[] y1s = new double[size];
//    public static double[] y2s = new double[size];
//    public static double[] ts = new double[size];
    public static double g = 9.8; // gravity
    public static double l1 = 1; //length of the first rod
    public static double l2 = 1; //length of the second rod
    public static double m1 = 1; //mass of the first particle
    public static double m2 = 1; //mass of the second particle
    public double theta1; //the first angle
    public double theta2; //the second angle
    public double omega1; //the first angular velocity
    public double omega2; //the second angular velocity
    public double t; //the time for an instance
//    public static double getL1() {
//        return l1;
//    }
//    public static double getL2() {
//        return l2;
//    }
//    public static boolean setL1(final double newL1) {
//        if (newL1 <= 0) {
//            return false;
//        }
//        l1 = newL1;
//        return true;
//    }
//    public static boolean setL2(final double newL2) {
//        if (newL2 <= 0) {
//            return false;
//        }
//        l2 = newL2;
//        return true;
//    }
//    public static double getM1() {
//        return m1;
//    }
//    public static double getM2() {
//        return m2;
//    }
//    public static boolean setM1(final double newM1) {
//        if (newM1 < 0) {
//            return false;
//        }
//        m1 = newM1;
//        return true;
//    }
//    public static boolean setM2(final double newM2) {
//        if (newM2 < 0) {
//            return false;
//        }
//        m2 = newM2;
//        return true;
//    }
//    public double getTheta1() {
//        return theta1;
//    }
//    public double getTheta2() {
//        return theta2;
//    }
//    public double getT() {
//        return t;
//    }
    public DoublePendulum(final double angle1, final double angle2, final double angVel1, final double angVel2, final double time) {
        theta1 = angle1;
        theta2 = angle2;
        omega1 = angVel1;
        omega2 = angVel2;
        t = time;
    }
    public double[] move(final double h) {
        double a = m2 / m1;
        double b = l2 / l1;
        double c = g / l1;

        double dtheta = theta1 - theta2;
        double part11 = (1 + a) * c * Math.sin(theta1);
        double part12 = a * b * omega2 * omega2 * Math.sin(dtheta);
        double part13 = a * Math.cos(dtheta) * (omega1 * omega1 * Math.sin(dtheta) - c * Math.sin(theta2));
        double part14 = 1 + a * Math.sin(dtheta) * Math.sin(dtheta);
        double a1 = - h * (part11 + part12 + part13) / part14;
        double part21 = (1 + a) * (omega1 * omega1 * Math.sin(dtheta) - c * Math.sin(theta2));
        double part22 = Math.cos(dtheta) * (1 + a) * c * Math.sin(theta1) + a * b * omega2 * omega2 * Math.sin(dtheta);
        double part23 = b * (1 + a * Math.sin(dtheta) * Math.sin(dtheta));
        double a2 = h * (part21 + part22) / part23;
        double a3 = h * omega1;
        double a4 = h * omega2;

        dtheta = theta1 + a3 / 2 - theta2 - a4 / 2;
        part11 = (1 + a) * c * Math.sin(theta1 + a3 / 2);
        part12 = a * b * (omega2 + a2 / 2) * (omega2 + a2 / 2) * Math.sin(dtheta);
        part13 = a * Math.cos(dtheta) * ((omega1 + a1 / 2) * (omega1 + a1 / 2) * Math.sin(dtheta) - c * Math.sin(theta2 + a4 / 2));
        part14 = 1 + a * Math.sin(dtheta) * Math.sin(dtheta);
        double b1 = - h * (part11 + part12 + part13) / part14;
        part21 = (1 + a) * ((omega1 + a1 / 2) * (omega1 + a1 / 2) * Math.sin(dtheta) - c * Math.sin(theta2 + a4 / 2));
        part22 = Math.cos(dtheta) * (1 + a) * c * Math.sin(theta1 + a3 / 2) + a * b * (omega2 + a2 / 2) * (omega2 + a2 / 2) * Math.sin(dtheta);
        part23 = b * (1 + a * Math.sin(dtheta) * Math.sin(dtheta));
        double b2 = h * (part21 + part22) / part23;
        double b3 = h * (omega1 + a1 / 2);
        double b4 = h * (omega2 + a2 / 2);

        dtheta = theta1 + b3 / 2 - theta2 - b4 / 2;
        part11 = (1 + a) * c * Math.sin(theta1 + b3 / 2);
        part12 = a * b * (omega2 + b2 / 2) * (omega2 + b2 / 2) * Math.sin(dtheta);
        part13 = a * Math.cos(dtheta) * ((omega1 + b1 / 2) * (omega1 + b1 / 2) * Math.sin(dtheta) - c * Math.sin(theta2 + b4 / 2));
        part14 = 1 + a * Math.sin(dtheta) * Math.sin(dtheta);
        double c1 = - h * (part11 + part12 + part13) / part14;
        part21 = (1 + a) * ((omega1 + b1 / 2) * (omega1 + b1 / 2) * Math.sin(dtheta) - c * Math.sin(theta2 + b4 / 2));
        part22 = Math.cos(dtheta) * (1 + a) * c * Math.sin(theta1 + b3 / 2) + a * b * (omega2 + b2 / 2) * (omega2 + b2 / 2) * Math.sin(dtheta);
        part23 = b * (1 + a * Math.sin(dtheta) * Math.sin(dtheta));
        double c2 = h * (part21 + part22) / part23;
        double c3 = h * (omega1 + b1 / 2);
        double c4 = h * (omega2 + b2 / 2);

        dtheta = theta1 + c3 - theta2 - c4;
        part11 = (1 + a) * c * Math.sin(theta1 + c3);
        part12 = a * b * (omega2 + c2) * (omega2 + c2) * Math.sin(dtheta);
        part13 = a * Math.cos(dtheta) * ((omega1 + c1) * (omega1 + c1) * Math.sin(dtheta) - c * Math.sin(theta2 + c4));
        part14 = 1 + a * Math.sin(dtheta) * Math.sin(dtheta);
        double d1 = - h * (part11 + part12 + part13) / part14;
        part21 = (1 + a) * ((omega1 + c1) * (omega1 + c1) * Math.sin(dtheta) - c * Math.sin(theta2 + c4));
        part22 = Math.cos(dtheta) * (1 + a) * c * Math.sin(theta1 + c3) + a * b * (omega2 + c2) * (omega2 + c2) * Math.sin(dtheta);
        part23 = b * (1 + a * Math.sin(dtheta) * Math.sin(dtheta));
        double d2 = h * (part21 + part22) / part23;
        double d3 = h * (omega1 + c1);
        double d4 = h * (omega2 + c2);

        double newAngVel1 = omega1 + a1 / 6 + b1 / 3 + c1 / 3 + d1 / 6;
        double newAngVel2 = omega2 + a2 / 6 + b2 / 3 + c2 / 3 + d2 / 6;
        double newAngle1 = theta1 + a3 / 6 + b3 / 3 + c3 / 3 + d3 / 6;
        double newAngle2 = theta2 + a4 / 6 + b4 / 3 + c4 / 3 + d4 / 6;

        double newTime = t + h;
        double[] returnArray = {newAngle1, newAngle2, newAngVel1, newAngVel2, newTime};
        return returnArray;
    }
    public static void main(String[] test) {
        DoublePendulum pendulum1 = new DoublePendulum(Math.PI / 2, Math.PI, 0,0,0);
        for (int i = 0; i < 100000000; i++) {
            System.out.println(i);
            double[] out = pendulum1.move(0.000001);
            for (double o : out) {
                System.out.print(o + "   ");
            }
            pendulum1 = new DoublePendulum(out[0], out[1], out[2], out[3], out[4]);
            System.out.println();
        }
    }
}
