package hu.ace.geaapp.data.network;

public class HttpCall {


    public enum RequestMethod{
        GET("GET"), POST("POST"),PUT("PUT"),DELETE("DELETE");

        private final String requestMethod;

        RequestMethod(String requestMethod){
            this.requestMethod = requestMethod;
        }

        public String getValue(){
            return requestMethod;
        }
    }


    private String url;
    private int methodtype;
    private RequestMethod method;
    private String params;

    private boolean useUserAuth = false;


    private HttpRequestAsyncTask t = new HttpRequestAsyncTask();


    public HttpCall(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserAuth(boolean value){
        this.useUserAuth = value;
    }

    public boolean isUserAuth() {
        return useUserAuth;
    }

    public int getMethodtype() {
        return methodtype;
    }

    public void setMethodtype(int methodtype) {
        this.methodtype = methodtype;
    }

    public void setMethod(RequestMethod r){
        method = r;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public HttpRequestAsyncTask get() {
        return t;
    }

}
