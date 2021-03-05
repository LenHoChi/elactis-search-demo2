/**
 * 
 */
package io.pratik.elasticsearch.controllers;

import java.util.List;

import io.pratik.elasticsearch.repositories.ProductRepository;
import io.pratik.elasticsearch.services.ProductSearchServiceWithRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

import io.pratik.elasticsearch.models.Product;
import io.pratik.elasticsearch.services.ProductSearchService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pratik Das
 *
 */
@RestController
@RequestMapping("/")
@Slf4j
public class SearchController {
	private ProductSearchServiceWithRepo productSearchServiceWithRepo;
	private  ProductSearchService searchService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	public SearchController(ProductSearchService searchService) { 
	    this.searchService = searchService;
	}
	
	@GetMapping("/products/{query}")
	//@ResponseBody
//	public List<Product> fetchByNameOrDesc(@RequestParam(value = "q", required = false) String query) {
	public List<Product> fetchByNameOrDesc(@PathVariable String query) {
        log.info("searching by name {}",query);
		List<Product> products = searchService.processSearch(query) ;
	    log.info("products {}",products);
		return products;
	  }
	@GetMapping("/products2/{query}")
	public List<Product> fetchByNameOrDesc2(@PathVariable String query) {
		log.info("searching by name {}",query);
		List<Product> products = searchService.processSearch2(query) ;
		log.info("products {}",products);
		return products;
	}
	@GetMapping("/suggestions/{query}")
	//@ResponseBody
	//public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
	public List<Product> fetchSuggestions(@PathVariable String query) {
		log.info("fetch suggests {}",query);
        List<Product> suggests = searchService.fetchSuggestions(query);
        log.info("suggests {}",suggests);
        return suggests;
	  }
	@PostMapping("/add-them-vo-1-it")
	public void saveProduct(@RequestBody List<Product> products){
		//productSearchServiceWithRepo.createProductIndexBulk(products);
		productRepository.saveAll(products);
	}
}
