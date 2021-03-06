/**
  * @(#)ProductSearchSession.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.remote.search;

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
import org.jbundle.base.remote.db.*;
import org.jbundle.thin.base.remote.*;
import com.tourgeek.tour.product.base.db.*;
import org.jbundle.thin.opt.location.*;
import com.tourgeek.tour.product.remote.*;
import com.tourgeek.thin.app.booking.entry.search.*;
import org.jbundle.base.remote.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.acctpay.db.*;
import java.text.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.product.base.event.*;
import com.tourgeek.tour.product.base.screen.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.booking.db.*;

/**
 *  ProductSearchSession - Base class for the remote side of product searches.
Used for thin searches in booking entry..
 */
public class ProductSearchSession extends Session
{
    public static final String MODIFIED_PARAM = "dirty";
    /**
     * Default constructor.
     */
    public ProductSearchSession() throws RemoteException
    {
        super();
    }
    /**
     * ProductSearchSession Method.
     */
    public ProductSearchSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Map<String, Object> objectID)
    {
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Override this to open the main file for this session.
     */
    public Record openMainRecord()
    {
        Record record = null;
        // NOTE: You MUST override this method
        //record = new Product(this);
        //try {   // Wrap the record in a ProductSessionObject so the default TableSesionObject isn't used.
        //  ProductSessionObject obj = new ProductSessionObject(this, record, null);
        //} catch (RemoteException ex)  {
        //  ex.printStackTrace();
        //}
        return record;
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ProductControl(this);
    }
    /**
     * Add the screen record to this session.
     */
    public Record addScreenRecord()
    {
        return new ProductScreenRecord(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        Record record = this.getMainRecord();
        record.setKeyArea(Product.DESC_SORT_KEY);
        if (!(record.getTable() instanceof GridTable))
        {
            GridTable gridTable = new GridTable(null, record);
            gridTable.setCache(true);  // Typically, the client is a gridscreen which caches the records (so I don't have to!)
        }
        
        Record recVendor = ((ReferenceField)record.getField(Product.VENDOR_ID)).getReferenceRecord();
        record.getField(Product.VENDOR_ID).addListener(new ReadSecondaryHandler(recVendor));
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord();
        recVendor.getField(Product.VENDOR_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        FieldListener listener = null;
        recVendor.getField(Vendor.VENDOR_NAME).addListener(listener = new MoveOnChangeHandler(record.getField(Product.VENDOR_NAME)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        recCurrencys.getField(Currencys.CURRENCY_CODE).addListener(listener = new MoveOnChangeHandler(record.getField(Product.CURRENCY_CODE)));
        listener.setRespondsToMode(DBConstants.READ_MOVE, true);
        this.selectGridFields();    // Initial value
        
        record.addListener(new MoveOnValidHandler(record.getField(Product.CURRENCY_CODE_LOCAL), this.getRecord(BookingModel.BOOKING_FILE).getField(BookingModel.CURRENCY_CODE)));
        
        this.addRateMessageListeners((Product)this.getMainRecord(), (ProductScreenRecord)this.getScreenRecord());
        this.addAvailabilityMessageListeners((Product)this.getMainRecord(), (ProductScreenRecord)this.getScreenRecord());
        this.addPriceListeners((Product)this.getMainRecord());
    }
    /**
     * AddPriceListeners Method.
     */
    public void addPriceListeners(Product recProduct)
    {
        recProduct.getField(Product.PRODUCT_PRICE_LOCAL).addListener(new AddCommissionHandler(null));
    }
    /**
     * Select the fields required for the grid screen.
     */
    public void selectGridFields()
    {
        Record record = this.getMainRecord();
        
        record.setSelected(false);
        record.getField(Product.ID).setSelected(true);
        record.getField(Product.DESCRIPTION).setSelected(true);
        record.getField(Product.PRODUCT_TYPE).setSelected(true);
        record.getField(Product.DISPLAY_COST_STATUS_ID).setSelected(true);
        record.getField(Product.PRODUCT_COST).setSelected(true);
        record.getField(Product.PRODUCT_COST_LOCAL).setSelected(true);
        record.getField(Product.PRODUCT_PRICE_LOCAL).setSelected(true);
        record.getField(Product.PP_PRICE_LOCAL).setSelected(true);
        record.getField(Product.INVENTORY_AVAILABILITY).setSelected(true);
        record.getField(Product.DISPLAY_INVENTORY_STATUS_ID).setSelected(true);
        record.getField(Product.VENDOR_ID).setSelected(true);
        record.getField(Product.CURRENCY_CODE).setSelected(true);
        record.getField(Product.CURRENCY_CODE_LOCAL).setSelected(true);
        record.getField(Product.VENDOR_NAME).setSelected(true);
        
        GetProductCostHandler listener = (GetProductCostHandler)record.getListener(GetProductCostHandler.class, false);
        if (listener != null)
            listener.clearCache();
        GetProductAvailabilityHandler listener2 = (GetProductAvailabilityHandler)record.getListener(GetProductAvailabilityHandler.class, false);
        if (listener2 != null)
            listener2.clearCache();
    }
    /**
     * Select the fields for the maint screen.
     */
    public void selectMaintFields()
    {
        Record record = this.getMainRecord();
        
        record.setSelected(true);
        record.getField(Product.DESC_SORT).setSelected(false);
        record.getField(Product.ITINERARY_DESC).setSelected(false);
        
        GetProductCostHandler listener = (GetProductCostHandler)record.getListener(GetProductCostHandler.class, false);
        if (listener != null)
            listener.clearCache();
        GetProductAvailabilityHandler listener2 = (GetProductAvailabilityHandler)record.getListener(GetProductAvailabilityHandler.class, false);
        if (listener2 != null)
            listener2.clearCache();
    }
    /**
     * Override this to do an action sent from the client.
     * @param strCommand The command to execute
     * @param properties The properties for the command
     * @returns Object Return a Boolean.TRUE for success, Boolean.FALSE for failure.
     */
    public Object doRemoteCommand(String strCommand, Map<String,Object> properties) throws RemoteException, DBException
    {
        if (SearchConstants.REQUERY_COMMAND.equalsIgnoreCase(strCommand))
        {
            Record recProduct = this.getMainRecord();
            Record recProductVars = this.getScreenRecord();
            recProductVars.handleNewRecord(); // Init the fields without changing the status (no table).
            if (properties.get(MODIFIED_PARAM) != null)
                properties.remove(MODIFIED_PARAM);
            FileListener listenerLast = this.removeTempListeners();
            this.setScreenFields(properties);
            this.markTempListeners(listenerLast);
            this.getMainRecord().close();
            this.getMainRecord().open();    // This forces a requery on the file (clears the autorecord cache)
            if (Boolean.TRUE.equals(properties.get(MODIFIED_PARAM)))
            {
                properties.put(SearchConstants.SUCCESS, Boolean.TRUE);
                properties.remove(MODIFIED_PARAM);
                return properties;
            }
            return Boolean.TRUE;    // Success
        }
        if (SearchConstants.SELECT_MAINT.equalsIgnoreCase(strCommand))
        {
            Record recProduct = this.getMainRecord();
            recProduct.close();
            this.selectMaintFields();
            TableSession session = (TableSession)this.getRemoteTable(this.getMainRecord().getTableNames(false));
            session.setFieldTypes(this.getMainRecord(), -1);
            return Boolean.TRUE;    // Success
        }
        if (SearchConstants.SELECT_GRID.equalsIgnoreCase(strCommand))
        {
            Record recProduct = this.getMainRecord();
            recProduct.close();
            recProduct.setKeyArea(Product.DESC_SORT_KEY);  // Default order
            this.selectGridFields();
            TableSession session = (TableSession)this.getRemoteTable(this.getMainRecord().getTableNames(false));
            session.setFieldTypes(this.getMainRecord(), -1);
            return Boolean.TRUE;    // Success
        }
        return super.doRemoteCommand(strCommand, properties);
    }
    /**
     * Remove any of the temporary file filters added on the last time through.
     */
    public FileListener removeTempListeners()
    {
        FileListener listenerLast = null;
        Record recProduct = this.getMainRecord();
        FileListener listenerNext = recProduct.getListener();
        while (listenerNext != null)
        {
            if ((listenerNext.getMasterSlaveFlag() & (FileListener.INITED_IN_SLAVE * 2)) != 0)
            {
                recProduct.removeListener(listenerNext, true);
                listenerNext = listenerLast;
                if (listenerNext == null)
                    listenerNext = recProduct.getListener();
            }
            else
            {
                listenerLast = listenerNext;    // Last one that wasn't removed
                listenerNext = (FileListener)listenerNext.getNextListener();
            }
        }
        return listenerLast;
    }
    /**
     * Mark all the listeners past this one as temporary.
     */
    public void markTempListeners(FileListener listenerLast)
    {
        while (listenerLast != null)
        {
            listenerLast = (FileListener)listenerLast.getNextListener();
            if (listenerLast != null)
                listenerLast.setMasterSlaveFlag(listenerLast.getMasterSlaveFlag() | (FileListener.INITED_IN_SLAVE * 2));
        }
    }
    /**
     * Add the listeners and message queues for rate lookups.
     */
    public void addRateMessageListeners(Product recProduct, ProductScreenRecord screenRecord)
    {
        // Override this to add the listeners and message queues (remember to call super)
        this.getMainRecord().getField(Product.PRODUCT_COST).setSelected(true);  // Now you can calc the USD amount (since you have this local amount)
        this.getMainRecord().getField(Product.PRODUCT_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Product.PRODUCT_COST_LOCAL)));
        this.getMainRecord().getField(Product.PP_COST).setSelected(true);  // Now you can calc the USD amount (since you have this local amount)
        this.getMainRecord().getField(Product.PP_COST).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Product.PP_COST_LOCAL)));
        // Create a private messageReceiver and listen for changes
        MessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
        Integer intRegistryID = null;
        if (messageManager != null)
        {
            Object source = this;
            BaseMessageFilter messageFilter = new BaseMessageFilter(MessageConstants.TRX_RETURN_QUEUE, MessageConstants.INTERNET_QUEUE, source, null);
            messageManager.addMessageFilter(messageFilter);
            this.addProductRateMessageFilter(recProduct, messageFilter);
            recProduct.addListener(new FreeOnFreeHandler(messageFilter));
            intRegistryID = messageFilter.getRegistryID();
            recProduct.addListener(this.getProductCostHandler(screenRecord, intRegistryID));
        }
    }
    /**
     * AddProductRateMessageFilter Method.
     */
    public ProductRateMessageListener addProductRateMessageFilter(Product recProduct, BaseMessageFilter messageFilter)
    {
        return new ProductRateMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductCostHandler Method.
     */
    public GetProductCostHandler getProductCostHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetProductCostHandler(screenRecord, intRegistryID);
    }
    /**
     * SetScreenFields Method.
     */
    public void setScreenFields(Map<String,Object> properties)
    {
        Record recProduct = this.getMainRecord();
        this.addThisRecordFilter(properties, City.CITY_FILE, JTreePanel.LOCATION, Product.CITY_ID, ProductScreenRecord.CITY_ID);
        Date date = this.getPropertyDate((String)properties.get(SearchConstants.DATE));
        if (date != null)
            ((DateTimeField)this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE)).setDateTime(date, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
        String strSearchText = (String)properties.get(SearchConstants.SEARCH_TEXT);
        if ((strSearchText != null) && (strSearchText.length() > 0))
            this.getScreenRecord().getField(ProductScreenRecord.DESCRIPTION).setString(strSearchText.toUpperCase());
        
        String strPax = (String)properties.get(SearchConstants.PAX);
        if ((strPax == null) || (strPax.length() == 0) || (strPax.equals("0")))     // Pax will be correct when pax are in a booking.
            strPax = "2";
        this.getScreenRecord().getField(ProductScreenRecord.PAX).setString(strPax);
        // For now:
        this.getScreenRecord().getField(ProductScreenRecord.REMOTE_QUERY_ENABLED).setState(true);
    }
    /**
     * RestoreProductParam Method.
     */
    public void restoreProductParam(Map<String,Object> properties, int iFieldSeq, String strParamName)
    {
        if (strParamName == null)
            strParamName = this.getScreenRecord().getField(iFieldSeq).getFieldName();
        if (properties.get(strParamName) != null)
        {
            this.getScreenRecord().getField(iFieldSeq).setString((String)properties.get(strParamName));
        }
    }
    /**
     * AddAvailabilityMessageListeners Method.
     */
    public void addAvailabilityMessageListeners(Product recProduct, ProductScreenRecord screenRecord)
    {
        // Override this to add the listeners and message queues (remember to call super)
        // Create a private messageReceiver and listen for changes
        MessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
        Integer intRegistryID = null;
        if (messageManager != null)
        {
            Object source = this;
            BaseMessageFilter messageFilter = new BaseMessageFilter(MessageConstants.TRX_RETURN_QUEUE, MessageConstants.INTERNET_QUEUE, source, null);
            messageManager.addMessageFilter(messageFilter);
            this.addProductAvailabilityMessageFilter(recProduct, messageFilter);
            recProduct.addListener(new FreeOnFreeHandler(messageFilter));
            intRegistryID = messageFilter.getRegistryID();
            recProduct.addListener(this.getProductAvailabilityHandler(screenRecord, intRegistryID));
        }
    }
    /**
     * AddProductAvailabilityMessageFilter Method.
     */
    public ProductAvailabilityMessageListener addProductAvailabilityMessageFilter(Product recProduct, BaseMessageFilter messageFilter)
    {
        return new ProductAvailabilityMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductAvailabilityHandler Method.
     */
    public GetProductAvailabilityHandler getProductAvailabilityHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetProductAvailabilityHandler(screenRecord, intRegistryID);
    }
    /**
     * If this location filter is specified, add this location filter.
     * @param properties properties passed from the user.
     * @param strRecordName Name of the location record I'm looking for (null for the reference record of this field).
     * @param strParamName The param name to get/set this value, ID, and record.
     * @param iFieldSeq Sequence of the field to set the filter on.
     * @returns true If the filter was added.
     */
    public boolean addThisRecordFilter(Map<String,Object> properties, String strRecordName, String strParamName, String iFieldSeq, String iScreenRecordField)
    {
        if (strParamName == null)
            strParamName = JTreePanel.LOCATION;
        String strLocation = (String)properties.get(strParamName);
        String strLocationID = (String)properties.get(strParamName + DBParams.ID);
        if ((strLocation != null) && (strLocation.length() > 0))
        {
            if ((strLocationID == null) || (strLocationID.length() == 0))
                strLocationID = this.findRecord(properties, strRecordName, strParamName, iScreenRecordField);
            String strLocationRecord = (String)properties.get(strParamName + MenuConstants.RECORD);
        
            if ((strRecordName == null) || (strRecordName.equals(strLocationRecord)))
            {
                this.addObjectIDFilter(strLocationID, iFieldSeq, iScreenRecordField);
                return true;
            }
        }
        return false;
    }
    /**
     * Given this reference field and key, create a filter.
     */
    public void addObjectIDFilter(String strID, String iFieldSeq, String iScreenRecordField)
    {
        Record screenRecord = this.getScreenRecord();
        ReferenceField fldReference = (ReferenceField)screenRecord.getField(iScreenRecordField);
        Record recLocation = fldReference.getReferenceRecord();
        try   {
            recLocation = recLocation.setHandle(strID, DBConstants.OBJECT_ID_HANDLE);
        } catch (DBException ex)    {
            ex.printStackTrace();
            recLocation = null;
        }
        if (recLocation != null)
        {
            Record recProduct = this.getMainRecord();
            recProduct.addListener(new ExtractRangeFilter(iFieldSeq, recLocation.getField(DBConstants.MAIN_FIELD)));
        }
    }
    /**
     * From the location text, look up a matching (or close) location.
     */
    public String findRecord(Map<String,Object> properties, String strRecordName, String strParamName, String iScreenRecordField)
    {
        String strLocationText = (String)properties.get(strParamName);
        if ((strLocationText == null) || (strLocationText.length() == 0))
            return null;
        Record screenRecord = this.getScreenRecord();
        ReferenceField fldReference = (ReferenceField)screenRecord.getField(iScreenRecordField);
        Record recLocation = fldReference.getReferenceRecord();
        if (recLocation == null)
            this.getRecord(strRecordName);
        if (recLocation == null)
        {
            if (Continent.CONTINENT_FILE.equalsIgnoreCase(strRecordName))
                recLocation = new Continent(this);
            else if (Region.REGION_FILE.equalsIgnoreCase(strRecordName))
                recLocation = new Region(this);
            else if (Country.COUNTRY_FILE.equalsIgnoreCase(strRecordName))
                recLocation = new Country(this);
            else if (State.STATE_FILE.equalsIgnoreCase(strRecordName))
                recLocation = new State(this);
            else if (City.CITY_FILE.equalsIgnoreCase(strRecordName))
                recLocation = new City(this);
        }
        if (recLocation == null)
            return null;
        try {
            int iCodeLength = recLocation.getKeyArea(Location.CODE_KEY).getField(DBConstants.MAIN_FIELD).getMaxLength();
            boolean bSuccess = false;
            if (strLocationText.length() <= iCodeLength)
            {
                recLocation.setKeyArea(Location.CODE_KEY);
                recLocation.getKeyArea(Location.CODE_KEY).getField(DBConstants.MAIN_FIELD).setString(strLocationText);
                bSuccess = recLocation.seek(null);
            }
            if (!bSuccess)
            {
                recLocation.setKeyArea(Location.NAME_KEY);
                recLocation.getKeyArea(Location.NAME_KEY).getField(DBConstants.MAIN_FIELD).setString(strLocationText);
                bSuccess = recLocation.seek(">=");
                if (bSuccess)
                    if (!recLocation.getKeyArea(Location.NAME_KEY).getField(DBConstants.MAIN_FIELD).toString().toUpperCase().startsWith(strLocationText.toUpperCase()))
                        bSuccess = false;
            }
            if (bSuccess)
            {
                properties.put(MODIFIED_PARAM, Boolean.TRUE);
                properties.put(strParamName, recLocation.getKeyArea(Location.NAME_KEY).getField(DBConstants.MAIN_FIELD).toString());
                properties.put(strParamName + DBParams.ID, recLocation.getHandle(DBConstants.OBJECT_ID_HANDLE).toString());
                if (strRecordName != null)
                    properties.put(strParamName + MenuConstants.RECORD, strRecordName);
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return (String)properties.get(strParamName + DBParams.ID);
    }
    /**
     * Convert the date from UTF format and return it.
     */
    public Date getPropertyDate(String strDate)
    {
        if ((strDate == null) || (strDate.length() == 0))
            return null;
        Converter.initGlobals();
        Date date = null;
        try   {
            synchronized (Converter.gCalendar)
            {
                date = Converter.gGMTDateTimeFormat.parse(strDate);
            }
        } catch (ParseException ex)   {
            ex.printStackTrace();
        }
        return date;
    }

}
