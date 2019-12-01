package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import net.guides.springboot2.springboot2jpacrudexample.model.Item;
import net.guides.springboot2.springboot2jpacrudexample.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@Api(value="Item inventory System", description="Operations pertaining to item in Item inventory System")
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	@ApiOperation(value = "View a list of available items", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@ApiOperation(value = "Get an item by Id")
	@GetMapping("/items/{itemNo}")
	public ResponseEntity<Item> getItemByItemNo(@PathVariable(value = "itemNo") Long itemNo)
			throws ResourceNotFoundException {
		Item item = itemRepository.findById(itemNo)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this item no :: " + itemNo));
		return ResponseEntity.ok().body(item);
	}

	@ApiOperation(value = "Add an item")
	@PostMapping("/items")
	public Item createItem(@ApiParam(value = "Item object store in database table", required = true) @Valid @RequestBody Item item) {


		return itemRepository.save(item);
	}


	@ApiOperation(value = "Update an item")
	@PutMapping("/items/{itemNo}")
	public ResponseEntity<Item> updateItem(@PathVariable(value = "itemNo") Long itemNo,
			@Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {
		Item item = itemRepository.findById(itemNo)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this item no :: " + itemNo));

		item.setInvCode(itemDetails.getInvCode());
		item.setAmount(itemDetails.getAmount());
		item.setName(itemDetails.getName());
		final Item updatedItem = itemRepository.save(item);
		return ResponseEntity.ok(updatedItem);
	}

	@ApiOperation(value = "Delete an item")
	@DeleteMapping("/items/{itemNo}")
	public Map<String, Boolean> deleteItem(@PathVariable(value = "itemNo") Long itemNo)
			throws ResourceNotFoundException {
		Item item = itemRepository.findById(itemNo)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this item no :: " + itemNo));

		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
