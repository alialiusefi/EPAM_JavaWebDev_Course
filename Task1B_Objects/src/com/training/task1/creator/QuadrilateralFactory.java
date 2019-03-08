package com.training.task1.creator;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.action.PropertyChecker;
import com.training.task1.exception.InvalidDataException;
import com.training.task1.registrator.QuadRegisterator;
import com.training.task1.repository.QuadRepository;
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

    public static Logger logger = LogManager.
            getLogger(QuadrilateralFactory.class);
    private static final int QUADRILATERAL_AMOUNT_OF_SIDES = 4;

    /**
     * @param ID     Identification number
     * @param points array of points
     * @return creates and return a new Quadrilateral
     * @throws InvalidDataException if given incorrect amount
     *                              of points or if null
     */
    public Quadrilateral createQuadrilateral(final int ID, final Point[] points)
            throws InvalidDataException {
        PropertyChecker propertyChecker = new PropertyChecker();
        if (points == null) {
            throw new InvalidDataException("Point array is null.");
        }
        if (points.length != QUADRILATERAL_AMOUNT_OF_SIDES) {
            throw new InvalidDataException("Point array length is not 4");
        }
        if (!propertyChecker.isQuadrilateral(points)) {
            throw new InvalidDataException(
                    "Point array given is not a quadrilateral");
        }
        Quadrilateral createdQuadrilateral = new Quadrilateral(ID, points);
        QuadRepository.getInstance().addQuadrilateral(createdQuadrilateral);
        QuadRegisterator registerator = new QuadRegisterator(ID);
        createdQuadrilateral.attach(registerator);
        QuadRepository.getInstance().addQuadRegisterator(registerator);
        return createdQuadrilateral;
    }

    /**
     * @param ID Identification number
     * @param x1 point 1
     * @param x2 point 2
     * @param x3 point 3
     * @param x4 point 4
     * @return creates and return a new Quadrilateral
     * @throws InvalidDataException if arguments are null
     */
    public Quadrilateral createQuadrilateral(
            final int ID, final Point x1, final Point x2,
            final Point x3, final Point x4) throws InvalidDataException {
        if (x1 != null && x2 != null && x3 != null && x4 != null) {
            return createQuadrilateral(ID, new Point[]{x1, x2, x3, x4});
        } else {
            throw new InvalidDataException("Point was found null!");
        }
    }

    /**
     * @param ID     Identification number
     * @param points list of string points that were parsed
     * @return returns a new created Quadrilateral
     * @throws InvalidDataException throws exception
     * if list is null or size != 4
     */
    public Quadrilateral createQuadrilateral(
            final int ID, final List<Pair<String, String>> points)
            throws InvalidDataException {
        if (points != null && points.size() == QUADRILATERAL_AMOUNT_OF_SIDES) {
            DataValidator dataValidator = new DataValidator();
            List<Pair<String, String>> validatedStringPoints = dataValidator.
                    validatePoints(points);
            List<Point> validPoints = new ArrayList<>();
            for (Pair<String, String> pair : validatedStringPoints) {
                double x = Double.parseDouble(pair.getKey());
                double y = Double.parseDouble(pair.getValue());
                validPoints.add(new PointFactory().createPoint(x, y));
            }
            return createQuadrilateral(ID, validPoints.toArray(new Point[0]));
        }
        throw new InvalidDataException(
                "List of points was found null or incorrect list size! ");

    }
}
