package com.model2.mvc.service.purchase.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		
		purchase.getPurchaseProd().setProdNo(10001);
		purchase.getBuyer().setUserId("testUser");
		purchase.setPaymentOption("1");
		purchase.setReceiverName("testName");
		purchase.setReceiverPhone("testPhone");
		purchase.setDivyAddr("testAddr");
		purchase.setDivyRequest("testRequest");
		purchase.setTranCode("testCode");
		purchase.setDivyDate("testDate");
		
		purchaseService.addPurchase(purchase);
		
		//product= productService.getProduct("testUserId");

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
		Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("testUser", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("testName", purchase.getReceiverName());
		Assert.assertEquals("testPhone", purchase.getReceiverPhone());
		Assert.assertEquals("testAddr", purchase.getDivyAddr());
		Assert.assertEquals("testRequest",purchase.getDivyRequest());
		Assert.assertEquals("testCode", purchase.getTranCode());
		Assert.assertEquals("testDate", purchase.getDivyDate());
	}
	/*
	//@Test
    public void testGetProduct() throws Exception {
		
		Product product = new Product();

		product = productService.getProduct(10009);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals("testDate", product.getManuDate());
		Assert.assertEquals(2000, product.getPrice());
		Assert.assertEquals("testImage", product.getFileName());

		//Assert.assertNotNull(userService.getUser("user02"));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		 Product product= productService.getProduct(10009);
		Assert.assertNotNull(product);

		product.setProdName("updateName");
		product.setProdDetail("updateDetail");
		product.setManuDate("upDate");
		product.setPrice(1111);
		product.setFileName("updateImage");
		
		productService.updateProduct(product);
		
		product= productService.getProduct(10009);
		Assert.assertNotNull(product);
		
		//==> console 확인
		System.out.println(product);
			
		//==> API 확인
		Assert.assertEquals("updateName", product.getProdName());
		Assert.assertEquals("updateDetail", product.getProdDetail());
		Assert.assertEquals("upDate", product.getManuDate());
		Assert.assertEquals(1111, product.getPrice());
		Assert.assertEquals("updateImage", product.getFileName());
	 }

	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println("list:"+list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount:"+totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }

	 //@Test
	 public void testGetProductListProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10009");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println("list:"+list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println("list:"+list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }

	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("updateName");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 }	 
	 
	//@Test
		 public void testGetProductListByPrice() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("2000");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Product> list = (List<Product>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println("list:"+list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 }	 
*/

}