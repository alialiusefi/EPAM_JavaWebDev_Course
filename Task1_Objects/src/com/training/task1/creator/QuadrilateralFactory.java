package com.training.task1.creator;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.action.PropertyChecker;
import com.training.task1.exception.InvalidDataException;
import com.training.task1.validator.DataValidator;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates quadrilateral.
 */

public final class QuadrilateralFactory {

    public static Logger LOGGER = LogManager.getLogger(QuadrilateralFactory.class);


    public Quadrilateral createQuadrilateral(Point[] points) throws InvalidDataException {
        PropertyChecker propertyChecker = new PropertyChecker();
        if (points == null) {
            throw new InvalidDataException("Point array is null.");
        }
        if (points.length != 4) {
            throw new InvalidDataException("Point array length is not 4");
        }
        if (!propertyChecker.isQuadrilateral(points)) {
            throw new InvalidDataException("Point array given is not a quadrilateral");
        }
        return new Quadrilateral(points);
    }

    public Quadrilateral createQuadrilateral(final Point x1, final Point x2,
                                             final Point x3, final Point x4)
            throws InvalidDataException {
        if (x1 != null && x2 != null && x3 != null && x4 != null) {
            return createQuadrilateral(new Point[]{x1, x2, x3, x4});
        } else {
            throw new InvalidDataException("Point was found null!");
        }
    }

    public Quadrilateral createQuadrilateral(final List<Pair<String, String>> points)
            throws InvalidDataException {
        if (points != null) {
            DataValidator dataValidator = new DataValidator();
            List<Pair<String, String>> validatedStringPoints = dataValidator.
                    validatePoints(points);
            List<Point> validPoints = new ArrayList<>();
            for (Pair<String, String> pair : validatedStringPoints) {
                double x = Double.parseDouble(pair.getKey());
                double y = Double.parseDouble(pair.getValue());
                validPoints.add(new PointFactory().createPoint(x, y));
            }
            return createQuadrilateral(validPoints.toArray(new Point[0]));
        }
        throw new InvalidDataException("List of points was found null!");

    }
}
