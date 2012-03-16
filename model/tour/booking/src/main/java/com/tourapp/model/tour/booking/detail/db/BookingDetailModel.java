/**
 * @(#)BookingDetailModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingDetailModel extends BookingSubModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String BOOKING_ID = BOOKING_ID;
    //public static final String BOOKING_PAX_ID = BOOKING_PAX_ID;
    //public static final String MODULE_ID = MODULE_ID;
    //public static final String TOUR_HEADER_DETAIL_ID = TOUR_HEADER_DETAIL_ID;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODULE_START_DATE = MODULE_START_DATE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String PRODUCT_TYPE = PRODUCT_TYPE;
    //public static final String REMOTE_REFERENCE_NO = REMOTE_REFERENCE_NO;
    public static final String PRODUCT_TYPE_ID = "ProductTypeID";
    public static final String DETAIL_DATE = "DetailDate";
    public static final String GMT_DATE = "GMTDate";
    public static final String GMT_OFFSET = "GMTOffset";
    public static final String PRODUCT_ID = "ProductID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String MARKUP_FROM_LAST = "MarkupFromLast";
    public static final String VENDOR_ID = "VendorID";
    public static final String TOUR_ID = "TourID";
    public static final String AP_TRX_ID = "ApTrxID";
    public static final String PRICING_DETAIL_KEY = "PricingDetailKey";
    public static final String TOTAL_COST = "TotalCost";
    public static final String EXCHANGE = "Exchange";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String TOTAL_COST_LOCAL = "TotalCostLocal";
    public static final String TOTAL_PRICE_LOCAL = "TotalPriceLocal";
    public static final String INFO_MESSAGE_TRANSPORT_ID = "InfoMessageTransportID";
    public static final String INFO_STATUS_ID = "InfoStatusID";
    public static final String INFO_REQUEST_KEY = "InfoRequestKey";
    public static final String INFO_STATUS_REQUEST = "InfoStatusRequest";
    public static final String COST_MESSAGE_TRANSPORT_ID = "CostMessageTransportID";
    public static final String COST_STATUS_ID = "CostStatusID";
    public static final String COST_REQUEST_KEY = "CostRequestKey";
    public static final String COST_STATUS_REQUEST = "CostStatusRequest";
    public static final String INVENTORY_MESSAGE_TRANSPORT_ID = "InventoryMessageTransportID";
    public static final String INVENTORY_STATUS_ID = "InventoryStatusID";
    public static final String INVENTORY_REQUEST_KEY = "InventoryRequestKey";
    public static final String INVENTORY_STATUS_REQUEST = "InventoryStatusRequest";
    public static final String PRODUCT_MESSAGE_TRANSPORT_ID = "ProductMessageTransportID";
    public static final String PRODUCT_STATUS_ID = "ProductStatusID";
    public static final String PRODUCT_REQUEST_KEY = "ProductRequestKey";
    public static final String PRODUCT_STATUS_REQUEST = "ProductStatusRequest";
    public static final String REMOTE_BOOKING_NO = "RemoteBookingNo";
    public static final String ACK_DAYS = "AckDays";
    public static final String DETAIL_END_DATE = "DetailEndDate";
    public static final String GMT_END_DATE = "GMTEndDate";
    public static final String MEAL_SUMMARY = "MealSummary";
    public static final String STATUS_SUMMARY = "StatusSummary";
    public static final String ITINERARY_DESC = "ItineraryDesc";
    public static final String PROPERTIES = "Properties";
    public static final String ERROR_PROPERTIES = "ErrorProperties";
    public static final String PP_COST = "PPCost";
    public static final String PP_PRICE_LOCAL = "PPPriceLocal";
    public static final String NIGHTS = "Nights";
    public static final String MEAL_PLAN_1ID = "MealPlan1ID";
    public static final String MEAL_PLAN_1_QTY = "MealPlan1Qty";
    public static final String MEAL_PLAN_1_DAYS = "MealPlan1Days";
    public static final String MEAL_PLAN_2ID = "MealPlan2ID";
    public static final String MEAL_PLAN_2_QTY = "MealPlan2Qty";
    public static final String MEAL_PLAN_2_DAYS = "MealPlan2Days";
    public static final String MEAL_PLAN_3ID = "MealPlan3ID";
    public static final String MEAL_PLAN_3_QTY = "MealPlan3Qty";
    public static final String MEAL_PLAN_3_DAYS = "MealPlan3Days";
    public static final String MEAL_PLAN_4ID = "MealPlan4ID";
    public static final String MEAL_PLAN_4_QTY = "MealPlan4Qty";
    public static final String MEAL_PLAN_4_DAYS = "MealPlan4Days";
    public static final String SINGLE_PAX = "SinglePax";
    public static final String DOUBLE_PAX = "DoublePax";
    public static final String TRIPLE_PAX = "TriplePax";
    public static final String QUAD_PAX = "QuadPax";
    public static final String CHILDREN = "Children";
    public static final String SINGLE_COST = "SingleCost";
    public static final String DOUBLE_COST = "DoubleCost";
    public static final String TRIPLE_COST = "TripleCost";
    public static final String QUAD_COST = "QuadCost";
    public static final String CHILDREN_COST = "ChildrenCost";
    public static final String ROOM_COST = "RoomCost";
    public static final String MEAL_COST = "MealCost";
    public static final String ROOM_COST_LOCAL = "RoomCostLocal";
    public static final String MEAL_COST_LOCAL = "MealCostLocal";
    public static final String VARIES_CODE = "VariesCode";
    public static final String VARIES_QTY = "VariesQty";
    public static final String VARIES_COST = "VariesCost";
    public static final String PMC_CUTOFF = "PMCCutoff";
    public static final String PMC_COST = "PMCCost";
    public static final String SIC_COST = "SICCost";
    public static final String BOOKING_AIR_HEADER_ID = "BookingAirHeaderID";
    public static final String GMT_TIME = "GMTTime";
    public static final String ETD = "Etd";
    public static final String XO = "XO";
    public static final String CITY_CODE = "CityCode";
    public static final String CITY_DESC = "CityDesc";
    public static final String AIRLINE_ID = "AirlineID";
    public static final String CARRIER = "Carrier";
    public static final String FLIGHT_NO = "FlightNo";
    public static final String FLIGHT_CLASS = "FlightClass";
    public static final String TO_CITY_CODE = "ToCityCode";
    public static final String TO_CITY_DESC = "ToCityDesc";
    public static final String ARRIVE_TIME = "ArriveTime";
    public static final String ADD_DAYS = "AddDays";
    public static final String ARC_STATUS = "ARCStatus";
    public static final String FARE_BASIS = "FareBasis";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String ALLOW = "Allow";
    public static final String DET_FARE = "DetFare";
    public static final String SEGMENT_CONF_NO = "SegmentConfNo";
    public static final String SEGMENT_CONFIRMED_BY = "SegmentConfirmedBy";
    public static final String COUPON = "Coupon";
    public static final String SEAT = "Seat";
    public static final String GATE = "Gate";
    public static final String SEAT_PREF = "SeatPref";
    public static final String SMOKING = "Smoking";
    public static final String MEALS = "Meals";
    public static final String DAYS = "Days";
    public static final String QUANTITY = "Quantity";
    public static final String ASK_FOR_ANSWER = "AskForAnswer";
    public static final String ALWAYS_RESOLVE = "AlwaysResolve";
    public static final String PRICING_TYPE_ID = "PricingTypeID";

    public static final String PRODUCT_ID_KEY = "ProductID";

    public static final String AP_TRX_ID_KEY = "ApTrxID";

    public static final String TOUR_ID_KEY = "TourID";

    public static final String DETAIL_DATE_KEY = "DetailDate";

    public static final String VENDOR_ID_KEY = "VendorID";
    public static final String MESSAGE_PARAM = "message";
    public static final String ERROR_PARAM = "error";
    public static final String INFO_PARAM = "info";
    public static final String COST_PARAM = "cost";
    public static final String INVENTORY_PARAM = "inventory";
    public static final String PRODUCT_PARAM = "product";

    public static final String BOOKING_DETAIL_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingDetail";

}
