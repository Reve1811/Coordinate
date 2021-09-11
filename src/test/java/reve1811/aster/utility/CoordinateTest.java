package reve1811.aster.utility;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    @Test
    public void changePointFrom4612To4301Test(){
        double fromX = 135.9618611;
        double fromY = 35.0222389;

        double[] exchanged1 = Coordinate.changePointCRS(fromX, fromY, "EPSG:4612", "EPSG:4301");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(6);
        String changedX = df.format(exchanged1[0]);
        String changedY = df.format(exchanged1[1]);

        assertEquals("135.964742",changedX);
        assertEquals("35.019021",changedY);
    }

    @Test
    public void changePointFrom4301To4612Test(){
        double fromX = 135.964742;
        double fromY = 35.019021;

        double[] exchanged1 = Coordinate.changePointCRS(fromX, fromY, "EPSG:4301", "EPSG:4612");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(6);
        String changedX = df.format(exchanged1[0]);
        String changedY = df.format(exchanged1[1]);

        assertEquals("135.961861",changedX);
        assertEquals("35.022239",changedY);
    }

    @Test
    public void changePointFrom4612To3857Test(){
        double fromX = 135.9618611;
        double fromY = 35.0222389;

        double[] exchanged1 = Coordinate.changePointCRS(fromX, fromY, "EPSG:4612", "EPSG:3857");

        double changedX = ((double)Math.round(exchanged1[0] * 100)) / 100 ;
        double changedY = ((double)Math.round(exchanged1[1] * 100)) / 100;

        assertEquals(15135205.14d,changedX);
        assertEquals(4166903.73d,changedY);
    }

    @Test
    public void changePointFrom4301To3857Test(){
        double fromX = 135.964742;
        double fromY = 35.019021;

        //can't 4301 to 3857 directory because of elips definition.
        double[] to4612 = Coordinate.changePointCRS(fromX, fromY, "EPSG:4301", "EPSG:4612");
        double[] exchanged1 = Coordinate.changePointCRS(to4612[0], to4612[1], "EPSG:4612", "EPSG:3857");

        double changedX = ((double)Math.round(exchanged1[0] * 100)) / 100 ;
        double changedY = ((double)Math.round(exchanged1[1] * 100)) / 100;

        assertEquals(15135205.1d,changedX);
        assertEquals(4166903.69d,changedY);
    }
}