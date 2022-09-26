package ua.goit;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class URLBuilder {
    private String url;
    private final List<String> params;

    public URLBuilder() {
        url = "";
        params = new ArrayList<>();
    }

    public URLBuilder(String url) {
        this();
        this.url = url;
    }

    public URLBuilder withParam(String name, String value) throws UnsupportedEncodingException {
        String charset = Charset.defaultCharset().name();
        String encodedName = URLEncoder.encode(name, charset);
        String encodedValue = URLEncoder.encode(value, charset);
        params.add(encodedName + "=" + encodedValue);
        return this;
    }

    public URL build() throws MalformedURLException {
        return new URL(this.toString());
    }

    @Override
    public String toString() {
        return url + "?" + String.join("&", params);
    }
}
