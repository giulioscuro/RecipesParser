package it.giulioscuro.ricettelight.fatsecret;

import java.util.List;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;


//REST API Consumer Key:
//
//Your REST API Consumer Key is the same value as your API key, i.e.:
//9256b63e34d746818fefe5c2063d710b

//Your REST API Shared Secret is provided below.
//REST API Shared Secret:
//
//90425bc3c41e46c4a26c3436e5e781eb
//NOTE: Do not disclose your Shared Secret to anyone. As per the 
//Terms of Service, you are responsible for maintaining the secrecy and security of your Keys.
//If you would like to change this Shared Secret, please refer to the Reset Secret page.

public class FatSecretService {
	public void searchFoodItems() {
		
		String key = "9256b63e34d746818fefe5c2063d710b";
		String secret = "90425bc3c41e46c4a26c3436e5e781eb";
		FatsecretService service = new FatsecretService(key, secret);
		
		String query = "uova"; //Your query string
		Response<CompactFood> response = service.searchFoods(query);
		//This response contains the list of food items at zeroth page for your query
		
		List<CompactFood> results = response.getResults();
		//This list contains summary information about the food items

for (CompactFood f : results) {
	
	System.out.println(f.getName());
}
		
		//Response<CompactFood> responseAtPage3 = service.searchFoods(query, 3);
		//This response contains the list of food items at page number 3 for your query
		//If total results are less, then this response will have empty list of the food items		
	}
}