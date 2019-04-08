/**
 * Created by OYurkiv on 1/2/2019.
 */
public class ProxyObject {
    private String proxyIp;
    private String proxyPort;
    private String proxyType;

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            ProxyObject proxyObject = (ProxyObject) object;
            //if (this.proxyIp.equals(proxyObject.getProxyIp()) && this.proxyPort.equals(proxyObject.getProxyPort())) {
            if (this.proxyIp.equals(proxyObject.getProxyIp())) {
                result = true;
            }
        }
        return result;
    }
}
