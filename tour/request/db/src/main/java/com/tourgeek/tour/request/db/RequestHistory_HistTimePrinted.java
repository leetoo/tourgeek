/**
  * @(#)RequestHistory_HistTimePrinted.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.request.db;

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

/**
 *  RequestHistory_HistTimePrinted - .
 */
public class RequestHistory_HistTimePrinted extends DateTimeField
{
    /**
     * Default constructor.
     */
    public RequestHistory_HistTimePrinted()
    {
        super();
    }
    /**
     * RequestHistory_HistTimePrinted Method.
     */
    public RequestHistory_HistTimePrinted(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize the field.
     */
    public int initField(boolean displayOption)
    {
        return this.setValue(currentTime(), displayOption, DBConstants.INIT_MOVE);
    }

}
