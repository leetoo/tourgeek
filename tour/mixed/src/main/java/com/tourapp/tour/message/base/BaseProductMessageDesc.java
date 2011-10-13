/**
 * @(#)BaseProductMessageDesc.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.base;

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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.main.msg.db.base.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.main.msg.db.*;
import java.math.*;
import javax.xml.datatype.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.model.db.*;

/**
 *  BaseProductMessageDesc - .
 */
public class BaseProductMessageDesc extends MessageRecordDesc
{
    public static final String MESSAGE_TIMESTAMP = "TimeStamp";
    /**
     * Default constructor.
     */
    public BaseProductMessageDesc()
    {
        super();
    }
    /**
     * BaseProductMessageDesc Method.
     */
    public BaseProductMessageDesc(MessageDataParent messageDataParent, String strKey)
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
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        this.addMessageFieldDesc("AltLangID", String.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc("EchoToken", String.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc("PrimaryLangID", String.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc("SequenceNmbr", Float.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc("Target", String.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc(MESSAGE_TIMESTAMP, XMLGregorianCalendar.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc("TransactionIdentifier", String.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc("TransactionStatusCode", String.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
        this.addMessageFieldDesc("Version", Float.class, MessageFieldDesc.OPTIONAL, MessageFieldDesc.STANDARD_PARAM | MessageFieldDesc.ECHO_PARAM, null);
    }
    /**
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(Rec record)
    {
        if (this.getMessage().getMessageHeader() != null)
            if (this.getMessage().getMessageHeader().get(MessageTransport.INITIAL_MESSAGE_DATA_STATUS) != null)
            {
                try {
                    return ((Integer)Converter.convertObjectToDatatype(this.getMessage().getMessageHeader().get(MessageTransport.INITIAL_MESSAGE_DATA_STATUS), Integer.class, new Integer(BaseDataStatus.DATA_VALID))).intValue();
                } catch (Exception ex) {
                    return BaseDataStatus.DATA_VALID;    // Never
                }
            }
        return super.checkRequestParams(record);    // So far, the data is valid
    }
    /**
     * Get the property key for an error property for this type of message.
     * @return The message type.
     */
    public String getMessageTypeParam()
    {
        String strRequestParam = DBConstants.BLANK;
        // The way I get the error param is a little lame, but it works!
        String strClassName = this.getClass().getName();
        if (strClassName.indexOf("Information") != -1)
            strRequestParam = BookingDetail.INFO_PARAM;
        else if (strClassName.indexOf("Rate") != -1)
            strRequestParam = BookingDetail.COST_PARAM;
        else if (strClassName.indexOf("Availability") != -1)
            strRequestParam = BookingDetail.INVENTORY_PARAM;
        else if (strClassName.indexOf("Booking") != -1)
            strRequestParam = BookingDetail.PRODUCT_PARAM;
        return strRequestParam;
    }
    /**
     * Initialize the fields in this record to prepare for this message.
     * Also, do any other preparation needed before sending this message.
     * @param record The record to initialize
     * @return An error code if there were any problems.
     */
    public int initForMessage(Record record)
    {
        return DBConstants.NORMAL_RETURN; // Override this
    }
    /**
     * A utility method to get a resource string using a record's owner.
     */
    public String getString(Record record, String strResourceName, String string)
    {
        if (record != null)
            if (record.getRecordOwner() != null)
                if (record.getRecordOwner().getTask() != null)
                    if (record.getRecordOwner().getTask().getApplication() != null)
                        string = record.getRecordOwner().getTask().getApplication().getResources(strResourceName, true).getString(string);
        return string;
    }

}
