package lv.venta.service.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDservice;
import lv.venta.service.IProductFilteringService;

@Service
public class ProductServiceImplementation implements IProductCRUDservice, IProductFilteringService{
	
	@Autowired
	private IProductRepo productRepo;

	@Override
	public ArrayList<Product> filterByQuantityThreshold(int threshold) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> filterByPriceBetween(float minPrice, float maxPrice) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(String title, String description, float price, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(productRepo.count() == 0) {
			throw new Exception("Table is empty");
		}
		ArrayList<Product> result = (ArrayList<Product>)productRepo.findAll();  //called casting/a cast
		return result;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		if(productRepo.count() == 0) {
			throw new Exception("Table is empty");
		}
		
		if(productRepo.existsById(id)) {
			return productRepo.findById(id).get();
		}
		
		throw new Exception("Product with id: " + id + " does not exist");
		
	}

	@Override
	public void updateById(int id, String title, String description, float price, int quantity) throws Exception {

		if(id < 0 || title == null || description == null || price < 0 || quantity < 0) {
			throw new Exception("Wrong input");
		}
		
		if(productRepo.existsById(id)) {
			Product productForUpdating = retrieveById(id);
			productForUpdating.setTitle(title);
			productForUpdating.setDescription(description);
			productForUpdating.setPrice(price);
			productForUpdating.setQuantity(quantity);
			
			productRepo.save(productForUpdating);
		}
		
	}

	@Override
	public void deleteById(int id) throws Exception {

		if(productRepo.existsById(id)) {
			Product prodToDelete = retrieveById(id);
			productRepo.delete(prodToDelete);
			
		}
		
		
		
	}

}
