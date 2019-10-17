// TO TEST A DATA STRUCTURE CLASS:
//
// for each data structure class file you wish to test:
//     1. create a test class (like this one) 
//     2. edit the actual type being created (line 16)
//     3. run this test class 
//     4. OR, configure Eclipse project to run all tests
//        Eclipse: Run->Run Configurations->"Run All Tests..."

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SuppressWarnings("rawtypes")
public class TestDS_My extends DataStructureADTTest {

	// the return type must be the name of the data structure class you are testing
	@Override
	protected DataStructureADT createInstance() {
		return new DS_My();
	}

	private DS_My dataStructureInstance;



	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance =(DS_My) createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}


	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}

	// TODO: implement tests 01 - 04

	// test01_after_insert_one_size_is_one
	@Test
	void test01_after_insert_one_size_is_one(){
		dataStructureInstance.insert("1","Hello");
		assertEquals(1,dataStructureInstance.size());
	}

	// test02_after_insert_one_remove_one_size_is_0
	@Test
	void test02_after_insert_one_remove_one_size_is_0(){
		dataStructureInstance.insert("2","Musa");
		dataStructureInstance.remove("2");
		assertEquals(0,dataStructureInstance.size());
	}

	// test03_duplicate_exception_is_thrown
	@Test
	void test03_duplicate_exception_is_thrown(){
		dataStructureInstance.insert("30","Hello");
		boolean thrown=false;

		try {
			dataStructureInstance.insert("30","Two");
		}catch (Exception e){
			thrown=true;
		}

		assertTrue(thrown);
	}

	// test04_remove_returns_false_when_key_not_present

	@Test
	void test04_remove_returns_false_when_key_not_present(){
		dataStructureInstance.insert("1","Musa");
		assertFalse(dataStructureInstance.remove("10"));
	}
}
