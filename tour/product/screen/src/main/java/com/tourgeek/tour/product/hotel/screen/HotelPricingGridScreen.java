/**
  * @(#)HotelPricingGridScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.hotel.screen;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourgeek.tour.product.base.screen.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.product.hotel.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.base.db.*;

/**
 *  HotelPricingGridScreen - Hotel pricing.
 */
public class HotelPricingGridScreen extends ProductPricingGridScreen
{
    /**
     * Default constructor.
     */
    public HotelPricingGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public HotelPricingGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Hotel pricing";
    }
    /**
     * Constructor when passing header record.
     */
    public HotelPricingGridScreen(Record recHotel, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHotel, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new HotelPricing(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new Hotel(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new HotelScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().addListener(new CompareFileFilter(HotelPricing.RATE_ID, this.getScreenRecord().getField(HotelScreenRecord.RATE_ID), "=", null, true));
        this.getMainRecord().addListener(new CompareFileFilter(HotelPricing.CLASS_ID, this.getScreenRecord().getField(HotelScreenRecord.CLASS_ID), "=", null, true));
        
        this.getScreenRecord().getField(HotelScreenRecord.RATE_ID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(HotelScreenRecord.CLASS_ID).addListener(new FieldReSelectHandler(this));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen screen = super.addToolbars();
        this.getScreenRecord().getField(HotelScreenRecord.RATE_ID).setupDefaultView(screen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), screen, ScreenConstants.DEFAULT_DISPLAY);
        this.getScreenRecord().getField(HotelScreenRecord.CLASS_ID).setupDefaultView(screen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), screen, ScreenConstants.DEFAULT_DISPLAY);
        return screen;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getHeaderRecord().getField(Product.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrency = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getRecord(HotelPricing.HOTEL_PRICING_FILE).getField(HotelPricing.PAX_CATEGORY_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelPricing.HOTEL_PRICING_FILE).getField(HotelPricing.RATE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelPricing.HOTEL_PRICING_FILE).getField(HotelPricing.CLASS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelPricing.HOTEL_PRICING_FILE).getField(HotelPricing.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelPricing.HOTEL_PRICING_FILE).getField(HotelPricing.END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(HotelPricing.HOTEL_PRICING_FILE).getField(HotelPricing.ROOM_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new HotelHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
