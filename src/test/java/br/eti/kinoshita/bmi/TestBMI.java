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

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.bmi.pages.CalculatorPage;
import br.eti.kinoshita.bmi.pages.YourBMIPage;

/**
 * Tests Body Mass Index.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
public class TestBMI 
extends BaseTest
{

	/**
	 * Provides input data for testBMI test.
	 * @return input data for testBMI test.
 	 */
	@DataProvider( name = "bmiDataProvider" )
	public Object[][] createBMIData()
	{
		return new Object[][]
        {
			{new Integer(170), new Integer(63), new Float(21.8)}, 
			{new Integer(190), new Integer(40), new Float(11.1)}, 
			{new Integer(160), new Integer(123), new Float(48.0)}
        };
	}
	
	/**
	 * Tests finding the Body Mass Index given height and weight.
	 */
	@Test( description = "Tests finding the Body Mass Index", dataProvider = "bmiDataProvider" )
	public void testBMI( Integer height, Integer weight, Float bmi )
	{
		this.getDriver().get( getConfiguration().getString( Constants.URL_PROPERTY ) );
		
		CalculatorPage calculatorPage = new CalculatorPage(getDriver(), getTimeout());
		
		calculatorPage.calculateYourBodyMassIndex(height, weight);
		
		YourBMIPage yourBMIPage = new YourBMIPage(getDriver(), getTimeout());
		
		Assert.assertEquals( yourBMIPage.getCalculatedBMI(), bmi );
	}
	
}
