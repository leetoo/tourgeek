/**
 *  @(#)ModifyTourSubField.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.tour.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.thin.base.message.remote.*;
import org.jbundle.base.message.record.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.thin.base.screen.*;

/**
 *  ModifyTourSubField - Tour sub field to modify.
 */
public class ModifyTourSubField extends ReferenceField
{
    public static final String SELECT_QUEUE = "selectTourHeaderDetail";
    /**
     * Default constructor.
     */
    public ModifyTourSubField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public ModifyTourSubField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        try {
            return (Record)this.getRecord().clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenField setupDefaultView(ScreenLocation itsLocation, BasePanel targetScreen, Converter converter, int iDisplayFieldDesc)
    {
        ScreenField sField = null;
        Record record = this.makeReferenceRecord();
        int fsField = TourHeaderLine.kDescription;
        if (record instanceof TourHeaderAirHeader)
            fsField = TourHeaderAirHeader.kAirlineDesc;
        if (record instanceof TourHeaderDetail)
        {
            Record recProduct = ((ReferenceField)record.getField(TourHeaderDetail.kProductID)).getReferenceRecord();
            if (recProduct != null)
            {
                fsField = record.getFieldCount();   // This will be the sequence of the new field
                BaseField field = new StringField(record, "Description", 30, null, null);
                field.setVirtual(true);     // Just being careful
                record.getField(TourHeaderDetail.kProductID).addListener(new ReadSecondaryHandler(recProduct));
                BaseField fldProductDesc = recProduct.getField(Product.kDescription);
                recProduct.addListener(new MoveOnValidHandler(field, fldProductDesc));
            }
            else
                fsField = TourHeaderDetail.kDay;    // Never (just in case)
        }
        sField = this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, -1, fsField, true, false);
        for (int i = 0; ; i++)
        {
            ScreenField screenField = this.getSFieldAt(i);
            if (screenField instanceof SSelectBox)
            {
                screenField.free();
                new SSelectBox(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, converter, ScreenConstants.DONT_DISPLAY_DESC, record)
                {
                    public boolean doCommand(String strCommand, ScreenField sourceSField, int iComandOptions)
                    {
                        Task task = null;
                        if (m_record.getRecordOwner() != null)
                            task = m_record.getRecordOwner().getTask();
                        if (task == null)
                            task = BaseApplet.getSharedInstance();
                        Application application = (Application)task.getApplication();
        
                        BaseField fldTourSubID = (BaseField)this.getConverter().getField();
                        Record recTourSub = fldTourSubID.getRecord();
                        Record recTourHeaderOption = null;
                        Record recTourHeader = null;
                        try {
                            RecordOwner recordOwner = Utility.getRecordOwner(this.getRecord());
                            recTourHeaderOption = new TourHeaderOption(recordOwner);
                            recordOwner.removeRecord(recTourHeaderOption);
                            recTourHeaderOption.getField(TourHeaderOption.kID).moveFieldToThis(recTourSub.getField(TourSub.kTourHeaderOptionID));
                            while (recTourHeaderOption.seek(null) == true)
                            {
                                if (TourHeaderOption.TOUR.equals(recTourHeaderOption.getField(TourHeaderOption.kTourOrOption).toString()))
                                {   // Finally made it to the tour.
                                    recTourHeader = new TourHeader(recordOwner);
                                    recordOwner.removeRecord(recTourHeader); // This is belong to the new option screen
                                    recTourHeader.getField(TourHeader.kID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kTourOrOptionID));
                                    boolean bSuccess = recTourHeader.seek(DBConstants.EQUALS);
                                    break;
                                }
                                recTourHeaderOption.getField(TourHeaderOption.kID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.kTourOrOptionID));
                            }
                            if (recTourHeader != null)
                            {
                                BasePanel parentScreen = Screen.makeWindow(application);
                                String strQueueName = ModifyTourSubField.SELECT_QUEUE;   // This is my private queue
                                parentScreen.setProperty(MessageConstants.QUEUE_NAME, strQueueName);
                                parentScreen.setProperty(RecordMessageConstants.TABLE_NAME, recTourSub.getTableNames(false));
                                GridScreen screen = new TourHeaderOptionGridScreen(recTourHeader, null, null, parentScreen, null, /*ScreenConstants.SELECT_MODE |*/ ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
                                BaseMessageManager messageManager = application.getMessageManager();
                                BaseMessageReceiver receiver = (BaseMessageReceiver)messageManager.getMessageQueue(strQueueName, MessageConstants.INTRANET_QUEUE).getMessageReceiver();
                                BaseScreen screenTarget = (BaseScreen)sourceSField.getParentScreen();
                                receiver.createDefaultFilter(screenTarget, true);
                            }
                        } catch (DBException ex) {
                            ex.printStackTrace();
                        } finally {
                            if (recTourHeaderOption != null)
                                recTourHeaderOption.free();
                            recTourHeaderOption = null;
                        }
                        return true;    // Handled
                    }
                };
                break;
            }
            if (screenField == null)
                break;  // Just being careful.
        }
        return sField;
    }

}
