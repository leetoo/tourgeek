/**
  * @(#)ProductUpdateMessageOutErrorProcessor.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.base.error;

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
import com.tourgeek.tour.message.base.request.data.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.main.db.base.*;
import com.tourgeek.tour.message.base.request.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;

/**
 *  ProductUpdateMessageOutErrorProcessor - .
 */
public class ProductUpdateMessageOutErrorProcessor extends ProductMessageErrorProcessor
{
    /**
     * Default constructor.
     */
    public ProductUpdateMessageOutErrorProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductUpdateMessageOutErrorProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Process this internal message.
     * @param internalMessage The message to process.
     * @return (optional) The return message if applicable.
     */
    public BaseMessage processMessage(BaseMessage internalMessage)
    {
        return super.processMessage(internalMessage);
    }

}
