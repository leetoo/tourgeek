/**
  * @(#)TourCostAnalysisHeading.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.report.tourcost;

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
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.acctpay.report.*;
import com.tourgeek.tour.booking.db.*;

/**
 *  TourCostAnalysisHeading - Tour cost analysis report.
 */
public class TourCostAnalysisHeading extends HeadingScreen
{
    /**
     * Default constructor.
     */
    public TourCostAnalysisHeading()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public TourCostAnalysisHeading(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tour cost analysis report";
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(CostAnalysisScreenRecord.COST_ANALYSIS_SCREEN_RECORD_FILE).getField(CostAnalysisScreenRecord.REPORT_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CostAnalysisScreenRecord.COST_ANALYSIS_SCREEN_RECORD_FILE).getField(CostAnalysisScreenRecord.REPORT_TIME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CostAnalysisScreenRecord.COST_ANALYSIS_SCREEN_RECORD_FILE).getField(CostAnalysisScreenRecord.REPORT_USER_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CostAnalysisScreenRecord.COST_ANALYSIS_SCREEN_RECORD_FILE).getField(CostAnalysisScreenRecord.REPORT_PAGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
