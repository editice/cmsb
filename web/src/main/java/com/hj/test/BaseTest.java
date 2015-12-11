package com.hj.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/9/24  23:14
 */
public class BaseTest {
    static ClassPathXmlApplicationContext ctx;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
			ctx = new ClassPathXmlApplicationContext(
			        "classpath:applicationContext.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
