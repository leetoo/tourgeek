/**
  * @(#)ProductCategoryModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface ProductCategoryModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String INCOME_ACCOUNT_ID = "IncomeAccountID";
    public static final String AR_ACCOUNT_ID = "ArAccountID";
    public static final String PP_ACCOUNT_ID = "PPAccountID";
    public static final String XL_CHG_ACCOUNT_ID = "XLChgAccountID";
    public static final String LAND_ACCOUNT_ID = "LandAccountID";
    public static final String UNINV_ACCOUNT_ID = "UninvAccountID";
    public static final String COST_OU_ACCOUNT_ID = "CostOUAccountID";
    public static final String AP_ACCOUNT_ID = "ApAccountID";
    public static final String CURR_OU_ACCOUNT_ID = "CurrOUAccountID";
    public static final String AIR_ACCOUNT_ID = "AirAccountID";
    public static final String PP_TIC_ACCOUNT_ID = "PPTicAccountID";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String PRODUCT_CATEGORY_FILE = "ProductCategory";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.base.db.ProductCategory";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.base.db.ProductCategory";

}
