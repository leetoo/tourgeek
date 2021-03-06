/**
  * @(#)BookingSub.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.detail.db;

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
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.product.tour.detail.db.*;
import com.tourgeek.tour.booking.detail.event.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.booking.db.event.*;
import com.tourgeek.model.tour.booking.db.*;
import com.tourgeek.model.tour.product.tour.detail.db.*;
import java.util.*;
import com.tourgeek.tour.base.field.*;
import com.tourgeek.model.tour.booking.detail.db.*;

/**
 *  BookingSub - Customer Sale Sub-File.
 */
public class BookingSub extends VirtualRecord
     implements BookingSubModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingSub()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingSub(RecordOwner screen)
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

    public static final String BOOKING_SUB_FILE = null;   // Screen field
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
            field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new BookingPaxField(this, BOOKING_PAX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new TourHeaderField(this, MODULE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new TourHeaderDetailField(this, TOUR_HEADER_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new TourHeaderOptionField(this, TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new DateTimeField(this, MODULE_START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new StringField(this, DESCRIPTION, 60, null, null);
        if (iFieldSeq == 10)
        {
            field = new StringField(this, PRODUCT_TYPE, 15, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 11)
            field = new StringField(this, REMOTE_REFERENCE_NO, 60, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * AddDetailBehaviors Method.
     */
    public void addDetailBehaviors(BookingModel recBooking, TourModel recTour)
    {
        FileListener subFileBeh = new SubFileFilter((Record)recBooking, true);
        this.addListener(subFileBeh);
        this.setKeyArea(BookingSub.BOOKING_ID_KEY);
        RecordOwner screen = this.getRecordOwner();
        if (screen != null) if (screen instanceof GridScreenParent)
        {
            FieldListener reSelect = new FieldReSelectHandler((GridScreenParent)screen);
            ((BaseField)recBooking.getField(Booking.ID)).addListener(reSelect);
        }
        this.addListener(new InitBookingDetailHandler((Booking)recBooking, (Tour)recTour));
    }
    /**
     * InitBookingDetailFields Method.
     */
    public int initBookingDetailFields(BookingModel recBooking, TourModel recTour, boolean bOnlyIfTargetIsNull)
    {
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingSub.BOOKING_ID).isNull()))
            this.getField(BookingSub.BOOKING_ID).moveFieldToThis((BaseField)recBooking.getField(Booking.ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if ((!bOnlyIfTargetIsNull) || (this.getField(BookingSub.MODULE_ID).isNull()))
            this.getField(BookingSub.MODULE_ID).moveFieldToThis((BaseField)recTour.getField(Tour.TOUR_HEADER_ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Add the detail line items from this tour detail.
     * (ie., read through this tour detail and add the items to the current booking detail).
     */
    public int setupAllDetail(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldKeyOrder = recTourHeaderDetail.getDefaultOrder();
        recTourHeaderDetail.setKeyArea(TourHeaderDetail.TOUR_HEADER_OPTION_ID_KEY);
        SubFileFilter subFileBehavior = new SubFileFilter(fldQaID, TourHeaderDetail.TOUR_HEADER_OPTION_ID, null, null, null, null);
        recTourHeaderDetail.addListener(subFileBehavior);
        try   {
            Object[] rgbFieldsEnabled = this.setEnableFieldListeners(false);
            this.addNew();  // This is required to clear InitOnce listeners (I must clear ALL the fields)
            this.setEnableFieldListeners(rgbFieldsEnabled);
            this.addNew();  // This is required to do InitOnce listeners
            recTourHeaderDetail.close();
            while (recTourHeaderDetail.hasNext())
            {
                TourSub recTourHeaderDetail2 = (TourSub)recTourHeaderDetail.next();
                iErrorCode = this.setupDetail(recTourHeaderDetail2, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    continue;   // Error just means this record doesn't need to be written here
                Record recBookingDetail = this.getTable().getCurrentTable().getRecord();
                if (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
                    recBookingDetail.add();
                else if (recBookingDetail.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                    recBookingDetail.set(); // It is possible that setdetail wrote the record
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recTourHeaderDetail.removeListener(subFileBehavior, true);
            recTourHeaderDetail.setKeyArea(iOldKeyOrder);
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Here is a single tour detail item.
     * Add it to the current booking line item detail.
     */
    public int setupDetail(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldOpenMode = this.getOpenMode();
        this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        boolean bListenerEnabledState = true;
        try   {
            if (recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) != null)
                bListenerEnabledState = recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).setEnabledListener(false); // Don't want to sense a broken tour detail (would cause the pricingtype to change)
            if (ModifyCodeField.ADD.equals(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_CODE).getString()))
            {
                this.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);    // This will set the record type
                this.addNew();
                this.setOpenMode(iOldOpenMode);
                BookingSub recBookingDetail = (BookingSub)this.getTable().getCurrentTable().getRecord();
                recBookingDetail.initBookingDetailFields(recBooking, recTour, false);
                recBookingDetail.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                iErrorCode = recBookingDetail.setDetailProductInfo(null, recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
            }
            else // if ((recTourHeaderDetail.getField(TourDetail.MODIFY_CODE) == ModifyCodeField.REPLACE) || (recTourDetail.getField(TourDetail.MODIFY_CODE) == ModifyCodeField.DELETE))
            {
                this.setKeyArea(BookingSub.DETAIL_ACCESS_KEY);
                this.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                this.getField(BookingSub.TOUR_HEADER_DETAIL_ID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_ID));
                boolean bSuccess = this.seek(DBConstants.EQUALS);
                if (bSuccess)
                {
                    BookingSub recBookingDetail = (BookingSub)this.getTable().getCurrentTable().getRecord();
                    if (ModifyCodeField.REPLACE.equals(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_CODE).getString()))
                    {
                        recBookingDetail.edit();
                        iErrorCode = recBookingDetail.setDetailProductInfo(null, recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
                    }
                    else // if (recTourHeaderDetail.getField(TourDetail.MODIFY_CODE) == ModifyCodeField.DELETE))
                    {
                        recBookingDetail.remove();
                    }
                }
                else
                {
                    if (ModifyCodeField.REPLACE.equals(recTourHeaderDetail.getField(TourHeaderDetail.MODIFY_CODE).getString()))
                    {   // Modify item not found, add.
                        this.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);    // This will set the record type
                        this.addNew();
                        this.setOpenMode(iOldOpenMode);
                        BookingSub recBookingDetail = (BookingSub)this.getTable().getCurrentTable().getRecord();
                        recBookingDetail.initBookingDetailFields(recBooking, recTour, false);
                        recBookingDetail.setupDetailKey(recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID, dateStart);
                        iErrorCode = recBookingDetail.setDetailProductInfo(null, recTourHeaderDetail, recBooking, recTour, fldPaxID, fldQaID, fldModID);
                    }
                    else
                        this.setEditMode(DBConstants.EDIT_NONE);    // Make sure this (blank) record is not written
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return ex.getErrorCode();
        } finally {
            this.setOpenMode(iOldOpenMode);
            if (recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class) != null)
                recBooking.getField(Booking.TOUR_PRICING_TYPE_ID).getListener(ChangePricingTypeHandler.class).setEnabledListener(bListenerEnabledState); // Don't want to sense a broken tour detail (would cause the pricingtype to change)
        }
        return iErrorCode;
    }
    /**
     * Set-up the current product info.
     * If properties are supplied, look in the properties for new values.
     * Else, if the target values are not already set, use the default values
     * supplied in the tour and booking records.
     */
    public int setDetailProductInfo(Map<String,Object> properties, TourSubModel recTourHeaderDetail, BookingModel recBooking, TourModel recTour, Field fldPaxID, Field fldQaID, Field fldModID)
    {
        int iErrorCode = this.setDetailProductFields((TourSub)recTourHeaderDetail, (Booking)recBooking, (Tour)recTour, (BaseField)fldPaxID, (BaseField)fldQaID, (BaseField)fldModID);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            if (properties != null)
                iErrorCode = this.setDetailProductProperties(properties);
        return iErrorCode;
    }
    /**
     * For this tour detail, move all the detail field to the current booking line item detail.
     */
    public int setDetailProductFields(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if (fldModID != null)
            this.getField(BookingSub.MODULE_ID).moveFieldToThis(fldModID);
        if (fldQaID != null)
            this.getField(BookingSub.TOUR_HEADER_OPTION_ID).moveFieldToThis(fldQaID);
        return iErrorCode;
    }
    /**
     * SetDetailProductProperties Method.
     */
    public int setDetailProductProperties(Map<String,Object> properties)
    {
        // Do something in the concrete classes
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Setup the detail key, given this tour detail record.
     */
    public int setupDetailKey(TourSub recTourHeaderDetail, Booking recBooking, Tour recTour, BaseField fldPaxID, BaseField fldQaID, BaseField fldModID, Date dateStart)
    {
        this.getField(BookingSub.BOOKING_ID).moveFieldToThis(recBooking.getField(Booking.ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        this.getField(BookingSub.BOOKING_PAX_ID).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if (fldPaxID != null) if (fldPaxID.getValue() != 0)
            this.getField(BookingSub.BOOKING_PAX_ID).moveFieldToThis(fldPaxID, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if (fldModID != null)
            this.getField(BookingSub.MODULE_ID).moveFieldToThis(fldModID, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        else
            this.getField(BookingSub.MODULE_ID).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        if (recTourHeaderDetail == null)
            this.getField(BookingSub.TOUR_HEADER_DETAIL_ID).initField(DBConstants.DISPLAY);
        else
            this.getField(BookingSub.TOUR_HEADER_DETAIL_ID).moveFieldToThis(recTourHeaderDetail.getField(TourHeaderDetail.ID), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        ((DateTimeField)this.getField(BookingSub.MODULE_START_DATE)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * DeleteAllDetail Method.
     */
    public int deleteAllDetail(Booking recBooking, BaseField fldBookingPaxID, BaseField fldTourModuleID, Date dateStart)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldKeyOrder = this.getDefaultOrder();
        this.setKeyArea(BookingSub.BOOKING_ID_KEY);
        FileListener subFileBehavior = new SubFileFilter(recBooking, true);
        this.addListener(subFileBehavior);
        
        try   {
            this.close();
            while (this.hasNext())
            {
                BookingSub recBookingSub2 = (BookingSub)this.next();
                
                BaseField fldDetailModuleID = this.getField(BookingSub.MODULE_ID);
                Date dateDetailStart = ((DateTimeField)this.getField(BookingSub.MODULE_START_DATE)).getDateTime();
                if (this instanceof BookingDetail)
                    if (this.getField(BookingDetail.PRODUCT_TYPE_ID).getValue() == ProductType.TOUR_ID)
                {
                    fldDetailModuleID = this.getField(BookingDetail.PRODUCT_ID);
                    dateDetailStart = ((DateTimeField)this.getField(BookingDetail.DETAIL_DATE)).getDateTime();                    
                }
                if ((fldTourModuleID != null) && (!fldTourModuleID.equals(fldDetailModuleID)))
                    continue;
                if ((dateStart != null) && (!dateStart.equals(dateDetailStart)))
                    continue;
        
                recBookingSub2.edit();
                recBookingSub2.remove();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            this.removeListener(subFileBehavior, true);
            this.setKeyArea(iOldKeyOrder);
        }
        return iErrorCode;
    }
    /**
     * ChangeAllDetail Method.
     */
    public int changeAllDetail(Booking recBooking, BaseField fldBookingPaxID, BaseField fldTourModuleID, Date dateOriginal, Date dateStart)
    {
        if ((dateStart == null) || (dateStart.equals(dateOriginal)))
            return DBConstants.NORMAL_RETURN;   // No change required
        int iDaysChange = (int)((dateStart.getTime() - dateOriginal.getTime() + DBConstants.KMS_IN_A_DAY - 1) / DBConstants.KMS_IN_A_DAY);
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldKeyOrder = this.getDefaultOrder();
        this.setKeyArea(BookingDetail.DETAIL_ACCESS_KEY);
        SubFileFilter subFileBehavior = new SubFileFilter(recBooking.getField(Booking.ID), BookingSub.BOOKING_ID, fldBookingPaxID, BookingSub.BOOKING_PAX_ID, fldTourModuleID, BookingSub.MODULE_ID);
        this.addListener(subFileBehavior);
        try   {
            this.close();
            while (this.hasNext())
            {
                BookingSub recBookingSub2 = (BookingSub)this.next();
                if (((DateTimeField)recBookingSub2.getField(BookingSub.MODULE_START_DATE)).getDateTime().equals(dateOriginal))
                {
                    recBookingSub2.edit();
                    if (recBookingSub2 instanceof BookingDetail)
                    {
                        Calendar calDate = ((DateTimeField)recBookingSub2.getField(BookingDetail.DETAIL_DATE)).getCalendar();
                        calDate.add(Calendar.DATE, iDaysChange);
                        ((DateTimeField)recBookingSub2.getField(BookingDetail.DETAIL_DATE)).setCalendar(calDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    }
                    if (recBookingSub2 instanceof BookingAnswer)
                    {
                        recBookingSub2.getField(BookingAnswer.DETAIL_ADDED).setState(false);
                        if ((recBookingSub2.getField(BookingAnswer.ASK_FOR_ANSWER).getState() == false)
                            || (recBookingSub2.getField(BookingAnswer.ALWAYS_RESOLVE).getState() == true))
                        {   // If automatic or forced, must ask again
                            recBookingSub2.remove();
                            continue;
                        }
                    }
                    ((DateTimeField)recBookingSub2.getField(BookingSub.MODULE_START_DATE)).setDateTime(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                    recBookingSub2.set();
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            this.removeListener(subFileBehavior, true);
            this.setKeyArea(iOldKeyOrder);
        }
        return iErrorCode;
    }
    /**
     * Get the main (Booking) record for this detail record.
     * Note: This will only return the main record if it already exists.
     */
    public BookingModel getBooking(boolean bCreateAndReadCurrent)
    {
        ReferenceField fldBookingID = (ReferenceField)this.getField(BookingSub.BOOKING_ID);
        if (bCreateAndReadCurrent)
            return (Booking)fldBookingID.getReference();
        else
            return (Booking)fldBookingID.getReferenceRecord(null, false);
    }

}
