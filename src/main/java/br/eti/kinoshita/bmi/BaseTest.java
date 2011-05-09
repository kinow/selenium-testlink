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

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Guice;

import com.google.inject.Inject;


/**
 * Abstract base test.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
@Guice( modules = BMIGuiceModule.class )
public abstract class BaseTest
{
	
	protected static final Logger LOGGER = Logger.getLogger( BaseTest.class );

	@Inject
	private WebDriver driver;
	
	@Inject
	private Configuration configuration;
	
	private int timeout = -1;
	
	public int getTimeout()
	{
		if ( this.timeout < 0 )
		{
			try
			{
				timeout = configuration.getInt( Constants.TIMEOUT_PROPERTY );
			} catch (Exception e)
			{
				LOGGER.fatal ("Failed to parse time-out from properties file: "
						+ e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return timeout;
	}
	
	public Configuration getConfiguration()
	{
		return this.configuration;
	}
	
	public WebDriver getDriver()
	{
		return this.driver;
	}
	
	/**
	 * Closes the driver and quit. This method is annotated to always run.
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown()
	{
		if (driver != null)
		{
			LOGGER.info("Closing WebDriver...");
			try
			{
				driver.close();
				driver.quit();

				LOGGER.info( "OK!" );
			} catch (Throwable t)
			{
				LOGGER.warn( t.getMessage(), t );
			}
		}
	}
	
}
