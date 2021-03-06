/**
  * @(#)HotelAvail.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.util.test.hotel.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.util.test.hotel.db.*;

public class HotelAvail extends FieldList
    implements HotelAvailModel
{
    private static final long serialVersionUID = 1L;


    public HotelAvail()
    {
        super();
    }
    public HotelAvail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE_MEMORY;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, HOTEL_CODE, 20, null, null);
        field = new FieldInfo(this, CHAIN_CODE, 30, null, null);
        field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        field = new FieldInfo(this, AMOUNT_BEFORE_TAX, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, AMOUNT_AFTER_TAX, 18, null, null);
        field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
    }

}
