package com.product.product;

import com.product.product.db.inheritance.singletable.StudentRepository;
import com.product.product.db.inheritance.singletable.MentorRepository;
import com.product.product.db.inheritance.join.UserRepository;
import com.product.product.models.*;
import com.product.product.repositories.CategoryRepository;
import com.product.product.repositories.OrderRepository;
import com.product.product.repositories.PriceRepository;
import com.product.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {
    private MentorRepository mentorRepository;
    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductApplication(@Qualifier("st_mentorrepository") MentorRepository mentorRepository, @Qualifier("j_userrepository") UserRepository userRepository, @Qualifier("st_studentrepository")StudentRepository studentRepository,
                              CategoryRepository categoryRepository,
                              PriceRepository priceRepository,
                              ProductRepository productRepository,
                              OrderRepository orderRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Mentor mentor = new Mentor();
//        mentor.setName("ravi");
//        mentor.setEmail("r@email.com");
//        mentor.setRating("10");
//        mentorRepository.save(mentor);
//
//       Student student = new Student();
//       student.setName("vinod");
//       student.setEmail("v@email.com");
//       student.setPsp("80");
//       studentRepository.save(student);

//            Category category = new Category();
//            category.setName("Mobile");
//            categoryRepository.save(category);

//            Price price = new Price();
//            price.setCurrency("INR");
//            price.setValue(50000);
//            Price price1 = priceRepository.save(price);
//
//          Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString("bd987de2-767b-4c14-96bc-7091ada8e584"));
//          Category category = optionalCategory.get();
//
//
//          Product product = new Product();
//          product.setTitle("iphone 12");
//          product.setCategory(category);
//          product.setPrice(price1);
//          Product product1 = productRepository.save(product);

//          productRepository.deleteById(UUID.fromString("b2c50d07-ff2d-4907-94ec-5a1f80513e7c"));
        List<Product> products = productRepository.getProductsByTitle("iphone 12");
//          Optional<Product> optionalProduct = productRepository.findById(UUID.fromString("1d76ce1e-8fbd-40f0-930b-ef84fa80b9b7"));
//          Product product = optionalProduct.get();
//          List<Product> products = new ArrayList<>();
//          products.add(product);

          Order order = new Order();
          order.setProducts(products);
          orderRepository.save(order);
    }
}
