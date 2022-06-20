package com.sportyshoe.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportyshoe.model.AppUser;
import com.sportyshoe.model.Category;
import com.sportyshoe.model.Products;
import com.sportyshoe.model.PurchaseDetails;
import com.sportyshoe.model.Users;
import com.sportyshoe.service.CategoryService;
import com.sportyshoe.service.ChangePasswordService;
import com.sportyshoe.service.ProductService;
import com.sportyshoe.service.PurchaseService;
import com.sportyshoe.service.UsersService;
import com.sportyshoe.utils.WebUtils;



@Controller
public class MainController {
	
	@Autowired
	ChangePasswordService changePasswordService;
	
	 @Autowired
	 ProductService productService;
	 
	 @Autowired
	 CategoryService categoryService;
	 
	 @Autowired
	 UsersService userService;
	 
	 @Autowired
	 PurchaseService purchaseService;
	
	   @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	    public String welcomePage(Model model) {
	        model.addAttribute("title", "Welcome");
	        model.addAttribute("message", "Sporty Shoe Ecommerce!");
	        return "welcomePage";
	    }
	   
	   @RequestMapping(value = { "/" }, method = RequestMethod.GET)
		public String viewHomePage(Model model) {
		    model.addAttribute("title", "Welcome to Sporty Shoe");
	        model.addAttribute("message", "Sporty Shoe Ecommerce!");
			List<Products> listProducts = productService.getProducts();
			model.addAttribute("listProducts", listProducts);
			
			return "index";
		}	    
	   
	   @RequestMapping(value = { "/purchaseReport" }, method = RequestMethod.GET)
		public String viewPurchaseReport(@ModelAttribute("purchase") PurchaseDetails purchase,Model model) {
		    
		    PurchaseDetails purchaseDetails= new PurchaseDetails();
		    model.addAttribute("purchaseDetails", purchaseDetails);
		    model.addAttribute("standardDate", new Date());
		    model.addAttribute("localDateTime", LocalDateTime.now());
		    model.addAttribute("localDate", LocalDate.now());
		    model.addAttribute("timestamp", Instant.now());
		    
		    System.out.println(purchase.getCategoryId());
		    System.out.println(purchase.getPurchaseDate());
		    
		    String pattern = "yyyy-MM-dd";
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		    String date;

		     if (purchase.getPurchaseDate() !=null)
		     {
		    	 date = simpleDateFormat.format(purchase.getPurchaseDate());
				  System.out.println(date);			   
		    	 
		     }
		     else {
		    	 date ="0";
		     }
		   
		    
		   
			List<PurchaseDetails> listPurchase = purchaseService.getAllPurchaseDetails(purchase.getCategoryId(),date);
			model.addAttribute("listPurchase", listPurchase);
			
			    List<Category> category = new ArrayList<>();
			    model.addAttribute("category", category);
			    List<Category> listCategory =  categoryService.getCategory();
			    model.addAttribute("listCategory", listCategory);
			
			return "purchase_report";
		}	    
	   
	        @RequestMapping(value = { "/searchUsers" }, method = RequestMethod.GET)
			public String searchUsers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
	        	
	        	   /* if(keyword!=null) {
	        		   List<Shop> list = service.getByKeyword(keyword);
	        		   model.addAttribute("list", list);
	        		  }*/
	            List<Users> listUsers = userService.getUsers(keyword);
				model.addAttribute("listUsers", listUsers);
						  			    
