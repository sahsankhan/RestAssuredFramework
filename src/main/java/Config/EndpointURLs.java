package Config;

public class EndpointURLs {

    //////Login////////
    public static final String loginUser = "/admin/login";

    //OrdersManagement
    public static final String getArchiveOrders = "/admin/orders/inactive?page=1&limit=20";
    public static final String getActiveOrders = "/admin/orders?page=1&limit=20";
    public static final String getServiceableZipcodes = "/checkout-widget/serviceable-zipcodes";
    public static final String getDeliverySlots = "/checkout-widget/get-delivery-slots";
    public static final String rescheduleDelivery = "/admin/deliveries/reschedule/";
    public static final String cancelDelivery = "/deliv-service/cancel-delivery/";

    /////////Edit Locations///////
    public static final String getAllLocations = "/admin/locations";
    public static final String getSingleLocation = "/admin/locations/";
    public static final String addZipCode = "/admin/locations/delivery/";

    //////////DeliveryLocationSettings//////////
    public static final String addSlot = "/admin/locations/slot/";
    public static final String deleteSlot = "/admin/locations/slot/";
    public static final String addBlackoutDate = "/admin/deliveries/update-blackout-dates/";
    public static final String deleteBlackoutDate = "/admin/deliveries/delete-blackout-date/";
    public static final String updateDeliverySurcharge = "/admin/deliveries/update_surcharge/22";
    public static final String updateFutureDateRange = "/admin/deliveries/update_future_date_range/22";


}
