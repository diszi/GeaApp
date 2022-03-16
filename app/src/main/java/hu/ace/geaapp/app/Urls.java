package hu.ace.geaapp.app;

public class Urls {

    public static String LOGIN_URL =null;
    public static String GET_VEHICLES_LIST = null;
    public static String GET_VEHICLE = null;
    public static String GET_VEHICLE_DETAILS = null;

    public static String SOAP_CREATE_DAMAGE = null;
    public static String SOAP_CREATE_ACCESSORIES = null;

    public static String SOAP_UPDATE_ACCESSORIES = null;
    public static String SOAP_UPDATE_STRUCT_FEATURES = null;


    public static String DELETE_DAMAGE = null;


    public static void setURLs(String ipAddress){
        LOGIN_URL ="http://"+ ipAddress+"/maxrest/rest/login";
        GET_VEHICLES_LIST = "http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053";

        SOAP_CREATE_DAMAGE = "http://192.168.133.51/meaweb/services/ACE_MOB_ACE_GEA_DAMAGE";


        SOAP_CREATE_ACCESSORIES = "http://192.168.133.51/meaweb/services/ACE_MOB_ACE_GEA_DAMAGE";

        SOAP_UPDATE_ACCESSORIES = "http://192.168.133.51/meaweb/services/ACE_MOB_ACE_GEA_DAMAGE";

        SOAP_UPDATE_STRUCT_FEATURES = "http://192.168.133.51/meaweb/services/ACE_MOB_ACE_GEA_DAMAGE";

        GET_VEHICLE ="http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053";

        DELETE_DAMAGE = "http://192.168.133.51/maxrest/rest/os/ACE_GJK_KAR/";//{id}
    }

}
