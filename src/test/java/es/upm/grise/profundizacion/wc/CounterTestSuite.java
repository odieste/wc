package es.upm.grise.profundizacion.wc;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({AppTest.class, CounterTest.class})
public class CounterTestSuite {}
