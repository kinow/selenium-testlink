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
package br.eti.kinoshita.bmi.pages;

import org.openqa.selenium.WebDriver;

/**
 * Base Page for Page Objects Pattern.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
public class BasePage
{
	
	/**
	 * Selenium2 Web Driver.
	 */
	private WebDriver driver;
	
	/**
	 * Time-out for operations in seconds.
	 */
	private int timeOutInSeconds;
	
	/**
	 * Constructor with parameters.
	 * 
	 * @param driver Selenium2 Web Driver.
	 * @param timeOutInSeconds Time-out for operations in seconds.
	 */
	public BasePage ( WebDriver driver, int timeOutInSeconds )
	{
		this.driver = driver;
		this.timeOutInSeconds = timeOutInSeconds;
	}
	
	/**
	 * Retrieves Selenium2 Web Driver.
	 * 
	 * @return Selenium2 Web Driver.
	 */
	public WebDriver getDriver()
	{
		return this.driver;
	}
	
	/**
	 * Retrieves Time-out for operations in seconds.
	 * 
	 * @return Time-out for operations in seconds.
	 */
	public int getTimeOut()
	{
		return this.timeOutInSeconds;
	}

}
