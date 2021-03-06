/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.thin.app.booking.entry.search.air;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.screen.landf.ScreenUtil;
import org.jbundle.thin.base.util.ThinUtil;

import com.tourgeek.thin.app.booking.entry.search.base.JProductContextScreen;
import com.tourgeek.thin.tour.product.air.db.Air;
import com.tourgeek.thin.tour.product.base.db.Product;

/**
 * Main Class for Product Context screen.
 */
public class JAirContextScreen extends JProductContextScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JAirContextScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JAirContextScreen(Object parent, Object obj)
    {
        this();
        this.init(parent, obj);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(Object parent, Object obj)
    {
        super.init(parent, obj);
    }
    /**
     * Get this field (or return null if this field doesn't belong on the screen).
     * This is the method to use to filter the items to display on the screen.
     */
    public Converter getFieldForScreen(int iIndex)
    {
        FieldList fieldList = this.getFieldList();
        switch (iIndex)
        {
            case 0:
            case 1:
                return super.getFieldForScreen(iIndex);
            case 2:
                return fieldList.getField(Air.CITY_CODE);
            case 3:
                return fieldList.getField(Air.TO_CITY_CODE);
            case 4:
            case 5:
            case 6:
                return super.getFieldForScreen(iIndex - 1);
            case 7:
                return fieldList.getField(Product.VENDOR_NAME);
        }
        return null;
    }
    /**
     * Add the screen controls to the second column of the grid.
     */
    public JComponent createScreenComponent(Converter fieldInfo)
    {
        if (fieldInfo.getFieldName().equals(Air.CITY_CODE))
        {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            
            JComponent labelDesc = super.createScreenComponent(fieldInfo);
            ScreenUtil.setEnabled(labelDesc, false);
            panel.add(labelDesc);
            
            labelDesc = super.createScreenComponent(this.getFieldList().getField(Air.ETD));
            ScreenUtil.setEnabled(labelDesc, false);
            panel.add(labelDesc);
            
            return panel;
        }
        if (fieldInfo.getFieldName().equals(Air.TO_CITY_CODE))
        {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            
            JComponent labelDesc = super.createScreenComponent(fieldInfo);
            ScreenUtil.setEnabled(labelDesc, false);
            panel.add(labelDesc);
            
            labelDesc = super.createScreenComponent(this.getFieldList().getField(Air.ARRIVE_TIME));
            ScreenUtil.setEnabled(labelDesc, false);
            panel.add(labelDesc);
            
            labelDesc = super.createScreenComponent(this.getFieldList().getField(Air.DAYS));
            ScreenUtil.setEnabled(labelDesc, false);
            panel.add(labelDesc);
            
            return panel;
        }
        return super.createScreenComponent(fieldInfo);
    }
}
