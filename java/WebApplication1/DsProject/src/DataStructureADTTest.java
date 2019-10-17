import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
	
	private T dataStructureInstance;
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
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
		assertFalse(dataStructureInstance.remove("1"));
	}
	
	// TODO: add tests to ensure that you can detect implementation that fail

	@Test
	void test05_test_failing_implementation(){
		dataStructureInstance.insert("1","Musa");
		dataStructureInstance.insert("2","Wesley");
		dataStructureInstance.insert("3","Jaden");
		dataStructureInstance.insert("4","Ontegi");
		dataStructureInstance.insert("5","Wawerui");
		dataStructureInstance.insert("6","Banana");

		assertFalse(dataStructureInstance.remove("10"));
		assertEquals(6,dataStructureInstance.size());
	}
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes


}
