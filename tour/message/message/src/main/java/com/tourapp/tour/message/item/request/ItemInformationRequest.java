/**
  * @(#)ItemInformationRequest.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.item.request;

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
import com.tourapp.tour.message.base.request.*;
import org.jbundle.model.message.*;
import com.tourapp.tour.message.item.request.data.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.model.main.msg.db.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.item.response.*;

/**
 *  ItemInformationRequest - .
 */
public class ItemInformationRequest extends ProductInformationRequest
{
    /**
     * Default constructor.
     */
    public ItemInformationRequest()
    {
        super();
    }
    /**
     * ItemInformationRequest Method.
     */
    public ItemInformationRequest(MessageDataParent messageDataParent, String strKey)
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
        return new ItemMessageData(this, PRODUCT_MESSAGE);
    }

}
