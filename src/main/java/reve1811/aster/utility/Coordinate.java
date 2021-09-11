package reve1811.aster.utility;

import org.osgeo.proj4j.*;

public class Coordinate {
    /**
     * change point coordination
     * @param pointX src x coordinate
     * @param pointY src y coordinate
     * @param fromEPSG original EPSG. pass by "EPSG:XXXX"
     * @param toEPSG target EPSG. pass by "EPSG:YYYY"
     * @return points which changed the EPSG
     */
    public static double[] changePointCRS(double pointX, double pointY, String fromEPSG, String toEPSG){
        double[] result = new double[]{Double.NaN, Double.NaN};
        CRSFactory factory = new CRSFactory();

        try{
            CoordinateReferenceSystem fromCRS = factory.createFromName(fromEPSG);
            CoordinateReferenceSystem toCRS = factory.createFromName(toEPSG);
            CoordinateTransform transform = new CoordinateTransformFactory().createTransform(fromCRS, toCRS);

            ProjCoordinate srcCoordinate = new ProjCoordinate(pointX, pointY);
            ProjCoordinate destCoordinate = new ProjCoordinate();
            transform.transform(srcCoordinate, destCoordinate);
            result[0] = destCoordinate.x;
            result[1] = destCoordinate.y;

            return result;
        }catch (UnknownAuthorityCodeException e){
            e.printStackTrace();
            return result;
        }
    }
}
