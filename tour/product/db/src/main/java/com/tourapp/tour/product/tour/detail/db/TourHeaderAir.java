/**
 * @(#)TourHeaderAir.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

/**
 *  TourHeaderAir - Tour Ticket Segment Detail.
 */
public class TourHeaderAir extends TourHeaderTransport
     implements TourHeaderAirModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TourHeaderAir()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderAir(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TOUR_HEADER_AIR_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour Ticket Segment Detail";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_AIR_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_AIR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
        {
            field = new TourHeaderOptionField(this, TOUR_HEADER_OPTION_ID, 8, null, null);
            field.setNullable(false);
        }
        //if (iFieldSeq == 4)
        //  field = new ModifyCodeField(this, MODIFY_CODE, 1, null, null);
        //if (iFieldSeq == 5)
        //  field = new ModifyTourSubField(this, MODIFY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
        {
            field = new ShortField(this, DAY, 2, null, new Short((short)1));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
        {
            field = new TimeField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 8)
        //  field = new ProductTypeField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new AirField(this, PRODUCT_ID, 4, null, null);
        if (iFieldSeq == 10)
            field = new AirRateSelect(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new AirClassSelect(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 12)
        //  field = new MessageTransportSelect(this, INFO_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 13)
        //  field = new InfoStatusSelect(this, INFO_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 14)
        //  field = new MessageTransportSelect(this, COST_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 15)
        //  field = new CostStatusSelect(this, COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 16)
        //  field = new MessageTransportSelect(this, INVENTORY_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 17)
        //  field = new InventoryStatusSelect(this, INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 18)
        //  field = new MessageTransportSelect(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 19)
        //  field = new ProductStatusSelect(this, PRODUCT_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 20)
        //  field = new ShortField(this, ACK_DAYS, 2, null, null);
        //if (iFieldSeq == 21)
        //  field = new MemoField(this, COMMENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 22)
        //  field = new XmlField(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 23)
            field = new UnusedField(this, NIGHTS, 2, null, null);
        if (iFieldSeq == 24)
            field = new UnusedField(this, MEAL_1, 2, null, null);
        if (iFieldSeq == 25)
            field = new UnusedField(this, MEAL_QTY_1, 2, null, null);
        if (iFieldSeq == 26)
            field = new UnusedField(this, MEAL_DAYS_1, 2, null, null);
        if (iFieldSeq == 27)
            field = new UnusedField(this, MEAL_2, 2, null, null);
        if (iFieldSeq == 28)
            field = new UnusedField(this, MEAL_QTY_2, 2, null, null);
        if (iFieldSeq == 29)
            field = new UnusedField(this, MEAL_DAYS_2, 2, null, null);
        if (iFieldSeq == 30)
            field = new UnusedField(this, MEAL_3, 2, null, null);
        if (iFieldSeq == 31)
            field = new UnusedField(this, MEAL_QTY_3, 2, null, null);
        if (iFieldSeq == 32)
            field = new UnusedField(this, MEAL_DAYS_3, 2, null, null);
        if (iFieldSeq == 33)
            field = new UnusedField(this, MEAL_4, 2, null, null);
        if (iFieldSeq == 34)
            field = new UnusedField(this, MEAL_QTY_4, 2, null, null);
        if (iFieldSeq == 35)
            field = new UnusedField(this, MEAL_DAYS_4, 2, null, null);
        if (iFieldSeq == 36)
            field = new UnusedField(this, PMC_CUTOFF, 3, null, null);
        //if (iFieldSeq == 37)
        //  field = new CityField(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 38)
        //  field = new StringField(this, CITY_CODE, 3, null, null);
        //if (iFieldSeq == 39)
        //  field = new CityField(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 40)
        {
            field = new StringField(this, TO_CITY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 41)
            field = new TourHeaderAirHeaderField(this, TOUR_HEADER_AIR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 42)
        {
            field = new TimeField(this, GMT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 43)
        {
            field = new StringField(this, CITY_DESC, 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 44)
        {
            field = new StringField(this, TO_CITY_DESC, 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 45)
            field = new ReferenceField(this, TICKET_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 46)
        {
            field = new StringField(this, XO, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 47)
        {
            field = new StringField(this, CARRIER, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 48)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 49)
        {
            field = new StringField(this, FLIGHT_NO, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 50)
        {
            field = new StringField(this, FLIGHT_CLASS, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 51)
        {
            field = new TimeField(this, ARRIVE_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 52)
            field = new ShortField(this, ADD_DAYS, 2, null, null);
        if (iFieldSeq == 53)
            field = new StringField(this, ARC_STATUS, 2, null, "OK");
        if (iFieldSeq == 54)
        {
            field = new StringField(this, FARE_BASIS, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 55)
        {
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 56)
        {
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 57)
        {
            field = new StringField(this, ALLOW, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 58)
        {
            field = new DoubleField(this, DET_FARE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 59)
        {
            field = new StringField(this, CONFIRMED_BY, 16, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 60)
        {
            field = new StringField(this, CONFIRMATION_NO, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 61)
        {
            field = new ShortField(this, COUPON, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 62)
        {
            field = new StringField(this, SEAT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 63)
        {
            field = new StringField(this, GATE, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 64)
        {
            field = new StringField(this, SEAT_PERF, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 65)
        {
            field = new BooleanField(this, SMOKING, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 66)
        {
            field = new StringField(this, MEALS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 67)
            field = new UnusedField(this, DAYS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 68)
            field = new UnusedField(this, PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourHeaderOptionID");
            keyArea.addKeyField(TOUR_HEADER_OPTION_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DAY, DBConstants.ASCENDING);
            keyArea.addKeyField(ETD, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductID");
            keyArea.addKeyField(PRODUCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DAY, DBConstants.ASCENDING);
            keyArea.addKeyField(ETD, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.addListener(new SharedFileHandler(TourHeaderDetail.PRODUCT_TYPE_ID, ProductType.AIR_ID));
    }

}