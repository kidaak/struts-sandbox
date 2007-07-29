package org.apache.struts2.osgi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.entities.PackageConfig;
import com.opensymphony.xwork2.config.impl.DefaultConfiguration;
import com.opensymphony.xwork2.config.providers.XmlConfigurationProvider;
import com.opensymphony.xwork2.util.location.Location;

public class BundlePackageLoader implements PackageLoader {

    public List<PackageConfig> loadPackages(Bundle bundle, ObjectFactory objectFactory, Map<String,PackageConfig> pkgConfigs) throws ConfigurationException {
        BundleConfigurationProvider prov = new BundleConfigurationProvider("struts.xml", bundle);
        Configuration config = new DefaultConfiguration("struts.xml");
        for (PackageConfig pkg : pkgConfigs.values()) {
            config.addPackageConfig(pkg.getName(), pkg);
        }
        prov.setObjectFactory(objectFactory);
        prov.init(config);
        prov.loadPackages();
        return new ArrayList<PackageConfig>(config.getPackageConfigs().values());
    }
    
    static class BundleConfigurationProvider extends XmlConfigurationProvider {
        private Bundle bundle;

        public BundleConfigurationProvider(String filename, Bundle bundle) { 
            super(filename, true);
            this.bundle = bundle;
        }
        public BundleConfigurationProvider(String filename) { super(filename); }

        @Override
        protected Iterator<URL> getConfigurationUrls(String fileName) throws IOException {
            Enumeration<URL> e = bundle.getResources("struts.xml");
            Iterator<URL> iter = new EnumeratorIterator<URL>(e);
            return iter;
        }
        @Override
        protected boolean verifyAction(String className, String name,
                Location loc) {
            try {
                Class cls = bundle.loadClass(className);
                return cls != null;
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        
        
    }
    
    static class EnumeratorIterator<E> implements Iterator<E> {
        Enumeration<E> e = null;
        public EnumeratorIterator(Enumeration<E> e) {
            this.e = e;
        }
        public boolean hasNext() {
          return e.hasMoreElements();
        }

        public E next() {
          return e.nextElement();
        }

        public void remove() {
          throw new UnsupportedOperationException();
        }
      }

}
