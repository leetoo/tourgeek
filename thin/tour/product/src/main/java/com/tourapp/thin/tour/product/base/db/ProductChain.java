/**
 * @(#)ProductChain.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.base.db.*;

public class ProductChain extends FieldList
    implements ProductChainModel
{

    public ProductChain()
    {
        super();
    }
    public ProductChain(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}