/**
  * @(#)HotelInformationRequest.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.hotel.request;

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
import com.tourgeek.tour.message.base.request.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.message.hotel.request.data.*;
import com.tourgeek.tour.message.base.request.data.*;
import org.jbundle.model.message.*;

/**
 *  HotelInformationRequest - .
 */
public class HotelInformationRequest extends ProductInformationRequest
{
    /**
     * Default constructor.
     */
    public HotelInformationRequest()
    {
        super();
    }
    /**
     * HotelInformationRequest Method.
     */
    public HotelInformationRequest(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        super.init(messageDataParent, strKey);
    }
    /**
     * CreateProductMessageData Method.
     */
    public ProductMessageData createProductMessageData()
    {
        return new HotelMessageData(this, PRODUCT_MESSAGE);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        // Note: I am including the number of nights in the information since the description and end data depend on it
    }

}