				return "userSearch";
			}	 
	   
			   
	    @RequestMapping("/new")
		public String showNewProductForm(Model model) {
			Products product = new Products();
			model.addAttribute("product", product);
			
			    List<Category> category = new ArrayList<>();
			    model.addAttribute("category", category);
			    List<Category> listCategory =  categoryService.getCategory();
			    model.addAttribute("listCategory", listCategory);
			return "new_product";
		}
	    
	    @RequestMapping("/newCat")
		public String showNewCategoryForm(Model model) {
			Category category = new Category();
			model.addAttribute("category", category);
			return "new_category";
		}
	    
	    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	  		public String saveCategory(@ModelAttribute("category") Category category) {
	    	categoryService.saveCategory(category);
	  			
	  			return "redirect:/";
	  		}
	    
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveProduct(@ModelAttribute("product") Products product) {
			productService.save(product);
			
			return "redirect:/";
		}

		@RequestMapping(value ="/edit/{id}", method = RequestMethod.GET)
		public ModelAndView showEditProductForm( @PathVariable(name = "id") long id,Model model) {
			ModelAndView mav = new ModelAndView("edit_product");
					    				
			Products product=productService.editProduct(id); 
			List<Category> category = new ArrayList<>();
		    model.addAttribute("category", category);
			List<Category> listCategory =  categoryService.getCategory();
			model.addAttribute("listCategory", listCategory);
			    
			mav.addObject("product", product);
			return mav;
		}	
		
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String update(@ModelAttribute("product") Products product) {
			productService.updateProduct(product);
		      
		    return "redirect:/";
		}
		
	    
		@RequestMapping("/delete/{id}")
		public String deleteProduct(@PathVariable(name = "id") Long id) {
			productService.deleteProduct(id);
			
			return "redirect:/";
		}
	    
	    /* Admin Controllers*/
	    @RequestMapping(value = "/admin", method = RequestMethod.GET)
	    public String adminPage(Model model, Principal principal) {
	        
	        User loginedUser = (User) ((Authentication) principal).getPrincipal();

	        String userInfo = WebUtils.toString(loginedUser);
	        model.addAttribute("userInfo", userInfo);
	        
	        return "adminPage";
	    }

	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String loginPage(Model model) {

	        return "loginPage";
	    }
	    
	    @RequestMapping(value = "/passwordChange", method = RequestMethod.GET)
	    public String showPasswordForm(Model model, Principal principal ) {
	    	
	    	    AppUser user =new AppUser();
	    	    model.addAttribute("user", user);
	    	    
	    	    String userName = principal.getName();
		        System.out.println("User Name: " + userName);
		        
		        return "changePassword";
	    }
	    
	    //@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	    @PostMapping("/passwordChange")
	    public String submitPasswordForm(@ModelAttribute("user") AppUser user, Principal principal ) {
	    	
	    	    String userName = principal.getName();
	    	    //request.getParameter(password);
		        //System.out.println("User Name: " + userName);
		             
		        //System.out.println(user);
		        
		        String newPassword=changePasswordService.changePassword(userName, user.getEncryptedPassword());
		        System.out.println(newPassword);
		        user.setEncryptedPassword(newPassword);
		       

	        return "adminPage";
	    }



	    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	    public String logoutSuccessfulPage(Model model) {
	        model.addAttribute("title", "Logout");
	        return "logoutSuccessfulPage";
	    }

	    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	    public String userInfo(Model model, Principal principal) {
  
	        // After user login successfully.
	      
	        String userName = principal.getName();
	      
	        //Users listUser = userService.getUser(userName);
	        //Users listAdmin = userService.getAdmin(userName);
	        
	       // System.out.println("userName: " + userName);
	        //System.out.println("listAdmin: " + listAdmin.getUserName());
	        
			//model.addAttribute("listUsers", listUser);
			//model.addAttribute("listAdmin", listAdmin);
	        //System.out.println("User Name: " + userName);

	        User loginedUser = (User) ((Authentication) principal).getPrincipal();

	        String userInfo = WebUtils.toString(loginedUser);
	        model.addAttribute("userInfo", userInfo);

	        return "userInfoPage";
	    }

	    @RequestMapping(value = "/403", method = RequestMethod.GET)
	    public String accessDenied(Model model, Principal principal) {

	        if (principal != null) {
	            User loginedUser = (User) ((Authentication) principal).getPrincipal();

	            String userInfo = WebUtils.toString(loginedUser);

	            model.addAttribute("userInfo", userInfo);

	            String message = "Hi " + principal.getName() //
	                    + "<br> You do not have permission to access this page!";
	            model.addAttribute("message", message);

	        }

	        return "403Page";
	    }

	   
		

}
