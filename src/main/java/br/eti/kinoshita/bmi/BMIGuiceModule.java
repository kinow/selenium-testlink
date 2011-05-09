/*
 * The MIT License
 *
 * Copyright (c) <2011> <Bruno P. Kinoshita>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.eti.kinoshita.bmi;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * BMI Guice module for TestNG 6.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
public class BMIGuiceModule 
implements Module
{
	
	private static final Logger LOGGER = Logger.getLogger( BMIGuiceModule.class );
	
	/**
	 * Default configuration file name.
	 */
	private static final String DEFAULT_CONFIGURATION_FILE = "bmi.properties";
	
	/**
	 * <p>Selenium2 Web Driver singleton.</p>
	 * 
	 * <p>One singleton for all tests. WebDriver is not Thread-Safe thus it is 
	 * ok have a single instance.</p>
	 */
	private static final WebDriver DRIVER;
	
	/**
	 * Composite configuration containing both System properties and 
	 * properties of BMI properties file. It is a singleton and bound to 
	 * Configuration.class instances.
	 */
	private static final CompositeConfiguration CONFIGURATION;
	
	/**
	 * Static constructor.
	 */
	static
	{
		DRIVER = new FirefoxDriver();
		
		try
		{
			Configuration defaults = new PropertiesConfiguration( DEFAULT_CONFIGURATION_FILE );
			Configuration systemProperties = new SystemConfiguration();
			CONFIGURATION = new CompositeConfiguration();
			CONFIGURATION.addConfiguration( defaults );
			CONFIGURATION.addConfiguration( systemProperties );
		}
		catch ( ConfigurationException ce )
		{
			LOGGER.fatal( ce.getMessage(), ce );
			throw new RuntimeException( ce );
		}
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Module#configure(com.google.inject.Binder)
	 */
	public void configure( Binder binder )
	{
		binder.bind(WebDriver.class).toInstance( DRIVER );
		binder.bind(Configuration.class).toInstance( CONFIGURATION );
	}

}
