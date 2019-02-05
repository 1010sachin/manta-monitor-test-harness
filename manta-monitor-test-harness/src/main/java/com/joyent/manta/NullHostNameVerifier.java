package com.joyent.manta;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class NullHostNameVerifier implements HostnameVerifier {
    @Override
    public  boolean verify(final String hostname, final SSLSession sslSession) {
        return true;
    }
}
