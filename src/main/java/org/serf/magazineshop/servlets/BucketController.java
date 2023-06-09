package org.serf.magazineshop.servlets;

import org.serf.magazineshop.domain.Bucket;
import org.serf.magazineshop.domain.Product;
import org.serf.magazineshop.domain.User;
import org.serf.magazineshop.service.BucketService;
import org.serf.magazineshop.service.ProductService;
import org.serf.magazineshop.service.UserService;
import org.serf.magazineshop.service.impl.BucketServiceImpl;
import org.serf.magazineshop.service.impl.ProductServiceImpl;
import org.serf.magazineshop.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;


@WebServlet(name = "BucketController", value = "/BucketController")
public class BucketController extends HttpServlet {
    private	BucketService bucketService = BucketServiceImpl.getBucketService();
    private ProductService productService = ProductServiceImpl.getProductService();
    private UserService userService = UserServiceImpl.getUserService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");

        Product product = productService.read(Integer.parseInt(productId));

        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        User user = userService.read(userId);


        Bucket bucket = new Bucket();
        bucket.setId(UUID.randomUUID().toString());
        bucket.setProduct(product);
        bucket.setUser(user);
        bucket.setPurchaseDate(new Date());

        bucketService.create(bucket);


        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bucketId = request.getParameter("bucketId");
        bucketService.delete(Integer.parseInt(bucketId));

        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }

}

