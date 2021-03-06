/**
  * @(#)DeductionModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.payroll.db;

import org.jbundle.model.db.*;

public interface DeductionModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String TYPE = "Type";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String ENTER_HOURS = "EnterHours";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String DEDUCTION_SCREEN_CLASS = "com.tourgeek.tour.payroll.screen.misc.DeductionScreen";

    public static final String DEDUCTION_FILE = "Deduction";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.payroll.db.Deduction";
    public static final String THICK_CLASS = "com.tourgeek.tour.payroll.db.Deduction";

}
