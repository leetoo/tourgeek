/**
  * @(#)SeatChoiceModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.base.db.shared;

import org.jbundle.model.db.*;

public interface SeatChoiceModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";

    public static final String SEAT_CHOICE_FILE = "SeatChoice";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.base.db.shared.SeatChoice";
    public static final String THICK_CLASS = "com.tourgeek.tour.base.db.shared.SeatChoice";

}
